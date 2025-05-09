package delivery.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "calificacion")
@Data

public class CalificacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int total_puntos;
    private Long idRepartidor;

    public CalificacionEntity() {
    }

    public CalificacionEntity(int total_puntos, Long idRepartidor) {
        this.total_puntos = total_puntos;
        this.idRepartidor = idRepartidor;
    }
}
