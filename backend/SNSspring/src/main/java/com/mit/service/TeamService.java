package com.mit.service;

import java.util.List;

import com.mit.dto.Team;
import com.mit.dto.Teaminfo;

public interface TeamService {
	public boolean insert(Team team);

	public List<Team> select(String no);

	public Team selectnoemail(String no, String leaderemail);

	public boolean update(Team team);

	public boolean delete(Team team);
}
