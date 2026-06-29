package notificationsystem.service;

import notificationsystem.entity.NotificationEntity;
import notificationsystem.entity.User;

public interface NotificationService {
    void send(NotificationEntity entity);
}
