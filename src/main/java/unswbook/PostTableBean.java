package unswbook;

import java.io.InputStream;
import java.io.Serializable;

public class PostTableBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String username;
	private String content;
	private String time;
	private String unlikes;
	private String likes;
	private InputStream picture;
	private String picturepath;
	private String facepath;
	private int nlike;
	private int nunlike;
	private String islike;
	private String isunlike;
	/**
	 * 
	 */
	public PostTableBean() {
	}
	public PostTableBean(String id, String username, String content, 
			String time, String unlikes, String likes,InputStream picture,
			String picturepath,String facepath,int nlike,int nunlike,String islike,String isunlike) {
		this.id = id;
		this.username = username;
		this.content = content;
		this.time = time;
		this.unlikes = unlikes;
		this.likes = likes;
		this.picture=picture;
		this.picturepath =picturepath;
		this.facepath = facepath;
		this.nlike =nlike;
		this.nunlike = nunlike;
		this.islike=islike;
		this.isunlike=isunlike;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUnlikes() {
		return unlikes;
	}
	public void setUnlikes(String unlikes) {
		this.unlikes = unlikes;
	}
	public String getLikes() {
		return likes;
	}
	public void setLikes(String likes) {
		this.likes = likes;
	}
	public InputStream getPicture() {
		return picture;
	}
	public void setPicture(InputStream picture) {
		this.picture = picture;
	}
	public String getPicturepath() {
		return picturepath;
	}
	public void setPicturepath(String picturepath) {
		this.picturepath = picturepath;
	}
	public String getFacepath() {
		return facepath;
	}
	public void setFacepath(String facepath) {
		this.facepath = facepath;
	}
	public int getNlike() {
		return nlike;
	}
	public void setNlike(int nlike) {
		this.nlike = nlike;
	}
	public int getNunlike() {
		return nunlike;
	}
	public void setNunlike(int nunlike) {
		this.nunlike = nunlike;
	}
	public String getIslike() {
		return islike;
	}
	public void setIslike(String islike) {
		this.islike = islike;
	}
	public String getIsunlike() {
		return isunlike;
	}
	public void setIsunlike(String isunlike) {
		this.isunlike = isunlike;
	}
	

}
