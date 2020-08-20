package com.mit.repo;

import java.util.List;

import com.mit.dto.Alarm;

public interface AlarmRepo {
	public boolean insert(Alarm alarm);

	public List<Alarm> select(String address);

	public int count(String address);

	public boolean update(String no);

	public boolean delete(String no);
}
