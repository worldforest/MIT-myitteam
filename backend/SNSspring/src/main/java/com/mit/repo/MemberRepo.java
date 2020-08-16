package com.mit.repo;

import java.util.List;

import com.mit.dto.Member;

public interface MemberRepo {

	public List<Member> select(String no, String leaderemail);

	public boolean delete(String no, String leaderemail, String teamemail);

	public boolean insert(Member member);

	public List<Member> selectEmail(String email);

	public int countMember(String no, String leaderemail);

}
