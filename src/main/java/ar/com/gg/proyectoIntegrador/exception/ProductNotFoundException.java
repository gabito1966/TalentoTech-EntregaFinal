package ar.com.gg.proyectoIntegrador.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class ProductNotFoundException extends RuntimeException {
        public ProductNotFoundException(String product) {
            super("Producto no encontrado: " + product);
        }
    }


