package delivery.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "cliente" , uniqueConstraints = {
@UniqueConstraint(columnNames = "nombre"),
@UniqueConstraint(columnNames = "direccion"),
@UniqueConstraint(columnNames = "correo")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Email
    @Column(name = "correo", nullable = false, unique = true)
    private String correo;

    private String password;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<TokenEntity> tokens;
}
