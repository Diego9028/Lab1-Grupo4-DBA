package delivery.demo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoServicioEntity {
    private Long id_producto_servicio;

    private int stock;
    private float precio;
    private Long id_categoria;
    private Long id_empresa_asociada;
    private Boolean es_producto;
    private LocalDate deleted_at;
}
