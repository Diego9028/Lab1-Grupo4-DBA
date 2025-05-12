package delivery.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedioPagoEntity {
    private Long id_medio_pago;

    private String tipo;
    private LocalDate deleted_at;

}
