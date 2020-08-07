package com.mit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.dto.Emailkey;
import com.mit.repo.EmailkeyRepo;

@Service
public class EmailkeyServiceImpl implements EmailkeyService {
	
	@Autowired
	EmailkeyRepo emailkeyRepo;
	
	@Override
	public Emailkey select(String email) {
		// TODO Auto-generated method stub
		return emailkeyRepo.select(email);
	}

	@Override
	public Emailkey check(Emailkey emailkey) {
		return emailkeyRepo.check(emailkey);
	}

	@Override
	public boolean update(Emailkey emailkey) {
		return emailkeyRepo.update(emailkey);
	}

	@Override
	public boolean insert(Emailkey emailkey) {
		// TODO Auto-generated method stub
		return emailkeyRepo.insert(emailkey);
	}

}
