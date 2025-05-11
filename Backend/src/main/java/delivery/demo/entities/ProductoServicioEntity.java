package delivery.demo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoServicioEntity {
    private Long id;

    private int stock;
    private float precio;
    private Long idCategoria;
    private Long idEmpresaAsociada;
    private Boolean es_producto;
}
