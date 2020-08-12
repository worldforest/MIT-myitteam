package com.mit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mit.algorithm.Path;
import com.mit.dto.Contents;
import com.mit.service.ContentsService;
import com.mit.service.TeamService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/contents")
public class ContentsController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private static Path path = new Path();

	@Autowired
	private ContentsService contentsService;

	@Autowired
	private TeamService teamService;

	@ApiOperation(value = "공모전 등록 ", notes = "성공시 200, 실패시 에러를 반환합니다. \n ")
	@PostMapping("create/contest")
	public ResponseEntity<String> createContests(@RequestBody Contents contents, HttpServletRequest request) {
		contents.setCategory(0);
		contentsService.insert(contents);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value = "모든 공모전 조회 ", notes = "JSON 형태로 공모전(category=0) 반환\n ")
	@GetMapping("readAll/contest")
	public ResponseEntity<List<Contents>> readAllContests() {
		List<Contents> contestAll = contentsService.selectAll();
		return new ResponseEntity<List<Contents>>(contestAll, HttpStatus.OK);
	}

	@ApiOperation(value = "공모전 상세정보 조회", notes = " ")
	@GetMapping("readOne/contest")
	public ResponseEntity<Contents> readOneContests(String no) {

		Contents contents = contentsService.selectOne(no);
		if (contents != null && contentsService.viewsplus(no)) {
			return new ResponseEntity<Contents>(contents, HttpStatus.OK);
		}
		return new ResponseEntity<Contents>(contents, HttpStatus.FAILED_DEPENDENCY);
	}

	@ApiOperation(value = "공모전 삭제", notes = " ")
	@GetMapping("delete")
	public ResponseEntity<String> deleteContests(String no) {
		teamService.deleteNo(no);
		if (contentsService.delete(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);

	}
}
