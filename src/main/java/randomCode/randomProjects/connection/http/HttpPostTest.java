package randomCode.randomProjects.connection.http;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class HttpPostTest {
	
	private static final String POST_URL = "https://jsonplaceholder.typicode.com/posts/";
	
	public static void main(String[] args) throws Exception {
		long userId = 12L;
		String title = "New Final Fantasy XIV expansion";
		String body = "A new expansion for Final Fantasy XIV has been announced. Its title is Shadowbringers";
		
		sendPostRequestJsonObject(userId, title, body);
		sendPostRequestPojo(userId, title, body);
		
	}
	
	private static void sendPostRequestJsonObject(long userId, String title, String body) {
		JsonObject jsonObjectRequest = new JsonObject();
		
		jsonObjectRequest.addProperty("userId", userId);
		jsonObjectRequest.addProperty("title", title);
		jsonObjectRequest.addProperty("body", body);
		
		String jsonResponse = sendPostRequest(jsonObjectRequest.toString());
		
		Gson gson = new Gson();
		JsonObject jsonObjectResponse = gson.fromJson(jsonResponse, JsonObject.class);
		
		Long id = jsonObjectResponse.get("id").getAsLong();
		
		jsonObjectRequest.addProperty("id", id);
		
		if (jsonObjectRequest.equals(jsonObjectResponse)) {
			System.out.println("Post Request With JsonObject Passed");
			
		} else {
			System.out.println("Post Request With JsonObject Failed");
		}
	}
	
	private static void sendPostRequestPojo(long userId, String title, String body) throws Exception {
		Post requestAsPost = new Post();
		
		requestAsPost.setUserId(userId);
		requestAsPost.setTitle(title);
		requestAsPost.setBody(body);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonRequest = mapper.writeValueAsString(requestAsPost);
		
		String jsonResponse = sendPostRequest(jsonRequest);
		
		Post responseAsPost = mapper.readValue(jsonResponse, Post.class);
		Long id = responseAsPost.getId();
		
		requestAsPost.setId(id);
		
		if (requestAsPost.equals(responseAsPost)) {
			System.out.println("Post Request With Pojo Passed");
			
		} else {
			System.out.println("Post Request With Pojo Failed");
		}
		
	}

	private static String sendPostRequest(String jsonRequest) {
		String jsonResponse = null;
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(POST_URL);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");
		
		
		try {
			StringEntity stringEntity = new StringEntity(jsonRequest);
			httpPost.setEntity(stringEntity);
			
			CloseableHttpResponse response = httpClient.execute(httpPost);
			jsonResponse = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			IOUtils.closeQuietly(httpClient);
		}
		
		return jsonResponse;
	}

}
