package gorest.co.in.tests.controller.interfaces;

import gorest.co.in.tests.dto.User;
import gorest.co.in.tests.dto.UserRequest;

import java.util.List;
import java.util.Optional;

public interface IUsersService {
    Optional<List<User>> getUsers(String url, String authToken) throws Exception;
    Optional<User> getUser(String url, Long id, String authToken) throws Exception;
    Optional<List<User>> getUsers(String url, String param, String value, String authToken) throws Exception;
    Optional<User> addUser(String url, UserRequest newUser, String authToken) throws Exception;
    Optional<User> updateUser(String url, Long id, UserRequest updatedUser, String authToken) throws Exception;
    void deleteUser(String url, Long id, String authToken);
}
