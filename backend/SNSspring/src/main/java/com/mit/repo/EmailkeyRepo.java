package com.mit.repo;

import com.mit.dto.Emailkey;

public interface EmailkeyRepo {
	public Emailkey select(String email);

	public Emailkey check(Emailkey emailkey);

	public boolean update(Emailkey emailkey);
	
	public boolean insert(Emailkey emailkey);
}
