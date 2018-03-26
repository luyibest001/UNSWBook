package unswbook;

import java.io.Serializable;

public class ntfTableBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String time;
	private String content;
	private String state;
	private String username2;
	
	
	public ntfTableBean() {
	}

	public ntfTableBean(String username, String time, String content, String state,String username2) {
		this.username = username;
		this.time = time;
		this.content = content;
		this.state = state;
		this.username2=username2;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUsername2() {
		return username2;
	}

	public void setUsername2(String username2) {
		this.username2 = username2;
	}
	

}
