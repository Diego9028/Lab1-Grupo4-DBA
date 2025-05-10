CREATE OR REPLACE FUNCTION registrar_pedido(
    _id_urgencia     BIGINT,
    _id_repartidor   BIGINT,
    _id_cliente      BIGINT,
    _id_medio_pago   BIGINT,
    _ids_prod        BIGINT[],   -- ← productos
    _cant_prod       INT[]       -- ← cantidades
) RETURNS BIGINT AS $$
DECLARE
_i          INT;
    _id_detalle BIGINT;
    _id_pedido  BIGINT;
BEGIN
INSERT INTO detalle_pedido(entregado, hora_entrega)
VALUES (FALSE, NULL)
    RETURNING id_detalle_pedido INTO _id_detalle;

INSERT INTO pedido(hora_pedido, id_urgencia, id_detalle_pedido,
                   id_repartidor, id_cliente, id_medio_pago)
VALUES (CURRENT_TIMESTAMP, _id_urgencia, _id_detalle,
        _id_repartidor, _id_cliente, _id_medio_pago)
    RETURNING id_pedido INTO _id_pedido;

FOR _i IN array_lower(_ids_prod, 1)..array_upper(_ids_prod, 1) LOOP
        INSERT INTO pedido_producto(id_pedido, id_producto_servicio, cantidad)
        VALUES (_id_pedido, _ids_prod[_i], _cant_prod[_i]);
END LOOP;

RETURN _id_pedido;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION cambiar_estado_pedido(
    _id_pedido BIGINT,
    _nuevo_estado BOOLEAN
) RETURNS BOOLEAN AS $$
DECLARE
_id_detalle BIGINT;
    _estado_actual BOOLEAN;
BEGIN
    -- Obtener el id_detalle_pedido y el estado actual
SELECT p.id_detalle_pedido, dp.entregado
INTO _id_detalle, _estado_actual
FROM pedido p
         JOIN detalle_pedido dp ON p.id_detalle_pedido = dp.id_detalle_pedido
WHERE p.id_pedido = _id_pedido;

-- Validar que el pedido existe
IF _id_detalle IS NULL THEN
        RAISE EXCEPTION 'Pedido con ID % no encontrado', _id_pedido;
END IF;

    -- Validar que no se está intentando cambiar al mismo estado
    IF _estado_actual = _nuevo_estado THEN
        RAISE NOTICE 'El pedido ya está en el estado solicitado';
RETURN FALSE;
END IF;

    -- Validar transición lógica (no se puede marcar como no entregado después de entregado)
    IF _estado_actual = TRUE AND _nuevo_estado = FALSE THEN
        RAISE EXCEPTION 'No se puede cambiar un pedido entregado a no entregado';
END IF;

    -- Actualizar el estado del pedido
UPDATE detalle_pedido
SET entregado = _nuevo_estado
WHERE id_detalle_pedido = _id_detalle;

RETURN TRUE;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION confirmar_pedido(_id_pedido BIGINT)
RETURNS VOID AS $$
DECLARE
rec          RECORD;
    v_detalle_id BIGINT;
BEGIN
    -- 1) Verificar existencia y no confirmado … (sin cambios)
SELECT id_detalle_pedido
INTO   v_detalle_id
FROM   pedido
WHERE  id_pedido = _id_pedido;

IF NOT FOUND THEN
        RAISE EXCEPTION 'Pedido % no existe', _id_pedido;
END IF;

    IF EXISTS (SELECT 1 FROM detalle_pedido
               WHERE id_detalle_pedido = v_detalle_id
                 AND entregado = TRUE) THEN
        RAISE EXCEPTION 'Pedido % ya fue entregado', _id_pedido;
END IF;

    -- 2) Recorrer SOLO productos físicos
FOR rec IN
SELECT pp.id_producto_servicio,
       pp.cantidad,
       ps.stock
FROM   pedido_producto pp
           JOIN   producto_servicio ps USING (id_producto_servicio)
WHERE  pp.id_pedido = _id_pedido
  AND  ps.es_producto = TRUE          -- ← ★ solo productos
    LOOP
        IF rec.stock < rec.cantidad THEN
            RAISE EXCEPTION
              'Stock insuficiente para producto %, disponible %, requerido %',
              rec.id_producto_servicio, rec.stock, rec.cantidad;
END IF;

UPDATE producto_servicio
SET    stock = stock - rec.cantidad
WHERE  id_producto_servicio = rec.id_producto_servicio;
END LOOP;

    -- 3) Marcar detalle como entregado (trigger fija hora_entrega)
UPDATE detalle_pedido
SET    entregado = TRUE
WHERE  id_detalle_pedido = v_detalle_id;
END;
$$ LANGUAGE plpgsql;
