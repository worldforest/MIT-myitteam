import axios from 'axios'
import router from '@/router'
import cookies from 'vue-cookies'
import 'url-search-params-polyfill'
// import { apply } from 'core-js/fn/reflect'
import Swal from 'sweetalert2'


const SERVER_URL = 'http://localhost:9999/mit'
// const SERVER_URL = 'https://i3b306.p.ssafy.io/mit'

export default {
	postToken2({ commit }, info) {
		Swal.fire({
			text: '회원가입을 축하합니다!',
			width: 600,
			padding: '3em',
			backdrop: `
				rgba(0,0,123,0.4)
				url("https://pgnqdrjultom1827145.cdn.ntruss.com/img/d6/3f/d63fc54819cd3fb0c319021e2e7cd6bfee951e8ce2db9e948bd828f538272da6_v1.jpg")
				left top
				no-repeat
			`
		})
		const params = new URLSearchParams();
		params.append('email', info.data.email);
		params.append('pwd', info.data.pwd);
		axios.post(`${SERVER_URL}/api/user/login/`, params)
			.then(response => {
				commit('SET_TOKEN', response.data.token)
				// commit('GET_EMAIL', response.data.email)
				cookies.set('auth-email', response.data.email)
				router.go({ name: "Home" })
			})
	},

	login(context, loginData) {
		const params = new URLSearchParams();
		params.append('email', loginData.email);
		params.append('pwd', loginData.pwd);
		axios.post(`${SERVER_URL}/api/user/login/`, params)
			.then(response => {
				context.commit('SET_TOKEN', response.data.token)
				// commit('GET_EMAIL', response.data.email)
				cookies.set('auth-email', response.data.email)
				router.go({ name: "Home" })
			})
			.catch(() => {
				Swal.fire({
					icon: 'error',
					title: '로그인 실패',
					text: '아이디 혹은 비밀번호를 확인해주세요.',
					footer: '회원이 아니신가요?<a href="/signup">   가입하기   </a>'
				})
			})
	},
	logout() {
		cookies.remove('auth-token')
		cookies.remove('auth-email')
		router.go({ name: "Home" })
	},
	postSignup({ dispatch }, signupInfo) {
		axios.post(`${SERVER_URL}/api/user/join`, signupInfo.data)
			.then(() => {
				const loginInfo = {
					data: {
						email: signupInfo.data.email,
						pwd: signupInfo.data.pwd
					}
				}
				dispatch("postToken2", loginInfo)
			})
			.catch(() => {
				alert("사용중인 아이디가 존재합니다.");
			})
	},
	signup({ dispatch }, signupData) {
		if (signupData.email.length < 6) {
			Swal.fire({
				icon: 'error',
				title: '이메일을 입력하지 않았습니다.',
			})
		} else if ((signupData.pwd !== signupData.pwd2) || signupData.pwd === '') {
			Swal.fire({
				icon: 'error',
				title: '비밀번호가 일치하지 않습니다.',
			})
		} else if (signupData.name === '') {
			Swal.fire({
				icon: 'error',
				title: '이름을 입력해주세요.',
			})
		} else if (signupData.nickname === '') {
			Swal.fire({
				icon: 'error',
				title: '닉네임을 입력해주세요.',
			})
		} else if (signupData.age === '') {
			Swal.fire({
				icon: 'error',
				title: '나이를 입력해주세요.',
			})
		} else if (signupData.gender === '') {
			Swal.fire({
				icon: 'error',
				title: '성별을 입력해주세요.',
			})
		} else if (signupData.major === '') {
			Swal.fire({
				icon: 'error',
				title: '전공을 입력해주세요.',
			})
		} else if (signupData.description === '') {
			Swal.fire({
				icon: 'error',
				title: '소개를 입력해주세요.',
			})
		} else if (signupData.address === '') {
			Swal.fire({
				icon: 'error',
				title: '주소를 입력해주세요.',
			})
		} else {
			const signupInfo = {
				data: signupData
			}
			dispatch('postSignup', signupInfo)
		}		
	},
	checkNickname(context, nickname) {
		if (!context.state.isLoggedIn) {
			const nick = {
				data: nickname
			}
			axios.get(`${SERVER_URL}/api/user/checkNickname/${nick.data}`)
				.then(() => {
					Swal.fire({
						icon: 'success',
						title: "등록가능한 닉네임입니다.",
						width: 600
					})
				})
				.catch(() => {
					Swal.fire({
						icon: 'error',
						title: '사용중인 닉네임입니다.',
						width: 600
					})
					nickname = ''
				})
		}
	},
	postEmailToken(context) {
		axios.post(`${SERVER_URL}/api/user/getEmail`, context.state.authToken)
			.then(res => {
				cookies.set('auth-email', res.data)
				// context.commit('POST_EMAIL', res)
			})
	},
	feedCreate(context, feedData) {
		if (feedData.description === '' || feedData.file === '') {
			Swal.fire({
				icon: 'error',
				title: '필수 항목들을 입력해주세요!(사진, 소개)',
			})
		} else {
			const formdata = new FormData();
			formdata.append('description', feedData.description)
			formdata.append('email', feedData.email) 
			formdata.append('file', feedData.file)
			formdata.append('tags', feedData.tags)
			axios.post(`${SERVER_URL}/api/feed/create/`, formdata)
			.then(() => {
				Swal.fire({
					icon: 'success',
					title: '피드 등록 완료!',
					width: 600
				})
				router.push({ name: "Profile"})
			})
			.catch(()=>{
				Swal.fire({
					icon: 'error',
					title: '파일 용량을 줄여주세요 :(',
					width: 600
				})
			})
		}		
	},
	

	//////////다인///////////////
	teamregister(context, applyData){
		axios.post(`${SERVER_URL}/api/team/contestteam`, applyData)
			.then(() => {
				Swal.fire({
					icon: 'success',
					title: '성공적으로 등록하였습니다.',
					width: 600
				})
				router.push({ name: "GongmoDetail"})
			})
	},
	projectregister(context, projectData){
		var startdate = new Date(projectData.start)
		var enddate = new Date(projectData.end)
		if (startdate < enddate) {
			axios.post(`${SERVER_URL}/api/team/projectteam`, projectData)
			.then(() => {
				Swal.fire({
					icon: 'success',
					title: '성공적으로 등록하였습니다.',
					width: 600
				})
				router.push({ name: "ProjectList"})
			})
		}
		else{
			Swal.fire({
				icon: 'error',
				title: '프로젝트 기간을 확인해주세요.',
				width: 600
			})
		}
	},
	getTeamData(context, no){
		const params = new URLSearchParams();
		params.append('no', no);
		axios.post(`${SERVER_URL}/api/team/contentsteamlist`, params)
		.then(res => {
			context.commit('GETTEAMDATA', res.data)
			sessionStorage.setItem('pjtteaminfo', JSON.stringify(res.data))
		})
	},
	apply(context, sendData){
		const params = new URLSearchParams();
		params.append('no', sendData.no);
		params.append('leaderemail', sendData.leaderemail);
		params.append('email', sendData.email);
		params.append('part', sendData.part);
		axios.post(`${SERVER_URL}/api/team/applyTeam`, params)
		.then(()=> {
			Swal.fire({
				icon: 'success',
				title: '팀장에게 프로필을 전달하였습니다.',
				width: 600
			})
		})
		.catch(() => {
			Swal.fire({
				icon: 'error',
				title: '이미 지원한 팀 입니다.',
				width: 600
			})
			router.push({name: 'GongmoDetail'})
		})
	},
	deleteTeam(context, deleteData){
		const params = new URLSearchParams();
		params.append('no', deleteData.no);
		params.append('leaderemail', deleteData.leaderemail);	
		axios.post(`${SERVER_URL}/api/team/deleteTeam`, params)
		.then(() => {
			Swal.fire({
				icon: 'info',
				title: '등록한 팀이 삭제되었습니다.',
				width: 600
			})
			router.push({ name: "AllContest" })
		})
	},
	updateCard(context, updateData){
		const params = new URLSearchParams();
		params.append('no', updateData.no);
		params.append('leaderemail', updateData.leaderemail);
		params.append('part', updateData.part);
		params.append('headcount', updateData.headcount);
		params.append('ability', updateData.ability);
		params.append('task', updateData.task);
		params.append('advantage', updateData.advantage);
		axios.post(`${SERVER_URL}/api/team/updateTeaminfo`, params)
		.then(() => {
			Swal.fire({
				icon: 'info',
				title: '등록한 프로젝트가 삭제되었습니다.',
				width: 600
			})
			router.push({ name: "ProjectList" })
		})
	},
	like(context, likeData){
		if (context.state.email !== "") {
			const params = new URLSearchParams();
			params.append('no', likeData.no),
			params.append('email', likeData.email)
			axios.post(`${SERVER_URL}/api/feed/feedlike`, params)
			.then(() => {
				context.dispatch('likeCnt', likeData)
				context.dispatch('likeUser', likeData)
			})
			.catch(()=>{
				
			})
		}		
	},
	unlike(context, likeData){
		const params = new URLSearchParams();
		params.append('no', likeData.no),
		params.append('email', likeData.email)
		axios.post(`${SERVER_URL}/api/feed/feedunlike`, params)
		.then(() => {
			context.dispatch('likeCnt', likeData)
			context.dispatch('likeUser', likeData)
		})
	},
	likeCnt(context, likeCntData){
		const params = new URLSearchParams();
		params.append('no', likeCntData.no)
		axios.post(`${SERVER_URL}/api/feed/feedlikeCnt`, params)
		.then( res => {
			context.commit('likeCnt', res.data)
		})
	},
	likeUser(context, likeCntData){
		const params = new URLSearchParams();
		params.append('no', likeCntData.no)
		axios.post(`${SERVER_URL}/api/feed/feedlikeUser`, params)
		.then((response) => {
			var data = []
			for(var i=0; i<(response.data).length; i++) {
				data.push(response.data[i])
			}
			var data2 = []
			for (var z=0; z<data.length; z++) {
				data2.push(data[z].email)
			}
			context.commit('likeUser', data)
			context.commit('likeUser2', data2)
		})
	},
	getNickname(context, email){
		axios.get(`${SERVER_URL}/api/user/selectNickname?email=${email}`)
		.then(res => {
			context.commit('getNick', res.data)
			context.dispatch('getalarm', res.data)
			sessionStorage.setItem('mynick', res.data)
		})
		.catch(()=>{
		})
	},
	privateChat(context, privateData){
		const params = new URLSearchParams();
		params.append('mynickname', privateData.myNickname)
		params.append('yournickname', privateData.yourNickname)
		axios.post(`${SERVER_URL}/api/chat/privateCaht`, params)
		.then( res => {
			context.commit('privateChatSave', res.data)
		})
		.catch(()=>{})
	},
	getalarm(context, nickname){
		const params =  new URLSearchParams();
		params.append('nickname', nickname)
		axios.post(`${SERVER_URL}/api/alarm/list`, params)
		.then( res => {
			context.commit('getalarmList', res.data)
		})
		.catch(()=>{})
	},
	deleteAlarm(context, alarm){
		const params = new URLSearchParams();
		params.append('no', alarm.no)
		axios.post(`${SERVER_URL}/api/alarm/delete`, params)
		.then( () => {
			context.dispatch('getalarm', alarm.addressee)
		})
		.catch(()=>{})
	},
	getAllChat(context, mynick){
		const params = new URLSearchParams();
		params.append('nickname', mynick)
		axios.post(`${SERVER_URL}/api/chat/selectAll`, params)
		.then( res => {
			context.commit('getallList', res.data)
		})
		.catch(()=>{})
	},
	teamChat(context, teamChatData){
		const params = new URLSearchParams();
		params.append('no', teamChatData.no)
		params.append('leaderemail', teamChatData.leaderemail)
		axios.post(`${SERVER_URL}/api/chat/groupChat`, params)
		.then( res => {
			context.commit('getteamChat', res.data)
		})
		.catch(()=>{})
	},
	deletePjt(context, deletePjtData){
		const params = new URLSearchParams();
		params.append('no', deletePjtData.no)
		axios.get(`${SERVER_URL}/api/contents/delete?no=${deletePjtData.no}`)
		.then(() => {
			Swal.fire({
				icon: 'info',
				text: '성공적으로 삭제하였습니다.',
			})
			router.push({ name: "ProjectList"})
		})
		.catch(()=>{})
	},
	//////////다인///////////////

	/////////지훈////////////////
	getContestData(context) {
		var contest = []
		var project = []
		axios
		.get(`${SERVER_URL}/api/contents/readAll/contest`)
		.then(res => {
			for(var i=0; i<(res.data).length; i++) {
				if (res.data[i].category === 0) {
				contest.push(res.data[i])
				}
				else {
					project.push(res.data[i])
				}
			}
			context.commit('contestData', contest.slice(9))
			if (project.length >= 10) {
				context.commit('projectData', project.slice(0, 9))
			} else {
				context.commit('projectData', project)
			}      
		});
  	},
	profile(context) {
		const email = cookies.get('auth-email')
		axios.get(`${SERVER_URL}/api/feed/${email}`)
			.then(response => {
				context.commit('INPUTDATA', response.data)
			})
	},

	userprofile(context, useremail) {
		axios.get(`${SERVER_URL}/api/feed/${useremail}`)
			.then(res => {
				context.commit('USERINPUT', res.data)
			})
			.catch(()=>{})
	},
	follow(context, res) {
		var params = new URLSearchParams();
		if (context.state.email !== null) {
			params.append('email', context.state.email);
			params.append('following', res)
			axios.post(`${SERVER_URL}/api/follow/follow`, params)
				.then(() => {
					context.dispatch('followerCnt', res)
					context.dispatch('myFollowerList', res)
				})
				.catch(()=>{})
		}
		else {
			Swal.fire({
				icon: 'error',
				text: '회원만 팔로우를 할 수 있습니다.',
				footer: '<br>회원이 아니신가요?<a href="/signup">   <br>가입하기   </a>'
			})
		}
	},

	myFollowerList(context, res) {
		var params = new URLSearchParams();
		var data = []
		params.append('email', res);
		axios.post(`${SERVER_URL}/api/follow/followerList`, params)
			.then((response) => {
				for(var i=0; i<(response.data).length; i++) {
						data.push(response.data[i])
			}
			var data2 = []
			for (var z=0; z<data.length; z++) {
				data2.push(data[z].email)
			}
			context.commit('INPUTFOLLOWER', data)
			context.commit('INPUTFOLLOWER2', data2)
			})
			.catch(()=>{})
	},

	myFollowList(context, res) {
		var params = new URLSearchParams();
		// var data = []
		params.append('email', res)
		axios.post(`${SERVER_URL}/api/follow/followingList`, params)
		.then((response) => {
			context.commit('INPUTFOLLOW', response.data)
		})
		.catch(()=>{})
	},

	unfollow(context, res) {
		var params = new URLSearchParams();
		params.append('email', context.state.email)
		params.append('following', res)
		axios.post(`${SERVER_URL}/api/follow/unfollow`, params)
		.then(() => {
			context.dispatch('myFollowerList', res)
			context.dispatch('followerCnt', context.state.email)
		})
		.catch(()=>{})
	},

	followerCnt(context, res) {
		var params = new URLSearchParams();
		params.append('email', res)
		axios.post(`${SERVER_URL}/api/follow/followerCnt`, params)
			.then((response) => {
				context.state.followCnt = response.data
		})
	},
	searchFeed(context) {
		axios.post(`${SERVER_URL}/api/feed/search`)
			.then((response) => {
				context.commit('setCommunity', response.data)
			})
			.catch(()=>{})
	},

	searchFollowFeed(context, res) {
		var params = new URLSearchParams();
		params.append('email', res)
		axios.post(`${SERVER_URL}/api/feed/search`, params)
			.then((response) => {
				context.commit('setCommunity', response.data)
			})
			.catch(()=>{})
	},

	searchTagFeed(context, res) {
		var params = new URLSearchParams();
		params.append('tag', res)
		axios.post(`${SERVER_URL}/api/feed/search`, params)
			.then((response) => {
				const data = {
					res: response.data,
					keyword: res
				}
				context.commit('setTag', data)
			})
			.catch(()=>{})
	},

	subEmail(context, res) {	
		context.commit('chageIsFlag')
		axios.get(`${SERVER_URL}/api/user/pwd/?email=${res}`)
	},

	pushCode(context, res) {
		// var params = new URLSearchParams();
		// params.append('code', res.code)
		// params.append('email', res.email)
		console.log(res.code)
		console.log(res.email)
		axios.post(`${SERVER_URL}/api/user/pwd?code=${res.code}&email=${res.email}`)
			.then((response) => {
				context.commit('getPwdToken', response.data)
			})
			.catch(() => {
				alert('코드를 확인해주세요!')
			});
	},

	getAllContest(context) {
		var data = []
		axios.get(`${SERVER_URL}/api/contents/readAll/contest`)
			.then((res) => {
				for(var i=0; i<(res.data).length; i++) {
					if (res.data[i].category === 0) {
						data.push(res.data[i])
					}
				}
				context.commit('getAllContest', data)
			}).catch(()=>{
			})
			
	},

	deleteFeed(context, res) {
		const params = new URLSearchParams();
		params.append('email', res.email)
		params.append('no', res.no)
		axios.post(`${SERVER_URL}/api/feed/delete`, params)
			.then(() => {
				Swal.fire({
					icon: 'success',
					text: '성공적으로 삭제하였습니다.!',
				})
				router.push({ name: "Profile" })
			})
	},

	feedUpdate(context, res) {
    const formdata = new FormData();
    formdata.append('description', res.description)
    formdata.append('file', res.src)
    formdata.append('no', res.no)
    axios.post(`${SERVER_URL}/api/feed/update`, formdata)
      .then(() => {
        Swal.fire({
          icon: 'success',
          text: '성공적으로 수정하였습니다.!',
        })
        router.push({ name: "Profile" })
      })
      .catch(() => {
        Swal.fire({
          icon: 'fail',
          text: '수정에 실패하였습니다. 빠진 항목이 없는지 확인해주세요 ㅜㅜ',
        })
      })
	},
	
	gotoMap(context, res) {
		axios.get(`${SERVER_URL}/api/teammanagement?leaderemail=${res.leaderemail}&no=${res.no}`)
			.then((response) => {
				context.commit('setMapInfo', response.data.center)
			})
	},

	/////////지훈////////////////
	getTeamInfo(context) {
		const params = new URLSearchParams();
		params.append('email', context.state.email)
		axios.post(`${SERVER_URL}/api/team/myteamlist/`, params)
		.then(response => {
			sessionStorage.setItem('myTeam', JSON.stringify(response.data))
			context.commit('myTeamInfo', response.data)
		})
	},
	postDate (context, info) {
		console.log(info)
		for (let i = 0; i < info.date.length; i++) {
			const dateinfo = {
				date : info.date[i],
				leaderemail: info.leaderemail,
				memberemail: info.memberemail,
				no: info.no
			}
			axios.post(`${SERVER_URL}/api/team/insetSchedule`, dateinfo)
			.then(() =>{
				setTimeout(() => {
					router.go()
				}, 100)
			})
			.catch(error => {
				console.log(error)
			})
		}

	},
	deleteDate (context, info) {
		for (let i = 0; i < info.date.length; i++) {
			const dateinfo = {
				date : info.date[i],
				leaderemail: info.leaderemail,
				memberemail: info.memberemail,
				no: info.no
			}
			axios.post(`${SERVER_URL}/api/team/deleteSchedule`, dateinfo)
			.then(() =>{
				setTimeout(() => {
					router.go()
				}, 100)
			})
		}
	},
	selectMember ({dispatch}, apply) {
		const params = new URLSearchParams();
		params.append('leaderemail', apply.leaderemail)
		params.append('no', apply.no)
		params.append('part', apply.part)
		params.append('teamemail', apply.teamemail)
		axios.post(`${SERVER_URL}/api/team/selectMember`, params)
		.then(() => {
			dispatch('getTeamInfo')
			setTimeout(() => {		
				router.go()
			}, 250)
		})
		.catch(() => {})
	},
	deleteMember ({dispatch}, apply) {
		const params = new URLSearchParams();
		params.append('leaderemail', apply.leaderemail)
		params.append('no', apply.no)
		params.append('part', apply.part)
		params.append('teamemail', apply.teamemail)
		axios.post(`${SERVER_URL}/api/team/deleteMember`, params)
		.then(() => {
			dispatch('getTeamInfo')
			setTimeout(() => {
				router.go()
			}, 250)
		})
	},
	selectDay(context, info) {
		const params = new URLSearchParams();
		params.append('leaderemail', info.leaderemail)
		params.append('no', info.no)
		axios.post(`${SERVER_URL}/api/team/selectSchedule/`, params)
		.then(response => {
			context.commit('selectDay', response.data)
		})
		.catch(() => {})
	},
	getMyday(context, info) {
		const params = new URLSearchParams();
		params.append('leaderemail', info.leaderemail)
		params.append('memberemail', context.state.email)
		params.append('no', info.no)
		axios.post(`${SERVER_URL}/api/team/memberSchedule/`, params)
		.then(response => {
			context.commit('getDay', response.data)
		})
		.catch(() => {})
	},
}