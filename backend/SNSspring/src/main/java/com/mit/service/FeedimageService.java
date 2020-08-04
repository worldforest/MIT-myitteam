package com.mit.service;

import java.util.List;

public interface FeedimageService {
	List<String> select(String no);
	boolean insert(String no, String src);
}
