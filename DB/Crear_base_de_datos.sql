CREATE TABLE CLIENTE
(
  id_cliente INT NOT NULL,
  direccion VARCHAR(255) NOT NULL,
  correo VARCHAR(100) NOT NULL,
  PRIMARY KEY (id_cliente)
);

CREATE TABLE EMPRESA_ASOCIADA
(
  id_empresa_asociada INT NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  PRIMARY KEY (id_empresa_asociada)
);

CREATE TABLE DETALLE_PEDIDO
(
  id_detalle_pedido INT NOT NULL,
  entregado BOOLEAN NOT NULL,
  hora_entrega TIMESTAMP,
  calificacion NUMERIC(4,2),
  PRIMARY KEY (id_detalle_pedido)
);

CREATE TABLE MEDIO_PAGO
(
  id_medio_pago INT NOT NULL,
  tipo VARCHAR(50) NOT NULL,
  PRIMARY KEY (id_medio_pago)
);

CREATE TABLE CATEGORIA
(
  id_categoria INT NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  PRIMARY KEY (id_categoria)
);

CREATE TABLE URGENCIA
(
  id_urgencia INT NOT NULL,
  tipo VARCHAR(50) NOT NULL,
  PRIMARY KEY (id_urgencia)
);

CREATE TABLE REPARTIDOR
(
  id_repartidor INT NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  id_empresa_asociada INT NOT NULL,
  PRIMARY KEY (id_repartidor),
  FOREIGN KEY (id_empresa_asociada) REFERENCES EMPRESA_ASOCIADA(id_empresa_asociada)
);

CREATE TABLE PRODUCTO_SERVICIO
(
  id_producto_servicio INT NOT NULL,
  stock INT NOT NULL,
  precio DECIMAL(10,2) NOT NULL,
  id_categoria INT NOT NULL,
  id_empresa_asociada INT NOT NULL,
  PRIMARY KEY (id_producto_servicio),
  FOREIGN KEY (id_categoria) REFERENCES CATEGORIA(id_categoria),
  FOREIGN KEY (id_empresa_asociada) REFERENCES EMPRESA_ASOCIADA(id_empresa_asociada)
);

CREATE TABLE PEDIDO
(
  id_pedido INT NOT NULL,
  hora_pedido TIMESTAMP NOT NULL,
  id_urgencia INT NOT NULL,
  id_detalle_pedido INT NOT NULL,
  id_repartidor INT NOT NULL,
  id_cliente INT NOT NULL,
  id_medio_pago INT NOT NULL,
  PRIMARY KEY (id_pedido),
  FOREIGN KEY (id_urgencia) REFERENCES URGENCIA(id_urgencia),
  FOREIGN KEY (id_detalle_pedido) REFERENCES DETALLE_PEDIDO(id_detalle_pedido),
  FOREIGN KEY (id_repartidor) REFERENCES REPARTIDOR(id_repartidor),
  FOREIGN KEY (id_cliente) REFERENCES CLIENTE(id_cliente),
  FOREIGN KEY (id_medio_pago) REFERENCES MEDIO_PAGO(id_medio_pago)
);

CREATE TABLE CALIFICACION (
  id_calificacion INT  NOT NULL,
  total_puntos    INT  NOT NULL,
  total_pedidos   INT  NOT NULL,
  promedio        NUMERIC(4,2) NOT NULL,
  id_repartidor   INT  NOT NULL,
  PRIMARY KEY (id_calificacion),
  UNIQUE (id_repartidor),          
  FOREIGN KEY (id_repartidor)
      REFERENCES REPARTIDOR(id_repartidor)
);

CREATE TABLE NOTIFICACION
(
  id_notificacion INT NOT NULL,
  id_pedido INT NOT NULL,
  mensaje VARCHAR(255) NOT NULL,
  PRIMARY KEY (id_notificacion),
  FOREIGN KEY (id_pedido) REFERENCES PEDIDO(id_pedido)
);


-- Tabla de relación entre pedidos y productos/servicios
CREATE TABLE PEDIDO_PRODUCTO
(
  id_pedido INT NOT NULL,
  id_producto_servicio INT NOT NULL,
  cantidad INT NOT NULL,
  PRIMARY KEY (id_pedido, id_producto_servicio),
  FOREIGN KEY (id_pedido) REFERENCES PEDIDO(id_pedido),
  FOREIGN KEY (id_producto_servicio) REFERENCES PRODUCTO_SERVICIO(id_producto_servicio)
);


CREATE OR REPLACE FUNCTION fn_notificar_error_pedido()
RETURNS TRIGGER AS $$
BEGIN
  -- Verifica si el pedido tiene errores de tipo de datos
  IF NEW.hora_pedido IS NULL THEN
    RAISE EXCEPTION 'Error: La hora del pedido no puede ser NULL.';
  END IF;
  IF NEW.id_urgencia IS NULL THEN
    RAISE EXCEPTION 'Error: La urgencia del pedido no puede ser NULL.';
  END IF;
  IF NEW.id_detalle_pedido IS NULL THEN
    RAISE EXCEPTION 'Error: El detalle del pedido no puede ser NULL.';
  END IF;
  IF NEW.id_repartidor IS NULL THEN
    RAISE EXCEPTION 'Error: El repartidor del pedido no puede ser NULL.';
  END IF;
  IF NEW.id_cliente IS NULL THEN
    RAISE EXCEPTION 'Error: El cliente del pedido no puede ser NULL.';
  END IF;
  IF NEW.id_medio_pago IS NULL THEN
    RAISE EXCEPTION 'Error: El medio de pago del pedido no puede ser NULL.';
  END IF;
  IF NEW.id_pedido IS NULL THEN
    RAISE EXCEPTION 'Error: El ID del pedido no puede ser NULL.';
  END IF;

  -- Verifica si el pedido tiene errores de lógica
  IF NEW.id_urgencia NOT IN (SELECT id_urgencia FROM URGENCIA) THEN
    RAISE EXCEPTION 'Error: La urgencia del pedido no es válida.';
  END IF;
  
  RETURN NEW;
  
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER notificar_error_pedido
AFTER INSERT ON PEDIDO
FOR EACH ROW
EXECUTE PROCEDURE fn_notificar_error_pedido();


CREATE OR REPLACE FUNCTION set_hora_entrega()
RETURNS TRIGGER AS $$
BEGIN
  IF NEW.entregado = TRUE AND OLD.entregado = FALSE THEN
    NEW.hora_entrega := CURRENT_TIMESTAMP;
  END IF;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER a_trigger_hora_entrega
BEFORE UPDATE ON DETALLE_PEDIDO
FOR EACH ROW
EXECUTE FUNCTION set_hora_entrega();



CREATE OR REPLACE FUNCTION fn_actualizar_promedio_repartidor()
RETURNS TRIGGER AS $$
DECLARE
  v_repartidor INT;
  v_delta      INT;
BEGIN
  /* ⚙️ 1. Averiguar qué repartidor entregó el pedido */
  SELECT p.id_repartidor
  INTO   v_repartidor
  FROM   PEDIDO p
  WHERE  p.id_detalle_pedido = NEW.id_detalle_pedido;

  IF v_repartidor IS NULL THEN
    RAISE EXCEPTION 'Pedido no encontrado para detalle %', NEW.id_detalle_pedido;
  END IF;

  /* ⚙️ 2. INSERT / UPDATE según el caso ----------------------------- */

  IF TG_OP = 'INSERT' THEN
    -- Inserción de calificación NUEVA (NEW.calificacion ya ≠ NULL)
    INSERT INTO CALIFICACION (id_repartidor, total_puntos, total_pedidos, promedio)
    VALUES (v_repartidor,
            NEW.calificacion,
            1,
            NEW.calificacion::NUMERIC)
    ON CONFLICT (id_repartidor) DO
      UPDATE
      SET total_puntos  = CALIFICACION.total_puntos  + EXCLUDED.total_puntos,
          total_pedidos = CALIFICACION.total_pedidos + 1,
          promedio      = (CALIFICACION.total_puntos  + EXCLUDED.total_puntos)::NUMERIC
                          / (CALIFICACION.total_pedidos + 1);

  ELSIF TG_OP = 'UPDATE' THEN
    /* Solo actuamos si la calificación cambió */
    IF NEW.calificacion IS DISTINCT FROM OLD.calificacion THEN
      v_delta := NEW.calificacion - COALESCE(OLD.calificacion, 0);

      UPDATE CALIFICACION
      SET total_puntos  = total_puntos + v_delta,
          -- si era NULL y ahora tiene nota sumamos pedido; si se borró restamos
          total_pedidos = total_pedidos
                          + CASE
                              WHEN OLD.calificacion IS NULL AND NEW.calificacion IS NOT NULL THEN 1
                              WHEN OLD.calificacion IS NOT NULL AND NEW.calificacion IS NULL THEN -1
                              ELSE 0
                            END,
          promedio      = CASE
                            WHEN total_pedidos + CASE
                                   WHEN OLD.calificacion IS NULL AND NEW.calificacion IS NOT NULL THEN 1
                                   WHEN OLD.calificacion IS NOT NULL AND NEW.calificacion IS NULL THEN -1
                                   ELSE 0
                                 END > 0
                            THEN (total_puntos + v_delta)::NUMERIC
                                 / (total_pedidos
                                    + CASE
                                        WHEN OLD.calificacion IS NULL AND NEW.calificacion IS NOT NULL THEN 1
                                        WHEN OLD.calificacion IS NOT NULL AND NEW.calificacion IS NULL THEN -1
                                        ELSE 0
                                      END)
                            ELSE NULL        -- nunca debería quedar en cero, salvo borrado masivo
                          END
      WHERE id_repartidor = v_repartidor;
    END IF;
  END IF;

  RETURN NEW;
END;
$$ LANGUAGE plpgsql;



/*  Se dispara cuando INSERTAS la calificación del pedido  */
CREATE TRIGGER trg_calif_insert
AFTER INSERT ON DETALLE_PEDIDO
FOR EACH ROW
WHEN (NEW.calificacion IS NOT NULL)
EXECUTE FUNCTION fn_actualizar_promedio_repartidor();


/*  …y cuando cambias o anulas esa calificación             */
CREATE TRIGGER trg_calif_update
AFTER UPDATE OF calificacion ON DETALLE_PEDIDO
FOR EACH ROW
EXECUTE FUNCTION fn_actualizar_promedio_repartidor();



CREATE OR REPLACE FUNCTION fn_calif_retraso_48h()
RETURNS TRIGGER AS $$
DECLARE
  v_hora_pedido TIMESTAMP;
BEGIN
  /* ①  Obtener la hora en que se hizo el pedido */
  SELECT hora_pedido
  INTO   v_hora_pedido
  FROM   PEDIDO
  WHERE  id_detalle_pedido = NEW.id_detalle_pedido;

  /* ②  Si existe el pedido y se cumplen las reglas, forzamos calificación = 1 */
  IF v_hora_pedido IS NOT NULL                                   -- asegura coincidencia
     AND NEW.entregado = TRUE                                     -- solo si está entregado
     AND NEW.calificacion IS NULL                                 -- aún sin nota
     AND NEW.hora_entrega IS NOT NULL                             -- hora registrada
     AND NEW.hora_entrega >= v_hora_pedido + INTERVAL '48 hours'  -- retraso ≥ 48 h
  THEN
     NEW.calificacion := 1;
  END IF;

  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_calif_retraso_48h
BEFORE UPDATE ON DETALLE_PEDIDO
FOR EACH ROW
EXECUTE FUNCTION fn_calif_retraso_48h();
