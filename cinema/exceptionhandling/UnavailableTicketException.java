package cinema.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UnavailableTicketException extends RuntimeException {
    public UnavailableTicketException(String cause) {
        super(cause);
    }
}
