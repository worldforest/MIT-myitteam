package com.mit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mit.dto.Member;
import com.mit.repo.MemberRepo;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	public MemberRepo memberRepo;

	@Override
	public List<Member> select(String no, String leaderemail) {
		return memberRepo.select(no, leaderemail);
	}

	@Override
	public boolean delete(String no, String leaderemail, String email) {
		return memberRepo.delete(no, leaderemail, email);
	}

	@Override
	public boolean insert(Member member) {
		return memberRepo.insert(member);
	}

	@Override
	public List<Member> selectEmail(String email) {
		return memberRepo.selectEmail(email);
	}

	@Override
	public int countMember(String no, String leaderemail) {
		return memberRepo.countMember(no, leaderemail);
	}

}
