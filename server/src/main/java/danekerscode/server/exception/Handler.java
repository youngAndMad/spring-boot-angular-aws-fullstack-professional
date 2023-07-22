package danekerscode.server.exception;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.function.BiFunction;
import java.util.function.Function;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(EmailRegisteredException.class)
    ResponseEntity<?> handle(EmailRegisteredException e){
        return response.apply(HttpStatus.BAD_REQUEST,e);
    }

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<?> handle(UserNotFoundException e){
        return response.apply(HttpStatus.BAD_REQUEST,e);
    }

    private final BiFunction<HttpStatus,RuntimeException,ResponseEntity<?>> response = ((status, e) -> new ResponseEntity<>(e.getMessage(),status));
}
