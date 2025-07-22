package ar.com.gg.proyectoIntegrador.HandlerException;

import ar.com.gg.proyectoIntegrador.dto.ExceptionResponseDTO;
import ar.com.gg.proyectoIntegrador.exception.ProductNotFoundException;
import ar.com.gg.proyectoIntegrador.exception.TechlabException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerException {

    private static final Logger logger = LoggerFactory.getLogger(HandlerException.class);

    // Manejo de excepciones específicas
    @ExceptionHandler(TechlabException.class)
    public ResponseEntity<ExceptionResponseDTO> techlabException(TechlabException e) {
        logger.error("TechlabException: {}", e.getMessage(), e);  // Logging de la excepción
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO();
        responseDTO.setTitle(e.getTitle());
        responseDTO.setMessage(e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(responseDTO);
    }

    // Manejo de ProductNotFoundException
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> productNotFoundException(ProductNotFoundException e) {
        logger.error("ProductNotFoundException: {}", e.getMessage(), e);  // Logging de la excepción
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO();
        responseDTO.setTitle("Product Not Found");
        responseDTO.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    // Manejo de excepciones generales
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> defaultHandler(Exception e) {
        logger.error("General Exception: {}", e.getMessage(), e);  // Logging de la excepción
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO();
        responseDTO.setTitle("There was a problem with the server");
        responseDTO.setMessage("An unexpected error occurred. Please try again later.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
    }
}
