package com.mit.dto;

public class Feed implements Comparable<Feed>{
	String no;
	String email;
	String nickname;
	String description;
	String writedate;
	String views;
	String tag;
	String src;
	String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}

	public String getViews() {
		return views;
	}

	public void setViews(String views) {
		this.views = views;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	@Override
	public String toString() {
		return "Feed [no=" + no + ", email=" + email + ", nickname=" + nickname + ", description=" + description
				+ ", writedate=" + writedate + ", views=" + views + ", tag=" + tag + "]";
	}

	@Override
	public int compareTo(Feed o) {
		return o.writedate.compareTo(this.writedate);
	}

}
