package cinema.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NonExistingSeatException extends RuntimeException{
    public NonExistingSeatException(String cause) {
        super(cause);
    }
}
