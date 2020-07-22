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

}
