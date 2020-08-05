package com.mit.repo;

import java.util.List;

import com.mit.dto.Follow;

public interface FollowRepo {
	public boolean follow(Follow follow);

	public boolean unfollow(Follow follow);
	
	public List<String> followerList(String email);
	
	public List<String> followingList(String email);

	public int followingCnt(String email);

	public int followerCnt(String email);
}
