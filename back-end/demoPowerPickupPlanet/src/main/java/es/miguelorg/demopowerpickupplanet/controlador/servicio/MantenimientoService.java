package es.miguelorg.demopowerpickupplanet.controlador.servicio;

import es.miguelorg.demopowerpickupplanet.entidad.Mantenimiento;
import es.miguelorg.demopowerpickupplanet.interfaz.MantenimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MantenimientoService {
    @Autowired
    private MantenimientoRepository mantenimientoRepository;

    // Encuentra a todos
    public List<Mantenimiento> findAll() {
        return mantenimientoRepository.findAll();
    }

    // Add
    public Mantenimiento add(Mantenimiento mantenimiento) {
        return mantenimientoRepository.save(mantenimiento);
    }

    // Encuentra por id
    public Optional<Mantenimiento> findById(Long id) {
        return mantenimientoRepository.findById(id);
    }

    // Guarda
    public void save(Mantenimiento mantenimiento) {
        mantenimientoRepository.save(mantenimiento);
    }

    // Encuentra por id
    public void deleteById(Long id) {
        mantenimientoRepository.deleteById(id);
    }

    // Elimina todos
    @Transactional
    public void deleteAll() {mantenimientoRepository.deleteAllInBatch();}
}

