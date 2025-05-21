package practicaFinal.pfinal.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practicaFinal.pfinal.model.Abuelo;

import practicaFinal.pfinal.model.Ayudante;
import practicaFinal.pfinal.service.AyudanteService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(
        path = "/api",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class AyudanteController {

    private static final Logger log = LoggerFactory.getLogger(AyudanteController.class);

    @Autowired
    private AyudanteService ayudanteService;

    @PostMapping("/ayudantes/new-ayudante")
    public @ResponseBody
    ResponseEntity<Ayudante> newUser(@Valid @RequestBody Ayudante request) {

        String nombre = request.getNombre();
        log.info("Realizando el registro para: {}", nombre);

        return ayudanteService.newAyudante(request.getId() ,nombre, request.getApellidos(), request.getCorreo(), request.getId_abuelo());
    }

    @GetMapping("/ayudantes")
    public ResponseEntity<List<Ayudante>> getAyudantes() {
        return ResponseEntity.ok().body(ayudanteService.getAyudantes());
    }
}