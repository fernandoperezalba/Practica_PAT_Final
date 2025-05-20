package practicaFinal.pfinal.repository;

import lombok.extern.slf4j.Slf4j;
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
public class AyudanteRepositoryTest {

    @Autowired
    private AyudanteRepository ayudanteRepository;

    @Test
    @Transactional
    public void given_repository_when_insert_ayudante_then_Ok() {

        //Given
        ayudanteRepository.insertNewAyudante(1L, "Paco", "Fernandez", "p@f", 2L);

        //When
        List<String> list = StreamSupport.stream(ayudanteRepository.getAyudantes().spliterator(), false)
                .map(Ayudante::toString)
                //.peek(LOGGER::info)
                .collect(Collectors.toUnmodifiableList());

        //Then
        then(list.size()).isEqualTo(1);
    }


}
