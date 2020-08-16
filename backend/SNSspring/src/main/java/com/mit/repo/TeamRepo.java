package com.mit.repo;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.mit.dto.Team;

@Repository
public interface TeamRepo {
	public boolean insert(Team team);

	public List<Team> select(String no);

	public Team selectnoemail(String no, String leaderemail);

	public boolean update(Team team);

	public boolean delete(String no, String leaderemail);

	public boolean deleteNo(String no);
}
