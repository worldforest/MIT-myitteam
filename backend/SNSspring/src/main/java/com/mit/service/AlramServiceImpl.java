package com.mit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.dto.Alarm;
import com.mit.repo.AlarmRepo;

@Service
public class AlramServiceImpl implements AlarmService {

	@Autowired
	private AlarmRepo alramRepo;

	@Override
	public boolean insert(Alarm alram) {
		return alramRepo.insert(alram);
	}

	@Override
	public List<Alarm> select(String address) {
		return alramRepo.select(address);
	}

	@Override
	public int count(String address) {
		return alramRepo.count(address);
	}

	@Override
	public boolean update(String no) {
		return alramRepo.update(no);
	}

	@Override
	public boolean delete(String no) {
		return alramRepo.delete(no);
	}

}
