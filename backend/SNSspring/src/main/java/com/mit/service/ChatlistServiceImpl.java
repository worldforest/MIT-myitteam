package com.mit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.repo.ChatlistRepo;

@Service
public class ChatlistServiceImpl implements ChatlistService {

	@Autowired
	private ChatlistRepo chatlistRepo;

	@Override
	public boolean insert(String roomname) {
		return chatlistRepo.insert(roomname);
	}

	@Override
	public String select(String no) {
		return chatlistRepo.select(no);
	}

	@Override
	public String selectno(String roomname) {
		return chatlistRepo.selectno(roomname);
	}

}
