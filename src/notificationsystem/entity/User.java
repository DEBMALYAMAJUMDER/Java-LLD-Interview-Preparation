package notificationsystem.entity;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String userId;
    private String userName;
    private String mobileNo;
    private String emailId;
    private String deviceToken;
    private Set<Channel> notificationChannels;

    public User(UserBuilder userBuilder) {
        this.userId = userBuilder.userId;
        this.userName = userBuilder.userName;
        this.emailId = userBuilder.emailId;
        this.mobileNo = userBuilder.mobileNo;
        this.deviceToken = userBuilder.deviceToken;
        this.notificationChannels = userBuilder.notificationChannels == null ? new HashSet<>() : userBuilder.notificationChannels;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public Set<Channel> getNotificationChannels() {
        return notificationChannels;
    }

    public boolean addNotificationChannel(Channel channel) {
        return notificationChannels.add(channel);
    }

    public boolean deleteChannel(Channel channel) {
        return notificationChannels.remove(channel);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", emailId='" + emailId + '\'' +
                ", deviceToken='" + deviceToken + '\'' +
                ", notificationChannels=" + notificationChannels +
                '}';
    }

    public static class UserBuilder {
        private String userId;
        private String userName;
        private String mobileNo;
        private String emailId;
        private String deviceToken;
        private Set<Channel> notificationChannels;

        public UserBuilder notificationChannels(Set<Channel> notificationChannels) {
            this.notificationChannels = notificationChannels;
            return this;
        }

        public UserBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public UserBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public UserBuilder mobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
            return this;
        }

        public UserBuilder emailId(String emailId) {
            this.emailId = emailId;
            return this;
        }

        public UserBuilder deviceToken(String deviceToken) {
            this.deviceToken = deviceToken;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
