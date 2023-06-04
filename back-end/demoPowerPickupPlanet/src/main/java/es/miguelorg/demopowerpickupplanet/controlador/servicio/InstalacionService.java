package es.miguelorg.demopowerpickupplanet.controlador.servicio;

import es.miguelorg.demopowerpickupplanet.entidad.Instalacion;
import es.miguelorg.demopowerpickupplanet.interfaz.InstalacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class InstalacionService {
    @Autowired
    private InstalacionRepository instalacionRepository;

    // Encuentra a todos
    public List<Instalacion> findAll() {
        return instalacionRepository.findAll();
    }

    // Add
    public Instalacion add(Instalacion instalacion) {
        return instalacionRepository.save(instalacion);
    }

    // Encuentra por id
    public Optional<Instalacion> findById(Long id) {
        return instalacionRepository.findById(id);
    }

    // Guarda
    public void save(Instalacion instalacion) {
        instalacionRepository.save(instalacion);
    }

    // Elimina por id
    public void deleteById(Long id) {
        instalacionRepository.deleteById(id);
    }

    // Elimina todos
    @Transactional
    public void deleteAll() {instalacionRepository.deleteAllInBatch();}
}

