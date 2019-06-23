package randomCode.randomProjects.connection.http;

import java.util.Objects;

public class Post {
	
	private Long userId;
	private Long id;
	
	private String title;
	private String body;
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		
		if (this.getClass() != object.getClass()) {
			return false;
		}
		
		if (this == object) {
			return true;
		}
		
		Post otherPost = (Post) object;
		
		boolean idEquality = Objects.equals(this.id, otherPost.id);
		boolean userIdEquality = Objects.equals(this.userId, otherPost.userId);
		boolean titleEquality = Objects.equals(this.title, otherPost.title);
		boolean bodyEquality = Objects.equals(this.body, otherPost.body);
		
		return idEquality && userIdEquality && titleEquality && bodyEquality;
		
	}
	
	@Override
	public String toString() {
		return String.format("Post [Id=%d, UserId=%d, Title=%s, Body=%s]", this.id, this.userId, this.title, this.body);
	}

}
