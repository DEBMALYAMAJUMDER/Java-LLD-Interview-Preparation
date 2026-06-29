package notificationsystem.exception;

public class DuplicateNotificationException extends RuntimeException {
    public DuplicateNotificationException(String msg) {
        super(msg);
    }
}
