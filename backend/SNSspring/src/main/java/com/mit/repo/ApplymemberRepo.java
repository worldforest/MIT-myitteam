package com.mit.repo;

import java.util.List;

import com.mit.dto.Applymember;

public interface ApplymemberRepo {
	public List<Applymember> select(String no, String leaderemail);

	public boolean delete(String no, String leaderemail, String teamemail);
	public boolean insert(Applymember applymember);
}
