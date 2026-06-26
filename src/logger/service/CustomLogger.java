package logger.service;

import logger.entity.LogMessage;

public abstract class CustomLogger {
    public abstract void log(LogMessage message);
}
