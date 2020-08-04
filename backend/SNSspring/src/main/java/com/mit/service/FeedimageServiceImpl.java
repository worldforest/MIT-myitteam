package com.mit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.repo.FeedimageRepo;

@Service
public class FeedimageServiceImpl implements FeedimageService {

	@Autowired
	FeedimageRepo feedimageRepo;

	@Override
	public List<String> select(String no) {
		// TODO Auto-generated method stub
		return feedimageRepo.select(no);
	}

	@Override
	public boolean insert(String no, String src) {
		return feedimageRepo.insert(no, src);
	}

}
