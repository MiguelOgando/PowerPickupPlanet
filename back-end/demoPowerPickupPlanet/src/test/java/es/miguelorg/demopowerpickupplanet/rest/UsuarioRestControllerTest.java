package es.miguelorg.demopowerpickupplanet.rest;

import es.miguelorg.demopowerpickupplanet.controlador.rest.UsuarioController;
import es.miguelorg.demopowerpickupplanet.entidad.Usuario;
import es.miguelorg.demopowerpickupplanet.controlador.servicio.UsuarioService;
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

class UsuarioRestControllerTest {
    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Mock del servicio
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(Usuario.builder()
                .id_usuario(1L)
                .nombre_usuario("John")
                .apellido("Doe")
                .build());
        usuarios.add(Usuario.builder()
                .id_usuario(2L)
                .nombre_usuario("Jane")
                .apellido("Smith")
                .build());
        when(usuarioService.findAll()).thenReturn(usuarios);

        // Llamada al método del controlador
        List<Usuario> result = usuarioController.findAll();

        // Verificación del resultado
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getNombre_usuario());
        assertEquals("Doe", result.get(0).getApellido());
        assertEquals("Jane", result.get(1).getNombre_usuario());
        assertEquals("Smith", result.get(1).getApellido());

        // Verificación de que el método del servicio fue llamado
        verify(usuarioService, times(1)).findAll();
    }

    @Test
    void testFindById() {
        // Mock del servicio
        Long id = 1L;
        Usuario usuario = Usuario.builder()
                .id_usuario(id)
                .nombre_usuario("John")
                .apellido("Doe")
                .build();
        when(usuarioService.findById(id)).thenReturn(usuario);

        // Llamada al método del controlador
        Usuario result = usuarioController.findById(id);

        // Verificación del resultado
        assertEquals("John", result.getNombre_usuario());
        assertEquals("Doe", result.getApellido());

        // Verificación de que el método del servicio fue llamado
        verify(usuarioService, times(1)).findById(id);
    }

    @Test
    void testLogin_UsuarioExistente() {
        // Mock del servicio
        String email = "john.doe@example.com";
        String password = "password";
        Usuario usuario = Usuario.builder()
                .id_usuario(1L)
                .nombre_usuario("John")
                .apellido("Doe")
                .build();
        when(usuarioService.login(email, password)).thenReturn(usuario);

        // Llamada al método del controlador
        Map<String, String> loginRequest = new HashMap<>();
        loginRequest.put("email", email);
        loginRequest.put("password", password);
        ResponseEntity<Object> result = usuarioController.login(loginRequest);

        // Verificación del resultado
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(usuario, result.getBody());

        // Verificación de que el método del servicio fue llamado
        verify(usuarioService, times(1)).login(email, password);
    }

    @Test
    void testLogin_UsuarioNoExistente() {
        // Mock del servicio
        String email = "john.doe@example.com";
        String password = "password";
        when(usuarioService.login(email, password)).thenReturn(null);

        // Llamada al método del controlador
        Map<String, String> loginRequest = new HashMap<>();
        loginRequest.put("email", email);
        loginRequest.put("password", password);
        ResponseEntity<Object> result = usuarioController.login(loginRequest);

        // Verificación del resultado
        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
        assertEquals("Usuario no existente", result.getBody());

        // Verificación de que el método del servicio fue llamado
        verify(usuarioService, times(1)).login(email, password);
    }

    @Test
    void testSave() {
        // Mock del servicio
        Usuario usuario = Usuario.builder()
                .id_usuario(1L)
                .nombre_usuario("John")
                .apellido("Doe")
                .build();

        // Llamada al método del controlador
        usuarioController.save(usuario);

        // Verificación de que el método del servicio fue llamado
        verify(usuarioService, times(1)).save(usuario);
    }

    @Test
    void testUpdate_UsuarioExistente() {
        // Mock del servicio
        Long id = 1L;
        Usuario usuario = Usuario.builder()
                .id_usuario(id)
                .nombre_usuario("John")
                .apellido("Doe")
                .build();
        Usuario usuarioActualizado = Usuario.builder()
                .id_usuario(id)
                .nombre_usuario("Jane")
                .apellido("Smith")
                .build();
        when(usuarioService.findById(id)).thenReturn(usuario);

        // Llamada al método del controlador
        usuarioController.update(id, usuarioActualizado);

        // Verificación de que el método del servicio fue llamado
        verify(usuarioService, times(1)).findById(id);
        verify(usuarioService, times(1)).save(usuario);
        assertEquals("Jane", usuario.getNombre_usuario());
        assertEquals("Smith", usuario.getApellido());
    }

    @Test
    void testUpdate_UsuarioNoExistente() {
        // Mock del servicio
        Long id = 1L;
        Usuario usuarioActualizado = Usuario.builder()
                .id_usuario(id)
                .nombre_usuario("Jane")
                .apellido("Smith")
                .build();
        when(usuarioService.findById(id)).thenReturn(null);

        // Llamada al método del controlador
        usuarioController.update(id, usuarioActualizado);

        // Verificación de que el método del servicio fue llamado
        verify(usuarioService, times(1)).findById(id);
        verify(usuarioService, never()).save(any());
    }

    @Test
    void testDeleteById() {
        // Mock del servicio
        Long id = 1L;

        // Llamada al método del controlador
        usuarioController.deleteById(id);

        // Verificación de que el método del servicio fue llamado
        verify(usuarioService, times(1)).deleteById(id);
    }
}

