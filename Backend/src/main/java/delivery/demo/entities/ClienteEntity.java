package delivery.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Email
    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TokenEntity> tokens;
}
