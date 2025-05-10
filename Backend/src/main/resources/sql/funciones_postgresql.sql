CREATE OR REPLACE FUNCTION registrar_pedido(
    _id_urgencia     BIGINT,
    _id_repartidor   BIGINT,
    _id_cliente      BIGINT,
    _id_medio_pago   BIGINT
) RETURNS BIGINT AS $$
DECLARE
_id_detalle BIGINT;
    _id_pedido  BIGINT;
BEGIN
INSERT INTO detalle_pedido(entregado, hora_entrega)
VALUES (FALSE, NULL)
    RETURNING id_detalle_pedido INTO _id_detalle;

INSERT INTO pedido(hora_pedido, id_urgencia, id_detalle_pedido,
                   id_repartidor, id_cliente, id_medio_pago)
VALUES (CURRENT_TIMESTAMP,
        _id_urgencia, _id_detalle,
        _id_repartidor, _id_cliente, _id_medio_pago)
    RETURNING id_pedido INTO _id_pedido;

RETURN _id_pedido;
END;
$$ LANGUAGE plpgsql;
