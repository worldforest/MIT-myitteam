package com.mit.service;

public interface ChatmemberService {
	public boolean insert(String no, String nickname);

	public String selectAll(String nickname);

	public String select(String mynickname, String yournickname);
}
