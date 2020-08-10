package com.mit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.dto.MemberSchedule;
import com.mit.repo.MemberScheduleRepo;

@Service
public class MemberScheduleServiceImpl implements MemberScheduleService {

	@Autowired
	private MemberScheduleRepo memberScheduleRepo;
	
	@Override
	public boolean insert(MemberSchedule memberschedule) {
		return memberScheduleRepo.insert(memberschedule);
	}

}
