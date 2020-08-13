package com.mit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.dto.Feedlike;
import com.mit.repo.FeedlikeRepo;
import com.mit.returnDto.FollowList;

@Service
public class FeedlikeServiceImpl implements FeedlikeService {

	@Autowired
	FeedlikeRepo feedlikeRepo;

	@Override
	public List<FollowList> selectAll(String no) {
		return feedlikeRepo.selectAll(no);
	}

	@Override
	public boolean insert(Feedlike feedlike) {
		return feedlikeRepo.insert(feedlike);
	}

	@Override
	public boolean delete(Feedlike feedlike) {
		return feedlikeRepo.delete(feedlike);
	}

	@Override
	public int select(String no) {
		return feedlikeRepo.select(no);
	}

	


}
