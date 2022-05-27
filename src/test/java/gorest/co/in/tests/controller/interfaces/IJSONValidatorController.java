package gorest.co.in.tests.controller.interfaces;

import org.json.JSONArray;
import org.json.JSONObject;

public interface IJSONValidatorController {

    void validateJSONWithSchema(JSONObject jo, String shemaFile) throws Exception;
    void validateJSONArrayWithSchema(JSONArray jo, String shemaFile) throws Exception;
    JSONObject getJSONObjectFromResponse(String response);
    JSONArray getJSONArrayFromResponse(String response);
}
