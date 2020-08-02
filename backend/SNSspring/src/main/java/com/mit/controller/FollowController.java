package com.mit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mit.dto.Follow;
import com.mit.service.FollowService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/follow")
public class FollowController {

	@Autowired
	private FollowService followService;

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@ApiOperation(value = "팔로우를 신청합니다.", notes = "팔로우 신청시, 로그인한 계정 email=email/팔로우한 계정 email=following")
	@PostMapping("follow")
	public ResponseEntity<String> follow(@RequestParam("email") String email,
			@RequestParam("following") String following) {

		Follow follow = new Follow();
		follow.setEmail(email);
		follow.setFollowing(following);
		if (followService.follow(follow)) {
			follow.setEmail(email);
			follow.setFollowing(following);
			System.out.println(email);
			System.out.println(following);
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
}
