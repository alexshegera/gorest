package gorest.co.in.tests.controller.interfaces;

import org.slf4j.Logger;

public interface ILoggerController {
    Logger log();
    Logger log(String msg);
    Logger log (String msg, String suffix);
    Logger log(String msg, Boolean debugMode);
    Boolean getDebugMode();
}
