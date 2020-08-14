package com.mit.service;

import java.util.List;

import com.mit.dto.MemberSchedule;

public interface MemberScheduleService {
	public boolean insert(MemberSchedule memberschedule);

	public boolean delete(MemberSchedule memberschedule);

	public List<MemberSchedule> selectAll(String no, String leaderemail);

	public boolean deleteMember(MemberSchedule memberschedule);

	public List<String> selectMember(String no, String leaderemail, String memberemail);
}
