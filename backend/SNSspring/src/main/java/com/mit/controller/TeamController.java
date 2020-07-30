package com.mit.controller;

import java.util.List;

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

import com.mit.dto.Contents;
import com.mit.dto.Team;
import com.mit.dto.Teaminfo;
import com.mit.returnDto.RegTeamInfo;
import com.mit.service.ContentsService;
import com.mit.service.TeamService;
import com.mit.service.TeaminfoService;

import io.swagger.annotations.ApiOperation;

//팀원 
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/team")
public class TeamController {

	@Autowired
	private TeamService teamService;
	@Autowired
	private TeaminfoService teaminfoService;
	@Autowired
	private ContentsService contentsService;

	private static final Logger logger = LoggerFactory.getLogger(TeamController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@ApiOperation(value = "프로젝트 팀을 생성합니다.", notes = "성공시 SUCESS를 반환합니다.")
	@PostMapping("projectteam")
	public ResponseEntity<String> projectTeamCreate(@RequestParam("title") String title,
			@RequestParam("email") String email, @RequestParam("description") String description,
			@RequestParam("local") String local, @RequestParam("start") String start, @RequestParam("end") String end,
			@RequestBody List<RegTeamInfo> datalist) {
		// projectteam은 contens에 등록한후 contents에서 번호를 얻어와서 등록한다.
		Contents contents = new Contents();
		contents.setCategory(1);
		contents.setDescription(description);
		contents.setEmail(email);
		contents.setTitle(title);
		// start와 end는 포맷을 맞추어서 넣어주자.
		contents.setStart(start);
		contents.setEnd(end);
		if (!contentsService.insert(contents))
			return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);

		Team team = new Team();
		String no = contentsService.LatestContents(email);

		team.setNo(no);
		team.setLeaderemail(email);
		team.setDescription(description);
		// title은 sql 문에서 찾아온다.
		team.setTitle(contentsService.selectOne(no).getTitle());
		// DB에 team 등록
		if (!teamService.insert(team))
			return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);

		// 방금 등록한 contents 번호 얻어오기
		System.out.println(no + "제발");
		for (RegTeamInfo regTeamInfo : datalist) {
			System.out.println("gogo");
			Teaminfo teaminfo = new Teaminfo();
			teaminfo.setNo(no);
			teaminfo.setLeaderemail(email);
			teaminfo.setAbility(regTeamInfo.getAblity());
			teaminfo.setPart(regTeamInfo.getPart());
			teaminfo.setTask(regTeamInfo.getTask());
			teaminfo.setAdvantage(regTeamInfo.getAdvantage());
			teaminfo.setHeadcount(regTeamInfo.getHeadCount());
			System.out.println(teaminfo);
			// DB 에 등록하기
			if (!teaminfoService.insert(teaminfo))
				return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value = "공모전 팀을 생성합니다. ", notes = "성공시 SUCESS를 반환합니다")
	@PostMapping("contestteam")
	public ResponseEntity<String> contestTeamCreate(@RequestParam("no") String no, @RequestParam("local") String local,
			@RequestParam("description") String description, @RequestParam("email") String leaderemail,
			@RequestBody List<RegTeamInfo> datalist) {

		Team team = new Team();

		team.setNo(no);
		team.setLeaderemail(leaderemail);
		team.setDescription(description);
		// title은 sql 문에서 찾아온다.
		team.setTitle(contentsService.selectOne(no).getTitle());
		// DB에 team 등록
		if (!teamService.insert(team))
			return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);

		for (RegTeamInfo regTeamInfo : datalist) {
			Teaminfo teaminfo = new Teaminfo();
			teaminfo.setNo(no);
			teaminfo.setLeaderemail(leaderemail);
			teaminfo.setAbility(regTeamInfo.getAblity());
			teaminfo.setPart(regTeamInfo.getPart());
			teaminfo.setTask(regTeamInfo.getTask());
			teaminfo.setAdvantage(regTeamInfo.getAdvantage());
			teaminfo.setHeadcount(regTeamInfo.getHeadCount());
			// DB 에 등록하기
			if (!teaminfoService.insert(teaminfo))
				return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

}
