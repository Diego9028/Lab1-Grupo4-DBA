package delivery.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoProductoEntity {

    private Long id;

    private Long idProductoServicio;
    private int cantidad;
}
