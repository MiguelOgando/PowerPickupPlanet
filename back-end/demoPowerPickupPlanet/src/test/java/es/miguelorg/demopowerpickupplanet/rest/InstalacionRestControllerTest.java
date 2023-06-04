package es.miguelorg.demopowerpickupplanet.rest;

import es.miguelorg.demopowerpickupplanet.controlador.rest.InstalacionController;
import es.miguelorg.demopowerpickupplanet.entidad.Instalacion;
import es.miguelorg.demopowerpickupplanet.controlador.servicio.InstalacionService;
import es.miguelorg.demopowerpickupplanet.controlador.servicio.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class InstalacionRestControllerTest {
    @Mock
    private InstalacionService instalacionService;

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private InstalacionController instalacionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarInstalaciones() {
        // Mock del servicio
        List<Instalacion> instalaciones = new ArrayList<>();
        instalaciones.add(new Instalacion(1L, "Instalacion 1", LocalDate.now(), 180.0, 30.0, true, new ArrayList<>()));
        instalaciones.add(new Instalacion(2L, "Instalacion 2", LocalDate.now(), 150.0, 20.0, false, new ArrayList<>()));
        when(instalacionService.findAll()).thenReturn(instalaciones);

        // Llamada al método del controlador
        List<Instalacion> result = instalacionController.listarInstalaciones();

        // Verificación del resultado
        assertEquals(2, result.size());
        assertEquals(instalaciones, result);

        // Verificación de que el método del servicio fue llamado
        verify(instalacionService, times(1)).findAll();
    }

    @Test
    void testCrearInstalacion() {
        // Mock del servicio
        Instalacion instalacion = new Instalacion(1L, "Instalacion 1", LocalDate.now(), 180.0, 30.0, true, new ArrayList<>());
        when(instalacionService.add(any(Instalacion.class))).thenReturn(instalacion);

        // Llamada al método del controlador
        Instalacion result = instalacionController.crearInstalacion(instalacion);

        // Verificación del resultado
        assertEquals(instalacion, result);

        // Verificación de que el método del servicio fue llamado
        verify(instalacionService, times(1)).add(instalacion);
    }

    @Test
    void testObtenerInstalacionExistente() {
        // Mock del servicio
        Long id = 1L;
        Instalacion instalacion = new Instalacion(id, "Instalacion 1", LocalDate.now(), 180.0, 30.0, true, new ArrayList<>());
        Optional<Instalacion> optionalInstalacion = Optional.of(instalacion);
        when(instalacionService.findById(id)).thenReturn(optionalInstalacion);

        // Llamada al método del controlador
        ResponseEntity<Instalacion> result = instalacionController.obtenerInstalacion(id);

        // Verificación del resultado
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(instalacion, result.getBody());

        // Verificación de que el método del servicio fue llamado
        verify(instalacionService, times(1)).findById(id);
    }

    @Test
    void testObtenerInstalacionNoExistente() {
        // Mock del servicio
        Long id = 1L;
        Optional<Instalacion> optionalInstalacion = Optional.empty();
        when(instalacionService.findById(id)).thenReturn(optionalInstalacion);

        // Llamada al método del controlador
        ResponseEntity<Instalacion> result = instalacionController.obtenerInstalacion(id);

        // Verificación del resultado
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());

        // Verificación de que el método del servicio fue llamado
        verify(instalacionService, times(1)).findById(id);
    }

    @Test
    void testActualizarInstalacionExistente() {
        // Mock del servicio
        Long id = 1L;
        Instalacion instalacionExistente = new Instalacion(id, "Instalacion 1", LocalDate.now(), 180.0, 30.0, true, new ArrayList<>());
        Optional<Instalacion> optionalInstalacionExistente = Optional.of(instalacionExistente);
        when(instalacionService.findById(id)).thenReturn(optionalInstalacionExistente);

        Instalacion instalacionActualizada = new Instalacion(id, "Instalacion 1 (actualizada)", LocalDate.now(), 150.0, 25.0, true, new ArrayList<>());
        doNothing().when(instalacionService).save(any(Instalacion.class));

        // Llamada al método del controlador
        ResponseEntity<Instalacion> result = instalacionController.actualizarInstalacion(instalacionActualizada, id);

        // Verificación del resultado
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(instalacionActualizada, result.getBody());

        // Verificación de que el método del servicio fue llamado
        verify(instalacionService, times(1)).findById(id);
        verify(instalacionService, times(1)).save(instalacionActualizada);
    }



    @Test
    void testActualizarInstalacionNoExistente() {
        // Mock del servicio
        Long id = 1L;
        Optional<Instalacion> optionalInstalacionExistente = Optional.empty();
        when(instalacionService.findById(id)).thenReturn(optionalInstalacionExistente);

        Instalacion instalacionActualizada = new Instalacion(id, "Instalacion 1 (actualizada)", LocalDate.now(), 150.0, 25.0, true, new ArrayList<>());

        // Llamada al método del controlador
        ResponseEntity<Instalacion> result = instalacionController.actualizarInstalacion(instalacionActualizada, id);

        // Verificación del resultado
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());

        // Verificación de que el método del servicio fue llamado
        verify(instalacionService, times(1)).findById(id);
        verify(instalacionService, never()).save(any());
    }

    @Test
    void testEliminarInstalacionExistente() {
        // Mock del servicio
        Long id = 1L;
        Instalacion instalacionExistente = new Instalacion(id, "Instalacion 1", LocalDate.now(), 180.0, 30.0, true, new ArrayList<>());
        Optional<Instalacion> optionalInstalacionExistente = Optional.of(instalacionExistente);
        when(instalacionService.findById(id)).thenReturn(optionalInstalacionExistente);

        // Llamada al método del controlador
        ResponseEntity<Void> result = instalacionController.eliminarInstalacion(id);

        // Verificación del resultado
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Verificación de que el método del servicio fue llamado
        verify(instalacionService, times(1)).findById(id);
        verify(instalacionService, times(1)).deleteById(id);
    }

    @Test
    void testEliminarInstalacionNoExistente() {
        // Mock del servicio
        Long id = 1L;
        Optional<Instalacion> optionalInstalacionExistente = Optional.empty();
        instalacionService = mock(InstalacionService.class);
        instalacionController = new InstalacionController();

        when(instalacionService.findById(id)).thenReturn(optionalInstalacionExistente);

        // Llamada al método del controlador
        ResponseEntity<Void> result = instalacionController.eliminarInstalacion(id);

        // Verificación del resultado
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());

        // Verificación de que el método del servicio fue llamado
        verify(instalacionService, times(1)).findById(id);
        verify(instalacionService, never()).deleteById(any());
    }
}


