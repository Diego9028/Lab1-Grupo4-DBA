package delivery.demo.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "detalle_pedido")
@Data

public class DetallePedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean entregado;
    private Date fecha;

    public DetallePedidoEntity() {

    }

    public DetallePedidoEntity(boolean entregado, Date fecha) {
        this.entregado = entregado;
        this.fecha = fecha;
    }
}
