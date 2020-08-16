package com.mit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.dto.Contents;
import com.mit.repo.ContentsRepo;

@Service
public class ContentsServiceImpl implements ContentsService {

	@Autowired
	private ContentsRepo contentsRepo;

	@Override
	public boolean insert(Contents contents) {
		return contentsRepo.insert(contents);
	}

	@Override
	public List<Contents> selectAll() {
		return contentsRepo.selectAll();
	}

	@Override
	public Contents selectOne(String no) {
		return contentsRepo.selectOne(no);
	}

	@Override
	public String LatestContents(String email) {
		return contentsRepo.LatestContents(email);
	}

	@Override
	public boolean delete(String no) {
		return contentsRepo.delete(no);
	}

	@Override
	public boolean viewsplus(String no) {
		return contentsRepo.viewsplus(no);
	}

}
