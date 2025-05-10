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
