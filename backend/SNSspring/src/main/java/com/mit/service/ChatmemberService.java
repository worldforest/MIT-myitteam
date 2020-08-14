package com.mit.service;

import java.util.List;

public interface ChatmemberService {
	public boolean insert(String no, String nickname);

	public List<String> selectAll(String nickname);

	public String select(String mynickname, String yournickname);
}
