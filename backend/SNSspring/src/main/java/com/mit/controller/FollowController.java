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
	public ResponseEntity<List<String>> followingList(@RequestParam("email") String email) {
		List<String> followingList = followService.followingList(email);
		if (followingList != null) {
			System.out.println(email + "가 팔로우한 리스트(팔로잉)");
			for (int i = 0; i < followingList.size(); i++) {
				System.out.println(followingList.get(i));
			}
			return new ResponseEntity<List<String>>(followingList, HttpStatus.OK);
		}
		return new ResponseEntity<List<String>>(followingList, HttpStatus.EXPECTATION_FAILED);
	}

	@ApiOperation(value = "나를 팔로우한 리스트를 보여준다.", notes = "list로 나를 팔로잉한 사람들 리스트 보여준다")
	@PostMapping("followerList")
	public ResponseEntity<List<String>> followerList(@RequestParam("email") String email) {
		List<String> followerList = followService.followerList(email);
		if (followerList != null) {
			System.out.println(email + "를 팔로우한 리스트(팔로워)");
			for (int i = 0; i < followerList.size(); i++) {
				System.out.println(followerList.get(i));
			}
			return new ResponseEntity<List<String>>(followerList, HttpStatus.OK);
		}
		return new ResponseEntity<List<String>>(followerList, HttpStatus.EXPECTATION_FAILED);
	}

	@ApiOperation(value = "내가 팔로우한 사람 수.", notes = "")
	@PostMapping("followingCnt")
	public ResponseEntity<String> followingCnt(@RequestParam("email") String email) {
		int followingCnt = followService.followingCnt(email);
		if (followingCnt != 0) {
			System.out.println("내가 팔로우한(팔로잉) 사람 수: " + followingCnt);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
	}

	@ApiOperation(value = "나를 팔로우한(팔로워) 사람 수.", notes = "")
	@PostMapping("followerCnt")
	public ResponseEntity<String> followerCnt(@RequestParam("email") String email) {
		int followerCnt = followService.followerCnt(email);
		if (followerCnt != 0) {
			System.out.println("나를 팔로잉한 사람 수: " + followerCnt);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
	}

}