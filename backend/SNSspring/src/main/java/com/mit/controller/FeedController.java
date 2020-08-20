package com.mit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

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

import com.mit.algorithm.Path;
import com.mit.algorithm.Token;
import com.mit.dto.Alarm;
import com.mit.dto.Feed;
import com.mit.dto.Feedlike;
import com.mit.dto.User;
import com.mit.returnDto.FollowList;
import com.mit.returnDto.PrivateFeed;
import com.mit.service.AlarmService;
import com.mit.service.FeedService;
import com.mit.service.FeedimageService;
import com.mit.service.FeedlikeService;
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
	private static Path path = new Path();
	private static Token token = new Token();

	@Autowired
	private FeedService feedService;
	@Autowired
	private FeedimageService feedimageService;
	@Autowired
	private FeedlikeService feedlikeService;
//	@Autowired
//	private FeedreplyService feedreplyService;
//	@Autowired
//	private FeedscrapService feedscrapService;
	@Autowired
	private FeedtagService feedtagService;
	@Autowired
	private FollowService followService;
	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private UserService userService;
	@Autowired
	private AlarmService alramService;

	@ApiOperation(value = "피드 등록 ", notes = "성공시 200, 실패시 에러를 반환합니다. \n ")
	@PostMapping("create")
	public ResponseEntity<String> createFeed(@RequestParam("email") String email,
			@RequestParam(value = "category", required = false) String category,
			@RequestParam("description") String description,
			@RequestParam(value = "tags", required = false) String tags,
			@RequestParam(value = "file", required = false) MultipartFile file) {

		// 시간과 originalFilename으로 매핑 시켜서 src 주소를 만들어 낸다.
		// feed-> image 는 대표이미지 feedimage에는 추가 이미지들을 삽입한다.
		// feed tag들을 삽입한다 ,로 구분
		Feed feed = new Feed();
		feed.setEmail(email);
		if (tags != null)
			feed.setTag(tags);
		else
			feed.setTag("");

		if (category != null)
			feed.setCategory(category);
		else
			feed.setCategory("");
		feed.setDescription(description);
		Date date = new Date();
		StringBuilder sb = new StringBuilder();

		if (file == null || file.isEmpty()) {
			// file image 가 없을 경우
			sb.append("none.png");
		} else {
			sb.append(date.getTime());
			sb.append(file.getOriginalFilename());
		}
		feed.setSrc(sb.toString());

		if (feedService.insert(feed)) {
			// 파일 업로드 끝
			if (file != null && !file.isEmpty()) {
				File dest = new File(path.getIm() + "images/feed/" + sb.toString());
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
		StringTokenizer st = null;
		if (tags != null)
			st = new StringTokenizer(tags, "#");
		// no 정보 가져오기 등록 email의 최신 no를 가져온다.
		String no = feedService.Latestfeed(email);
		while (st != null && st.hasMoreTokens()) {
			String tag = st.nextToken();
			feedtagService.insert(no, tag);
		}

		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value = "피드 수정 ", notes = "성공시 200, 실패시 에러를 반환합니다. \n ")
	@PostMapping("update")
	public ResponseEntity<String> updateFeed(@RequestParam("no") String no,
			@RequestParam("description") String description,
			@RequestParam(value = "tags", required = false) String tags,
			@RequestParam(value = "file", required = false) MultipartFile file) {

		// 시간과 originalFilename으로 매핑 시켜서 src 주소를 만들어 낸다.
		// feed-> image 는 대표이미지 feedimage에는 추가 이미지들을 삽입한다.
		// feed tag들을 삽입한다 ,로 구분
		Feed feed = new Feed();
		feed.setNo(no);
		if (tags != null)
			feed.setTag(tags);
		else
			feed.setTag("");

		feed.setDescription(description);
		Date date = new Date();
		StringBuilder sb = new StringBuilder();

		if (file == null || file.isEmpty()) {
			// file image 가 없을 경우
//			sb.append("none.png");
			feed.setSrc(feed.getSrc());
		} else {
			sb.append(date.getTime());
			sb.append(file.getOriginalFilename());
		}
		feed.setSrc(sb.toString());

		if (feedService.update(feed)) {
			// 파일 업로드 끝
			if (file != null && !file.isEmpty()) {
				File dest = new File(path.getIm() + "images/feed/" + sb.toString());
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
//		else {
//			return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
//		}

		// feed tag 등록
		StringTokenizer st = null;
		if (tags != null)
			st = new StringTokenizer(tags, "#");
		while (st != null && st.hasMoreTokens()) {
			String tag = st.nextToken();
			feedtagService.insert(no, tag);
		}

		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value = "피드 삭제 ", notes = "성공시 200, 실패시 에러를 반환합니다. \n ")
	@PostMapping("delete")
	public ResponseEntity<String> deleteFeed(@RequestParam("no") String no, @RequestParam("email") String email) {
		feedService.deletetags(no);
		feedService.delete(no, email);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	// feed image 반환하기
	@ApiOperation(value = "feed image 조회 ", notes = "feed Ima	ge를 반환합니다. 못찾은경우 기본 image를 반환합니다.")
	@GetMapping(value = "image/{imagename}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> userSearch(@PathVariable("imagename") String imagename) throws IOException {
		System.out.println("test");

		InputStream imageStream = new FileInputStream(path.getIm() + "images/feed/" + imagename);
		byte[] imageByteArray = IOUtils.toByteArray(imageStream);
		imageStream.close();
		return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
	}

	@ApiOperation(value = "sns 계정 개인 페이지 조회", notes = " get 타입으로 email을 통 개인페이지를 조회합니다.\n"
			+ "Json 형태로 nickname , followerCnt , follwingCnt , description, feeds(개인피드들 정보 feed 번호, feedimagesrc 정보)Jsons 형태로 반환")
	@GetMapping(value = "{email}")
	public ResponseEntity<PrivateFeed> userPage(@PathVariable("email") String email) {

		PrivateFeed privateFeedDto = new PrivateFeed();
		System.out.println(followService.followerCnt(email));
		privateFeedDto.setFollowerCnt(followService.followerCnt(email));
		privateFeedDto.setFollowingCnt(followService.followingCnt(email));

		User user = userService.selectPrivate(email);
		privateFeedDto.setNickname(user.getNickname());
		privateFeedDto.setDescription(user.getDescription());
		privateFeedDto.setAddress(user.getAddress());

		privateFeedDto.setSrc(path.getPath() + "/mit/api/user/image/" + user.getSrc());
		List<Feed> feeds = feedService.selectEmail(email);
		for (int i = 0; i < feeds.size(); i++) {
			feeds.get(i).setSrc(path.getPath() + "/mit/api/feed/image/" + feeds.get(i).getSrc());
		}
		Collections.sort(feeds);
		privateFeedDto.setFeeds(feeds);
		privateFeedDto.setNickname(userService.selectNickname(email));
		return new ResponseEntity<PrivateFeed>(privateFeedDto, HttpStatus.OK);
	}

	@ApiOperation(value = "feed 조회", notes = "email,tag 가 없을땐 모든 feed들을 시간순으로 조회합니다.\n"
			+ "email 값만 있을 땐 email이 팔로윙하는 feed만 반환합니다.\n" + "tag만 있으면 tag 가 달린 feed들을 찾아서 조회합니다.\n" + "")
	@PostMapping("search")
	public ResponseEntity<List<Feed>> feedsearch(@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "tag", required = false) String tag) {

		List<Feed> feeds = null;
		// 모든 피드들을 전체조회힌다.
		if (email == null && tag == null) {
			feeds = feedService.selectAll();
			Collections.sort(feeds);
		}
		// email 정보가 주어지면 email 이 팔로우한 사람들의 feed를 반환한다.
		else if (email != null) {
			System.out.println("test");
			List<FollowList> follows = followService.followingList(email);

			feeds = new ArrayList<Feed>();
			for (FollowList follow : follows) {
				feeds.addAll(feedService.selectEmail(follow.getEmail()));
			}
			// Sort 한다 기준은 최신순으로 솔팅한다.
			Collections.sort(feeds);
		}
		// tag 정보가 주어지면 tag를 추가한 feed들을 반환한다.
		else if (tag != null) {
			// set으로 활용한다.
			Set<String> set = new TreeSet<String>();
			List<String> Nos = feedtagService.selectno(tag);
			for (String no : Nos) {
				set.add(no);
			}
			// 중복없이 no를 뽑앗음
			feeds = new ArrayList<Feed>();

			for (Iterator<String> it = set.iterator(); it.hasNext();) {
				feeds.add(feedService.selectno(it.next()));
			}

			// no가 늦은 순으로 하는 게 최신순입니다.
			Collections.reverse(feeds);
		}
		// 내가 팔로우 한사람중에 tag를 조회한다.
		else {
			// set으로 활용한다.
			Set<String> set = new TreeSet<String>();
			List<String> Nos = feedtagService.selectno(tag);
			for (String no : Nos) {
				set.add(no);
			}
			// 중복없이 no를 뽑앗음
			feeds = new ArrayList<Feed>();

			for (Iterator<String> it = set.iterator(); it.hasNext();) {
				feeds.add(feedService.slectnoemail(it.next(), email));
			}

			// no가 늦은 순으로 하는 게 최신순입니다.
			Collections.reverse(feeds);
		}

		for (int i = 0; i < feeds.size(); i++) {
			feeds.get(i).setSrc(path.getPath() + "/mit/api/feed/image/" + feeds.get(i).getSrc());
		}

		return new ResponseEntity<List<Feed>>(feeds, HttpStatus.OK);
	}

	@ApiOperation(value = "feed 좋아요", notes = "해당 피드 번호 no, 좋아요 누르는 사람의 email")
	@PostMapping("feedlike")
	public ResponseEntity<String> feedlike(@RequestParam String no, @RequestParam String email) {
		Feedlike feedlike = new Feedlike();
		feedlike.setNo(no);
		feedlike.setEmail(email);
		if (feedlikeService.insert(feedlike)) {
			Alarm alram = new Alarm();
			Feed feed = feedService.selectno(no);
			String addressnickname = userService.selectNickname(feed.getEmail());
			alram.setAddressee(addressnickname);
			String sendernickname = userService.selectNickname(email);
			alram.setSender(sendernickname);
			alram.setMessage(sendernickname + "님이 피드에 좋아요를 눌렀습니다.");
			alram.setFlag("0");
			alramService.insert(alram);

			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
	}

	@ApiOperation(value = "feed 좋아요 취소", notes = "해당 피드 번호 no, 좋아요 누르는 사람의 email")
	@PostMapping("feedunlike")
	public ResponseEntity<String> feedunlike(@RequestParam String no, @RequestParam String email) {
		Feedlike feedlike = new Feedlike();
		feedlike.setNo(no);
		feedlike.setEmail(email);
		if (feedlikeService.delete(feedlike)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
	}

	@ApiOperation(value = "feed 좋아요 개수", notes = "해당 피드 번호 no")
	@PostMapping("feedlikeCnt")
	public ResponseEntity<Integer> feedlikeCnt(@RequestParam String no) {

		return new ResponseEntity<Integer>(feedlikeService.select(no), HttpStatus.OK);
	}

	@ApiOperation(value = "feed 좋아요한 사람 명단", notes = "해당 피드 번호 no")
	@PostMapping("feedlikeUser")
	public ResponseEntity<List<FollowList>> feedlikeUser(@RequestParam String no) {
		List<FollowList> feedlikeuser = feedlikeService.selectAll(no);// email목록

		return new ResponseEntity<List<FollowList>>(feedlikeuser, HttpStatus.OK);
	}

	@ApiOperation(value = "feed 조회수 1증가", notes = " 해당 피드번호의 조회수를 1증가 시키는 API")
	@GetMapping("viewplus")
	public ResponseEntity<String> viewplus(@RequestParam String no) {
		if (feedService.viewsplus(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.FAILED_DEPENDENCY);
	}

}
