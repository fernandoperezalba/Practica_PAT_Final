package practicaFinal.pfinal.service;

import practicaFinal.pfinal.model.Abuelo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import practicaFinal.pfinal.model.Ayudante;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.BDDAssertions.then;

@Slf4j
@SpringBootTest
public class AyudanteServiceTest {

    @Autowired
    private AyudanteService ayudanteService;

    @Test
    @Transactional
    public void given_service_when_insert_ayudante_then_Ok() {

        //Given
        List<Ayudante> allAyudantes = ayudanteService.getAyudantes();

        //When
        Date fecha = new Date(1940, 03, 27);
        ayudanteService.newAyudante(1L, "Paco", "Fernandez", "p@f", 2L);

        //Then
        List<Ayudante> allAyudantes2 = ayudanteService.getAyudantes();
        then(allAyudantes2.size()).isEqualTo(allAyudantes.size() + 1);
    }


}
