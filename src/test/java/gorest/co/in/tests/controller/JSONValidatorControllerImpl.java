package gorest.co.in.tests.controller;

import gorest.co.in.tests.controller.interfaces.IJSONValidatorController;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;

import static org.junit.Assert.assertNotNull;

public class JSONValidatorControllerImpl implements IJSONValidatorController {
    public void validateJSONWithSchema(JSONObject jo, String shemaFile) throws Exception {
        Schema schema = SchemaLoader.load(new JSONObject(new JSONTokener(new FileInputStream(shemaFile))));
        assertNotNull("The scheme is NULL!", schema);
        schema.validate(jo);
    }

    public void validateJSONArrayWithSchema(JSONArray jo, String shemaFile) throws Exception {
        Schema schema = SchemaLoader.load(new JSONObject(new JSONTokener(new FileInputStream(shemaFile))));
        assertNotNull("The scheme is NULL!", schema);
        schema.validate(jo);
    }

    //Serialize response to JSON Object and checking if JSON schema is valid
    public JSONObject getJSONObjectFromResponse(String response) {
        JSONObject jo = new JSONObject(response);

        assertNotNull("The response is empty!", jo);

        return jo;
    }

    //Serialize response to JSON Array and checking if JSON schema is valid
    public JSONArray getJSONArrayFromResponse(String response) {
        JSONArray jo = new JSONArray(response);

        assertNotNull("The response is empty!", jo);

        return jo;
    }
}
