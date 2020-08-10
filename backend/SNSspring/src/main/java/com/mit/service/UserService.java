package com.mit.service;

import com.mit.dto.User;

public interface UserService {
	public boolean join(User user);
	public User login(User user);
	public String findPwd(String email);
	public int emailCheck(String email);
	public int nicknameCheck(String nickname);
	public String selectDescription(String email);
	public String selectNickname(String email);
	public User selectPrivate(String email);
	public boolean update(String email, String pwd);
	public boolean update(User user);
}
