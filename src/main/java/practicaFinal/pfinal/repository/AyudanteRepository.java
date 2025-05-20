package practicaFinal.pfinal.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import practicaFinal.pfinal.model.Ayudante;

import java.util.List;

public interface AyudanteRepository extends CrudRepository<Ayudante, Long> {
    @Modifying
    @Query("INSERT INTO AYUDANTE (ID, NOMBRE, APELLIDOS, CORREO, ID_ABUELO) VALUES (:id, :nombre, :apellidos, :correo, :id_abuelo)")
    void insertNewAyudante(@Param("id") Long id,
                           @Param("nombre") String nombre,
                           @Param("apellidos") String apellidos,
                           @Param("correo") String correo,
                           @Param("id_abuelo") Long id_abuelo);

    @Query("SELECT * FROM AYUDANTE")
    List<Ayudante> getAyudantes();
}