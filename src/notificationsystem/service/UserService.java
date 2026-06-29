package notificationsystem.service;

import notificationsystem.entity.Channel;
import notificationsystem.entity.User;

import java.util.Set;

public interface UserService {
    void registerUser(User user);

    User deleteUser(String userId);

    boolean addChannel(String userId, Channel channel);

    boolean deleteChannel(String userId, Channel channel);

    Set<Channel> getAllActiveChannels(String userId);

    User getUser(String userId);
}
