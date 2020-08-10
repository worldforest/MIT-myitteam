package com.mit.service;

import java.util.List;

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

	@Override
	public List<Teaminfo> select(String no, String leaderemail) {
		return teaminfoRepo.select(no, leaderemail);
	}

	@Override
	public boolean update(Teaminfo teaminfo) {
		return teaminfoRepo.update(teaminfo);
	}

	@Override
	public boolean delete(Teaminfo teaminfo) {
		return teaminfoRepo.delete(teaminfo);
	}

}
