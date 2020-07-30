package com.mit.repo;

public interface FollowRepo {
	public int followingCnt(String follower);
	public int followerCnt(String following);
}
