package com.mit.service;

import java.util.List;

import com.mit.dto.Alram;

public interface AlramService {
	public boolean insert(Alram alram);

	public List<Alram> select(String address);

	public int count(String address);

	public boolean update(Alram alram);

	public boolean delete(String no);
}
