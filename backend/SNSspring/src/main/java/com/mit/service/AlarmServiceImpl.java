package com.mit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.dto.Alarm;
import com.mit.repo.AlarmRepo;

@Service
public class AlarmServiceImpl implements AlarmService {

	@Autowired
	private AlarmRepo alarmRepo;

	@Override
	public boolean insert(Alarm alram) {
		return alarmRepo.insert(alram);
	}

	@Override
	public List<Alarm> select(String address) {
		return alarmRepo.select(address);
	}

	@Override
	public int count(String address) {
		return alarmRepo.count(address);
	}

	@Override
	public boolean update(String no) {
		return alarmRepo.update(no);
	}

	@Override
	public boolean delete(String no) {
		return alarmRepo.delete(no);
	}

}
