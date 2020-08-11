package com.mit.service;

import com.mit.dto.Emailkey;

public interface EmailkeyService {
	public Emailkey select(String emailkey);

	public Emailkey check(Emailkey emailkey);

	public boolean update(Emailkey emailkey);
	
	public boolean insert(Emailkey emailkey);
}
