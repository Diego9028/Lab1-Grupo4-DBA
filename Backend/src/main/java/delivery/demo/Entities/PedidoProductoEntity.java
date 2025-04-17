package delivery.demo.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pedido_producto")
@Data

public class PedidoProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idProductoServicio;
    private int cantidad;

    public PedidoProductoEntity() {

    }

    public PedidoProductoEntity(Long idProductoServicio, int cantidad) {
        this.idProductoServicio = idProductoServicio;
        this.cantidad = cantidad;
    }
}
