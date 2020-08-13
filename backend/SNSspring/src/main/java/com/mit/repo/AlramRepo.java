package com.mit.repo;

import java.util.List;

import com.mit.dto.Alram;

public interface AlramRepo {
	public boolean insert(Alram alram);

	public List<Alram> select(String address);

	public int count(String address);

	public boolean update(Alram alram);

	public boolean delete(String no);
}
