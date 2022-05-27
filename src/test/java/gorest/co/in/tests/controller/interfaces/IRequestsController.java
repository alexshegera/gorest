package gorest.co.in.tests.controller.interfaces;

import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

public interface IRequestsController {
    Optional <CloseableHttpResponse> sendGetRequest(String url, String authToken);
    Optional <CloseableHttpResponse> sendPostRequest(String url, String requestBody, String authToken);
    Optional <CloseableHttpResponse> sendPatchRequest(String url, String requestBody, String authToken);
    Optional<CloseableHttpResponse> sendDeleteRequest(String url, String authToken);
}
