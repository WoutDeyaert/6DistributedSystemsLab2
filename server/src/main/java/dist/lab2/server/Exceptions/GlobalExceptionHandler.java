package dist.lab2.server.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorBody> handleNotFound(AccountNotFoundException ex) {
        ErrorBody error = new ErrorBody( ex.getMessage(),404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorBody> handleNInsufficientBalance(InsufficientBalanceException ex) {
        ErrorBody error = new ErrorBody( ex.getMessage(),400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }



}
