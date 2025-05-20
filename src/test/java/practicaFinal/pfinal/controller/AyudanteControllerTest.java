package practicaFinal.pfinal.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import practicaFinal.pfinal.model.Abuelo;
import practicaFinal.pfinal.model.Ayudante;
import practicaFinal.pfinal.service.AyudanteService;

import java.util.Date;
import java.util.List;

import static practicaFinal.pfinal.Utils.asJsonString;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AyudanteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AyudanteService service;

    @Test
    public void given_controller_when_getAyudantes_then_Ok() throws Exception {

        //Given
        List<Ayudante> expectedList = List.of(getAyudantes());
        when(service.getAyudantes()).thenReturn(expectedList);

        //When
        //Then
        this.mockMvc
                .perform(get("/api/ayudantes").contentType(APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(asJsonString(expectedList))));

    }

    private Ayudante getAyudantes() {

        Date fecha = new Date(1940, 03, 27);

        return Ayudante.builder()
                .id(2L)
                .nombre("Paco")
                .apellidos("Fernandez")
                .correo("p@f")
                .id_abuelo(1L)
                .build();
    }
}
