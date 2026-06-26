package logger.entity;

public enum LogLevel {
    INFO(0),
    DEBUG(1),
    WARN(2),
    ERROR(3);
    private int priority;

    LogLevel(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
