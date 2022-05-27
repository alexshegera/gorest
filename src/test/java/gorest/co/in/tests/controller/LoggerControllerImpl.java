package gorest.co.in.tests.controller;

import gorest.co.in.tests.TestsBase;
import gorest.co.in.tests.controller.interfaces.ILoggerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerControllerImpl implements ILoggerController {

    protected Logger logger = LoggerFactory.getLogger(TestsBase.class);
    protected static Logger loggerS = LoggerFactory.getLogger(TestsBase.class);
    private Boolean debugMode;

    public LoggerControllerImpl(Boolean debugMode) {
        this.debugMode = debugMode;
    }

    public Logger log() {
        return logger;
    }

    public Logger log(String msg) {
        logger.info(msg);
        return logger;
    }

    public Logger log (String msg, String suffix) {
        logger.info(msg, suffix);
        return logger;
    }

    public Logger log(String msg, Boolean debugMode) {
        this.debugMode = debugMode;
        if (debugMode) {
            if (msg.length() > 0 && msg != null && !msg.equals("response body: ")) {
                logger.debug(msg);
            }
            else {
                logger.debug("Response is empty!");
            }
        }
        return logger;
    }

    public Boolean getDebugMode() {
        return debugMode;
    }
}
