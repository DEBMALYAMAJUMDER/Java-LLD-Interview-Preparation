package ratelimitter.exception;

public class TooManyRequestsException extends RuntimeException {
    private String errCode;

    public TooManyRequestsException(String errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
    }
}
