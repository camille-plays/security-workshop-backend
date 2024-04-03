package wise.wisewomenhackathon.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import wise.wisewomenhackathon.controllers.BalanceController;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(BalanceController.BalanceNotFoundException.class)
    public ResponseEntity<String> handleBalanceNotFoundException(BalanceController.BalanceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
