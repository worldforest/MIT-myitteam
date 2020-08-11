package com.mit.service;

import java.util.List;

import com.mit.dto.Feedlike;

public interface FeedlikeService {
	List<String> selectAll(String no);

	public boolean insert(Feedlike feedlike);

	public boolean delete(Feedlike feedlike);

	public int select(String no);
}
