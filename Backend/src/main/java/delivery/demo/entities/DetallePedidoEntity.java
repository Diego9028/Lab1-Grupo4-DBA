package delivery.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "detalle_pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedidoEntity {

    @Id
    @Column(name = "id_detalle_pedido")
    private Integer id; // No usamos @GeneratedValue porque el ID lo asignás desde el backend

    @Column(nullable = false)
    private Boolean entregado;

    @Column(name = "hora_entrega")
    private Timestamp horaEntrega;

    @Column(precision = 4, scale = 2)
    private BigDecimal calificacion;
}
