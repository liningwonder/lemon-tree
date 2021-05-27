package io.lemon.tree.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LemonTreeMetricLog {

    private static final Logger logger = LoggerFactory.getLogger(LemonTreeMetricLog.class);

    public static Logger getLogger() {
        return logger;
    }

}
