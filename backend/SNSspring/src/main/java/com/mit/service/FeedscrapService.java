package com.mit.service;

import java.util.List;

import com.mit.dto.Feed;

public interface FeedscrapService {
	// scrap에 있는 email가지고 feed들을 반환해주는 기능이 있어야한다.
	List<Feed> selectAll(String email);
}
