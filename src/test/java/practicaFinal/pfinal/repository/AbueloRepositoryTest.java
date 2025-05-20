package practicaFinal.pfinal.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import practicaFinal.pfinal.model.Abuelo;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.BDDAssertions.then;

@Slf4j
@SpringBootTest
public class AbueloRepositoryTest {

    @Autowired
    private AbueloRepository abueloRepository;

    @Test
    @Transactional
    public void given_repository_when_insert_abuelos_then_Ok() {

        //Given
        Date fecha = new Date(1940, 03, 27);
        abueloRepository.insertNewAbuelo(1L, "Lourdes", "Artacho", fecha);

        //When
        List<String> list = StreamSupport.stream(abueloRepository.getAbuelos().spliterator(), false)
                .map(Abuelo::toString)
                //.peek(LOGGER::info)
                .collect(Collectors.toUnmodifiableList());

        //Then
        then(list.size()).isEqualTo(1);
    }


}
