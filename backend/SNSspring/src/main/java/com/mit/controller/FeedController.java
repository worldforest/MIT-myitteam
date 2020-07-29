package com.mit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mit.algorithm.Token;
import com.mit.dto.Feed;
import com.mit.dto.User;
import com.mit.repo.FollowRepo;
import com.mit.repo.UserRepo;
import com.mit.returnDto.PrivateFeed;
import com.mit.service.FeedService;
import com.mit.service.FeedimageService;
import com.mit.service.FeedlikeService;
import com.mit.service.FeedreplyService;
import com.mit.service.FeedscrapService;
import com.mit.service.FeedtagService;
import com.mit.service.FollowService;
import com.mit.service.UserService;

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
	@Autowired
	private FollowService followService;
	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private UserService userService;

	@ApiOperation(value = "피드 등록 ", notes = "성공시 200, 실패시 에러를 반환합니다. \n ")
	@PostMapping("create")
	public ResponseEntity<String> createFeed(@RequestParam("email") String email,
			@RequestParam("description") String description, @RequestParam("tags") String tags,
			@RequestParam("file") MultipartFile file) {
		// 시간과 originalFilename으로 매핑 시켜서 src 주소를 만들어 낸다.
		// feed-> image 는 대표이미지 feedimage에는 추가 이미지들을 삽입한다.
		// feed tag들을 삽입한다 ,로 구분
		Feed feed = new Feed();
		feed.setEmail(email);
		feed.setTag(tags);
		feed.setDescription(description);
		Date date = new Date();
		StringBuilder sb = new StringBuilder();

		if (file.isEmpty()) {
			// file image 가 없을 경우
			sb.append("none");
		} else {
			sb.append(date.getTime());
			sb.append(file.getOriginalFilename());
		}
		feed.setSrc("http://localhost:9999/mit/api/feed/image/" + sb.toString());

		if (feedService.insert(feed)) {
			// 파일 업로드 끝
			if (!file.isEmpty()) {
				File dest = new File("C://images/feed/" + sb.toString());
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
		while (st.hasMoreTokens()) {
			String tag = st.nextToken();
			feedtagService.insert(no, tag);
		}

		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	// feed image 반환하기
	@ApiOperation(value = "feed image 조회 ", notes = "feed Ima	ge를 반환합니다. 못찾은경우 기본 image를 반환합니다.")
	@GetMapping(value = "image/{imagename}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> userSearch(@PathVariable("imagename") String imagename) throws IOException {
		System.out.println("test");
		InputStream imageStream = new FileInputStream("C://images/feed/" + imagename);
		byte[] imageByteArray = IOUtils.toByteArray(imageStream);
		imageStream.close();
		return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
	}

	@ApiOperation(value = "sns 계정 개인 페이지 조회", notes = " get 타입으로 email을 통회 개인페이지를 조회합니다.\n"
			+ "Json 형태로 nickname , followerCnt , follwingCnt , description, feeds(개인피드들 정보 feed 번호, feedimagesrc 정보)Jsons 형태로 반환")
	@GetMapping(value = "{email}")
	public ResponseEntity<PrivateFeed> userPage(@PathVariable("email") String email) {

		PrivateFeed privateFeedDto = new PrivateFeed();
		privateFeedDto.setFollowerCnt(followService.followerCnt(email));
		privateFeedDto.setFollowingCnt(followService.followingCnt(email));

		User user = userService.selectPrivate(email);
		privateFeedDto.setNickname(user.getNickname());
		privateFeedDto.setDescription(user.getDescription());
		privateFeedDto.setSrc("http://localhost:9999/mit/api/user/image/"+user.getSrc());
		List<Feed> feeds = feedService.selectEmail(email);
		for (int i=0;i<feeds.size();i++) {
			feeds.get(i).setSrc("http://localhost:9999/mit/api/feed/image/" + feeds.get(i).getSrc());
		}
		privateFeedDto.setFeeds(feeds);
		privateFeedDto.setNickname(userService.selectNickname(email));
		return new ResponseEntity<PrivateFeed>(privateFeedDto, HttpStatus.OK);
	}

}
