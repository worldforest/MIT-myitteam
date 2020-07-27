package com.mit.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mit.algorithm.Token;
import com.mit.dto.Feed;
import com.mit.dto.User;
import com.mit.service.FeedService;
import com.mit.service.FeedimageService;
import com.mit.service.FeedlikeService;
import com.mit.service.FeedreplyService;
import com.mit.service.FeedscrapService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/feed")
public class FeedController {
	private static final Logger logger = LoggerFactory.getLogger(FeedController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private static Token token = new Token();

	@Autowired
	private FeedService feedService;
	@Autowired
	private FeedscrapService feedscrapService;
	@Autowired
	private FeedimageService feedimageService;
	@Autowired
	private FeedlikeService feedlikeService;
	@Autowired
	private FeedreplyService feedreplyService;

	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@ApiOperation(value = "피드 등록 ", notes = "성공시 200, 실패시 에러를 반환합니다.")
	@PostMapping("create")
	public ResponseEntity<String> createFeed(@RequestBody Feed feed, @RequestParam("file") MultipartFile file,
			HttpServletRequest request) {

		Date date = new Date();
		StringBuilder sb = new StringBuilder();
		sb.append("C:/Image/");
		sb.append(file.getOriginalFilename());
		sb.append(date.getTime());
		feed.setSrc(sb.toString());
		if (feedService.insert(feed)) {
			// no는 무엇?
			// 파일 업로드 끝
			if (!file.isEmpty()) {
				File dest = new File(sb.toString());
				try {
					file.transferTo(dest);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				// db에 파일 위치랑 번호 등록
			}
		}
		// feed 나머지 등록
		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
	}

}
