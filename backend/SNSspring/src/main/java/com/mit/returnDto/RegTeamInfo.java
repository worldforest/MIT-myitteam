package com.mit.returnDto;

public class RegTeamInfo {
	String headCount;
	String task;
	String part;
	String ability;
	String advantage;
	public String getHeadCount() {
		return headCount;
	}
	public void setHeadCount(String headCount) {
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
		return ability;
	}
	public void setAblity(String ablity) {
		this.ability = ablity;
	}
	public String getAdvantage() {
		return advantage;
	}
	public void setAdvantage(String advantage) {
		this.advantage = advantage;
	}
	@Override
	public String toString() {
		return "RegTeamInfo [headCount=" + headCount + ", task=" + task + ", part=" + part + ", ablity=" + ability
				+ ", advantage=" + advantage + "]";
	}
	
	
}
