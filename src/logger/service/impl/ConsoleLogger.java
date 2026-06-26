package logger.service.impl;


import logger.entity.LogMessage;
import logger.service.CustomLogger;

public class ConsoleLogger extends CustomLogger {
    @Override
    public void log(LogMessage message) {
        System.out.println(message.getMessage());
    }
}
