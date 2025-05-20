package practicaFinal.pfinal.service;

import org.springframework.http.ResponseEntity;
import practicaFinal.pfinal.model.Abuelo;

import java.util.Date;
import java.util.List;

public interface AbueloService {
    ResponseEntity<Abuelo> newAbuelo(Long id, String nombre, String apellidos, Date fechaNacimiento);
    List<Abuelo> getAbuelos();
}
