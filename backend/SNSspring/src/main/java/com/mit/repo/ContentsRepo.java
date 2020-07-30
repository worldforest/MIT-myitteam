package com.mit.repo;

import java.util.List;

import com.mit.dto.Contents;

public interface ContentsRepo {
	public boolean insert(Contents contents);
	public List<Contents> selectAll();
	public Contents selectOne(int no);
	public boolean update(Contents contents);
	public boolean delete(Contents contents);
}
