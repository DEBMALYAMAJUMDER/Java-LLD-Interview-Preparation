package logger;


import logger.decorator.ClassNameLogger;
import logger.decorator.LogLevelLogger;
import logger.decorator.TimestampLogger;
import logger.entity.LogLevel;
import logger.entity.LogMessage;
import logger.service.CustomLogger;
import logger.service.impl.ConsoleLogger;

import java.util.logging.Logger;

public class LoggerMain {
    public static void main(String[] args) {
        LogMessage message = new LogMessage("Good Morning", LogLevel.INFO);
        CustomLogger logger = new TimestampLogger(new ConsoleLogger());
        logger.log(message);
        CustomLogger logger1 = new ClassNameLogger(LoggerMain.class,logger);
        LogMessage message1 = new LogMessage("Good Morning", LogLevel.INFO);
        logger1.log(message1);

        CustomLogger logger2 = new ClassNameLogger(LoggerMain.class,new LogLevelLogger(LogLevel.DEBUG,logger));
        LogMessage message2 = new LogMessage("Good Morning", LogLevel.DEBUG);
        logger2.log(message2);
        CustomLogger customLogger =
                new LogLevelLogger(
                        LogLevel.INFO,
                        new TimestampLogger(
                                new ClassNameLogger(
                                        LoggerMain.class,
                                        new ConsoleLogger()
                                )
                        )
                );

        customLogger.log(new LogMessage("Application Started", LogLevel.INFO));

    }
}
