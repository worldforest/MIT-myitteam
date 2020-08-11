package com.mit.service;

import java.util.List;

import com.mit.dto.Applymember;

public interface ApplymemberService {
	public List<Applymember> select(String no, String leaderemail);

	public boolean delete(String no, String leaderemail, String teamemail);

	public boolean insert(Applymember applymember);
}
