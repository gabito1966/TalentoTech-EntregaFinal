package ar.com.gg.proyectoIntegrador.servicios;

import ar.com.gg.proyectoIntegrador.excepciones.ProductoNoEncontradoException;
import ar.com.gg.proyectoIntegrador.entity.Producto;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GestorProductos {

    private final Map<Long, Producto> productos = new HashMap<>();
    private long idActual = 1;

    public GestorProductos() {
        cargarProductosIniciales();
    }

    private void cargarProductosIniciales() {
        agregarProductoInicial("Monitor LED 24", 1200.00, 15);
        agregarProductoInicial("Teclado mecánico RGB", 450.00, 25);
        agregarProductoInicial("Mouse inalámbrico", 300.00, 30);
        agregarProductoInicial("Memoria RAM 8GB DDR4", 350.00, 20);
        agregarProductoInicial("Disco SSD 500GB", 700.00, 18);
        agregarProductoInicial("Placa madre ATX", 950.00, 12);
        agregarProductoInicial("Procesador Intel i5", 1300.00, 10);
        agregarProductoInicial("Fuente de poder 600W", 600.00, 14);
        agregarProductoInicial("Gabinete con RGB", 800.00, 10);
        agregarProductoInicial("Cooler CPU", 250.00, 22);
        agregarProductoInicial("Disco duro 1TB", 500.00, 16);
        agregarProductoInicial("Webcam HD", 200.00, 30);
        agregarProductoInicial("Parlantes estéreo", 150.00, 28);
        agregarProductoInicial("Impresora multifunción", 900.00, 8);
        agregarProductoInicial("Router Wi-Fi", 350.00, 20);
        agregarProductoInicial("Adaptador USB Wi-Fi", 180.00, 35);
        agregarProductoInicial("Cable HDMI 2m", 50.00, 50);
        agregarProductoInicial("Mouse pad gamer", 120.00, 40);
        agregarProductoInicial("Hub USB 4 puertos", 100.00, 25);
        agregarProductoInicial("Kit limpieza para PC", 180.00, 12);
        agregarProductoInicial("Monitor LED 27", 1600.00, 10);
        agregarProductoInicial("Teclado inalámbrico", 400.00, 18);
        agregarProductoInicial("Mouse gamer con DPI ajustable", 380.00, 20);
        agregarProductoInicial("Memoria RAM 16GB DDR4", 650.00, 15);
        agregarProductoInicial("Disco SSD 1TB", 1200.00, 10);
        agregarProductoInicial("Placa madre microATX", 850.00, 14);
        agregarProductoInicial("Procesador AMD Ryzen 5", 1250.00, 12);
        agregarProductoInicial("Fuente de poder 750W", 750.00, 8);
        agregarProductoInicial("Gabinete ATX con ventiladores", 950.00, 7);
        agregarProductoInicial("Cooler líquido AIO", 1100.00, 5);
        agregarProductoInicial("Disco duro externo 2TB", 1000.00, 9);
        agregarProductoInicial("Webcam 4K", 450.00, 12);
        agregarProductoInicial("Parlantes Bluetooth", 300.00, 20);
        agregarProductoInicial("Impresora láser", 1500.00, 6);
        agregarProductoInicial("Switch de red 8 puertos", 400.00, 10);
        agregarProductoInicial("Adaptador Bluetooth USB", 120.00, 25);
        agregarProductoInicial("Cable DisplayPort 1.5m", 70.00, 30);
        agregarProductoInicial("Soporte para monitor", 220.00, 15);
        agregarProductoInicial("Enfriador para laptop", 200.00, 18);
        agregarProductoInicial("Tira LED RGB para gabinete", 90.00, 25);
        agregarProductoInicial("Micrófono condensador", 700.00, 10);
        agregarProductoInicial("Auriculares gamer", 650.00, 17);
        agregarProductoInicial("Batería UPS 1000VA", 1700.00, 4);
        agregarProductoInicial("Tableta digitalizadora", 800.00, 6);
        agregarProductoInicial("Tarjeta gráfica NVIDIA GTX 1660", 2500.00, 5);
        agregarProductoInicial("Tarjeta de sonido externa", 300.00, 8);
        agregarProductoInicial("Control para videojuegos", 450.00, 13);
        agregarProductoInicial("Silla gamer ergonómica", 2200.00, 3);
        agregarProductoInicial("Licencia Windows 11 Pro", 600.00, 30);
        agregarProductoInicial("Licencia Office", 500.00, 25);
    }

    private void agregarProductoInicial(String nombre, double precio, int stock) {
        Producto producto = new Producto(idActual++, nombre, precio, stock);
        productos.put(producto.getId(), producto);
    }

    public Producto agregarProducto(Producto producto) {
        validarProducto(producto);
        producto.setId(idActual++);
        productos.put(producto.getId(), producto);
        return producto;
    }

    private void validarProducto(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede ser nulo o vacío.");
        }
        if (producto.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio del producto no puede ser negativo.");
        }
        if (producto.getStock() < 0) {
            throw new IllegalArgumentException("El stock del producto no puede ser negativo.");
        }
    }

    public List<Producto> listarProductos() {
        return new ArrayList<>(productos.values());
    }

    public Producto buscarPorId(long id) throws ProductoNoEncontradoException {
        Producto producto = productos.get(id);
        if (producto == null) {
            throw new ProductoNoEncontradoException("Producto con ID " + id + " no encontrado.");
        }
        return producto;
    }

    public boolean eliminarProducto(long id) {
        return productos.remove(id) != null;
    }

    public Producto modificarProducto(long id, String nuevoNombre, double nuevoPrecio, int nuevoStock) throws ProductoNoEncontradoException {
        // Llamada directa al método buscarPorId que ahora devuelve un Producto directamente o lanza la excepción
        Producto producto = buscarPorId(id);

        // Realiza las modificaciones solo si son válidas
        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
            producto.setNombre(nuevoNombre);
        }
        if (nuevoPrecio >= 0) {
            producto.setPrecio(nuevoPrecio);
        }
        if (nuevoStock >= 0) {
            producto.setStock(nuevoStock);
        }

        return producto;
    }
    public List<Producto> filtrarPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return listarProductos();
        }
        return productos.values().stream()
                .filter(p -> p.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Producto> filtrarPorPrecio(Double precioMin, Double precioMax) {
        return productos.values().stream()
                .filter(p -> (precioMin == null || p.getPrecio() >= precioMin) &&
                        (precioMax == null || p.getPrecio() <= precioMax))
                .collect(Collectors.toList());
    }
}
