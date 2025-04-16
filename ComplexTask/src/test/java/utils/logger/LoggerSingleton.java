package utils.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerSingleton {
    private LoggerSingleton() {}

    // LoggerFactory.getLogger() ya gestiona un caché de instancias de logger internamente,
    // por lo que no necesitamos crear nuestro propio singleton para eso.
    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}