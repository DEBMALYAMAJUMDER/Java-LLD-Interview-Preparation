package notificationsystem.service.impl;

import logger.decorator.ClassNameLogger;
import logger.decorator.TimestampLogger;
import logger.entity.LogLevel;
import logger.entity.LogMessage;
import logger.service.CustomLogger;
import logger.service.impl.ConsoleLogger;
import notificationsystem.entity.NotificationEntity;
import notificationsystem.entity.NotificationResponse;
import notificationsystem.entity.NotificationStatus;
import notificationsystem.exception.DuplicateNotificationException;
import notificationsystem.factory.NotificationChannelFactory;
import notificationsystem.repository.NotificationRepository;
import notificationsystem.service.NotificationService;
import notificationsystem.service.PublishNotificationService;
import notificationsystem.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository = NotificationRepository.getNotificationRepository();
    private final UserService userService = UserServiceImpl.getUserServiceImpl();
    private final CustomLogger customLogger = new TimestampLogger(new ClassNameLogger(this.getClass(), new ConsoleLogger()));
    private static final NotificationServiceImpl notificationImpl = new NotificationServiceImpl();

    private NotificationServiceImpl() {

    }

    public static NotificationServiceImpl getNotificationImpl() {
        return notificationImpl;
    }

    @Override
    public void send(NotificationEntity entity) {
        AtomicInteger counter = new AtomicInteger(0);
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        if (notificationRepository.isNotificationPresent(entity.getNotificationId())) {
            throw new DuplicateNotificationException("Notification Already present =" + entity);
        }
        NotificationResponse response = new NotificationResponse(entity.getNotificationId(), entity.getUserId(), LocalDate.now(), NotificationStatus.PENDING);
        var user = userService.getUser(entity.getUserId());
        var channels = user.getNotificationChannels();
        List<CompletableFuture<Void>> futures = channels.stream()
                .map(channel -> CompletableFuture.runAsync(() -> {
                    PublishNotificationService publishNotificationService = NotificationChannelFactory.getChannel(channel);
                    try {
                        publishNotificationService.doSend(user, entity);
                        counter.incrementAndGet();
                    } catch (Exception e) {
                        customLogger.log(new LogMessage("Exception :" + e.getMessage(), LogLevel.ERROR));
                    }
                }, executorService))
                .toList();
        CompletableFuture<Void> allOff = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        allOff.join();
        executorService.shutdown();
        if (counter.get() == channels.size()) {
            response.setStatus(NotificationStatus.SUCCESS);
        } else if (counter.get() > 0) {
            response.setStatus(NotificationStatus.PARTIAL_SUCCESS);
        } else {
            response.setStatus(NotificationStatus.FAILED);
        }
        response.setDate(LocalDate.now());
        notificationRepository.addNotificationData(response);
    }
}
