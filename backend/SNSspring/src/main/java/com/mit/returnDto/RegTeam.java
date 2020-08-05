package com.mit.returnDto;

import java.util.List;

public class RegTeam {
	String no;
	String title;
	String local;
	String description;
	String email;
	String start;
	String end;
	List<RegTeamInfo>datalist;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public List<RegTeamInfo> getDatalist() {
		return datalist;
	}
	public void setDatalist(List<RegTeamInfo> datalist) {
		this.datalist = datalist;
	}
	
}
