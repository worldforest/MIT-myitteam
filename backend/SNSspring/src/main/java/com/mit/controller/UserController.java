package com.mit.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.RestController;

import com.mit.algorithm.Token;
import com.mit.dto.User;
import com.mit.service.UserService;

import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiParam;

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

	@ApiOperation(value = "로그인 ", notes="성공시 jwt 토큰을 반환합니다.")
	@PostMapping("login")
	public ResponseEntity<String> login(@RequestBody User user) {
		user = userService.login(user);
		if (user != null) {
			String tokenstr = token.getToken(user);
			return new ResponseEntity<String>(tokenstr, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
	}


	@ApiOperation(value = "회원 가입 ", notes="성공시 200, 실패시 에러를 반환합니다.")
	@PostMapping("join")
	public ResponseEntity<String> Join(@RequestBody User user) {

		if (userService.join(user)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
	}

 
	
	@ApiOperation(value = "getToken",notes="이메일을 주면 Token을 반환합니다.")
	@PostMapping("getToken")
	public ResponseEntity<String> getToken(@RequestBody String email) {
		Token token = new Token();
		return new ResponseEntity<String>(token.getToken(email), HttpStatus.OK);
	}

	@ApiOperation(value = "getEmail", notes="Token을 주면 email을 반환합니다. 이때 유효하지 않은 토큰은 에러처리합니다.")
	@PostMapping("getEmail")
	public ResponseEntity<String> getEmail(@RequestBody String tokenstr) {
		Token token = new Token();
		if (token.cmpToekn(tokenstr)) {
			return new ResponseEntity<String>(token.getEmail(tokenstr), HttpStatus.OK);
		}

		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
	}

	@ApiOperation(value = "checkEmail", notes = "email이 DB에 존재하는지 확인")
	@PostMapping("checkEmail")
	public int emailCheck(User user) {
		int result = userService.emailCheck(user);
		return result;
	}

	@ApiOperation(value = "checkNickname", notes = "nickname이 DB에 존재하는지 확인 / 닉네임이 존재하면 실패(FAIL) 존재하지 않으면 (SUCCES)")
	@PostMapping("checkNickname")
	public ResponseEntity<String> checkNickname(@RequestParam("nickname") String nickname) {
		int result = userService.nicknameCheck(nickname);
		if (result == 1) {//있으면
			return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
		}
		//있지 않으면, 사용할 수 있어
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

//	@ApiOperation(value = "findPwd", notes="email과 일치하는 비밀번호 알려주기")//이메일로 보내기
//	@PostMapping("checkEmail")
//	public int findPwd(User user) {
//		int result = userService.emailCheck(user);
//		return result;
//	}

	// 비밀번호 변경
	// 테스트
//	@ApiOperation(value = "비밀번호변경",notes="비밀번호 변경 성공시 success 실패시 에러를 반환합니다.")
//	@PutMapping("updatepwd")
//	public ResponseEntity<String> update(@RequestBody Map<String, String> map) {
//		Token token = new Token();
//		if (token.cmpToekn(map.get("tokenstr"))) {
//			return new ResponseEntity<String>(token.getEmail(map.get("tokenstr")), HttpStatus.OK);
//		}
//
//		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
//	}


//	@ApiOperation(value = "email, pwd 정보를 주면 비밀번호를 변경한다.")
//	@PutMapping
//	public ResponseEntity<String> updateMember(@RequestBody User user) {
//		if (userService.updateMember(user)) {
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		}
//		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
//	}

}
