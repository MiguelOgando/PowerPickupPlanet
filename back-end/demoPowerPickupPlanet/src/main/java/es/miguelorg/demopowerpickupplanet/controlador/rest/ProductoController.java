package es.miguelorg.demopowerpickupplanet.controlador.rest;

import es.miguelorg.demopowerpickupplanet.entidad.Producto;
import es.miguelorg.demopowerpickupplanet.controlador.servicio.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    /**
     * Crea un producto
     * */
    @PostMapping("/add")
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoService.add(producto);
    }

    /**
     * Recoge un producto por su ID
     * */
    @GetMapping("/get/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        Optional<Producto> producto = productoService.findById(id);
        return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Muestra todos los productos
     * */
    @GetMapping("/getAll")
    public List<Producto> obtenerTodosLosProductos() {
        return productoService.findAll();
    }

    /**
     * Edita un producto por su ID
     * */
    @PutMapping("/edit/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto productoActualizado) {
        Optional<Producto> productoExistente = productoService.findById(id);
        if (productoExistente.isPresent()) {
            productoActualizado.setId_producto(id);
            Producto productoActualizadoBD = productoService.save(productoActualizado);
            return ResponseEntity.ok(productoActualizadoBD);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina un producto por su ID
     * */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarProductoPorId(@PathVariable Long id) {
        Optional<Producto> producto = productoService.findById(id);
        if (producto.isPresent()) {
            productoService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

