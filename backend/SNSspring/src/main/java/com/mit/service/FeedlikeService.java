package com.mit.service;

import java.util.List;

import com.mit.dto.Likefeed;

public interface FeedlikeService {
	List<String> selectAll(String no);
	boolean insert(Likefeed likefeed);
	boolean delete(Likefeed likefeed);
}
