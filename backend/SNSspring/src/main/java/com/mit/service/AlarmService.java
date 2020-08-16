package com.mit.service;

import java.util.List;

import com.mit.dto.Alarm;

public interface AlarmService {
	public boolean insert(Alarm alarm);

	public List<Alarm> select(String address);

	public int count(String address);

	public boolean update(String no);

	public boolean delete(String no);
}
