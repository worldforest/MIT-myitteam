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
	@ApiOperation(value = "1:1방 이름 설정", notes = "1:1방 가져오기 했는데 '내 메일, 상대 메일'로 채팅방 이름 설정")
	@PostMapping("createprivate")
	public ResponseEntity<String> createprivate(@RequestParam("mynickname") String mynickname,
			@RequestParam("yournickname") String yournickname) {

		String roomname = mynickname.concat(",").concat(yournickname);
		chatlistService.insert(roomname);
		String roomnum = chatlistService.selectno(roomname);
		chatmemberService.insert(roomnum, mynickname);
		chatmemberService.insert(roomnum, yournickname);
		return new ResponseEntity<String>(roomname, HttpStatus.OK);
	}

	@ApiOperation(value = "나의 1:1방 가져오기", notes = "'내 메일, 상대 메일'로 채팅방 이름 가져오기")
	@PostMapping("findfrivate")
	public ResponseEntity<String> findfrivate(@RequestParam("mynickname") String mynickname,
			@RequestParam("yournickname") String yournickname) {
		String no = chatmemberService.select(mynickname, yournickname);
		if (no != null) {
			String roomname = chatlistService.select(no);
			return new ResponseEntity<String>(roomname, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
	}

	@ApiOperation(value = "팀 채팅방 이름 설정", notes = "no,email보내면 팀멤버들이름으로  채팅방 이름 설정")
	@PostMapping("creategroupChat")
	public ResponseEntity<String> groupChat(@RequestParam("no") String no,
			@RequestParam("leaderemail") String leaderemail) {

		teamService.selectnoemail(no, leaderemail);
		List<Member> member = memberService.select(no, leaderemail);
		String roomname = "";
		int size = member.size();
		for (Member mem : member) {
			String nick = userService.selectNickname(mem.getMemberemail());
			size--;
			// 마지막에 , 안붙이려고
			if (size != 0)
				roomname = roomname.concat(nick).concat(",");
			else
				roomname = roomname.concat(nick);
		}

		chatlistService.insert(roomname);
		String roomnum = chatlistService.selectno(roomname);
		for (Member mem : member) {
			String nickname = userService.selectNickname(mem.getMemberemail());

			chatmemberService.insert(roomnum, nickname);
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
