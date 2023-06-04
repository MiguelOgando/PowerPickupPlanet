package es.miguelorg.demopowerpickupplanet.interfaz;

import es.miguelorg.demopowerpickupplanet.entidad.Calculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculoRepository extends JpaRepository<Calculo, Long> {
}
