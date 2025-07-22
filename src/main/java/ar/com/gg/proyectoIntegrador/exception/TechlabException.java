package ar.com.gg.proyectoIntegrador.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class TechlabException extends RuntimeException{
    protected String title;
    protected HttpStatus status;

    public TechlabException(String title, String message, HttpStatus status){
        super(message);
        this.title = title;
        this.status = status;
    }
}