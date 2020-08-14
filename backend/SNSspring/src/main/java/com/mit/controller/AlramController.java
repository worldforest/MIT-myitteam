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

import com.mit.dto.Alram;
import com.mit.service.AlramService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/alram")
public class AlramController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private AlramService alramService;

	@ApiOperation(value = "알람 목록 보여주기", notes = "nickname을 보내면 목록보여주기, 성공하면 list로 보내줘")
	@PostMapping("list")
	public ResponseEntity<List<Alram>> list(@RequestParam String nickname) {

		List<Alram> alramlist = alramService.select(nickname);
		return new ResponseEntity<List<Alram>>(alramlist, HttpStatus.OK);
	}

	@ApiOperation(value = "알람 읽음 표시", notes = "no 읽음표시 원하는 알림 번호, 성공하면 SUCCESS, 실패하면 FAIL")
	@PostMapping("update")
	public ResponseEntity<String> update(@RequestParam String no) {
		// 선택한 알람 번호로 flag를 업데이트 flag=1(읽음표시)
		if (alramService.update(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
	}

	@ApiOperation(value = "알람 삭제하기", notes = "no 삭제 원하는 알림 번호, 성공하면 SUCCESS, 실패하면 FAIL")
	@PostMapping("delete")
	public ResponseEntity<String> delete(@RequestParam String no) {
		if (alramService.delete(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
	}

}
