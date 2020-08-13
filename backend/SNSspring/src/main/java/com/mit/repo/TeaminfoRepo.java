package com.mit.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mit.dto.Teaminfo;

@Repository
public interface TeaminfoRepo {
	public boolean insert(Teaminfo teaminfo);

	public List<Teaminfo> select(String no, String leaderemail);
	
	public String selectHeadcount(String no, String leaderemail, String part);

	public boolean update(String no, String leaderemail, String part, String headcount);

	public boolean delete(Teaminfo teaminfo);
	
	public int countHead(String no, String leaderemail);
}
