package ar.com.gg.proyectoIntegrador.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {
    private Long id;
    private String image;
    private String name;
    private String description;
    private double price;
    private int stock;
}
