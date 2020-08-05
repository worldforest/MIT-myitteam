package com.mit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.repo.FeedtagRepo;

@Service
public class FeedtagServiceImpl implements FeedtagService{
	
	@Autowired
	private FeedtagRepo feedtagRepo;
	
	@Override
	public boolean insert(String no, String tag) {
		return feedtagRepo.insert(no, tag);
	}

}
