package atm.exception;

public class InvalidCardDetailsException extends RuntimeException {
    public InvalidCardDetailsException(String msg) {
        super(msg);
    }
}
