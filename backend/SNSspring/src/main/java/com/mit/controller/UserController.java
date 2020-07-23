package com.mit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mit.algorithm.Token;
import com.mit.dto.User;
import com.mit.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private static Token token = new Token();
	// Service
	@Autowired
	private UserService userService;

	@ApiOperation(value = "로그인 (토큰반환) email , pwd 만 정보를 주면된다.")
	@PostMapping("login")
	public ResponseEntity<String> login(@RequestBody User user) {
		user = userService.login(user);
		if (user != null) {
			String tokenstr = token.getToken(user);
			return new ResponseEntity<String>(tokenstr, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
	}

	@ApiOperation(value = "회원 가입 회원가입에 필요한 정보를 주면된다. 등록날짜는 입력안해도된다.")
	@PostMapping("join")
	public ResponseEntity<String> Join(@RequestBody User user) {

		if (userService.join(user)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
	}

	@ApiOperation(value = "email 을 주면 token을 반환")
	@PostMapping("getToken")
	public ResponseEntity<String> getToken(@RequestBody String email) {
		Token token = new Token();
		return new ResponseEntity<String>(token.getToken(email), HttpStatus.OK);
	}

	@ApiOperation(value = "token을 주면 email을 반환")
	@PostMapping("getEmail")
	public ResponseEntity<String> getEmail(@RequestBody String tokenstr) {
		Token token = new Token();
		if (token.cmpToekn(tokenstr)) {
			return new ResponseEntity<String>(token.getEmail(tokenstr), HttpStatus.OK);
		}

		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
	}
	
	// 비밀번호 변경
	@ApiOperation(value = "비밀번호를 변경합니다.")
	@PutMapping("updatepwd")
	public ResponseEntity<String> update(@RequestBody String tokenstr) {
		Token token = new Token();
		if (token.cmpToekn(tokenstr)) {
			return new ResponseEntity<String>(token.getEmail(tokenstr), HttpStatus.OK);
		}

		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
	}
	
	
//	@ApiOperation(value = "email, pwd 정보를 주면 비밀번호를 변경한다.")
//	@PutMapping
//	public ResponseEntity<String> updateMember(@RequestBody User user) {
//		if (userService.updateMember(user)) {
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		}
//		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
//	}

}
