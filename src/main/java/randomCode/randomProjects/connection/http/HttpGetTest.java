package randomCode.randomProjects.connection.http;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class HttpGetTest {
	private static final String GET_POST_BY_ID_URL = "https://jsonplaceholder.typicode.com/posts/%d";
	
	public static void main(String[] args) {
		Post expectedGetPost = new Post();
		
		expectedGetPost.setId(3L);
		expectedGetPost.setUserId(1L);
		expectedGetPost.setTitle("ea molestias quasi exercitationem repellat qui ipsa sit aut");
		expectedGetPost.setBody("et iusto sed quo iure"
				+ "\nvoluptatem occaecati omnis eligendi aut ad"
				+ "\nvoluptatem doloribus vel accusantium quis pariatur"
				+ "\nmolestiae porro eius odio et labore et velit aut");
		
		Post actualGetPost = getPostByID(3, "Jackson");
		
		if (expectedGetPost.equals(actualGetPost)) {
			System.out.println("Jackson Get Test Passed");
		} else {
			System.out.println("Jackson Get Test Failed");
		}
		
		actualGetPost = getPostByID(3, "Gson");
		
		if (expectedGetPost.equals(actualGetPost)) {
			System.out.println("Gson Get Test Passed");
		} else {
			System.out.println("Gson Get Test Failed");
		}
	}
	
	private static Post getPostByID(int ID, String lib) {
		Post post = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(String.format(GET_POST_BY_ID_URL, ID));
		httpGet.setHeader("Accept", "application/json");
		
		try {
			CloseableHttpResponse response = httpClient.execute(httpGet);;
			String jsonString = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
			
			
			if (lib.equals("Jackson")) {
				post = unmarshalWithJackson(jsonString);
				
			} else if (lib.equals("Gson")) {
				post = unmarshalWithGson(jsonString);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(httpClient);
		}
		
		return post;
	}

	private static Post unmarshalWithJackson(String jsonString) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(jsonString, Post.class);
	}

	private static Post unmarshalWithGson(String jsonString) throws Exception {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, Post.class);	
	}

}
