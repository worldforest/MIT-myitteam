package com.mit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.repo.FeedlikeRepo;

@Service
public class FeedlikeServiceImpl implements FeedimageService {

	@Autowired
	FeedlikeRepo feedlikeRepo;

	@Override
	public List<String> select(int no) {
		// TODO Auto-generated method stub
		return null;
	}

}
