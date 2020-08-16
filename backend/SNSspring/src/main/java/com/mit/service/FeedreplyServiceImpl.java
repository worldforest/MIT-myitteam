package com.mit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.dto.Reply;
import com.mit.repo.FeedreplyRepo;

@Service
public class FeedreplyServiceImpl implements FeedreplyService {

	@Autowired
	private FeedreplyRepo feedreplyRepo;

	@Override
	public List<Reply> selectAll(String no) {
		// TODO Auto-generated method stub
		return feedreplyRepo.selectAll(no);
	}

	@Override
	public boolean insert(Reply reply) {
		// TODO Auto-generated method stub
		return feedreplyRepo.insert(reply);
	}

	@Override
	public boolean update(Reply reply) {
		// TODO Auto-generated method stub
		return feedreplyRepo.update(reply);
	}

	@Override
	public boolean delete(Reply reply) {
		// TODO Auto-generated method stub
		return feedreplyRepo.delete(reply);
	}


}
