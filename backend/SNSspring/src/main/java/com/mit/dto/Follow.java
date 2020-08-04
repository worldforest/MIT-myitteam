package com.mit.dto;

public class Follow {

	private String email;// 로그인한 사람
	private String following;// emial인 사람이 follow하는 사람
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFollowing() {
		return following;
	}
	public void setFollowing(String following) {
		this.following = following;
	}
	
}
