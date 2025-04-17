package delivery.demo.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "producto_servicio")
@Data

public class ProductoServivioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int stock;
    private float precio;
    private Long idCategoria;
    private Long idEmpresaAsociada;

    public ProductoServivioEntity() {

    }

    public ProductoServivioEntity(int stock, float precio, Long idCategoria, Long idEmpresaAsociada) {
        this.stock = stock;
        this.precio = precio;
        this.idCategoria = idCategoria;
        this.idEmpresaAsociada = idEmpresaAsociada;
    }
}
