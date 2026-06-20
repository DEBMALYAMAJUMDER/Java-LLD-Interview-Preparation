package vendingmachine.exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String expMsg) {
        super(expMsg);
    }
}
