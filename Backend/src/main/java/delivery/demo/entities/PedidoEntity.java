package delivery.demo.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoEntity {

    private Long id;

    private Date fecha;
    private Long idUrgencia;
    private Long idDetallePedido;
    private Long idRepartidor;
    private Long idCliente;
    private Long idMedioPago;
}
