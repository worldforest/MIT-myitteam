package com.mit.service;

import java.util.List;

public interface FeedtagService {
	public boolean insert(String no, String tag);
	public List<String> selectno(String tag);

}
