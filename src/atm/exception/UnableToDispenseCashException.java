package atm.exception;

public class UnableToDispenseCashException extends RuntimeException {
    public UnableToDispenseCashException(String msg) {
        super(msg);
    }
}
