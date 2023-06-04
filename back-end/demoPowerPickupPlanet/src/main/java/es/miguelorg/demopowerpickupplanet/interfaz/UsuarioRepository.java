package es.miguelorg.demopowerpickupplanet.interfaz;

import es.miguelorg.demopowerpickupplanet.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Busca a un usuario
    Usuario findUsuarioByEmailAndPassword(String email, String password);
}