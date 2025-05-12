package delivery.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalificacionEntity {

    private Integer idCalificacion;   // id_calificacion
    private Integer totalPuntos;      // total_puntos
    private Integer totalPedidos;     // total_pedidos
    private BigDecimal promedio;      // promedio (NUMERIC)
    private Integer idRepartidor;     // id_repartidor
}
