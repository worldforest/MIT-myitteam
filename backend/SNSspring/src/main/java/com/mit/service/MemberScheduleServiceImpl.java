package com.mit.service;

import java.util.List;

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

	@Override
	public boolean delete(MemberSchedule memberschedule) {
		return memberScheduleRepo.delete(memberschedule);
	}

	@Override
	public List<MemberSchedule> selectAll(String no, String leaderemail) {
		return memberScheduleRepo.selectAll(no, leaderemail);
	}

	@Override
	public List<String> selectMember(String no, String leaderemail, String memberemail) {
		return memberScheduleRepo.selectMember(no, leaderemail, memberemail);
	}

}
