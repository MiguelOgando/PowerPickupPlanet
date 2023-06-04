package es.miguelorg.demopowerpickupplanet.interfaz;

import es.miguelorg.demopowerpickupplanet.entidad.Mantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MantenimientoRepository extends JpaRepository<Mantenimiento, Long> {
}
