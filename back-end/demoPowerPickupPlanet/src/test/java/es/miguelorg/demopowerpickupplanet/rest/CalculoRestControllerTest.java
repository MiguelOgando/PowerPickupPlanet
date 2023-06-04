package es.miguelorg.demopowerpickupplanet.rest;

import es.miguelorg.demopowerpickupplanet.controlador.rest.CalculoController;
import es.miguelorg.demopowerpickupplanet.entidad.Calculo;
import es.miguelorg.demopowerpickupplanet.controlador.servicio.CalculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class CalculoRestControllerTest {
    @Mock
    private CalculoService calculoService;

    @InjectMocks
    private CalculoController calculoController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(calculoController).build();
    }

    @Test
    void testCalcularAhorroEnergiaYDinero() throws Exception {
        // Resultado esperado
        Calculo resultadoEsperado = Calculo.builder()
                .potencia(100.0)
                .eficiencia(0.8)
                .horasSolDia(6)
                .costoElectricidad(0.15)
                .dimension(20.0)
                .cantidad(3)
                .ahorroEnergia(2592.0)
                .ahorroDinero(31104.0)
                .build();

        // Mock del servicio
        when(calculoService.calcularAhorroEnergiaYDinero(any(Calculo.class))).thenReturn(resultadoEsperado);

        // Llamada al endpoint del controlador mediante MockMvc
        MvcResult result = mockMvc.perform(post("/calculo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"potencia\":100.0,\"eficiencia\":0.8,\"horasSolDia\":6,\"costoElectricidad\":0.15,\"dimension\":20.0,\"cantidad\":3}"))
                .andReturn();

        // Verificación del código de respuesta
        int status = result.getResponse().getStatus();
        assertEquals(200, status);

        // Verificación del resultado
        String content = result.getResponse().getContentAsString();
        assertEquals("{\"id_calculo\":null,\"potencia\":100.0,\"eficiencia\":0.8,\"horasSolDia\":6,\"costoElectricidad\":0.15,\"dimension\":20.0,\"cantidad\":3,\"ahorroEnergia\":2592.0,\"ahorroDinero\":31104.0}", content);
    }
}


