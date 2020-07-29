package com.mit.repo;

import org.springframework.stereotype.Repository;

import com.mit.dto.Team;

@Repository
public interface TeamRepo {
	public boolean insert(Team team);
}
