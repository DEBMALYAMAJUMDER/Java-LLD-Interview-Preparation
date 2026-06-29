package notificationsystem.service.impl;

import logger.decorator.ClassNameLogger;
import logger.decorator.TimestampLogger;
import logger.entity.LogLevel;
import logger.entity.LogMessage;
import logger.service.CustomLogger;
import logger.service.impl.ConsoleLogger;
import notificationsystem.entity.NotificationEntity;
import notificationsystem.entity.User;
import notificationsystem.exception.PushNotificationException;
import notificationsystem.service.PublishNotificationService;

public class PushNotificationServiceImpl extends PublishNotificationService {
    private final CustomLogger customLogger = new TimestampLogger(new ClassNameLogger(this.getClass(), new ConsoleLogger()));
    private static final PushNotificationServiceImpl pushNotification = new PushNotificationServiceImpl();

    private PushNotificationServiceImpl() {

    }

    public static PushNotificationServiceImpl getPushNotification() {
        return pushNotification;
    }

    @Override
    public void validateUser(User user) {
        if (user.getDeviceToken() == null) {
            throw new PushNotificationException("Device Token Not Found,Invalid User");
        }
    }

    @Override
    public void sendNotification(User user, NotificationEntity entity) {
        int retryCnt = DEFAULT_RETRY_COUNT;
        /**
         * Currently Implemented Manual Retry When we Implemented Kafka then spring retry will be Implemented
         */
        while (retryCnt > 0) {
            try {
                LogMessage logMessage = new LogMessage("Sending Push Notification " + entity.getMessage() + " Device Token : " + user.getDeviceToken(), LogLevel.INFO);
                customLogger.log(logMessage);
                return;
            } catch (Exception e) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    throw new PushNotificationException("Failed to send Push Notification Exception");
                }
                retryCnt--;
                LogMessage logMessage = new LogMessage("Sending Push Notification ,Retry Attempt Left : " + retryCnt, LogLevel.INFO);
                customLogger.log(logMessage);
            }
        }
        throw new PushNotificationException("Push Notification Failed After all retries");
    }
}
