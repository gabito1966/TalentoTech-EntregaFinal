package ar.com.gg.proyectoIntegrador.HandlerException;

import ar.com.gg.proyectoIntegrador.dto.ExceptionResponseDTO;
import ar.com.gg.proyectoIntegrador.exception.TechlabException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(TechlabException.class)
    public ResponseEntity<ExceptionResponseDTO> techlabException(TechlabException e) {
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO();
        responseDTO.setTitle(e.getTitle());
        responseDTO.setMessage(e.getMessage());

        return ResponseEntity.status(e.getStatus()).body(responseDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> defaultHandler(Exception e) {
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO();
        responseDTO.setTitle("There was a problem with the server");
        responseDTO.setMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(responseDTO);
    }
}