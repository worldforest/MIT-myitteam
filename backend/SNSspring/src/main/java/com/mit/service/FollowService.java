package com.mit.service;

import java.util.List;

import com.mit.dto.Follow;
import com.mit.returnDto.FollowList;

public interface FollowService {
	public boolean follow(Follow follow);

	public boolean unfollow(Follow follow);

	public List<FollowList> followingList(String email);

	public List<FollowList> followerList(String email);

	public int followingCnt(String email);

	public int followerCnt(String email);
}
