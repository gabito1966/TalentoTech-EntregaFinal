package ar.com.gg.proyectoIntegrador.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString
public class ProductResponseDTO {
    private Long id;

    @Size(max = 255, message = "La ruta de la imagen no puede tener más de 255 caracteres.")
    private String image;

    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres.")
    private String name;

    @Size(max = 500, message = "La descripción no puede tener más de 500 caracteres.")
    private String description;

    @Positive(message = "El precio debe ser positivo.")
    private double price;

    @Min(value = 0, message = "El stock no puede ser negativo.")
    private int stock;

    // Constructor adicional, si lo necesitas
    public ProductResponseDTO(String image, String name, String description, Double price, int stock) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
}
