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

import com.mit.algorithm.Path;
import com.mit.dto.Alarm;
import com.mit.dto.Follow;
import com.mit.returnDto.FollowList;
import com.mit.service.AlarmService;
import com.mit.service.FollowService;
import com.mit.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/follow")
public class FollowController {
	@Autowired
	private FollowService followService;
	@Autowired
	private AlarmService alramService;
	@Autowired
	private UserService userService;
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private static Path path = new Path();
	@ApiOperation(value = "팔로우를 신청합니다.", notes = "팔로우 신청시, 로그인한 계정 email=email/팔로우한 계정 email=following \n"
			+"팔로우 알람 : check=0 확인 안 한 상태, check=1 확인")
	@PostMapping("follow")
	public ResponseEntity<String> follow(@RequestParam("email") String email,
			@RequestParam("following") String following) {
		Follow follow = new Follow();
		follow.setEmail(email);
		follow.setFollowing(following);
		if (followService.follow(follow)) {
			follow.setEmail(email);
			follow.setFollowing(following);
			
			Alarm alram = new Alarm();
			String followingnickname=userService.selectNickname(following);			
			alram.setAddressee(followingnickname);
			String mynickname = userService.selectNickname(email);
			alram.setSender(mynickname);
			alram.setMessage(mynickname+"님이 팔로우 신청을 했습니다.");
			alram.setFlag("0");
			alramService.insert(alram);
			
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
	}

	@ApiOperation(value = "팔로우를 취소합니다.", notes = "팔로우 취소시, email과 following이 일치하는 data 삭제")
	@PostMapping("unfollow")
	public ResponseEntity<String> unfollow(@RequestParam("email") String email,
			@RequestParam("following") String following) {
		Follow follow = new Follow();
		follow.setEmail(email);
		follow.setFollowing(following);
		if (followService.unfollow(follow)) {
			follow.setEmail(email);
			follow.setFollowing(following);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
	}

	@ApiOperation(value = "내가 팔로우한 리스트를 보여준다.", notes = "list로 팔로우한 사람들 리스트 보여준다")
	@PostMapping("followingList")
	public ResponseEntity<List<FollowList>> followingList(@RequestParam("email") String email) {
		List<FollowList> followingList = followService.followingList(email);
		if (followingList != null) {
			System.out.println(email + "가 팔로우한 리스트(팔로잉)");
			return new ResponseEntity<List<FollowList>>(followingList, HttpStatus.OK);
		}
		return new ResponseEntity<List<FollowList>>(followingList, HttpStatus.EXPECTATION_FAILED);
	}

	@ApiOperation(value = "나를 팔로우한 리스트를 보여준다.", notes = "list로 나를 팔로잉한 사람들 리스트 보여준다")
	@PostMapping("followerList")
	public ResponseEntity<List<FollowList>> followerList(@RequestParam("email") String email) {
		List<FollowList> followerList = followService.followerList(email);
		
		if (followerList != null) {
			System.out.println(email + "를 팔로우한 리스트(팔로워)");
			return new ResponseEntity<List<FollowList>>(followerList, HttpStatus.OK);
		}
		return new ResponseEntity<List<FollowList>>(followerList, HttpStatus.EXPECTATION_FAILED);
	}

	@ApiOperation(value = "내가 팔로우한 사람 수.", notes = "")
	@PostMapping("followingCnt")
	public int followingCnt(@RequestParam("email") String email) {
		int followingCnt = followService.followingCnt(email);
		if (followingCnt!=0) {
			System.out.println("내가 팔로우한(팔로잉) 사람 수: "+followingCnt);
			return followingCnt;
		}
		return followingCnt;
	}

	@ApiOperation(value = "나를 팔로우한(팔로워) 사람 수.", notes = "")
	@PostMapping("followerCnt")
	public int followerCnt(@RequestParam("email") String email) {
		int followerCnt = followService.followerCnt(email);
		if (followerCnt!=0) {
			System.out.println("나를 팔로잉한 사람 수: "+followerCnt);
			return followerCnt;
		}
		return followerCnt;
	} 
	
}