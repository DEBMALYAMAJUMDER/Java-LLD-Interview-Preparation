package vendingmachine.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String expMsg) {
        super(expMsg);
    }
}
