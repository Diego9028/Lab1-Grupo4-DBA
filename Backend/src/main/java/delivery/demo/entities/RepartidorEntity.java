package delivery.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepartidorEntity {
    private Long id;

    private String nombre;

    private Long idEmpresaAsociada;
}
