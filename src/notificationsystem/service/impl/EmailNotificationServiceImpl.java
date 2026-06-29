package notificationsystem.service.impl;

import logger.decorator.ClassNameLogger;
import logger.decorator.TimestampLogger;
import logger.entity.LogLevel;
import logger.entity.LogMessage;
import logger.service.CustomLogger;
import logger.service.impl.ConsoleLogger;
import notificationsystem.entity.NotificationEntity;
import notificationsystem.entity.User;
import notificationsystem.exception.EmailSendNotificationException;
import notificationsystem.exception.SMSSendNotificationException;
import notificationsystem.service.PublishNotificationService;

public class EmailNotificationServiceImpl extends PublishNotificationService {
    private final CustomLogger customLogger = new TimestampLogger(new ClassNameLogger(this.getClass(), new ConsoleLogger()));
    private static final EmailNotificationServiceImpl emailNotification = new EmailNotificationServiceImpl();

    private EmailNotificationServiceImpl() {

    }

    public static EmailNotificationServiceImpl getEmailNotification() {
        return emailNotification;
    }

    @Override
    public void validateUser(User user) {
        if (user.getEmailId() == null) {
            throw new EmailSendNotificationException("Email Id Not Found,Invalid User");
        }
    }

    @Override
    public void sendNotification(User user, NotificationEntity entity) {
        int retryCnt = DEFAULT_RETRY_COUNT;
        /**
         * Currently Implemented Manual Retry When we Implemented Java-Email then spring retry will be Implemented
         */
        while (retryCnt > 0) {
            try {
                LogMessage logMessage = new LogMessage("Sending Email Notification " + entity.getMessage() + " Email : " + user.getEmailId(), LogLevel.INFO);
                customLogger.log(logMessage);
                return;
            } catch (Exception e) {
                try {
                    Thread.sleep(2000L * (5 - retryCnt));
                } catch (InterruptedException ex) {
                    throw new EmailSendNotificationException("Failed to send Email Notification");
                }
                retryCnt--;
                LogMessage logMessage = new LogMessage("Sending Email Notification ,Retry Attempt Left : " + retryCnt, LogLevel.INFO);
                customLogger.log(logMessage);

            }
        }
        throw new EmailSendNotificationException("Email Send Failed After All retries");
    }
}
