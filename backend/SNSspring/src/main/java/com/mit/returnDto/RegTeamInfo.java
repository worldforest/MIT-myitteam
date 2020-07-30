package com.mit.returnDto;

public class RegTeamInfo {
	int headCount;
	String task;
	String part;
	String ablity;
	String advantage;
	public int getHeadCount() {
		return headCount;
	}
	public void setHeadCount(int headCount) {
		this.headCount = headCount;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getAblity() {
		return ablity;
	}
	public void setAblity(String ablity) {
		this.ablity = ablity;
	}
	public String getAdvantage() {
		return advantage;
	}
	public void setAdvantage(String advantage) {
		this.advantage = advantage;
	}
	@Override
	public String toString() {
		return "RegTeamInfo [headCount=" + headCount + ", task=" + task + ", part=" + part + ", ablity=" + ablity
				+ ", advantage=" + advantage + "]";
	}
	
	
}
