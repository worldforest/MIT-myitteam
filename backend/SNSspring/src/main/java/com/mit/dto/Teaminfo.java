package com.mit.dto;

public class Teaminfo {
	String no;
	String leaderemail;
	String part;
	String task;
	String ability;
	String advantage;
	String headcount;

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

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getAbility() {
		return ability;
	}

	public void setAbility(String ability) {
		this.ability = ability;
	}

	public String getAdvantage() {
		return advantage;
	}

	public void setAdvantage(String advantage) {
		this.advantage = advantage;
	}

	public String getHeadcount() {
		return headcount;
	}

	public void setHeadcount(String headcount) {
		this.headcount = headcount;
	}

	@Override
	public String toString() {
		return "Teaminfo [no=" + no + ", leaderemail=" + leaderemail + ", part=" + part + ", task=" + task
				+ ", ability=" + ability + ", advantage=" + advantage + ", headcount=" + headcount + "]";
	}

}
