package notificationsystem;

import notificationsystem.entity.Channel;
import notificationsystem.entity.NotificationEntity;
import notificationsystem.entity.NotificationType;
import notificationsystem.entity.User;
import notificationsystem.service.NotificationService;
import notificationsystem.service.UserService;
import notificationsystem.service.impl.NotificationServiceImpl;
import notificationsystem.service.impl.UserServiceImpl;

import java.util.HashSet;
import java.util.Set;

public class NotificationMain {
    public static void main(String[] args) {
        // User 1 : Email only
        User user1 = new User.UserBuilder()
                .userId("U1")
                .userName("Debmalya")
                .emailId("deb@gmail.com")
                .notificationChannels(new HashSet<>(Set.of(Channel.EMAIL)))
                .build();

        // User 2 : SMS only
        User user2 = new User.UserBuilder()
                .userId("U2")
                .userName("Rahul")
                .mobileNo("9876543210")
                .notificationChannels(new HashSet<>(Set.of(Channel.SMS)))
                .build();

        // User 3 : Push only
        User user3 = new User.UserBuilder()
                .userId("U3")
                .userName("Aniket")
                .deviceToken("DEVICE-123")
                .notificationChannels(new HashSet<>(Set.of(Channel.SMS,Channel.PUSH)))
                .build();

        // User 4 : Email + SMS
        User user4 = new User.UserBuilder()
                .userId("U4")
                .userName("Amit")
                .emailId("amit@gmail.com")
                .mobileNo("9999999999")
                .notificationChannels(new HashSet<>(Set.of(
                        Channel.EMAIL,
                        Channel.SMS)))
                .build();

        // User 5 : Email + Push
        User user5 = new User.UserBuilder()
                .userId("U5")
                .userName("Rohit")
                .emailId("rohit@gmail.com")
                .deviceToken("DEVICE-555")
                .notificationChannels(new HashSet<>(Set.of(
                        Channel.EMAIL,
                        Channel.PUSH)))
                .build();

        // User 6 : All channels
        User user6 = new User.UserBuilder()
                .userId("U6")
                .userName("Sneha")
                .emailId("sneha@gmail.com")
                .mobileNo("8888888888")
                .deviceToken("DEVICE-777")
                .notificationChannels(new HashSet<>(Set.of(
                        Channel.EMAIL,
                        Channel.SMS,
                        Channel.PUSH)))
                .build();
        UserService userService = UserServiceImpl.getUserServiceImpl();
        userService.registerUser(user1);
        userService.registerUser(user2);
        userService.registerUser(user3);
        userService.registerUser(user4);
        userService.registerUser(user5);
        userService.registerUser(user6);
        NotificationService notificationService = NotificationServiceImpl.getNotificationImpl();
        notificationService.send(
                new NotificationEntity(
                        "N1",
                        "U1",
                        NotificationType.ORDER_DELIVERED,
                        "Order Delivered"
                )
        );
        notificationService.send(
                new NotificationEntity(
                        "N2",
                        "U2",
                        NotificationType.PAYMENT_SUCCESS,
                        "Payment Successful"
                )
        );
        try {
            notificationService.send(
                    new NotificationEntity(
                            "N2",
                            "U2",
                            NotificationType.OTP,
                            "Hello"
                    )
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            notificationService.send(
                    new NotificationEntity(
                            "N3",
                            "XYZ",
                            NotificationType.OTP,
                            "Hello"
                    )
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            notificationService.send(
                    new NotificationEntity(
                            "N4",
                            "U6",
                            NotificationType.OTP,
                            "OTP-1234"
                    )
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        /**
         * Revoking Channel for User6
         */
        userService.deleteChannel("U6",Channel.SMS);
        try {
            notificationService.send(
                    new NotificationEntity(
                            "N5",
                            "U6",
                            NotificationType.OTP,
                            "OTP-1234"
                    )
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        /**
         * Adding SMS again
         */
        userService.addChannel("U6",Channel.SMS);
        try {
            notificationService.send(
                    new NotificationEntity(
                            "N6",
                            "U6",
                            NotificationType.OTP,
                            "OTP-1234"
                    )
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            notificationService.send(
                    new NotificationEntity(
                            "N7",
                            "U3",
                            NotificationType.PROMOTION,
                            "PROMOTIONAL"
                    )
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
