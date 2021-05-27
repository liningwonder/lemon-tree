package io.lemon.tree.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceCallMonitorLogger {

    private static final Logger logger = LoggerFactory.getLogger(ServiceCallMonitorLogger.class);

    public static Logger getLogger() {
        return logger;
    }
}
