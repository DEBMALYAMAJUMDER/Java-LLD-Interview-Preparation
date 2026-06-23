package atm.exception;

public class CardAuthenticationException extends RuntimeException {
    public CardAuthenticationException(String msg) {
        super(msg);
    }
}
