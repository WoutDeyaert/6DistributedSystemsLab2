package dist.lab2.server.Exceptions;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ErrorBody {
    private int status;
    private LocalDateTime localDate;
    private String message;

    public ErrorBody(String message, int status) {
        this.localDate = LocalDateTime.now();
        this.message = message;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getLocalDate() {
        return localDate;
    }

    public String getMessage() {
        return message;
    }
}
