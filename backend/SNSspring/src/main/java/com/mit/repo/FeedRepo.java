package com.mit.repo;

import java.util.List;
import java.util.Map;

import com.mit.dto.Feed;
import com.mit.dto.User;

public interface FeedRepo {
	public List<Feed> selectAll();

	public List<Feed> selectEmail(String email);

	public Feed selectOne(String no);

	public boolean insert(Feed feed);

	public boolean update(Feed feed);

	public boolean delete(String no,String email);

	public boolean deletetags(String no);

	public String Latestfeed(String email);

	public User selectPrivate(String email);

	public Feed selectno(String no);

	public Feed slectnoemail(String no, String email);
	
	public boolean viewsplus(String no);
}
