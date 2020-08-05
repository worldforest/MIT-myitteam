package com.mit.service;


import com.mit.dto.Teaminfo;

public interface TeaminfoService {
	public boolean insert(Teaminfo teaminfo);

	public Teaminfo select(String no, String leaderemail);
}
