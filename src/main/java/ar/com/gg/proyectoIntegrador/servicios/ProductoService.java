package ar.com.gg.proyectoIntegrador.servicios;

import ar.com.gg.proyectoIntegrador.entity.Producto;
import ar.com.gg.proyectoIntegrador.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }

    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }

    public Producto actualizar(Long id, Producto datosActualizados) {
        return productoRepository.findById(id).map(producto -> {
            producto.setNombre(datosActualizados.getNombre());
            producto.setPrecio(datosActualizados.getPrecio());
            producto.setStock(datosActualizados.getStock());
            return productoRepository.save(producto);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }
}
