package practicaFinal.pfinal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import practicaFinal.pfinal.model.Ayudante;
import practicaFinal.pfinal.repository.AyudanteRepository;
import practicaFinal.pfinal.service.AyudanteService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AyudanteServiceImpl implements AyudanteService {
    @Autowired
    private AyudanteRepository ayudanteRepository;

    @Override
    public ResponseEntity<Ayudante> newAyudante(Long id, String nombre, String apellidos, String correo, Long id_abuelo){
        Ayudante tempAyudante = new Ayudante(id,nombre,apellidos,correo,id_abuelo);
        ayudanteRepository.insertNewAyudante(id, nombre, apellidos, correo, id_abuelo);
        return ResponseEntity.ok().body(tempAyudante);
    }

    @Override
    public List<Ayudante> getAyudantes() {
        return StreamSupport.stream(ayudanteRepository.findAll().spliterator(), false)
                .collect(Collectors.toUnmodifiableList());
    }
}
