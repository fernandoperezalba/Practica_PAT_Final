package practicaFinal.pfinal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import practicaFinal.pfinal.model.Abuelo;
import practicaFinal.pfinal.repository.AbueloRepository;
import practicaFinal.pfinal.service.AbueloService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AbueloServiceImpl implements AbueloService {

    @Autowired
    private AbueloRepository abueloRepository;

    @Override
    public ResponseEntity<Abuelo> newAbuelo(Long id, String nombre, String apellidos, Date fechaNacimiento){
        Abuelo tempAbuelo = new Abuelo(id,nombre,apellidos,fechaNacimiento);
        abueloRepository.insertNewAbuelo(id, nombre, apellidos, fechaNacimiento);
        return ResponseEntity.ok().body(tempAbuelo);
    }

    @Override
    public List<Abuelo> getAbuelos() {
        return StreamSupport.stream(abueloRepository.findAll().spliterator(), false)
                .collect(Collectors.toUnmodifiableList());
    }
}