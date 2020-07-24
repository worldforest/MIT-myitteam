package com.mit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mit.algorithm.Token;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/feed")
public class FeedController {
	private static final Logger logger = LoggerFactory.getLogger(FeedController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private static Token token = new Token();

//	@Autowired
//	private FeedService feedService;
//
//	@Autowired
//	private FeedscrapService feedscrapService;
//
//	@Autowired
//	private FeedimageService feedimageService;
//
//	@Autowired
//	private FeedlikeService feedlikeService;
//
//	@Autowired
//	private FeedreplyService feedreplyService;
}
