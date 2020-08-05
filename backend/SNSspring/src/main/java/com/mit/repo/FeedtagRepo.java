package com.mit.repo;

import java.util.List;

public interface FeedtagRepo {
	public boolean insert(String no, String tag);
	public List<String> selectno(String tag);
}
