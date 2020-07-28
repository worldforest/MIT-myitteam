package com.mit.dto;

public class User {
	String email;
	String pwd;
	String name;
	String nickname;
	String joindate;
	int age;
	String gender;
	String major;
	String address;
//	String city;
//	String gu;
//	String dong;
//	String details;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getJoindate() {
		return joindate;
	}

	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

//	public String getCity() {
//		return city;
//	}
//	public void setCity(String city) {
//		this.city = city;
//	}
//	public String getGu() {
//		return gu;
//	}
//	public void setGu(String gu) {
//		this.gu = gu;
//	}
//	public String getDong() {
//		return dong;
//	}
//	public void setDong(String dong) {
//		this.dong = dong;
//	}
//	public String getDetails() {
//		return details;
//	}
//	public void setDetails(String details) {
//		this.details = details;
//	}
	@Override
	public String toString() {
		return "User [email=" + email + ", pwd=" + pwd + ", name=" + name + ", nickname=" + nickname + ", joindate="
				+ joindate + ", age=" + age + ", gender=" + gender + ", major=" + major + ","+
//						+ "city=" + city + ", gu="
//				+ gu + ", dong=" + dong + ", details=" + details + 
				"address : " + address + "]";
	}
}
