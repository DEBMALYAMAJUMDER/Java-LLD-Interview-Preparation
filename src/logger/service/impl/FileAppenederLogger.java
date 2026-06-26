package logger.service.impl;

import logger.entity.LogMessage;
import logger.service.CustomLogger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileAppenederLogger extends CustomLogger {

    @Override
    public void log(LogMessage message) {
        try {
            Files.writeString(
                    Path.of("output.txt"),
                    message.getMessage() + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
