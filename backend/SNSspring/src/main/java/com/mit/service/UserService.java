package com.mit.service;

import com.mit.dto.User;

public interface UserService {
	public boolean join(User user);
	public User login(User user);
	public int emailCheck(User user);
	public int nicknameCheck(String nickname);
}
