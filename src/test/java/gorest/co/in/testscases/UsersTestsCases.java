package gorest.co.in.testscases;

import gorest.co.in.steps.OperationsWithUsersSteps;
import gorest.co.in.tests.TestsBase;
import gorest.co.in.tests.dto.UserRequest;
import lombok.Builder;
import org.testng.annotations.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UsersTestsCases extends TestsBase {

	private OperationsWithUsersSteps operationsWithUsersSteps = new OperationsWithUsersSteps();
	private UserRequest defaultUser;
	private UserRequest updUser;

	@BeforeMethod
	@Parameters({"domain", "endpoint", "secretKey", "iVector", "token"})
	public void setUp(String domain, String endpoint, String secretKey, String iVector, String token) throws Exception {
		PATH_TO_HTTPS_SERVER = domain;
		SUFFIX_GET_LIST_OF_USERS = endpoint;
		BEARER_TOKEN = tokenDecrypt(secretKey.getBytes(), iVector.getBytes(), token.getBytes());


		defaultUser = new UserRequest.Builder()
				.setName("alexshegera")
				.setEmail("ns872h837hs.87h872h32@abc.com")
				.setGender("female")
				.setStatus("inactive")
				.build();
		updUser = new UserRequest.Builder()
				.setName("Bruce Willis")
				.setEmail("iuhweiudbub8238@abc.com")
				.setGender("male")
				.setStatus("active")
				.build();
	}

	@Test
	public void listUsers_shouldBeGetListOfUsers_whenListIsNotEmpty() throws Exception {
		final String NAME_OF_CURRENT_TEST =
				"Get list of users if the list is not empty: GET /public/v2/users";
		controllers.getLoggerController().log("Starting test: {}", NAME_OF_CURRENT_TEST);

		operationsWithUsersSteps.listUsersStep();

		assertEquals("Status code is not 200, test failed!",
				200, operationsWithUsersSteps.getResponse().get().getStatusLine().getStatusCode());
		assertTrue("The list of users is empty!",
				operationsWithUsersSteps.getUsers().isPresent());

		// Test completed
		controllers.getLoggerController().log("Test \"{}\" completed.", NAME_OF_CURRENT_TEST);
	}

	@Test
	public void addUser_shouldBeAdded() throws Exception {
		final String NAME_OF_CURRENT_TEST =
				"Add new user to users list: GET /public/v2/users";
		controllers.getLoggerController().log("Starting test: {}", NAME_OF_CURRENT_TEST);

		operationsWithUsersSteps.getUserByEmailStep(defaultUser.getEmail());

		if (operationsWithUsersSteps.getUsers().isPresent())
			operationsWithUsersSteps.deleteUserStep(operationsWithUsersSteps.getUsers().get().get(0).getId());

		operationsWithUsersSteps.addUserStep(defaultUser);
		assertTrue("The user is not returned!", operationsWithUsersSteps.getUser().isPresent());
		assertEquals(defaultUser.getName(), operationsWithUsersSteps.getUser().get().getName());
		assertEquals(defaultUser.getEmail(), operationsWithUsersSteps.getUser().get().getEmail());
		assertEquals(defaultUser.getStatus(), operationsWithUsersSteps.getUser().get().getStatus());
		assertEquals(defaultUser.getGender(), operationsWithUsersSteps.getUser().get().getGender());

		operationsWithUsersSteps.getUserByEmailStep(defaultUser.getEmail());
		assertTrue("The list of users is empty!", operationsWithUsersSteps.getUsers().isPresent());

		controllers.getLoggerController().log("User added!");
		controllers.getLoggerController().log("Test \"{}\" completed.", NAME_OF_CURRENT_TEST);
	}

	@Test
	public void updateUser_shouldBeUpdated_whenUserIsPresent() throws Exception {
		final String NAME_OF_CURRENT_TEST =
				"Update user when is present: PATCH /public/v2/users";
		controllers.getLoggerController().log("Starting test: {}", NAME_OF_CURRENT_TEST);

		operationsWithUsersSteps.getUserByEmailStep(defaultUser.getEmail());
		if (operationsWithUsersSteps.getUsers().isPresent())
			operationsWithUsersSteps.deleteUserStep(operationsWithUsersSteps.getUsers().get().get(0).getId());

		operationsWithUsersSteps.getUserByEmailStep(updUser.getEmail());
		if (operationsWithUsersSteps.getUsers().isPresent())
			operationsWithUsersSteps.deleteUserStep(operationsWithUsersSteps.getUsers().get().get(0).getId());

		operationsWithUsersSteps.addUserStep(defaultUser);
		assertTrue("The user is not returned!", operationsWithUsersSteps.getUser().isPresent());
		assertEquals(defaultUser.getName(), operationsWithUsersSteps.getUser().get().getName());
		assertEquals(defaultUser.getEmail(), operationsWithUsersSteps.getUser().get().getEmail());
		assertEquals(defaultUser.getStatus(), operationsWithUsersSteps.getUser().get().getStatus());
		assertEquals(defaultUser.getGender(), operationsWithUsersSteps.getUser().get().getGender());

		operationsWithUsersSteps.getUserByEmailStep(defaultUser.getEmail());
		assertTrue("The list of users is empty!",
				operationsWithUsersSteps.getUsers().isPresent());
		controllers.getLoggerController().log("User added!");

		operationsWithUsersSteps.updateUserStep(operationsWithUsersSteps.getUsers().get().get(0).getId(), updUser);
		assertTrue("The user is not returned!", operationsWithUsersSteps.getUsers().isPresent());
		assertEquals(updUser.getName(), operationsWithUsersSteps.getUser().get().getName());
		assertEquals(updUser.getEmail(), operationsWithUsersSteps.getUser().get().getEmail());
		assertEquals(updUser.getStatus(), operationsWithUsersSteps.getUser().get().getStatus());
		assertEquals(updUser.getGender(), operationsWithUsersSteps.getUser().get().getGender());

		operationsWithUsersSteps.getUserByEmailStep(updUser.getEmail());
		assertTrue("", operationsWithUsersSteps.getUsers().isPresent());
		assertEquals(updUser.getName(), operationsWithUsersSteps.getUser().get().getName());
		assertEquals(updUser.getEmail(), operationsWithUsersSteps.getUser().get().getEmail());
		assertEquals(updUser.getStatus(), operationsWithUsersSteps.getUser().get().getStatus());
		assertEquals(updUser.getGender(), operationsWithUsersSteps.getUser().get().getGender());

		// Test completed
		controllers.getLoggerController().log("Test \"{}\" completed.", NAME_OF_CURRENT_TEST);
	}


	/*@Rule
	public TestWatcher testWatcher = new TestWatcher() {
	    protected void failed(Throwable e, Description description) {
	     controllers.getLoggerController().log().error(description.getDisplayName() + " failed: {}", e.getMessage());
	    }
	  };*/
}