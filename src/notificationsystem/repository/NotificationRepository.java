package notificationsystem.repository;

import notificationsystem.entity.NotificationEntity;
import notificationsystem.entity.NotificationResponse;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class NotificationRepository {
    private ConcurrentHashMap<String, NotificationResponse> notificationMap = new ConcurrentHashMap<>();
    private static final NotificationRepository notificationRepository = new NotificationRepository();

    private NotificationRepository() {

    }

    public static NotificationRepository getNotificationRepository() {
        return notificationRepository;
    }

    public void addNotificationData(NotificationResponse response) {
        notificationMap.put(response.getNotificationId(), response);
        System.out.println("Adding Notification Entity : " + notificationMap);
    }

    public boolean isNotificationPresent(String notificationId) {
        return notificationMap.containsKey(notificationId);
    }
}
