package es.miguelorg.demopowerpickupplanet.controlador.servicio;

import es.miguelorg.demopowerpickupplanet.entidad.Producto;
import es.miguelorg.demopowerpickupplanet.interfaz.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Create
    public Producto add(Producto producto) {
        return productoRepository.save(producto);
    }

    // Read
    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }

    // Read All
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    // Update
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    // Delete
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

    // Delete All
    @Transactional
    public void deleteAll() {productoRepository.deleteAllInBatch();}
}

