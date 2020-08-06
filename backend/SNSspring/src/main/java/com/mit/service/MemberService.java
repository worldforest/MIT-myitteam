package com.mit.service;

import java.util.List;

import com.mit.dto.Member;

public interface MemberService {
	public List<Member> select(String no, String leaderemail);
	public boolean delete(String no, String leaderemail, String email);
	public boolean insert(Member member);
}
