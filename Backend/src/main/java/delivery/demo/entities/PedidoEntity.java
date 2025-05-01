package delivery.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "pedido")
@Data

public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fecha;
    private Long idUrgencia;
    private Long idDetallePedido;
    private Long idRepartidor;
    private Long idCliente;
    private String idMedioPago;

    public PedidoEntity() {

    }

    public PedidoEntity(Date fecha, Long idUrgencia, Long idDetallePedido, Long idRepartidor, Long idCliente, String idMedioPago) {
        this.fecha = fecha;
        this.idUrgencia = idUrgencia;
        this.idDetallePedido = idDetallePedido;
        this.idRepartidor = idRepartidor;
        this.idCliente = idCliente;
        this.idMedioPago = idMedioPago;
    }
}
