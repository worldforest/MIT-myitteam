package com.mit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mit.dto.Member;
import com.mit.service.ChatlistService;
import com.mit.service.ChatmemberService;
import com.mit.service.MemberService;
import com.mit.service.TeamService;
import com.mit.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/chat")
public class ChatController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private UserService userService;

	@Autowired
	private TeamService teamService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private ChatmemberService chatmemberService;

	@Autowired
	private ChatlistService chatlistService;

	// 닉네임으로 채팅방 만들기
	@ApiOperation(value = "1:1방 이름 설정", notes = "1:1방 가져오기 했는데 없으면 만들고 있으면 채팅방이름 반환")
	@PostMapping("privateCaht")
	public ResponseEntity<String> privateCaht(@RequestParam("mynickname") String mynickname,
			@RequestParam("yournickname") String yournickname) {

		String roomname = null;
		// 채팅방이 없으면
		// mynickname인 사람이 있는 방에서 상대가 yournickname인 방번호가 없으면
		if (chatmemberService.select(mynickname, yournickname) == null) {
			// 새로 만들기
			roomname = mynickname.concat(",").concat(yournickname);
			chatlistService.insert(roomname);
			String roomnum = chatlistService.selectno(roomname);
			chatmemberService.insert(roomnum, mynickname);
			chatmemberService.insert(roomnum, yournickname);
		}
		// 있으면
		String roomno = chatmemberService.select(mynickname, yournickname);
		roomname = chatlistService.select(roomno);
		return new ResponseEntity<String>(roomname, HttpStatus.OK);
	}

	@ApiOperation(value = "팀 채팅방 이름 설정", notes = "no,email보내면 팀멤버들이름으로  채팅방 이름 설정")
	@PostMapping("groupChat")
	public ResponseEntity<String> groupChat(@RequestParam("no") String no,
			@RequestParam("leaderemail") String leaderemail) {

		// no, leaderemail, member 이메일, 닉네임, 파트
		List<Member> memberlist = memberService.select(no, leaderemail);
		String roomname = "";
		int size = memberlist.size();
		// 멤버의 닉네임 가져와서 방 이름 만들기
		for (Member member : memberlist) {
			String membernickname = member.getMembernickname();
			size--;
			// 마지막에 , 안붙이려고
			if (size != 0)
				roomname = roomname.concat(membernickname).concat(",");
			else
				roomname = roomname.concat(membernickname);
		}

		// 방이 없었으면
		if (chatlistService.selectno(roomname) == null) {
			// chatlist에 방 만들고
			chatlistService.insert(roomname);
		}
		return new ResponseEntity<String>(roomname, HttpStatus.OK);
	}

	@ApiOperation(value = "내가 속한 채팅방 이름 목록", notes = "nickname보내면 방 이름 목록")
	@PostMapping("selectAll")
	public ResponseEntity<List<String>> selectAll(@RequestParam String nickname) {
		List<String> roomno = chatmemberService.selectAll(nickname);
		List<String> roomname = new ArrayList<String>();
		for (String no : roomno) {
			roomname.add(chatlistService.select(no));
		}
		return new ResponseEntity<List<String>>(roomname, HttpStatus.OK);
	}

}
