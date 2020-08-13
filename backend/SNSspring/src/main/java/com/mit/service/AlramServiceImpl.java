package com.mit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.dto.Alram;
import com.mit.repo.AlramRepo;

@Service
public class AlramServiceImpl implements AlramService {

	@Autowired
	private AlramRepo alramRepo;

	@Override
	public boolean insert(Alram alram) {
		return alramRepo.insert(alram);
	}

	@Override
	public List<Alram> select(String address) {
		return alramRepo.select(address);
	}

	@Override
	public int count(String address) {
		return alramRepo.count(address);
	}

	@Override
	public boolean update(Alram alram) {
		return alramRepo.update(alram);
	}

	@Override
	public boolean delete(String no) {
		return alramRepo.delete(no);
	}

}
