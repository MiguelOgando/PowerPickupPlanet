package es.miguelorg.demopowerpickupplanet.controlador.servicio;

import es.miguelorg.demopowerpickupplanet.entidad.Usuario;
import es.miguelorg.demopowerpickupplanet.interfaz.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Encuentra a todos
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    // Busca por id
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // Busca por email y password
    public Usuario login(String email, String password) {
        return usuarioRepository.findUsuarioByEmailAndPassword(email, password);
    }

    // Add
    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    // Elimina por id
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    // Elimina todos
    @Transactional
    public void deleteAll() {usuarioRepository.deleteAllInBatch();}
}

