package notificationsystem.entity;

import java.time.LocalDate;
import java.util.Date;

public class NotificationResponse {
    private String notificationId;
    private String userId;
    private LocalDate date;
    private NotificationStatus status;

    public NotificationResponse(String notificationId, String userId, LocalDate date, NotificationStatus status) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.date = date;
        this.status = status;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "NotificationResponse{" +
                "notificationId='" + notificationId + '\'' +
                ", userId='" + userId + '\'' +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}
