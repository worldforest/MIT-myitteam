package com.mit.repo;

import com.mit.dto.User;

public interface UserRepo {
	public User login(User user);
	public int join(User user);
}
