package notificationsystem.service;

import notificationsystem.entity.NotificationEntity;
import notificationsystem.entity.User;

public abstract class PublishNotificationService {
    protected static final Integer DEFAULT_RETRY_COUNT = 3;

    public void doSend(User user, NotificationEntity entity) {
        validateUser(user);
        sendNotification(user, entity);
    }

    public abstract void validateUser(User user);

    public abstract void sendNotification(User user, NotificationEntity entity);
}
