package com.mit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.repo.ChatmemberRepo;

@Service
public class ChatmemberServiceImpl implements ChatmemberService {

	@Autowired
	private ChatmemberRepo chatmemberRepo;

	@Override
	public boolean insert(String no, String nickname) {
		return chatmemberRepo.insert(no, nickname);
	}

	@Override
	public List<String> selectAll(String nickname) {
		return chatmemberRepo.selectAll(nickname);
	}

	@Override
	public String select(String mynickname, String yournickname) {
		return chatmemberRepo.select(mynickname, yournickname);
	}

}
