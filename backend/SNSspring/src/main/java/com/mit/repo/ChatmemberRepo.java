package com.mit.repo;

public interface ChatmemberRepo {

	public boolean insert(String no, String nickname);

	public String selectAll(String nickname);

	public String select(String mynickname, String yournickname);
}
