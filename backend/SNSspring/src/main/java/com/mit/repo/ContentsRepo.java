package com.mit.repo;

import java.util.List;

import com.mit.dto.Contents;

public interface ContentsRepo {
	public boolean insert(Contents contents);

	public List<Contents> selectAll();

	public Contents selectOne(String no);

	public boolean update(Contents contents);

	public String LatestContents(String email);

	public boolean delete(String no);
	
	public boolean viewsplus(String no);
}
