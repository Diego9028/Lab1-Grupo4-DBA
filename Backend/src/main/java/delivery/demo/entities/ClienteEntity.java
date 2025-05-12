package delivery.demo.entities;


import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteEntity {

    private Long id_cliente;
    private String nombre;
    private String direccion;

    @Email
    private String correo;

    private String password;

    private LocalDate deleted_at;
}
