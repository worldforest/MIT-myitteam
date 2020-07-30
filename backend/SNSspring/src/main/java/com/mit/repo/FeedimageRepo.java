package com.mit.repo;

import java.util.List;

public interface FeedimageRepo {
	List<String> select(String no);
	boolean insert(String no, String src);
}
