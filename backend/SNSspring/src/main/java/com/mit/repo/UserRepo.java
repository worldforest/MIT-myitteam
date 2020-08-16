package com.mit.repo;

import java.util.List;

import com.mit.dto.User;

public interface UserRepo {
	public User login(User user);

	public boolean join(User user);

	public String findPwd(String email);

	public int emailCheck(String email);

	public int nicknameCheck(String nickname);

	public String selectDescription(String email);

	public String selectNickname(String email);

	public User selectPrivate(String email);

	public boolean update(String email, String pwd);

	public boolean updateUser(User user);

	public List<User> selectAddressMember(String no, String leaderemail);

}
