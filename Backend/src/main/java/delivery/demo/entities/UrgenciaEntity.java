package delivery.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "urgencia")
@Data

public class UrgenciaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;

    public UrgenciaEntity() {

    }

    public UrgenciaEntity(String tipo) {
        this.tipo = tipo;
    }
}
