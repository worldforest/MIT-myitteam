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
import org.springframework.web.bind.annotation.RestController;

import com.mit.dto.Contents;
import com.mit.service.ContentsService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/contents")
public class ContentsController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private ContentsService contentsService;

	@ApiOperation(value = "공모전 등록 ", notes = "성공시 200, 실패시 에러를 반환합니다. \n ")
	@PostMapping("create/contest")
	public ResponseEntity<String> createContests(@RequestBody Contents contents, HttpServletRequest request) {
//		int category=contents.getCategory();
//		if(category==0) {//공모전
//			
//			
//		}
		contents.setCategory(0);
		contentsService.insert(contents);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "프로젝트 등록 ", notes = "성공시 200, 실패시 에러를 반환합니다. \n ")
	@PostMapping("create/project")
	public ResponseEntity<String> createProject(@RequestBody Contents contents, HttpServletRequest request) {
		contents.setCategory(1);
		contentsService.insert(contents);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "공모전 조회 ", notes = "성공시 200, 실패시 에러를 반환합니다. \n ")
	@GetMapping("readAll/contest")
	public List<Contents> readContests() {
		List<Contents> contestAll = contentsService.selectAll();
		return contestAll;
	}

}
