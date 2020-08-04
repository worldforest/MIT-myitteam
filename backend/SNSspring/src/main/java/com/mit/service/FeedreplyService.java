package com.mit.service;

import java.util.List;

import com.mit.dto.Reply;

public interface FeedreplyService {
	public List<Reply> selectAll(String no);
	public boolean insert(Reply reply);
	public boolean update(Reply reply);
	public boolean delete(Reply reply);
}
