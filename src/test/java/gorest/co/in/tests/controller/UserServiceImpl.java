package gorest.co.in.tests.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import gorest.co.in.tests.controller.interfaces.IUsersService;
import gorest.co.in.tests.dto.BadResponseDto;
import gorest.co.in.tests.dto.User;
import gorest.co.in.tests.dto.UserRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserServiceImpl implements IUsersService {

	protected static final String SCHEME_PATH = "./src/test/resources/scheme/";

	private Optional <CloseableHttpResponse> httpResponse = Optional.empty();
	private Integer returnCode;
	private Optional <BadResponseDto> badResponse = Optional.empty();
	private ObjectMapper mapper = new ObjectMapper();
	private JSONObject requestBody = new JSONObject();
	private RequestsControllerImpl requestsController;
	private JSONValidatorControllerImpl jsonValidatorController = new JSONValidatorControllerImpl();
	private LoggerControllerImpl loggerController = new LoggerControllerImpl(true);

	public UserServiceImpl(RequestsControllerImpl requestsController) {
		this.requestsController = requestsController;
	}

	public Optional<List<User>> getUsers(String url, String authToken) throws Exception {
		httpResponse = requestsController.sendGetRequest(url, authToken);
		assertTrue("Response is NULL, test failed!", httpResponse.isPresent());
		String responseBody = EntityUtils.toString(httpResponse.get().getEntity());
		returnCode = httpResponse.get().getStatusLine().getStatusCode();

		jsonValidatorController.validateJSONArrayWithSchema(jsonValidatorController
						.getJSONArrayFromResponse(responseBody), SCHEME_PATH + "getAllUsers.json");
		badResponse = Optional.empty();

		assertTrue("Response is NULL, test failed!", httpResponse.isPresent());
		return Optional.ofNullable(mapper.readValue(responseBody, new TypeReference<>() {}));
	}

	public Optional<User> getUser(String url, Long id, String authToken) throws Exception {
		httpResponse = requestsController.sendGetRequest(url + "/" + id, authToken);
		assertTrue("Response is NULL, test failed!", httpResponse.isPresent());
		User user = mapper.readValue(EntityUtils.toString(httpResponse.get().getEntity()), User.class);
		if (user == null)
			return Optional.empty();
		return Optional.of(user);
	}

	public Optional<List<User>> getUsers(String url, String param, String value, String authToken) throws Exception {
		httpResponse = requestsController.sendGetRequest(url + "?" + param + "=" + value, authToken);
		returnCode = httpResponse.get().getStatusLine().getStatusCode();
		List<User> users = mapper.readValue(EntityUtils.toString(httpResponse.get().getEntity()), new  TypeReference<>() {});
		if (users.isEmpty()) {
			loggerController.log("The user with " + param + " = " + value + " not found!");
			return Optional.empty();
		}
		return Optional.of(users);
	}

	public Optional<User> addUser(String url, UserRequest newUser, String authToken) throws Exception {
		String requestBody = mapper.writeValueAsString(newUser);
		httpResponse = requestsController.sendPostRequest(url, requestBody, authToken);
		assertTrue("Response is NULL, test failed!", httpResponse.isPresent());
		String responseBody = EntityUtils.toString(httpResponse.get().getEntity());
		returnCode = httpResponse.get().getStatusLine().getStatusCode();

		jsonValidatorController.validateJSONWithSchema(jsonValidatorController
				.getJSONObjectFromResponse(responseBody), SCHEME_PATH + "getUser.json");

		badResponse = Optional.empty();

		assertEquals("Return code not 201", 201, returnCode.intValue());
		return Optional.ofNullable(mapper.readValue(responseBody, User.class));
	}

	public Optional<User> updateUser(String url, Long id, UserRequest updatedUser, String authToken) throws Exception {
		String requestBody = mapper.writeValueAsString(updatedUser);
		httpResponse = requestsController.sendPatchRequest(url + "/" + id, requestBody, authToken);
		assertTrue("Response is NULL, test failed!", httpResponse.isPresent());
		String responseBody = EntityUtils.toString(httpResponse.get().getEntity());
		returnCode = httpResponse.get().getStatusLine().getStatusCode();

		jsonValidatorController.validateJSONWithSchema(jsonValidatorController
				.getJSONObjectFromResponse(responseBody), SCHEME_PATH + "getUser.json");

		assertEquals("Return code not 200", 200, returnCode.intValue());
		return Optional.ofNullable(mapper.readValue(responseBody, User.class));
	}

	public void deleteUser(String url, Long id, String authToken) {
		httpResponse = requestsController.sendDeleteRequest(url + "/" + id, authToken);
		assertTrue("Response is NULL, test failed!", httpResponse.isPresent());
	}

	public Optional <CloseableHttpResponse> getHttpResponse() {
		return httpResponse;
	}

	public void setBadResponse(BadResponseDto badResponse) {
		this.badResponse = Optional.of(badResponse);
	}
}