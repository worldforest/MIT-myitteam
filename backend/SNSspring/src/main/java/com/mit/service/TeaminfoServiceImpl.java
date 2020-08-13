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
	public boolean update(String no, String leaderemail, String part, String headcount) {
		return teaminfoRepo.update(no, leaderemail, part, headcount);
	}

	@Override
	public boolean delete(Teaminfo teaminfo) {
		return teaminfoRepo.delete(teaminfo);
	}

	@Override
	public String selectHeadcount(String no, String leaderemail, String part) {
		return teaminfoRepo.selectHeadcount(no, leaderemail, part);
	}

	@Override
	public int countHead(String no, String leaderemail) {
		return teaminfoRepo.countHead(no, leaderemail);
	}

}
