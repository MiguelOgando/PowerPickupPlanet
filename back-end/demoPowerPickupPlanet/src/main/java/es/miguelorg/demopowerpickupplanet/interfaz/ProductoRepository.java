package es.miguelorg.demopowerpickupplanet.interfaz;

import es.miguelorg.demopowerpickupplanet.entidad.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
