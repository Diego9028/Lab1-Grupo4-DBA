package delivery.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "repartidor")
@Data

public class RepartidorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Long idEmpresaAsociada;

    public RepartidorEntity() {

    }

    public RepartidorEntity(String nombre, Long idEmpresaAsociada) {
        this.nombre = nombre;
        this.idEmpresaAsociada = idEmpresaAsociada;
    }
}
