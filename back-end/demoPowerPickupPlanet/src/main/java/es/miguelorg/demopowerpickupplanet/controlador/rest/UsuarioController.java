package es.miguelorg.demopowerpickupplanet.controlador.rest;

import es.miguelorg.demopowerpickupplanet.entidad.Usuario;
import es.miguelorg.demopowerpickupplanet.controlador.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    /**
     * Muestra todos los usuarios
     * */
    @GetMapping("/getAll")
    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }

    /**
     * Muestra un usuario por su ID
     * */
    @GetMapping("/get/{id}")
    public Usuario findById(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    /**
     * Permite que un usuario se registre a la pagina
     * */
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
        Usuario usuario = usuarioService.login(email, password);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no existente");
        }
    }

    /**
     * Crea a un usuario
     * */
    @PostMapping("/add")
    public void save(@RequestBody Usuario usuario) {
        usuarioService.save(usuario);
    }

    /**
     * Edita a un usuario por su ID
     * */
    @PutMapping("/edit/{id}")
    public void update(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario u = usuarioService.findById(id);
        if (u != null) {
            u.setDni(usuario.getDni());
            u.setNombre_usuario(usuario.getNombre_usuario());
            u.setApellido(usuario.getApellido());
            u.setTarjeta(usuario.getTarjeta());
            u.setEmail(usuario.getEmail());
            u.setPassword(usuario.getPassword());
            usuarioService.save(u);
        }
    }

    /**
     * Elimina a un usuario por si ID
     * */
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        usuarioService.deleteById(id);
    }
}
