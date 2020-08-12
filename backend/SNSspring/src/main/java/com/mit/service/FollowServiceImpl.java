package com.mit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.dto.Follow;
import com.mit.repo.FollowRepo;
import com.mit.returnDto.FollowList;

@Service
public class FollowServiceImpl implements FollowService {
	@Autowired
	private FollowRepo followRepo;

	@Override
	public boolean follow(Follow follow) {
		return followRepo.follow(follow);
	}

	@Override
	public boolean unfollow(Follow follow) {
		return followRepo.unfollow(follow);
	}

	@Override
	public List<FollowList> followingList(String email) {
		return followRepo.followingList(email);
	}

	@Override
	public List<FollowList> followerList(String email) {
		return followRepo.followerList(email);
	}

	@Override
	public int followingCnt(String email) {
		return followRepo.followingCnt(email);
	}

	@Override
	public int followerCnt(String email) {
		return followRepo.followerCnt(email);
	}

}
