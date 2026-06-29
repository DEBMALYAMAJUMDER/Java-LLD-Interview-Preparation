package notificationsystem.entity;

public enum NotificationType {
    OTP(1),
    ORDER_PLACED(2),
    ORDER_DELIVERED(2),
    PAYMENT_SUCCESS(2),
    PASSWORD_RESET(1),
    PROMOTION(3);

    private int priority;

    NotificationType(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
