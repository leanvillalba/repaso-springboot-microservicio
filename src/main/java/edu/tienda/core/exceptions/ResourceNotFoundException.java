package edu.tienda.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody //esta excepción al lanzarse desde algún controlador,
            // formará parte del cuerpo de respuesta de la petición.

@ResponseStatus(HttpStatus.NOT_FOUND) // Spring creará una “ResponseEntity” por nosotros al detectar
                                    //el lanzamiento de esta excepción.
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
