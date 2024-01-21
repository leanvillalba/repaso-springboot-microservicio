package edu.tienda.core.controllers;

import edu.tienda.core.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice // Indica que esta clase estará destinada a manejar las respuestas de
                // los servicios REST para las excepciones requeridas.
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class) // Especifica que el método handleBadRequestException
                                                // manejará las excepciones del tipo BadRequestException.
    protected ResponseEntity<Object> handleBadResquest(
            RuntimeException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("Error", HttpStatus.BAD_REQUEST.toString());

        /*
        * Retornamos un “ResponseEntity” con código de status 400 y un pequeño body
        * de respuesta que se traducirá en un JSON conteniendo algunos datos útiles. Entre
        ellos, el más destacado es el error de la excepcion que nosotros generamos a medida*/
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
