package com.mit.repo;

import java.util.List;

import com.mit.dto.Feedlike;
import com.mit.returnDto.FollowList;

public interface FeedlikeRepo {
	// 그 피드를 좋아요하는 모든 email 반환
	List<FollowList> selectAll(String no);
	// 좋아요
	public boolean insert(Feedlike likefeed);
	// 좋아요 취소
	public boolean delete(Feedlike likefeed);
	// 그 피드 좋아요 수
	public int select(String no);

}
