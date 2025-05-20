package practicaFinal.pfinal.service;

import org.springframework.http.ResponseEntity;
import practicaFinal.pfinal.model.Ayudante;

import java.util.List;

public interface AyudanteService {
    ResponseEntity<Ayudante> newAyudante(Long id, String nombre, String apellidos, String correo, Long id_abuelo);
    List<Ayudante> getAyudantes();
}