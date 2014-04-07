package by.betfair.core.ng.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;

import by.betfair.core.ng.entity.JsonrpcRequest;
import by.betfair.core.ng.managers.IActionManager;

public class HttpUtil {

	private static final Logger logger = Logger.getLogger(HttpUtil.class);

	private static final String SPORTS_APING = "SportsAPING/v1.0/";
	private static final String APING_URL = "https://api.betfair.com/exchange/betting/json-rpc/v1";

	// header names
	private static final String HTTP_HEADER_X_APPLICATION = "X-Application";
	private static final String HTTP_HEADER_X_AUTHENTICATION = "X-Authentication";
	private static final String HTTP_HEADER_CONTENT_TYPE = "Content-Type";
	private static final String HTTP_HEADER_ACCEPT = "Accept";
	private static final String HTTP_HEADER_ACCEPT_CHARSET = "Accept-Charset";

	// params for header
	private static final String APPLICATION_JSON = "application/json";
	private static final String ENCODING_UTF8 = "UTF-8";
	private static final Integer TIMEOUT = 10000;

	public static String sendPostRequest(String url, String ssoToken) throws ClientProtocolException, IOException {
		HttpPost post = new HttpPost(url);
		setHeader(post, ssoToken);
		return send(post);
	}

	public static String sendPostRequest(String operation, Map<String, Object> params, String ssoToken) throws ClientProtocolException, IOException {
		String requestString = createJsonRequest(operation, params);
		logger.debug("Request: " + requestString);
		HttpPost post = new HttpPost(APING_URL);
		setHeader(post, ssoToken);
		try {
			post.setEntity(new StringEntity(requestString, ENCODING_UTF8));
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
			return null;
		}
		return send(post);

	}

	private static void setHeader(HttpPost post, String ssoToken) {
		post.setHeader(HTTP_HEADER_CONTENT_TYPE, APPLICATION_JSON);
		post.setHeader(HTTP_HEADER_ACCEPT, APPLICATION_JSON);
		post.setHeader(HTTP_HEADER_ACCEPT_CHARSET, ENCODING_UTF8);
		post.setHeader(HTTP_HEADER_X_APPLICATION, IActionManager.APPLICATION_KEY);
		post.setHeader(HTTP_HEADER_X_AUTHENTICATION, ssoToken);
	}

	private static String send(HttpPost postRequest) throws ClientProtocolException, IOException {
		String resp = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpParams httpParams = httpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT);
		HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT);
		resp = httpClient.execute(postRequest, new JsonResponseHandler());
		logger.debug("RESPONSE: " + resp);
		return resp;
	}

	private static String createJsonRequest(String operation, Map<String, Object> params) {
		JsonrpcRequest request = new JsonrpcRequest();
		request.setId("1");
		request.setMethod(SPORTS_APING + operation);
		request.setParams(params);
		return JsonConverter.convertToJson(request);
	}
}
