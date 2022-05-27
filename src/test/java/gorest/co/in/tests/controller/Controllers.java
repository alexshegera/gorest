package gorest.co.in.tests.controller;

public class Controllers {

    private LoggerControllerImpl loggerController;
    private RequestsControllerImpl requestsController;
    private UserServiceImpl authorizationController;
    private JSONValidatorControllerImpl jsonValidatorController;

    public Controllers(LoggerControllerImpl loggerController) {
        this.loggerController = loggerController;
        requestsController = new RequestsControllerImpl(loggerController);
        jsonValidatorController = new JSONValidatorControllerImpl();
        authorizationController = new UserServiceImpl(requestsController);
    }

    public LoggerControllerImpl getLoggerController() {
        return loggerController;
    }

    public UserServiceImpl userServiceController() {
        return authorizationController;
    }
}