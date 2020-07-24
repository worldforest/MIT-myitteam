package com.mit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.dto.Feed;
import com.mit.repo.FeedRepo;

@Service
public class FeedServiceImpl implements FeedService {

	@Autowired
	private FeedRepo feedRepo;

	@Override
	public List<Feed> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Feed> selectNikckname(String nickname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Feed selectOne(String no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(Feed feed) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Feed feed) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String no) {
		// TODO Auto-generated method stub
		return false;
	}

}
