package com.mit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.dto.Feed;
import com.mit.repo.FeedscrapRepo;

@Service
public class FeedscrapServiceImpl implements FeedscrapService {

	@Autowired
	FeedscrapRepo feedscrapRepo;

	@Override
	public List<Feed> selectAll(String email) {
		// TODO Auto-generated method stub
		return feedscrapRepo.selectAll(email);
	}

}
