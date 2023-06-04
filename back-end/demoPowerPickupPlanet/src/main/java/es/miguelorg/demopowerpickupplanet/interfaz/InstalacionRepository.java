package es.miguelorg.demopowerpickupplanet.interfaz;

import es.miguelorg.demopowerpickupplanet.entidad.Instalacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstalacionRepository extends JpaRepository<Instalacion, Long> {
}
