package practicaFinal.pfinal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("AYUDANTE")
@NoArgsConstructor
@AllArgsConstructor
public class Ayudante {
    @Id
    private Long id;

    private String nombre;

    private String apellidos;

    private String correo;

    private Long id_abuelo;
}
