package ar.com.gg.proyectoIntegrador.controller;

import ar.com.gg.proyectoIntegrador.entity.Producto;
import ar.com.gg.proyectoIntegrador.servicios.GestorProductos;
import ar.com.gg.proyectoIntegrador.excepciones.ProductoNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private GestorProductos gestorProductos;

    // Crear un nuevo producto
    @PostMapping
    public Producto agregarProducto(@RequestBody Producto producto) {
        return gestorProductos.agregarProducto(producto);
    }

    // Listar todos los productos
    @GetMapping
    public List<Producto> listarProductos() {
        return gestorProductos.listarProductos();
    }

    // Modificar un producto
    @PutMapping("/{id}")
    public ResponseEntity<Producto> modificarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        try {
            Producto actualizado = gestorProductos.modificarProducto(id, producto.getNombre(), producto.getPrecio(), producto.getStock());
            return ResponseEntity.ok(actualizado);
        } catch (ProductoNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable long id) {
        try {
            gestorProductos.eliminarProducto(id);
            return ResponseEntity.noContent().build();
        } catch (ProductoNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Integer id) {
        try {
            // Ahora obtener el producto directamente
            Producto producto = gestorProductos.buscarPorId(id);
            return ResponseEntity.ok(producto);
        } catch (ProductoNoEncontradoException e) {
            // En caso de no encontrar el producto, respondemos con 404
            return ResponseEntity.notFound().build();
        }
    }


    // Filtrar productos por nombre o precio
    @GetMapping("/filtrar")
    public List<Producto> filtrarPorNombre(@RequestParam(required = false) String nombre,
                                           @RequestParam(required = false) Double precioMin,
                                           @RequestParam(required = false) Double precioMax) {
        if (nombre != null) {
            return gestorProductos.filtrarPorNombre(nombre);
        } else if (precioMin != null && precioMax != null) {
            return gestorProductos.filtrarPorPrecio(precioMin, precioMax);
        } else {
            return gestorProductos.listarProductos();
        }
    }
}
