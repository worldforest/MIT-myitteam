package com.mit.repo;

import com.mit.dto.Contents;

public interface ContentsRepo {
	public boolean insert(Contents contents);
	public boolean read(Contents contents);
	public boolean update(Contents contents);
	public boolean delete(Contents contents);
}
