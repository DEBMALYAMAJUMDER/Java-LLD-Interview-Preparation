package notificationsystem.factory;

import notificationsystem.entity.Channel;
import notificationsystem.entity.NotificationType;
import notificationsystem.exception.UnSupportedChannelException;
import notificationsystem.service.PublishNotificationService;
import notificationsystem.service.impl.EmailNotificationServiceImpl;
import notificationsystem.service.impl.PushNotificationServiceImpl;
import notificationsystem.service.impl.SMSNotificationServiceImpl;

public class NotificationChannelFactory {
    public static PublishNotificationService getChannel(Channel channel) {
        return switch (channel) {
            case EMAIL -> EmailNotificationServiceImpl.getEmailNotification();
            case SMS -> SMSNotificationServiceImpl.getSmsNotification();
            case PUSH -> PushNotificationServiceImpl.getPushNotification();
            default -> throw new UnSupportedChannelException("Channel is Not Supported");
        };
    }
}
