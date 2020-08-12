package com.mit.returnDto;

import org.apache.ibatis.type.Alias;

@Alias("followlist")
public class FollowList {
	private String email;
	private String nickname;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
