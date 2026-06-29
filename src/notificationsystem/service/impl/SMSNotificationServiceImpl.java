package notificationsystem.service.impl;

import logger.decorator.ClassNameLogger;
import logger.decorator.TimestampLogger;
import logger.entity.LogLevel;
import logger.entity.LogMessage;
import logger.service.CustomLogger;
import logger.service.impl.ConsoleLogger;
import notificationsystem.entity.NotificationEntity;
import notificationsystem.entity.User;
import notificationsystem.exception.SMSSendNotificationException;
import notificationsystem.service.PublishNotificationService;

public class SMSNotificationServiceImpl extends PublishNotificationService {
    private final CustomLogger customLogger = new TimestampLogger(new ClassNameLogger(this.getClass(), new ConsoleLogger()));
    private static final SMSNotificationServiceImpl smsNotification = new SMSNotificationServiceImpl();

    private SMSNotificationServiceImpl() {

    }

    public static SMSNotificationServiceImpl getSmsNotification() {
        return smsNotification;
    }

    @Override
    public void validateUser(User user) {
        if (user.getMobileNo() == null) {
            throw new SMSSendNotificationException("Mobile No is Not Present,Invalid user");
        }
    }

    @Override
    public void sendNotification(User user, NotificationEntity entity) {
        int retryCnt = DEFAULT_RETRY_COUNT;
        /**
         * Currently Implemented Manual Retry When we Implemented Twilio then spring retry will be Implemented
         */
        while (retryCnt > 0) {
            try {
                LogMessage logMessage = new LogMessage("Sending SMS Notification " + entity.getMessage() + " Contact : " + user.getMobileNo(), LogLevel.INFO);
                customLogger.log(logMessage);
                return;
            } catch (Exception e) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    throw new SMSSendNotificationException("Failed To Send SMS Notification");
                }
                retryCnt--;
                LogMessage logMessage = new LogMessage("Sending SMS Notification ,Retry Attempt Left : " + retryCnt, LogLevel.INFO);
                customLogger.log(logMessage);
            }
        }
        throw new SMSSendNotificationException("SMS Send Failed After All Retry");
    }
}
