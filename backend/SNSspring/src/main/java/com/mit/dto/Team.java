package com.mit.dto;

public class Team {
	String no;
	String leaderemail;
	String title;
	String local;
	String description;
	Teaminfo teaminfo;

	public Teaminfo getTeaminfo() {
		return teaminfo;
	}

	public void setTeaminfo(Teaminfo teaminfo) {
		this.teaminfo = teaminfo;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getLeaderemail() {
		return leaderemail;
	}

	public void setLeaderemail(String leaderemail) {
		this.leaderemail = leaderemail;
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

}
