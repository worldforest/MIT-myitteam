package com.mit.service;

import java.util.List;

import com.mit.dto.Contents;

public interface ContentsService {
	public boolean insert(Contents contents);
	public List<Contents> selectAll();
	public Contents selectOne(int no);//공모전 번호로 상세정보 가져오기
}
