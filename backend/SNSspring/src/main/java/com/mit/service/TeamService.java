package com.mit.service;

import java.util.List;

import com.mit.dto.Team;

public interface TeamService {
	public boolean insert(Team team);

	public List<Team> select(String no);
}
