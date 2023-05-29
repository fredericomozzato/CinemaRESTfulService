package cinema.exceptionhandling;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(String cause) {
        super(cause);
    }
}
