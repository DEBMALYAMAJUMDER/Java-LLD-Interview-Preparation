package notificationsystem.service.impl;

import notificationsystem.entity.Channel;
import notificationsystem.entity.User;
import notificationsystem.exception.ChannelListModificationFailureException;
import notificationsystem.repository.UserRepository;
import notificationsystem.service.UserService;

import java.util.Set;

public class UserServiceImpl implements UserService {
    private static final UserServiceImpl userServiceImpl = new UserServiceImpl();
    private final UserRepository userRepository = UserRepository.getUserRepository();

    private UserServiceImpl() {

    }

    public static UserServiceImpl getUserServiceImpl() {
        return userServiceImpl;
    }

    @Override
    public void registerUser(User user) {
        userRepository.registerUser(user);
    }

    @Override
    public User deleteUser(String userId) {
        return userRepository.removeUser(userId);
    }

    @Override
    public boolean addChannel(String userId, Channel channel) {
        boolean isAdded = userRepository.addNotificationChannel(channel, userId);
        if (!isAdded) {
            throw new ChannelListModificationFailureException("Failed To Add Channel for userId=" + userId + " channel=" + channel);
        }
        return true;
    }

    @Override
    public boolean deleteChannel(String userId, Channel channel) {
        boolean isDeleted = userRepository.removeChannel(userId, channel);
        if (!isDeleted) {
            throw new ChannelListModificationFailureException("Failed To Delete Channel for userId=" + userId + " channel=" + channel);
        }
        return true;
    }

    @Override
    public Set<Channel> getAllActiveChannels(String userId) {
        return userRepository.getNotificationChannels(userId);
    }

    @Override
    public User getUser(String userId) {
        return userRepository.getUser(userId);
    }
}
