package com.mit.repo;

import java.util.List;

public interface ChatmemberRepo {

	public boolean insert(String no, String nickname);

	public List<String> selectAll(String nickname);

	public String select(String mynickname, String yournickname);
}
