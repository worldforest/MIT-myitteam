package com.mit.repo;

import org.springframework.stereotype.Repository;

import com.mit.dto.Teaminfo;

@Repository
public interface TeaminfoRepo {
	public boolean insert(Teaminfo teaminfo);
}
