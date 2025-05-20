package practicaFinal.pfinal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Builder
@Table("ABUELO")
@NoArgsConstructor
@AllArgsConstructor
public class Abuelo {
    @Id
    private Long id;

    private String nombre;

    private String apellidos;

    private Date fecha; //de nacimiento
}