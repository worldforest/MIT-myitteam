package com.mit.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mit.algorithm.Token;
import com.mit.dto.User;
import com.mit.returnDto.Userlogin;
import com.mit.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String PWDFAIL = "pwdfail";
	private static final String NOTUSER = "not user";
	private static final String FAIL = "fail";

	private static Token token = new Token();
	// Service
	@Autowired
	private UserService userService;

	@ApiOperation(value = "로그인 ", notes = "성공시 jwt 토큰을 반환합니다.")
	@PostMapping("login")
	public ResponseEntity<Userlogin> login(@RequestParam("email") String email, @RequestParam("pwd") String pwd) {
		User user = new User();
		Userlogin userlogin = new Userlogin();
		user.setEmail(email);
		user.setPwd(pwd);
		user = userService.login(user);
		if (user != null) {
			// 로그인 성공시
			userlogin.setData(SUCCESS);
			userlogin.setEmail(email);
			userlogin.setNickname(user.getNickname());
			userlogin.setToken(token.getToken(email));
			return new ResponseEntity<Userlogin>(userlogin, HttpStatus.OK);

		} else {
			user = new User();
			System.out.println("test >>");
			int result = userService.emailCheck(email);
			System.out.println("test <<");
			// 로그인 실패시
			// 아이디만 있는경우
			if (result != 0) {
				userlogin.setData(PWDFAIL);
			}
			// 아이디가 없는 경우
			else {
				userlogin.setData(NOTUSER);

			}
			return new ResponseEntity<Userlogin>(userlogin, HttpStatus.OK);
		}

	}

	@ApiOperation(value = "회원 가입 ", notes = "성공시 200, 실패시 에러를 반환합니다.")
	@PostMapping("join")
	public ResponseEntity<String> Join(@RequestBody User user) {
		// join되면
		if (userService.join(user)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
	}

	@ApiOperation(value = "getToken", notes = "이메일을 주면 Token을 반환합니다.")
	@PostMapping("getToken")
	public ResponseEntity<String> getToken(@RequestBody String email) {
		Token token = new Token();
		return new ResponseEntity<String>(token.getToken(email), HttpStatus.OK);
	}

	@ApiOperation(value = "getEmail", notes = "Token을 주면 email을 반환합니다. 이때 유효하지 않은 토큰은 에러처리합니다.")
	@PostMapping("getEmail")
	public ResponseEntity<String> getEmail(@RequestBody String tokenstr) {
		Token token = new Token();
		if (token.cmpToekn(tokenstr)) {
			return new ResponseEntity<String>(token.getEmail(tokenstr), HttpStatus.OK);
		}

		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
	}

	@ApiOperation(value = "checkNickname", notes = "nickname이 DB에 존재하는지 확인 / 닉네임이 존재하면 실패(FAIL) 존재하지 않으면 (SUCCES)")
	@GetMapping("checkNickname/{nickname}")
	public ResponseEntity<String> checkNickname(@PathVariable("nickname") String nickname) {
		System.out.println(nickname);
		int result = userService.nicknameCheck(nickname);
		if (result == 1) {// 있으면
			return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
		} else {
			// 있지 않으면, 사용할 수 있어
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
	}

	// userSrc 이미지 정보를 반환합니다
	// feed image 반환하기
	@ApiOperation(value = "user image 조회 ", notes = "user Image를 반환합니다. 못찾은경우 기본 image를 반환합니다.")
	@GetMapping(value = "image/{imagename}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> userImageSearch(@PathVariable("imagename") String imagename) throws IOException {
		InputStream imageStream = new FileInputStream("C://images/user/" + imagename);
		byte[] imageByteArray = IOUtils.toByteArray(imageStream);
		imageStream.close();
		return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
	}

	@ApiOperation(value = "findPwd", notes = "email과 일치하는 비밀번호 알려주기") // 이메일로 보내기
	@PostMapping("findPwd")
	public String findPwd(User user) {
		String result = userService.findPwd(user.getEmail());
		return result;
	}

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
