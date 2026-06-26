package logger.decorator;

import logger.entity.LogLevel;
import logger.entity.LogMessage;
import logger.service.CustomLogger;

public class LogLevelLogger extends CustomLogger {
    protected LogLevel logLevel;
    protected CustomLogger logger;

    public LogLevelLogger(LogLevel logLevel, CustomLogger logger) {
        this.logLevel = logLevel;
        this.logger = logger;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    @Override
    public void log(LogMessage message) {
        if (logLevel.getPriority() <= message.getLevel().getPriority()) {
            logger.log(new LogMessage(" [" + message.getLevel() + "] " + message.getMessage(), message.getLevel()));
        }
    }
}
