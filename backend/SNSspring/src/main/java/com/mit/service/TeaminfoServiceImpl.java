package com.mit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.dto.Teaminfo;
import com.mit.repo.TeaminfoRepo;
@Service
public class TeaminfoServiceImpl implements TeaminfoService {
	@Autowired
	private TeaminfoRepo teaminfoRepo;

	@Override
	public boolean insert(Teaminfo teaminfo) {
		return teaminfoRepo.insert(teaminfo);
	}

}
