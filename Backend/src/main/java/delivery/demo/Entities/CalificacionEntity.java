package delivery.demo.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "calificacion")
@Data

public class CalificacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int puntuacion;
    private Long idRepartidor;

    public CalificacionEntity() {
    }

    public CalificacionEntity(int puntuacion, Long idRepartidor) {
        this.puntuacion = puntuacion;
        this.idRepartidor = idRepartidor;
    }
}
