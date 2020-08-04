package com.mit.repo;

import java.util.List;


import com.mit.dto.Likefeed;

public interface FeedlikeRepo {
	// 그피드를 좋아요하는 모든 nickname 반환
	List<String> selectAll(String no);
	boolean insert(Likefeed likefeed);
	boolean delete(Likefeed likefeed);

}
