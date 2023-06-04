package es.miguelorg.demopowerpickupplanet.controlador.rest;

import es.miguelorg.demopowerpickupplanet.entidad.Mantenimiento;
import es.miguelorg.demopowerpickupplanet.entidad.Producto;
import es.miguelorg.demopowerpickupplanet.controlador.servicio.MantenimientoService;
import es.miguelorg.demopowerpickupplanet.controlador.servicio.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mantenimientos")
public class MantenimientoController {
    @Autowired
    private MantenimientoService mantenimientoService;

    @Autowired
    private ProductoService productoService;

    /**
     * Muestra todos los mantenimientos
     * */
    @GetMapping("/getAll")
    public List<Mantenimiento> listarMantenimientos() {
        return mantenimientoService.findAll();
    }

    /**
     * Crea un mantenimiento
     * */
    @PostMapping("/add")
    public Mantenimiento crearMantenimiento(@RequestBody Mantenimiento mantenimiento) {
        return mantenimientoService.add(mantenimiento);
    }

    /**
     * Selecciona un mantenimiento por su ID
     * */
    @GetMapping("/get/{id}")
    public ResponseEntity<Mantenimiento> obtenerMantenimiento(@PathVariable Long id) {
        Optional<Mantenimiento> mantenimiento = mantenimientoService.findById(id);
        if (mantenimiento.isPresent()) {
            return ResponseEntity.ok(mantenimiento.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Edita un mantenimiento por su ID
     * */
    @PutMapping("/edit/{id}")
    public ResponseEntity<Mantenimiento> actualizarMantenimiento(@RequestBody Mantenimiento mantenimiento, @PathVariable Long id) {
        Optional<Mantenimiento> mantenimientoExistente = mantenimientoService.findById(id);
        if (mantenimientoExistente.isPresent()) {
            mantenimiento.setId_mantenimiento(id);
            mantenimientoService.save(mantenimiento);
            return ResponseEntity.ok(mantenimiento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina un mantenimiento por su ID
     * */
    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarInstalacion(@PathVariable Long id) {
        Mantenimiento mantenimiento = null;
        try {
            mantenimiento = mantenimientoService.findById(id)
                    .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
            // Eliminar el mantenimiento de todos los productos que la tengan asignada
            for (Producto producto : mantenimiento.getProductos()) {
                producto.setMantenimiento(null);
                productoService.save(producto);
            }

            // Eliminar la mantenimiento
            mantenimientoService.deleteById(mantenimiento.getId_mantenimiento());

            return ResponseEntity.noContent().build();
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

