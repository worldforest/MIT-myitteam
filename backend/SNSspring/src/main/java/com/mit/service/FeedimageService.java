package com.mit.service;

import java.util.List;

public interface FeedimageService {
	List<String> select(int no);
	boolean insert(int no, String src);
}
