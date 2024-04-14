package wise.wisewomenhackathon.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(BalanceNotFoundException.class)
    public ResponseEntity<String> handleBalanceNotFoundException(BalanceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InsufficientFoundsException.class)
    public ResponseEntity<String> hanldeInsufficientFounds(InsufficientFoundsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(IncorrectBalanceTypeException.class)
    public ResponseEntity<String> incorrectBalanceType(IncorrectBalanceTypeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UsernameAlreadyExists.class)
    public ResponseEntity<String> usernameTaken(UsernameAlreadyExists ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(BalanceLimitReachedException.class)
    public ResponseEntity<String> balanceLimit(BalanceLimitReachedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(AuthorizationFlagException.class)
    public ResponseEntity<String> getAuthorizationFlag(AuthorizationFlagException ex) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ex.getMessage());
    }

}
