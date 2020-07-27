package com.mit.repo;

import com.mit.dto.User;

public interface UserRepo {
	public User login(User user);

	public boolean join(User user);
	
	
	public int emailCheck(User user);

	public int nicknameCheck(String nickname);
}
