package delivery.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalificacionEntity {

    private Long id;

    private int total_puntos;
    private Long idRepartidor;

}
