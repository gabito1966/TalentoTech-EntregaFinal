package ar.com.gg.proyectoIntegrador.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String searchTerm) {
        super(String.format("No se encontro ningun producto. se busco usando el siguiente termino: %s", searchTerm));
    }
}

