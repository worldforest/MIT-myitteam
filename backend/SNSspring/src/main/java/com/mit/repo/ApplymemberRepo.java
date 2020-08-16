package com.mit.repo;

import java.util.List;

import com.mit.dto.Applymember;

public interface ApplymemberRepo {
	public List<Applymember> select(String no, String leaderemail);

	public int selectOne(String no, String leaderemail, String teamemail);

	public boolean insert(Applymember applymember);

	public boolean delete(String no, String leaderemail, String teamemail, String part);

	public boolean deleteAll(String no, String leaderemail);
}
