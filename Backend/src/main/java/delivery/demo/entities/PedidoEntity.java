package delivery.demo.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoEntity {

    private Long id_pedido;

    private Date hora_pedido;
    private Long id_urgencia;
    private Long id_detalle_pedido;
    private Long id_repartidor;
    private Long id_cliente;
    private Long id_medio_pago;
    private LocalDate deleted_at;
}
