package com.mit.service;

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
	
	

}
