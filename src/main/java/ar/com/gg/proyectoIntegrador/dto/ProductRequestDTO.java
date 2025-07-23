package ar.com.gg.proyectoIntegrador.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductRequestDTO {

    // Atributo para la ruta de la imagen
    @Size(max = 255, message = "La ruta de la imagen no puede tener más de 255 caracteres.")
    private MultipartFile image;

    // Atributo para el nombre del producto
    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres.")
    private String name;

    // Atributo para la descripción del producto
    @Size(max = 500, message = "La descripción no puede tener más de 500 caracteres.")
    private String description;

    // Atributo para el precio del producto
    @NotNull(message = "El precio no puede ser nulo.")
    @Positive(message = "El precio debe ser positivo.")
    private double price;

    // Atributo para el stock del producto
    @NotNull(message = "El stock no puede ser nulo.")
    @Min(value = 0, message = "El stock no puede ser negativo.")
    private int stock;
}
