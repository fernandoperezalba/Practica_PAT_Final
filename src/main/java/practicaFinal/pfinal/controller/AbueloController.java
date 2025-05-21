package practicaFinal.pfinal.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practicaFinal.pfinal.model.Abuelo;

import practicaFinal.pfinal.service.AbueloService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(
        path = "/api",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class AbueloController {

    private static final Logger log = LoggerFactory.getLogger(AbueloController.class);

    @Autowired
    private AbueloService abueloService;

    @PostMapping("/abuelos/new-abuelo")
    public @ResponseBody
    ResponseEntity<Abuelo> newAbuelo(@Valid @RequestBody Abuelo request) {

        String nombre = request.getNombre();
        log.info("Realizando el registro para: "+nombre);

        return abueloService.newAbuelo(request.getId() ,nombre, request.getApellidos(), request.getFecha());
    }

    @GetMapping("/abuelos")
    public ResponseEntity<List<Abuelo>> getAbuelos() {
        return ResponseEntity.ok().body(abueloService.getAbuelos());
    }
}