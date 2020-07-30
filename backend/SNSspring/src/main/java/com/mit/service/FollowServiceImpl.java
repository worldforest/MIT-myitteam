package com.mit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.repo.FollowRepo;
@Service
public class FollowServiceImpl implements FollowService {
	@Autowired
	private FollowRepo followRepo;

	@Override
	public int followingCnt(String follower) {
		// TODO Auto-generated method stub
		return followRepo.followingCnt(follower);
	}

	@Override
	public int followerCnt(String following) {
		return followRepo.followerCnt(following);
	}

}
