package delivery.demo.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cliente")
@Data

public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String direccion;
    private String correo;

    public ClienteEntity() {
    }

    public ClienteEntity(String direccion, String correo) {
        this.direccion = direccion;
        this.correo = correo;
    }
}
