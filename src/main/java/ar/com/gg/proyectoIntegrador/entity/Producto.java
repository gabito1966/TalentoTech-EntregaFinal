package ar.com.gg.proyectoIntegrador.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private double precio;
    private int stock;
    private int cantidadAComprar;

    // Constructor sin ID (el ID se genera automáticamente por la base de datos)
    public Producto(String nombre, double precio, int stock, int cantidadAComprar) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        if (cantidadAComprar < 0) {
            throw new IllegalArgumentException("La cantidad a comprar no puede ser negativa.");
        }
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.cantidadAComprar = cantidadAComprar;
    }


    public Producto() {}

    public Producto(long id, String nombre, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }


    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        }
    }

    public void setPrecio(double precio) {
        if (precio >= 0) {
            this.precio = precio;
        }
    }

    public void setStock(int stock) {
        if (stock >= 0) {
            this.stock = stock;
        }
    }

    public void setCantidadAComprar(int cantidadAComprar) {
        if (cantidadAComprar >= 0) {
            this.cantidadAComprar = cantidadAComprar;
        }
    }
}
