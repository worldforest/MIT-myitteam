package com.mit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.dto.Likefeed;
import com.mit.repo.FeedlikeRepo;

@Service
public class FeedlikeServiceImpl implements FeedlikeService {

	@Autowired
	FeedlikeRepo feedlikeRepo;

	@Override
	public List<String> selectAll(String no) {
		// TODO Auto-generated method stub
		return feedlikeRepo.selectAll(no);
	}

	@Override
	public boolean insert(Likefeed likefeed) {
		// TODO Auto-generated method stub
		return feedlikeRepo.insert(likefeed);
	}

	@Override
	public boolean delete(Likefeed likefeed) {
		// TODO Auto-generated method stub
		return feedlikeRepo.delete(likefeed);
	}


}
