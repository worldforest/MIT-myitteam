package com.mit.controller;

import java.util.ArrayList;
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
import com.mit.returnDto.RegTeam;
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

	private static final Logger logger = LoggerFactory.getLogger(TeamController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private TeamService teamService;
	@Autowired
	private TeaminfoService teaminfoService;
	@Autowired
	private ContentsService contentsService;

	@ApiOperation(value = "프로젝트 팀을 생성합니다.", notes = "성공시 SUCESS를 반환합니다.\n" + "필요 데이터\n"
			+ "description,email(프로젝트팀 생성자),title,start,end,info")
	@PostMapping("projectteam")
	public ResponseEntity<String> projectTeamCreate(@RequestBody RegTeam regTeam) {
		// projectteam은 contens에 등록한후 contents에서 번호를 얻어와서 등록한다.
		Contents contents = new Contents();
		contents.setCategory(1);
		contents.setDescription(regTeam.getDescription());
		contents.setEmail(regTeam.getEmail());
		contents.setTitle(regTeam.getTitle());
		// start와 end는 포맷을 맞추어서 넣어주자.
		contents.setStart(regTeam.getStart());
		contents.setEnd(regTeam.getEnd());
		if (!contentsService.insert(contents))
			return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);

		Team team = new Team();
		String no = contentsService.LatestContents(regTeam.getEmail());

		team.setNo(no);
		team.setLeaderemail(regTeam.getEmail());
		team.setDescription(regTeam.getDescription());
		team.setLocal(regTeam.getLocal());
		// title은 sql 문에서 찾아온다.
		team.setTitle(contentsService.selectOne(no).getTitle());
		// DB에 team 등록
		if (!teamService.insert(team))
			return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);

		// 방금 등록한 contents 번호 얻어오기
		System.out.println(no + "제발");
		for (RegTeamInfo regTeamInfo : regTeam.getDataList()) {
			System.out.println("gogo");
			Teaminfo teaminfo = new Teaminfo();
			teaminfo.setNo(no);
			teaminfo.setLeaderemail(regTeam.getEmail());
			teaminfo.setAbility(regTeamInfo.getAbility());
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

	@ApiOperation(value = "공모전 팀을 생성합니다. ", notes = "성공시 SUCESS를 반환합니다\n" + "필요 정보 \n" + "no, email(생성자),description")
	@PostMapping("contestteam")
	public ResponseEntity<String> contestTeamCreate(@RequestBody RegTeam regTeam) {

		Team team = new Team();

		team.setNo(regTeam.getNo());
		team.setLeaderemail(regTeam.getEmail());
		team.setDescription(regTeam.getDescription());
		team.setLocal(regTeam.getLocal());
		// title은 sql 문에서 찾아온다.
		team.setTitle(contentsService.selectOne(regTeam.getNo()).getTitle());
		// DB에 team 등록
		if (!teamService.insert(team))
			return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);

		for (RegTeamInfo regTeamInfo : regTeam.getDataList()) {
			Teaminfo teaminfo = new Teaminfo();
			teaminfo.setNo(regTeam.getNo());
			teaminfo.setLeaderemail(regTeam.getEmail());
			teaminfo.setAbility(regTeamInfo.getAbility());
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

	@ApiOperation(value = "공모전, 프로젝트 번호로 해당 contents에 등록된 전체 팀 목록을 조회", notes = "팀 전체목록 list로 반환(프로젝트는 팀이 하나)")
	@PostMapping("contentsteamlist")
	public ResponseEntity<List<RegTeam>> contestTeamList(@RequestParam("no") String no) {
		
		// 해당 공모전, 프로젝트에 해당하는 팀 정보 조회
		// 공모전, 프로젝트 번호로 검색
		List<Team> teamlist = teamService.select(no);// 전체 select

		List<RegTeam> regTeamList = new ArrayList<RegTeam>();
		for (Team team : teamlist) {
			RegTeam regTeam = new RegTeam();
			regTeam.setNo(team.getNo());
			regTeam.setEmail(team.getLeaderemail());
			regTeam.setTitle(team.getTitle());
			regTeam.setLocal(team.getLocal());
			regTeam.setDescription(team.getDescription());
			
			List<Teaminfo> teaminfoList = teaminfoService.select(regTeam.getNo(), regTeam.getEmail());
			List<RegTeamInfo> regTeamInfoList = new ArrayList<RegTeamInfo>();
			for (Teaminfo teaminfo : teaminfoList) {
				RegTeamInfo regTeamInfo = new RegTeamInfo();
				regTeamInfo.setPart(teaminfo.getPart());
				regTeamInfo.setTask(teaminfo.getTask());
				regTeamInfo.setAbility(teaminfo.getAbility());
				regTeamInfo.setAdvantage(teaminfo.getAdvantage());
				regTeamInfo.setHeadCount(teaminfo.getHeadcount());

				regTeamInfoList.add(regTeamInfo);
			}
			regTeam.setDataList(regTeamInfoList);
			regTeamList.add(regTeam);
			for (int i = 0; i < regTeamList.size(); i++) {
				System.out.println(regTeamList.get(i).getTitle());
			}
		}

		return new ResponseEntity<List<RegTeam>>(regTeamList, HttpStatus.OK);
	}

}
