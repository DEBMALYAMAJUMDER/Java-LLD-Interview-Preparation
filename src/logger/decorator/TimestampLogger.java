package logger.decorator;

import logger.entity.LogMessage;
import logger.service.CustomLogger;


import java.time.LocalDateTime;


public class TimestampLogger extends CustomLogger {
    protected CustomLogger logger;

    public TimestampLogger(CustomLogger logger) {
        this.logger = logger;
    }

    @Override
    public void log(LogMessage message) {
        logger.log(new LogMessage("[ " + LocalDateTime.now() + " ] : " + message.getMessage(), message.getLevel()));
    }
}
