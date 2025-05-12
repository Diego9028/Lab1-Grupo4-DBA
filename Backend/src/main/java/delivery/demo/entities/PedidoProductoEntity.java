package delivery.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoProductoEntity {

    private Long id_pedido;
    private Long id_producto_servicio;
    private int cantidad;
    private LocalDate deleted_at;
}
