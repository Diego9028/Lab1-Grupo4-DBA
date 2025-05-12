package delivery.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrgenciaEntity {
    private Long id_urgencia;

    private String tipo;
    private LocalDate deleted_at;
}
