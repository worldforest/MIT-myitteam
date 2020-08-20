package com.mit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mit.algorithm.Path;
import com.mit.algorithm.Pwd;
import com.mit.algorithm.Token;
import com.mit.dto.Emailkey;
import com.mit.dto.User;
import com.mit.returnDto.Userlogin;
import com.mit.service.EmailkeyService;
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
	private static Path path = new Path();
	@Autowired
	private JavaMailSender mailSender;

	private static Token token = new Token();
	// Service
	@Autowired
	private UserService userService;
	@Autowired
	private EmailkeyService emailkeyService;

	@ApiOperation(value = "로그인 ", notes = "성공시 jwt 토큰을 반환합니다.")
	@PostMapping("login")
	public ResponseEntity<Userlogin> login(@RequestParam String email, @RequestParam String pwd) {
		User user = new User();
		Userlogin userlogin = new Userlogin();
		user.setEmail(email);
		user.setPwd(pwd);
		user = userService.login(user);
		if (user != null) {
			// 로그인 성공시
			userlogin.setEmail(email);
			userlogin.setNickname(user.getNickname());
			userlogin.setToken(token.getToken(email));
			return new ResponseEntity<Userlogin>(userlogin, HttpStatus.OK);

		}
		return new ResponseEntity<Userlogin>(userlogin, HttpStatus.EXPECTATION_FAILED);
	}

	@ApiOperation(value = "회원 가입 ", notes = "성공시 200, 실패시 에러를 반환합니다.")
	@PostMapping("join")
	public ResponseEntity<String> Join(@RequestBody User user) {
		// join되면
		user.setSrc("none.png");
		if (userService.join(user)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
	}

	@ApiOperation(value = "회원 정보 수정", notes = "성공시 200, 실패시 에러를 반환합니다.")
	@PostMapping("update")
	public ResponseEntity<String> update(@RequestParam String email, @RequestParam String nickname,
			@RequestParam String pwd, @RequestParam String description, @RequestParam String address,
			@RequestParam(value = "file", required = false) MultipartFile file) {

		User user = new User();
		user.setEmail(email);
		user.setNickname(nickname);
		user.setPwd(pwd);
		user.setDescription(description);
		user.setAddress(address);

		// image고유 이름 만들기
		Date date = new Date();
		StringBuilder sb = new StringBuilder();
		if (file == null || file.isEmpty()) {
			// file image 가 없을 경우
			sb.append("none.png");
		} else {
			sb.append(date.getTime());
			sb.append(file.getOriginalFilename());
		}
		user.setSrc(sb.toString());


		if (userService.updateUser(user)) {
			// 파일을local, server에 저장
			if (file != null && !file.isEmpty()) {
				// 파일을 저장할 위치에 파일 고유 이름 파일을 저장
				File dest = new File(path.getIm() + "images/user/" + sb.toString());
				try {
					file.transferTo(dest);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else {
			return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);			
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
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

	@ApiOperation(value = "닉네임 알려주기", notes = "email 주면 nickname을 반환")
	@GetMapping("selectNickname")
	public ResponseEntity<String> selectNickname(@RequestParam("email") String email) {
		String nickname = userService.selectNickname(email);
		return new ResponseEntity<String>(nickname, HttpStatus.OK);

	}

	// userSrc 이미지 정보를 반환합니다
	// feed image 반환하기
	@ApiOperation(value = "user image 조회 ", notes = "user Image를 반환합니다. 못찾은경우 기본 image를 반환합니다.")
	@GetMapping(value = "image/{imagename}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> userImageSearch(@PathVariable("imagename") String imagename) throws IOException {
		InputStream imageStream = new FileInputStream(path.getIm() + "images/user/" + imagename);
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

	@ApiOperation(value = "등록된 이메일로 인증번호 보내기", notes = "get 방식으로 email를 보내면 email로 6자리의 인증키를 보냅니다. \n 이  때 Spring session에 저장합니다.")
	@GetMapping("pwd")
	public ResponseEntity<String> sendEmail(@RequestParam("email") String email) {
		Pwd pwd = new Pwd();
		String certification = pwd.getRnadomcode();
		Emailkey emailkey = emailkeyService.select(email);

		if (emailkey == null) {
			emailkey = new Emailkey();
			emailkey.setEmail(email);
			emailkey.setEmailkey(certification);
			emailkeyService.insert(emailkey);
		} else {
			emailkey.setEmailkey(certification);
			emailkeyService.update(emailkey);
		}

		if (pwd.sedEmail(email, certification, mailSender))
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
	}

	@ApiOperation(value = "인증번호 확인", notes = "Post 방식으로 받은 email과 code의 유효성을 검증한다. 성공하면 token을 반환한다. 실패하면 415 에러를 발생시킨다.")
	@PostMapping("pwd")
	public ResponseEntity<String> Authentication(@RequestParam("email") String email,
			@RequestParam("code") String certification) {

		Emailkey emailkey = new Emailkey();
		emailkey.setEmail(email);
		emailkey.setEmailkey(certification);
		if (emailkeyService.check(emailkey) != null) {
			Token token = new Token();
			return new ResponseEntity<String>(token.getToken(email), HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
	}

	@ApiOperation(value = "인증 완료 후 비밀번호변경", notes = "Post 방식으로 받은 email과 code의 유효성을 검증한다. 성공하면 SUCCESS 실패하면 415 에러를 발생시킨다.")
	@PutMapping("pwd")
	public ResponseEntity<String> changePwd(@RequestParam("token") String tokenstr, @RequestParam("pwd") String pwd) {
		Token token = new Token();
		if (token.cmpToekn(tokenstr)) {
			String email = token.getEmail(tokenstr);
			if (userService.update(email, pwd))
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
	}
}
