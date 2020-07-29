package com.mit.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.mit.dto.Team;
import com.mit.repo.TeamRepo;

public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepo teamRepo;
	
	@Override
	public boolean insert(Team team) {
		return teamRepo.insert(team);
	}

}
