package com.mit.service;

public interface ChatlistService {
	// 채팅방 이름 넣으면 저장
	public boolean insert(String roomname);

	// 채팅방 번호로 채팅방 이름 반환
	public String select(String no);

	// 채팅방 이름으로 채팅방 번호 반환
	public String selectno(String roomname);

}
