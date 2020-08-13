package com.mit.service;

import java.util.List;

import com.mit.dto.Feedlike;
import com.mit.returnDto.FollowList;

public interface FeedlikeService {
	List<FollowList> selectAll(String no);

	public boolean insert(Feedlike feedlike);

	public boolean delete(Feedlike feedlike);

	public int select(String no);
}
