package dist.lab2.server.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InsufficientBalance extends RuntimeException {
    public InsufficientBalance() {
        super("Insufficient Balance");
    }
}
