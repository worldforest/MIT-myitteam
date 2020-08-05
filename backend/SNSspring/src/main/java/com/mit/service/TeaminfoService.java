package com.mit.service;

import java.util.List;

import com.mit.dto.Teaminfo;

public interface TeaminfoService {
	public boolean insert(Teaminfo teaminfo);

	public List<Teaminfo> select(String no, String leaderemail);
}
