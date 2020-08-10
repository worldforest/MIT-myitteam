package com.mit.service;

import java.util.List;

import com.mit.dto.Team;

public interface TeamService {
	public boolean insert(Team team);

//	전체 조회(no, email, no와 email)

	public List<Team> select(String no);
	
	public Team selectnoemail(String no, String leaderemail);
}
