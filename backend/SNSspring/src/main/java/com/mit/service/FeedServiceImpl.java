package com.mit.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.dto.Feed;
import com.mit.dto.User;
import com.mit.repo.FeedRepo;

@Service
public class FeedServiceImpl implements FeedService {

	@Autowired
	private FeedRepo feedRepo;

	@Override
	public List<Feed> selectAll() {
		return feedRepo.selectAll();
	}

	@Override
	public List<Feed> selectEmail(String email) {
		return feedRepo.selectEmail(email);
	}

	@Override
	public Feed selectOne(String no) {
		return feedRepo.selectOne(no);
	}

	@Override
	public boolean insert(Feed feed) {
		return feedRepo.insert(feed);
	}

	@Override
	public boolean update(Feed feed) {
		return feedRepo.update(feed);
	}

	@Override
	public boolean delete(String no,String email) {
		return feedRepo.delete(no,email);
	}

	@Override
	public boolean deletetags(String no) {
		return feedRepo.deletetags(no);
	}

	@Override
	public String Latestfeed(String email) {
		return feedRepo.Latestfeed(email);
	}

	@Override
	public User selectPrivate(String email) {
		return feedRepo.selectPrivate(email);
	}

	@Override
	public Feed selectno(String no) {
		return feedRepo.selectno(no);
	}

	@Override
	public Feed slectnoemail(String no, String email) {
		return feedRepo.slectnoemail(no, email);
	}

	@Override
	public boolean viewsplus(String no) {
		return feedRepo.viewsplus(no);
	}

}
