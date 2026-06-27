package hotelbooking.exception;

public class BookingFailedException extends RuntimeException {
    public BookingFailedException(String msg) {
        super(msg);
    }
}
