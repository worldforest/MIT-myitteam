package com.mit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

import com.mit.dto.Alarm;
import com.mit.dto.Applymember;
import com.mit.dto.Contents;
import com.mit.dto.Member;
import com.mit.dto.MemberSchedule;
import com.mit.dto.Team;
import com.mit.dto.Teaminfo;
import com.mit.returnDto.RegTeam;
import com.mit.returnDto.RegTeamInfo;
import com.mit.returnDto.TeamDto;
import com.mit.service.AlarmService;
import com.mit.service.ApplymemberService;
import com.mit.service.ContentsService;
import com.mit.service.MemberScheduleService;
import com.mit.service.MemberService;
import com.mit.service.TeamService;
import com.mit.service.TeaminfoService;
import com.mit.service.UserService;

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
	private UserService userService;
	@Autowired
	private TeamService teamService;
	@Autowired
	private TeaminfoService teaminfoService;
	@Autowired
	private ContentsService contentsService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private ApplymemberService applymemberService;
	@Autowired
	private MemberScheduleService memberScheduleService;
	@Autowired
	private AlarmService alramService;

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
		for (RegTeamInfo regTeamInfo : regTeam.getDataList()) {
			Teaminfo teaminfo = new Teaminfo();
			teaminfo.setNo(no);
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

		Member member = new Member();
		member.setPart("팀장");
		member.setLeaderemail(regTeam.getEmail());
		member.setMemberemail(regTeam.getEmail());
		member.setNo(no);

		if (!memberService.insert(member))
			return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);

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

		Member member = new Member();
		member.setPart("팀장");
		member.setLeaderemail(regTeam.getEmail());
		member.setMemberemail(regTeam.getEmail());
		member.setNo(regTeam.getNo());

		if (!memberService.insert(member))
			return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);

		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value = "공모전, 프로젝트 번호로 해당 contents에 등록된 전체 팀 목록을 조회", notes = "팀 전체목록 list로 반환(프로젝트는 팀이 하나)")
	@PostMapping("contentsteamlist")
	public ResponseEntity<List<RegTeam>> contentsTeamList(@RequestParam("no") String no) {

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
				regTeamList.get(i).getTitle();
			}
		}

		return new ResponseEntity<List<RegTeam>>(regTeamList, HttpStatus.OK);
	}

	@ApiOperation(value = "나의 팀관리 정보", notes = "나의 이메일을 이용해서 현재 내가 멤버로 속한 모든 팀에대한 List 반환")
	@PostMapping("myteamlist")
	public ResponseEntity<List<TeamDto>> myteamlist(@RequestParam("email") String email) {
		List<TeamDto> teams = new ArrayList<TeamDto>();

		// email이 속한 모든 멤버를 가져온다 여기서 no 와 leaderemail를 서치한다 이것을 기준으로 필요한 데이터를 만든다.
		// 내가 멤버로 있는 팀 정보 가져와서
		List<Member> members = memberService.selectEmail(email);

		for (Member member : members) {
			// 그 팀의 no와 leaderemail
			String no = member.getNo();
			String leaderemail = member.getLeaderemail();
			// 팀을 찾고
			Team team = teamService.selectnoemail(no, leaderemail);
			
			// 어떤 공모전, 프로젝트인지 찾고
			Contents contents = contentsService.selectOne(no);

			// 필요한 정보들 저장하고
			TeamDto teamDto = new TeamDto();
			teamDto.setCategory(contents.getCategory() + "");
			teamDto.setDescription(team.getDescription());
			teamDto.setEnd(contents.getEnd());
			teamDto.setStart(contents.getStart());
			teamDto.setHost(contents.getHost());
			teamDto.setImagesrc(contents.getImagesrc());
			teamDto.setLeaderemail(team.getLeaderemail());
			teamDto.setLocal(team.getLocal());
			teamDto.setNo(contents.getNo());
			teamDto.setRegemail(contents.getEmail());
			teamDto.setReward(contents.getReward());
			teamDto.setTitle(team.getTitle());

			teamDto.setAllCnt(teaminfoService.countHead(no, leaderemail) + "");

			// 그 팀의 멤버들 가져와서 가져오고
			teamDto.setMembers(memberService.select(no, leaderemail));
			// 그 팀에 지원한 사람들 가져오고
			teamDto.setApplymembers(applymemberService.select(no, leaderemail));

			teams.add(teamDto);
		}
		

		return new ResponseEntity<List<TeamDto>>(teams, HttpStatus.OK);
	}

	@ApiOperation(value = "팀 지원하기", notes = "팀 정보(번호, 리더메일)과 나의 이메일과 지원파트를 applymember에 저장")
	@PostMapping("applyTeam")
	public ResponseEntity<String> applyTeam(@RequestParam("no") String no,
			@RequestParam("leaderemail") String leaderemail, @RequestParam("part") String part,
			@RequestParam("email") String email) {
		
		Applymember applymember = new Applymember();
		applymember.setNo(no);
		applymember.setLeaderemail(leaderemail);
		applymember.setPart(part);
		applymember.setTeamemail(email);
		applymemberService.insert(applymember);
		Alarm alram = new Alarm();
		String leadernickname = userService.selectNickname(leaderemail);
		alram.setAddressee(leadernickname);// 팀장에게
		String membernickname = userService.selectNickname(email);
		alram.setSender(membernickname);// 내가
		alram.setMessage(membernickname + "님이 팀에 지원했습니다.");
		alram.setFlag("0");
		alramService.insert(alram);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value = "팀원 확정하기", notes = "no, leaderemail(팀 구분), part(팀원 파트), teamemail(팀원 메일)")
	@PostMapping("selectMember")
	public ResponseEntity<String> selectMember(@RequestParam("no") String no,
			@RequestParam("leaderemail") String leaderemail, @RequestParam("part") String part,
			@RequestParam("teamemail") String teamemail) {

		// applymember에 없으면 fail
		if ((applymemberService.selectOne(no, leaderemail, teamemail)) != 1) {
			return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
		}
		// applymember에 등록되어있으면
		else {
			// applymember에서 지우고
			applymemberService.delete(no, leaderemail, teamemail, part);
			// member에 등록하고
			Member member = new Member();
			member.setNo(no);
			member.setLeaderemail(leaderemail);
			member.setPart(part);
			String membernickname = userService.selectNickname(teamemail);
			member.setMembernickname(membernickname);
			member.setMemberemail(teamemail);
			if (memberService.insert(member)) {
			}
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);

		}

	}

	@ApiOperation(value = "팀원 거절하기", notes = "no, leaderemail(팀 구분), part(팀원 파트), teamemail(팀원 메일)")
	@PostMapping("deleteMember")
	public ResponseEntity<String> deleteMember(@RequestParam("no") String no,
			@RequestParam("leaderemail") String leaderemail, @RequestParam("part") String part,
			@RequestParam("teamemail") String teamemail) {

		if (applymemberService.delete(no, leaderemail, teamemail, part)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);

	}

	@ApiOperation(value = "팀 삭제하기", notes = "팀장이 팀 삭제(팀장메일과 로그인된 이메일이랑 같을때만 동작가능하게)")
	@PostMapping("deleteTeam")
	public ResponseEntity<String> deleteTeam(@RequestParam("no") String no,
			@RequestParam("leaderemail") String leaderemail) {

		teamService.delete(no, leaderemail);

		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@ApiOperation(value = "팀원 일정 등록하기", notes = "팀원별 일정 등록")
	@PostMapping("insetSchedule")
	public ResponseEntity<String> insertSchedule(@RequestBody MemberSchedule memberschedule) {

		if (memberScheduleService.insert(memberschedule)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
	}

	@ApiOperation(value = "팀원 일정 삭제하기", notes = "팀원별 일정 삭제(no, leaderemail로 팀 구분, memberemail로 팀원 구분, date로 원하는 날짜 선택")
	@PostMapping("deleteSchedule")
	public ResponseEntity<String> deleteSchedule(@RequestBody MemberSchedule memberschedule) {

		if (memberScheduleService.delete(memberschedule)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.EXPECTATION_FAILED);
	}

	@ApiOperation(value = "팀 회의날짜 선정하기", notes = "팀원별 일정 등록")
	@PostMapping("selectSchedule")
	public ResponseEntity<List<String>> selectSchedule(@RequestParam String no, @RequestParam String leaderemail) {

		// 그 팀의 member들 일정 가져오기
		List<MemberSchedule> memberschedule = memberScheduleService.selectAll(no, leaderemail);

		// 팀원들이 가장 많이 되는 날 찾기위해 정보 저장
		Map<String, Integer> selectSchedule = new HashMap<>();

		for (MemberSchedule schedule : memberschedule) {
			String memberdate = schedule.getDate();// 팀원 일정

			// 날짜는 key로 두고 몇명이 되는지 체크
			// date가 있으면
			if (selectSchedule.containsKey(memberdate)) {
				// 그 날짜 date에 value(되는 사람 수)++
				selectSchedule.put(memberdate, selectSchedule.get(memberdate) + 1);
			} else {
				// 없으면 map에 추가
				selectSchedule.put(memberdate, 1);
			}
		}

		// map-->iterator
		// 확인하는 방법
		Iterator<String> keys = selectSchedule.keySet().iterator();

		int memberCnt = memberService.countMember(no, leaderemail);
		System.out.println(memberCnt);
		List<String> selectDate = new ArrayList<>();
		while (keys.hasNext()) {
			String key = keys.next();
			// selectSchedule.get(key)는 그 날짜에 몇명 key는 날짜(date)
			int total = selectSchedule.get(key);
			if (total == memberCnt) {
				selectDate.add(key);
			}
		}
		return new ResponseEntity<List<String>>(selectDate, HttpStatus.OK);
	}

	@ApiOperation(value = "팀원 일정 확인", notes = "no, leaderemail로 팀 구분하고 memberemail로 팀원 구분후 팀원별 일정 확인")
	@PostMapping("memberSchedule")
	public ResponseEntity<List<String>> memberSchedule(@RequestParam String no, @RequestParam String leaderemail,
			@RequestParam String memberemail) {
		List<String> memberSchedule = memberScheduleService.selectMember(no, leaderemail, memberemail);
		return new ResponseEntity<List<String>>(memberSchedule, HttpStatus.OK);
	}

	@ApiOperation(value = "팀원 총인원 구하기", notes = "contents no 와 leaderemail을 등록하면 총 수를 반환합니다 리더수 포함.")
	@PostMapping("countTeam")
	public ResponseEntity<Integer> contTeam(@RequestParam String no, @RequestParam String leaderemail) {
		return new ResponseEntity<Integer>(memberService.countMember(no, leaderemail), HttpStatus.OK);
	}

}