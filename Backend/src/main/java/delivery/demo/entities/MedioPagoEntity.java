package delivery.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "medio_pago")
@Data

public class MedioPagoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;

    public MedioPagoEntity() {

    }

    public MedioPagoEntity(String tipo) {
        this.tipo = tipo;
    }
}
