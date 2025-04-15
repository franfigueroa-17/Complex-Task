package utils.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerSingleton {
    private static Logger logger = null;

    private LoggerSingleton() {}

    public static Logger getLogger(Class<?> clazz) {
        if (logger == null) {
            logger = LogManager.getLogger(clazz);
        }
        return logger;
    }
}
