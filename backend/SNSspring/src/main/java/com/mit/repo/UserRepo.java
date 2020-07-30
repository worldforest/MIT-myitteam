package com.mit.repo;

import com.mit.dto.User;

public interface UserRepo {
	public User login(User user);

	public boolean join(User user);

	public int emailCheck(User user);

	public int nicknameCheck(String nickname);

	public String selectDescription(String email);

	public String selectNickname(String email);

	public User selectPrivate(String email);

}
