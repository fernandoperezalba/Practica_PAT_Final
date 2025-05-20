package practicaFinal.pfinal.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

import practicaFinal.pfinal.model.Abuelo;

public interface AbueloRepository extends CrudRepository<Abuelo, Long> {
    @Modifying
    @Query("INSERT INTO ABUELO (ID, NOMBRE, APELLIDOS, FECHA) VALUES (:id, :nombre, :apellidos, :fecha)")
    void insertNewAbuelo(@Param("id") Long id,
                           @Param("nombre") String nombre,
                           @Param("apellidos") String apellidos,
                           @Param("fecha") Date fecha);

    @Query("SELECT * FROM ABUELO")
    List<Abuelo> getAbuelos();
}