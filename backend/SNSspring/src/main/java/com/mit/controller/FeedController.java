package com.mit.controller;

import java.io.File;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mit.algorithm.Token;
import com.mit.dto.Feed;
import com.mit.service.FeedService;
import com.mit.service.FeedimageService;
import com.mit.service.FeedlikeService;
import com.mit.service.FeedreplyService;
import com.mit.service.FeedscrapService;
import com.mit.service.FeedtagService;

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
	@Autowired
	private FeedtagService feedtagService;

	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@ApiOperation(value = "피드 등록 ", notes = "성공시 200, 실패시 에러를 반환합니다. \n ")
	@PostMapping("create")
	public ResponseEntity<String> createFeed(@RequestParam("email") String email,
			@RequestParam("description") String description, @RequestParam("tags") String tags,
			@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		// 시간과 originalFilename으로 매핑 시켜서 src 주소를 만들어 낸다.
		// feed-> image 는 대표이미지 feedimage에는 추가 이미지들을 삽입한다.
		// feed tag들을 삽입한다 ,로 구분
		Feed feed = new Feed();
		feed.setEmail(email);
		feed.setTag(tags);
		feed.setDescription(description);
		Date date = new Date();
		StringBuilder sb = new StringBuilder();
		sb.append("C:/Image/");

		if (file.isEmpty()) {
			// file image 가 없을 경우
			sb.append("none");
		} else {
			sb.append(file.getOriginalFilename());
			sb.append(date.getTime());
		}
		feed.setSrc(sb.toString());

		if (feedService.insert(feed)) {
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
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
		}

		// feed tag 등록
		StringTokenizer st = new StringTokenizer(tags, ",");
		// no 정보 가져오기 등록 email의 최신 no를 가져온다.
		String no = feedService.Latestfeed(email);
		System.out.println(no);
		while (st.hasMoreTokens()) {
			String tag = st.nextToken();
			System.out.println(tag);
			feedtagService.insert(no, tag);
		}

		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

}
