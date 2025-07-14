package ar.com.gg.proyectoIntegrador.entity;

public class ProductoPedido {
    private final long id;         // ID único para el producto en el pedido
    private final String nombre;
    private final double precio;
    private final int cantidad;

    // Constructor
    public ProductoPedido(long id, String nombre, double precio, int cantidad) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    // Método para obtener el total
    public double getTotal() {
        return precio * cantidad;
    }

    // Método toString para una mejor representación del objeto
    @Override
    public String toString() {
        return "ProductoPedido{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                ", total=" + getTotal() +
                '}';
    }

    // Método equals para comparar dos productos de un pedido
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProductoPedido that = (ProductoPedido) obj;
        return id == that.id;
    }

    // Método hashCode para asegurar que los objetos se manejen correctamente en colecciones como HashSet
    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}
