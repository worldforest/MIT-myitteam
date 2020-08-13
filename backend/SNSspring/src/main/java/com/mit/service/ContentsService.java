package com.mit.service;

import java.util.List;

import com.mit.dto.Contents;

public interface ContentsService {
	public boolean insert(Contents contents);

	public List<Contents> selectAll();

	public Contents selectOne(String no);// 공모전 번호로 상세정보 가져오기

	public String LatestContents(String email);

	public boolean delete(String no);
	
	public boolean viewsplus(String no);
}
