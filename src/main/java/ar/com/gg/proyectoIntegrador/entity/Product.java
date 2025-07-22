package ar.com.gg.proyectoIntegrador.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255, message = "La ruta de la imagen no puede tener más de 255 caracteres.")
    private String image;

    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres.")
    private String name;

    @Size(max = 500, message = "La descripción no puede tener más de 500 caracteres.")
    private String description;

    @Min(value = 0, message = "El precio no puede ser negativo.")
    private double price;

    @Min(value = 0, message = "El stock no puede ser negativo.")
    private int stock;

    // Constructor principal
    public Product(String image, String name, String description, double price, int stock) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    // Constructor vacío
    public Product() {}

    // Método para buscar productos por nombre (mejor hacerlo a nivel de servicio/repo)
    public boolean contieneNombre(String busqueda){
        String nombreMinuscula = this.name.toLowerCase();
        return nombreMinuscula.contains(busqueda.toLowerCase());
    }

    // Método toString para depuración
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", nombre='" + name + '\'' +
                ", description='" + description + '\'' +
                ", precio=" + price +
                ", stock=" + stock +
                '}';
    }
}
