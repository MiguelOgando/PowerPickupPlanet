package es.miguelorg.demopowerpickupplanet.controlador.rest;

import es.miguelorg.demopowerpickupplanet.entidad.Instalacion;
import es.miguelorg.demopowerpickupplanet.entidad.Producto;
import es.miguelorg.demopowerpickupplanet.controlador.servicio.InstalacionService;
import es.miguelorg.demopowerpickupplanet.controlador.servicio.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/instalaciones")
public class InstalacionController {
    @Autowired
    private InstalacionService instalacionService;

    @Autowired
    private ProductoService productoService;

    /**
     * Selcciona todas las instalaciones
     * */
    @GetMapping("/getAll")
    public List<Instalacion> listarInstalaciones() {
        return instalacionService.findAll();
    }

    /**
     * Crea una instalacion
     * */
    @PostMapping("/add")
    public Instalacion crearInstalacion(@RequestBody Instalacion instalacion) {
        return instalacionService.add(instalacion);
    }

    /**
     * Obtiene una instalacion por su ID
     * */
    @GetMapping("/get/{id}")
    public ResponseEntity<Instalacion> obtenerInstalacion(@PathVariable Long id) {
        Optional<Instalacion> instalacion = instalacionService.findById(id);
        if (instalacion.isPresent()) {
            return ResponseEntity.ok(instalacion.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Edita una instalacion buscandola por su ID
     * */
    @PutMapping("/edit/{id}")
    public ResponseEntity<Instalacion> actualizarInstalacion(@RequestBody Instalacion instalacion, @PathVariable Long id) {
        Optional<Instalacion> instalacionExistente = instalacionService.findById(id);
        if (instalacionExistente.isPresent()) {
            instalacion.setId_instalacion(id);
            instalacionService.save(instalacion);
            return ResponseEntity.ok(instalacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina una instalacion por su ID
     * */
    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarInstalacion(@PathVariable Long id) {
        Instalacion instalacion = null;
        try {
            instalacion = instalacionService.findById(id)
                    .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
            // Eliminar la instalación de todos los productos que la tengan asignada
            for (Producto producto : instalacion.getProductos()) {
                producto.setInstalacion(null);
                productoService.save(producto);
            }

            // Eliminar la instalación
            instalacionService.deleteById(instalacion.getId_instalacion());

            return ResponseEntity.noContent().build();
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}

