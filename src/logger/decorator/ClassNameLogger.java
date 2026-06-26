package logger.decorator;

import logger.entity.LogMessage;
import logger.service.CustomLogger;

public class ClassNameLogger extends CustomLogger {
    protected Class<?> cl;
    protected CustomLogger logger;

    public ClassNameLogger(Class<?> cl, CustomLogger logger) {
        this.cl = cl;
        this.logger = logger;
    }

    @Override
    public void log(LogMessage message) {
        logger.log(new LogMessage(cl.getName() + " | " + message.getMessage(),message.getLevel()));
    }
}
