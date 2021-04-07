package guru.springframework.msscbeerservice.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException ex){
        final Set<ConstraintViolation<?>> lstViolations = ex.getConstraintViolations();

        final List<String> errorsList = new ArrayList<>(lstViolations.size());
        lstViolations.forEach(error -> errorsList.add(error.toString()));

        return new ResponseEntity<>(errorsList, HttpStatus.BAD_REQUEST);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List>  handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(fieldName + ": " +errorMessage);
        });
        return new ResponseEntity<List>(errors, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleBeerNotFoundException(final NotFoundException beerNotFoundException) {
        return new ResponseEntity<String>(beerNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

}
