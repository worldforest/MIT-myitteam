package com.mit.repo;

import java.util.List;

import com.mit.dto.MemberSchedule;

public interface MemberScheduleRepo {
	public boolean insert(MemberSchedule memberschedule);

	public boolean delete(MemberSchedule memberschedule);

	public List<MemberSchedule> selectAll(String no, String leaderemail);
	
	public List<String> selectMember(String no, String leaderemail,String memberemail);	

}
