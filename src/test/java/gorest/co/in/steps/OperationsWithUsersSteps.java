package gorest.co.in.steps;

import gorest.co.in.tests.TestsBase;
import gorest.co.in.tests.dto.User;
import gorest.co.in.tests.dto.UserRequest;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.List;
import java.util.Optional;

public class OperationsWithUsersSteps extends TestsBase {

    private Optional <List<User>> users = Optional.empty();
    private Optional <User> user = Optional.empty();

    public void listUsersStep() throws Exception {
        controllers.getLoggerController().log("Step: Getting list of users");
        users = controllers.userServiceController().getUsers(PATH_TO_HTTPS_SERVER + SUFFIX_GET_LIST_OF_USERS, BEARER_TOKEN);
        controllers.getLoggerController().log("end step");
    }

    public void getUserByEmailStep(String email) throws Exception {
        controllers.getLoggerController().log("Step: Getting list of users by email");
        users = controllers.userServiceController().getUsers(PATH_TO_HTTPS_SERVER + SUFFIX_GET_LIST_OF_USERS,"email", email, BEARER_TOKEN);
        controllers.getLoggerController().log("end step");
    }

    public void addUserStep(UserRequest newUser) throws Exception {
        controllers.getLoggerController().log("Step: Adding user to users list step");
        user = controllers.userServiceController().addUser(PATH_TO_HTTPS_SERVER + SUFFIX_GET_LIST_OF_USERS, newUser, BEARER_TOKEN);
        controllers.getLoggerController().log("end step");
    }

    public void updateUserStep(Long id, UserRequest updatedUser) throws Exception {
        controllers.getLoggerController().log("Step: Updating user step");
        user = controllers.userServiceController().updateUser(PATH_TO_HTTPS_SERVER + SUFFIX_GET_LIST_OF_USERS, id, updatedUser, BEARER_TOKEN);
        controllers.getLoggerController().log("end step");
    }

    public void deleteUserStep(Long id) {
        controllers.getLoggerController().log("Step: Adding user to users list step");
        controllers.userServiceController().deleteUser(PATH_TO_HTTPS_SERVER + SUFFIX_GET_LIST_OF_USERS, id, BEARER_TOKEN);
        controllers.getLoggerController().log("end step");
    }

    public Optional<CloseableHttpResponse> getResponse() {
        return controllers.userServiceController().getHttpResponse();
    }

    public Optional<List<User>> getUsers() {
        return users;
    }

    public Optional<User> getUser() {
        return user;
    }
}