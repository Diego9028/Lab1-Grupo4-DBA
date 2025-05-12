package delivery.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalificacionEntity {

    private Integer id_calificacion;   // id_calificacion
    private Integer total_puntos;      // total_puntos
    private Integer total_pedidos;     // total_pedidos
    private BigDecimal promedio;      // promedio (NUMERIC)
    private Integer id_repartidor;     // id_repartidor
    private LocalDate deleted_at;
}
