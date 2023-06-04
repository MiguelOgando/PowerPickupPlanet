package es.miguelorg.demopowerpickupplanet.rest;

import es.miguelorg.demopowerpickupplanet.controlador.rest.ProductoController;
import es.miguelorg.demopowerpickupplanet.entidad.Producto;
import es.miguelorg.demopowerpickupplanet.controlador.servicio.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductoRestControllerTest {
    @Mock
    private ProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearProducto() {
        // Mock del servicio
        Producto producto = Producto.builder()
                .marca("SunPower")
                .modelo("Maxeon 3")
                .dimension(170.0)
                .cantidad(4)
                .potencia(55.5)
                .eficiencia(20.0)
                .build();
        producto.setId_producto(1L);
        when(productoService.add(producto)).thenReturn(producto);

        // Llamada al método del controlador
        Producto result = productoController.crearProducto(producto);

        // Verificación del resultado
        assertEquals(producto, result);

        // Verificación de que el método del servicio fue llamado
        verify(productoService, times(1)).add(producto);
    }

    @Test
    void testObtenerProductoPorId_ProductoExistente() {
        // Mock del servicio
        Long id = 1L;
        Producto producto = Producto.builder()
                .marca("SunPower")
                .modelo("Maxeon 3")
                .dimension(170.0)
                .cantidad(4)
                .potencia(55.5)
                .eficiencia(20.0)
                .build();
        producto.setId_producto(id);
        Optional<Producto> optionalProducto = Optional.of(producto);
        when(productoService.findById(id)).thenReturn(optionalProducto);

        // Llamada al método del controlador
        ResponseEntity<Producto> result = productoController.obtenerProductoPorId(id);

        // Verificación del resultado
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(producto, result.getBody());

        // Verificación de que el método del servicio fue llamado
        verify(productoService, times(1)).findById(id);
    }

    @Test
    void testObtenerProductoPorId_ProductoNoExistente() {
        // Mock del servicio
        Long id = 1L;
        Optional<Producto> optionalProducto = Optional.empty();
        when(productoService.findById(id)).thenReturn(optionalProducto);

        // Llamada al método del controlador
        ResponseEntity<Producto> result = productoController.obtenerProductoPorId(id);

        // Verificación del resultado
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());

        // Verificación de que el método del servicio fue llamado
        verify(productoService, times(1)).findById(id);
    }

    @Test
    void testObtenerTodosLosProductos() {
        // Mock del servicio
        List<Producto> productos = new ArrayList<>();
        productos.add(Producto.builder()
                .marca("SunPower")
                .modelo("Maxeon 3")
                .dimension(170.0)
                .cantidad(4)
                .potencia(55.5)
                .eficiencia(20.0)
                .build());
        productos.add(Producto.builder()
                .marca("LG Solar")
                .modelo("Neon R")
                .dimension(180.0)
                .cantidad(12)
                .potencia(55.0)
                .eficiencia(19.8)
                .build());
        when(productoService.findAll()).thenReturn(productos);

        // Llamada al método del controlador
        List<Producto> result = productoController.obtenerTodosLosProductos();

        // Verificación del resultado
        assertEquals(2, result.size());

        // Verificación de que el método del servicio fue llamado
        verify(productoService, times(1)).findAll();
    }

    @Test
    void testActualizarProducto_ProductoExistente() {
        // Mock del servicio
        Long id = 1L;
        Producto productoExistente = Producto.builder()
                .marca("SunPower")
                .modelo("Maxeon 3")
                .dimension(170.0)
                .cantidad(4)
                .potencia(55.5)
                .eficiencia(20.0)
                .build();
        productoExistente.setId_producto(id);
        Optional<Producto> optionalProductoExistente = Optional.of(productoExistente);
        when(productoService.findById(id)).thenReturn(optionalProductoExistente);

        Producto productoActualizado = Producto.builder()
                .marca("LG Solar")
                .modelo("Neon R")
                .dimension(180.0)
                .cantidad(12)
                .potencia(55.0)
                .eficiencia(19.8)
                .build();
        productoActualizado.setId_producto(id);
        when(productoService.save(productoActualizado)).thenReturn(productoActualizado);

        // Llamada al método del controlador
        ResponseEntity<Producto> result = productoController.actualizarProducto(id, productoActualizado);

        // Verificación del resultado
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(productoActualizado, result.getBody());

        // Verificación de que el método del servicio fue llamado
        verify(productoService, times(1)).findById(id);
        verify(productoService, times(1)).save(productoActualizado);
    }

    @Test
    void testActualizarProducto_ProductoNoExistente() {
        // Mock del servicio
        Long id = 1L;
        Optional<Producto> optionalProductoExistente = Optional.empty();
        when(productoService.findById(id)).thenReturn(optionalProductoExistente);

        Producto productoActualizado = new Producto();
        productoActualizado.setId_producto(id);

        // Llamada al método del controlador
        ResponseEntity<Producto> result = productoController.actualizarProducto(id, productoActualizado);

        // Verificación del resultado
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());

        // Verificación de que el método del servicio fue llamado
        verify(productoService, times(1)).findById(id);
        verify(productoService, never()).save(any());
    }

    @Test
    void testEliminarProductoPorId_ProductoExistente() {
        // Mock del servicio
        Long id = 1L;
        Producto productoExistente = Producto.builder()
                .marca("LG Solar")
                .modelo("Neon R")
                .dimension(180.0)
                .cantidad(12)
                .potencia(55.0)
                .eficiencia(19.8)
                .build();
        productoExistente.setId_producto(id);
        Optional<Producto> optionalProductoExistente = Optional.of(productoExistente);
        when(productoService.findById(id)).thenReturn(optionalProductoExistente);

        // Llamada al método del controlador
        ResponseEntity<Void> result = productoController.eliminarProductoPorId(id);

        // Verificación del resultado
        assertEquals(HttpStatus.OK, result.getStatusCode());

        // Verificación de que el método del servicio fue llamado
        verify(productoService, times(1)).findById(id);
        verify(productoService, times(1)).deleteById(id);
    }

    @Test
    void testEliminarProductoPorId_ProductoNoExistente() {
        // Mock del servicio
        Long id = 1L;
        Optional<Producto> optionalProductoExistente = Optional.empty();
        when(productoService.findById(id)).thenReturn(optionalProductoExistente);

        // Llamada al método del controlador
        ResponseEntity<Void> result = productoController.eliminarProductoPorId(id);

        // Verificación del resultado
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());

        // Verificación de que el método del servicio fue llamado
        verify(productoService, times(1)).findById(id);
        verify(productoService, never()).deleteById(any());
    }
}

