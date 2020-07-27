package com.mit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.dto.User;
import com.mit.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public boolean join(User user) {
		return userRepo.join(user);
	}

	@Override
	public User login(User user) {
		return userRepo.login(user);
	}

	@Override
	public int emailCheck(User user) {
		int result = userRepo.emailCheck(user);
		return result;
	}

	@Override
	public int nicknameCheck(String nickname) {
		int result = userRepo.nicknameCheck(nickname);
		return result;
	}

}
