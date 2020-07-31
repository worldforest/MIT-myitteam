package com.mit.service;

import com.mit.dto.Follow;

public interface FollowService {
	public boolean follow(Follow follow);
	public boolean unfollow(Follow follow);
	public int followingCnt(String follower);
	public int followerCnt(String following);
}
