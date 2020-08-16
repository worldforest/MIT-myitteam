package com.mit.service;

import java.util.List;

import com.mit.dto.Teaminfo;

public interface TeaminfoService {
	public boolean insert(Teaminfo teaminfo);

	public List<Teaminfo> select(String no, String leaderemail);

	public String selectHeadcount(String no, String leaderemail, String part);

	public boolean update(String no, String leaderemail, String part, String headcount);

	public boolean delete(Teaminfo teaminfo);

	public int countHead(String no, String leaderemail);
}
