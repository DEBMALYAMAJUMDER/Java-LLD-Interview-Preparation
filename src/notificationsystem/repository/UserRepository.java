package notificationsystem.repository;

import logger.decorator.ClassNameLogger;
import logger.decorator.TimestampLogger;
import logger.entity.LogLevel;
import logger.entity.LogMessage;
import logger.service.CustomLogger;
import logger.service.impl.ConsoleLogger;
import notificationsystem.entity.Channel;
import notificationsystem.entity.User;
import notificationsystem.exception.UserNotFoundException;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository {
    private static final UserRepository userRepository = new UserRepository();
    private ConcurrentHashMap<String, User> userMap = new ConcurrentHashMap<>();
    CustomLogger customLogger = new ClassNameLogger(this.getClass(), new TimestampLogger(new ConsoleLogger()));

    private UserRepository() {

    }

    public static UserRepository getUserRepository() {
        return userRepository;
    }

    public Set<Channel> getNotificationChannels(String userId) {
        User user = userMap.get(userId);
        if (user == null) {
            throw new UserNotFoundException("User Not Found For the UserId=" + userId);
        }
        return user.getNotificationChannels();
    }

    public User getUser(String userId) {
        User user = userMap.get(userId);
        if (user == null) {
            throw new UserNotFoundException("User Not Found For the UserId=" + userId);
        }
        return user;
    }

    public boolean removeChannel(String userId, Channel channel) {
        User user = userMap.get(userId);
        if (user == null) {
            throw new UserNotFoundException("User Not Found For the UserId=" + userId);
        }
        return user.deleteChannel(channel);
    }

    public boolean addNotificationChannel(Channel channel, String userId) {
        User user = userMap.get(userId);
        if (user == null) {
            throw new UserNotFoundException("User Not Found For the UserId=" + userId);
        }
        return user.addNotificationChannel(channel);
    }

    public void registerUser(User user) {
        userMap.put(user.getUserId(), user);
        customLogger.log(new LogMessage("User Added " + user, LogLevel.INFO));
    }

    public User removeUser(String userId) {
        User user = userMap.remove(userId);
        if (user == null) {
            throw new UserNotFoundException("User is Not Found in the System , userId=" + userId);
        }
        return user;
    }
}
