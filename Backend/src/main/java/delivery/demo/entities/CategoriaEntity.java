package delivery.demo.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaEntity {

    private Long id_categoria;

    private String nombre;
    private LocalDate deleted_at;
}
