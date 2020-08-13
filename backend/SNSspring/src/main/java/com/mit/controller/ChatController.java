package com.mit.controller;

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

	// 닉네임으로 채팅방 만들기
	@ApiOperation(value = "1:1방 이름 설정", notes = "'내 메일, 상대 메일'로 채팅방 이름 설정")
	@PostMapping("privateChat")
	public ResponseEntity<String> privateChat(@RequestParam("mynickname") String mynickname,
			@RequestParam("yournickname") String yournickname) {
		
		String roomName = mynickname.concat(",").concat(yournickname);
		System.out.println(roomName);
		return new ResponseEntity<String>(roomName, HttpStatus.OK);
	}

	@ApiOperation(value = "팀 채팅방 이름 설정", notes = "팀명으로 채팅방 이름 설정")
	@PostMapping("groupChat")
	public ResponseEntity<String> groupChat(@RequestParam("no") String no,
			@RequestParam("leaderemail") String leaderemail) {

		teamService.selectnoemail(no, leaderemail);
		List<Member> member = memberService.select(no, leaderemail);
		String roomName = "";
		int size = member.size();
		for (Member mem : member) {
			String nick = userService.selectNickname(mem.getMemberemail());
			size--;
			// 마지막에 , 안붙이려고
			if (size != 0)
				roomName = roomName.concat(nick).concat(",");
			else
				roomName = roomName.concat(nick);
		}

		System.out.println(roomName);
		return new ResponseEntity<String>(roomName, HttpStatus.OK);
	}

}
