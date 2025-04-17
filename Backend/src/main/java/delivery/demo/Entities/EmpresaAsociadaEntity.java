package delivery.demo.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "empresa_asociada")
@Data

public class EmpresaAsociadaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    public EmpresaAsociadaEntity() {

    }

    public EmpresaAsociadaEntity(String nombre) {
        this.nombre = nombre;
    }
}
