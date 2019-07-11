package randomCode.randomProjects.connection.http;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class HttpGetTest {
	
	private static final String POST_URL = "https://jsonplaceholder.typicode.com/posts/";
	
	public static void main(String[] args) {
		Post expectedGetPost = new Post();
		
		expectedGetPost.setId(3L);
		expectedGetPost.setUserId(1L);
		expectedGetPost.setTitle("ea molestias quasi exercitationem repellat qui ipsa sit aut");
		
		StringBuilder bodyText = new StringBuilder();
		bodyText.append("et iusto sed quo iure\n");
		bodyText.append("voluptatem occaecati omnis eligendi aut ad\n");
		bodyText.append("voluptatem doloribus vel accusantium quis pariatur\n");
		bodyText.append("molestiae porro eius odio et labore et velit aut");
		
		expectedGetPost.setBody(bodyText.toString());
		
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
		
		
		
		Post expectedLastPostInList = new Post();
		
		expectedLastPostInList.setId(100L);
		expectedLastPostInList.setUserId(10L);
		expectedLastPostInList.setTitle("at nam consequatur ea labore ea harum");
		
		bodyText = new StringBuilder();
		bodyText.append("cupiditate quo est a modi nesciunt soluta\n");
		bodyText.append("ipsa voluptas error itaque dicta in\n");
		bodyText.append("autem qui minus magnam et distinctio eum\n");
		bodyText.append("accusamus ratione error aut");
				
		expectedLastPostInList.setBody(bodyText.toString());
		
		List<Post> postList = getAllPosts("Jackson");	
		int listSize = postList.size();
		Post actualLastPostInList = postList.get(listSize -1);
		
		if (listSize == 100 && expectedLastPostInList.equals(actualLastPostInList)) {
			System.out.println("Jackson Get List Test Passed");
		} else {
			System.out.println("Jackson Get List Test Failed");
		}
		
		
		postList = getAllPosts("Gson");
		listSize = postList.size();
		actualLastPostInList = postList.get(listSize -1);
		
		if (listSize == 100 && expectedLastPostInList.equals(actualLastPostInList)) {
			System.out.println("Gson Get List Test Passed");
		} else {
			System.out.println("Gson Get List Test Failed");
		}
	}
	

	private static Post getPostByID(int ID, String lib) {
		Post post = null;
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(POST_URL + ID);
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
	
	private static List<Post> getAllPosts(String lib) {
		List<Post> postList = new ArrayList<>();
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(POST_URL);
		httpGet.setHeader("Accept", "application/json");
		
		try {
			CloseableHttpResponse response = httpClient.execute(httpGet);;
			String jsonString = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
			
			if (lib.equals("Jackson")) {
				postList = unmarshalListWithJackson(jsonString);
				
			} else if (lib.equals("Gson")) {
				postList = unmarshalListWithGson(jsonString);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(httpClient);
		}
		
		
		return postList;
	}
	

	private static Post unmarshalWithJackson(String jsonString) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(jsonString, Post.class);
	}
	
	private static List<Post> unmarshalListWithJackson(String jsonString) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		
		TypeFactory typeFactory = mapper.getTypeFactory();
		JavaType listType = typeFactory.constructCollectionType(List.class, Post.class);
		
		return mapper.readValue(jsonString, listType);
	}

	private static Post unmarshalWithGson(String jsonString) throws Exception {
		Gson gson = new Gson();
		return gson.fromJson(jsonString, Post.class);
	}
	
	private static List<Post> unmarshalListWithGson(String jsonString) throws Exception {
		Gson gson = new Gson();
	
		TypeToken<?> token = TypeToken.getParameterized(List.class, Post.class);
		Type listType = token.getType();
		
		return gson.fromJson(jsonString, listType);		
	}
}
