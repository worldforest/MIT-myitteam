package com.mit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.dto.Applymember;
import com.mit.repo.ApplymemberRepo;

@Service
public class ApplymemberServiceImpl implements ApplymemberService {
	@Autowired
	public ApplymemberRepo applymemberRepo;

	@Override
	public List<Applymember> select(String no, String leaderemail) {
		return applymemberRepo.select(no, leaderemail);
	}

	@Override
	public int selectOne(String no, String leaderemail, String teamemail) {
		return applymemberRepo.selectOne(no, leaderemail, teamemail);
	}

	@Override
	public boolean delete(String no, String leaderemail, String teamemail, String part) {
		return applymemberRepo.delete(no, leaderemail, teamemail, part);
	}

	@Override
	public boolean insert(Applymember applymember) {
		return applymemberRepo.insert(applymember);
	}

	@Override
	public boolean deleteAll(String no, String leaderemail) {
		return applymemberRepo.deleteAll(no, leaderemail);
	}

}
