package practicaFinal.pfinal.service;

import practicaFinal.pfinal.model.Abuelo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.BDDAssertions.then;

@Slf4j
@SpringBootTest
public class AbueloServiceTest {

    @Autowired
    private AbueloService abueloService;

    @Test
    @Transactional
    public void given_service_when_insert_abuelo_then_Ok() {

        //Given
        List<Abuelo> allAbuelos = abueloService.getAbuelos();

        //When
        Date fecha = new Date(1940, 03, 27);
        abueloService.newAbuelo(1L, "Fernando", "Alba", fecha);

        //Then
        List<Abuelo> allAbuelos2 = abueloService.getAbuelos();
        then(allAbuelos2.size()).isEqualTo(allAbuelos.size() + 1);
    }


}
