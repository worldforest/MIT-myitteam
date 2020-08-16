package com.mit.returnDto;

import java.util.List;

import com.mit.dto.Applymember;
import com.mit.dto.Member;

public class TeamDto {
	// 공모전 또는 프로젝트의 고유번호
	String no;
	// 공모전 : 0, 프로젝트 : 1 로 분류한다.
	String category;
	// 공모전 또는 프로젝트 등록 이메일 contents 테이블에 잇는 이메일임
	String regemail;
	// 팀등록한 이메일
	String leaderemail;
	// 팀 타이틀 이메일
	String title;
	// 지역
	String local;
	// 팀 설명
	String description;
	// 팀 시작 날자
	String start;
	// 팀 끝 날자
	String end;
	// 상품
	String reward;
	// 개최자
	String host;
	// 뭐 대표이미지 ??? <이건 만드는 창안만들면 직접넣어야함 크롤링한값>
	String imagesrc;
	// 공모전 관련 URL
	String url;
	// 역할별 총수
	String allCnt;

	public String getAllCnt() {
		return allCnt;
	}

	public void setAllCnt(String allCnt) {
		this.allCnt = allCnt;
	}

	// applymembers 대기자 명단
	List<Applymember> applymembers;
	// members 현재 팀원 명단
	List<Member> members;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRegemail() {
		return regemail;
	}

	public void setRegemail(String regemail) {
		this.regemail = regemail;
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

	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getImagesrc() {
		return imagesrc;
	}

	public void setImagesrc(String imagesrc) {
		this.imagesrc = imagesrc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Applymember> getApplymembers() {
		return applymembers;
	}

	public void setApplymembers(List<Applymember> applymembers) {
		this.applymembers = applymembers;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

}
