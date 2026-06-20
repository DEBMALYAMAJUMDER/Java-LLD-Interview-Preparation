package vendingmachine.exception;

public class InsufficientProductException extends RuntimeException {
    public InsufficientProductException(String expMsg) {
        super(expMsg);
    }
}
