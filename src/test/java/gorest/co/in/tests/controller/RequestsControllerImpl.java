package gorest.co.in.tests.controller;

import gorest.co.in.tests.controller.interfaces.IRequestsController;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;

import java.util.Optional;


public class RequestsControllerImpl implements IRequestsController {

	private LoggerControllerImpl loggerController;

	public RequestsControllerImpl(LoggerControllerImpl loggerController) {
		this.loggerController = loggerController;
	}

	public Optional<CloseableHttpResponse> sendGetRequest(String url, String authToken) {
		HttpGet request = new HttpGet(url);
		Optional <CloseableHttpResponse> response = Optional.empty();
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(1000)
				.setConnectTimeout(1000)
				.setSocketTimeout(1000)
				.build();
		request.setConfig(requestConfig);

		request.addHeader("Accept", "*/*");
		request.addHeader("User-Agent", "PostmanRuntime/7.29.0");
		request.addHeader("Content-Type", "application/json");
		request.addHeader("Authorization", authToken);

		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			response = Optional.ofNullable(httpClient.execute(request));
			loggerController.log("response status code is " + response.get().getStatusLine().getStatusCode(), loggerController.getDebugMode());
			return response;
		} catch (Exception e) {
			loggerController.log("The response is empty after send GET call to the URL: " + url);
		}
		return response;
	}

	public Optional <CloseableHttpResponse> sendPostRequest(String url, String requestBody, String authToken) {
		HttpPost request = new HttpPost(url);
		Optional <CloseableHttpResponse> response = Optional.empty();
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(1000)
				.setConnectTimeout(1000)
				.setSocketTimeout(1000)
				.build();
		request.setConfig(requestConfig);

		request.setHeader("User-Agent", "PostmanRuntime/7.29.0");
		request.setHeader("Content-Type", "application/json");
		request.setHeader("Accept", "application/json");
		request.setHeader("Authorization", authToken);

		request.setEntity(new StringEntity(requestBody, ContentType.APPLICATION_JSON));

		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
			response = Optional.ofNullable(httpClient.execute(request));
			loggerController.log("response status code is " + response.get().getStatusLine().getStatusCode(), loggerController.getDebugMode());
			return response;
		} catch (Exception e) {
			loggerController.log("The response is empty after send GET call to the URL: " + url);
		}
		return response;
	}

	public Optional <CloseableHttpResponse> sendPatchRequest(String url, String requestBody, String authToken) {
		HttpPatch request = new HttpPatch(url);
		Optional <CloseableHttpResponse> response = Optional.empty();
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(1000)
				.setConnectTimeout(1000)
				.setSocketTimeout(1000)
				.build();
		request.setConfig(requestConfig);

		request.setHeader("User-Agent", "PostmanRuntime/7.29.0");
		request.setHeader("Content-Type", "application/json");
		request.setHeader("Accept", "application/json");
		request.setHeader("Authorization", authToken);

		request.setEntity(new StringEntity(requestBody, ContentType.APPLICATION_JSON));

		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
			response = Optional.ofNullable(httpClient.execute(request));
			loggerController.log("response status code is " + response.get().getStatusLine().getStatusCode(), loggerController.getDebugMode());
			return response;
		} catch (Exception e) {
			loggerController.log("The response is empty after send GET call to the URL: " + url);
		}
		return response;
	}

	public Optional<CloseableHttpResponse> sendDeleteRequest(String url, String authToken) {
		HttpDelete request = new HttpDelete(url);
		Optional <CloseableHttpResponse> response = Optional.empty();
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(1000)
				.setConnectTimeout(1000)
				.setSocketTimeout(1000)
				.build();
		request.setConfig(requestConfig);

		request.addHeader("Accept", "*/*");
		request.addHeader("User-Agent", "PostmanRuntime/7.29.0");
		request.addHeader("Content-Type", "application/json");
		request.addHeader("Authorization", authToken);

		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			response = Optional.ofNullable(httpClient.execute(request));
			if (response.get().getStatusLine().getStatusCode() == 204) {
				loggerController.log("The response status code is 204 No Content, user deleted!", loggerController.getDebugMode());
			} else {
				loggerController.log("The user has not been deleted! Skip step!", loggerController.getDebugMode());
			}
			return response;
		} catch (Exception e) {
			loggerController.log("The response is empty after send GET call to the URL: " + url);
		}
		return response;
	}
}