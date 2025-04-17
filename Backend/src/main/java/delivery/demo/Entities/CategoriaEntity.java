package delivery.demo.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categoria")
@Data

public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    public CategoriaEntity() {
    }

    public CategoriaEntity(String nombre) {
        this.nombre = nombre;
    }
}
