package notificationsystem.entity;

public class NotificationEntity {
    private String notificationId;
    private String userId;
    private NotificationType notificationType;
    private String message;

    public NotificationEntity(String notificationId, String userId, NotificationType notificationType, String message) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.notificationType = notificationType;
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "NotificationEntity{" +
                "notificationId='" + notificationId + '\'' +
                ", userId='" + userId + '\'' +
                ", notificationType=" + notificationType +
                ", message='" + message + '\'' +
                '}';
    }
}
