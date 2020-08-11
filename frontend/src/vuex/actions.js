import axios from 'axios'
import router from '@/router'
import cookies from 'vue-cookies'
import 'url-search-params-polyfill'
// import { apply } from 'core-js/fn/reflect'
import Swal from 'sweetalert2'


const SERVER_URL = 'http://localhost:9999/mit'
// const SERVER_URL = 'http:/i3b306.p.ssafy.io:9999/mit'

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

		// var auth2 = gapi.auth2.getAuthInstance();
		// auth2.signOut().then(function () {
		//   console.log("User signed out.");
		// });

		router.go({ name: "Home" })
	},
	postSignup({ dispatch }, signupInfo) {
		axios.post("http://localhost:9999/mit/api/user/join", signupInfo.data)
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
		console.log(context)
		console.log(nickname)
		if (!context.state.isLoggedIn) {
			const nick = {
				data: nickname
			}
			axios.get(`http://localhost:9999/mit/api/user/checkNickname/${nick.data}`)
				.then(() => {
					alert('사용가능한 별명입니다')
				})
				.catch(() => {
					alert('사용중인 별명입니다.')
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
		const formdata = new FormData();
		formdata.append('category', feedData.category)
		formdata.append('description', feedData.description)
		formdata.append('email', feedData.email) 
		formdata.append('file', feedData.file)
		formdata.append('tags', feedData.tags)
		axios.post(`${SERVER_URL}/api/feed/create/`, formdata)
		.then(() => {
				router.push({ name: "Profile"})
		})
		.catch(error => {
				console.log(error)
		})
	},
	

	//////////다인///////////////
	teamregister(context, applyData){
		console.log(applyData)
		axios.post(`${SERVER_URL}/api/team/contestteam`, applyData)
			.then(() => {
				alert('성공적으로 등록하였습니다.')
				router.push({ name: "GongmoDetail"})
			})
			.catch(error => console.log(error.response.data))
	},
	projectregister(context, projectData){
		console.log(context)
		console.log(projectData)
		axios.post(`${SERVER_URL}/api/team/projectteam`, projectData)
		.then(() => {
			alert('성공적으로 등록하였습니다.')
			router.push({ name: "ProjectList"})
		})
		.catch(err => {
			console.log(err.response.data)
		})
	},
	getTeamData(context, no){
		console.log(no)
		const params = new URLSearchParams();
		params.append('no', no);
		axios.post(`${SERVER_URL}/api/team/contentsteamlist`, params)
		.then(res => {
			console.log(res)
			context.commit('GETTEAMDATA', res.data)
		})
		.catch(err => {
			console.log(err.response.data)
		})
	},
	apply(context, sendData){
		console.log(context)
		console.log('Action')
		console.log(sendData)

		const params = new URLSearchParams();
		params.append('no', sendData.no);
		params.append('leaderemail', sendData.leaderemail);
		params.append('email', sendData.email);
		params.append('part', sendData.part);
		axios.post(`${SERVER_URL}/api/team/applyTeam`, params)
		.then(()=> {
			console.log('팀장에게 보내기 완료 !! ')
		})
		.catch(err => {
			console.log(err.response.data)
		})
	},
	deleteTeam(context, deleteData){
		console.log(context)
		console.log(deleteData)

		const params = new URLSearchParams();
		params.append('no', deleteData.no);
		params.append('leaderemail', deleteData.leaderemail);	
		axios.post(`${SERVER_URL}/api/team/deleteTeam`, params)
		.then(() => {
			alert('팀이 삭제되었습니다.')
		})
		.catch( err => {
			console.log(err.response.data)
		})
	},
	updateCard(context, updateData){
		console.log(context)
		console.log(updateData)
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
			console.log('수정하자')
		})
		.catch( err => {
			console.log(err.response.data)
		})
	},
	deleteCard(context, deleteData){
		console.log(context)
		console.log(deleteData)
		const params = new URLSearchParams();
		params.append('no', deleteData.no);
		params.append('leaderemail', deleteData.leaderemail);
		params.append('part', deleteData.part);
		axios.post(`${SERVER_URL}/api/team/deleteTeaminfo`, params)
		.then(() => {
			alert('성공적으로 삭제되었습니다.')
		})
		.catch( err => console.log(err.response.data))
	},
	//////////다인///////////////

	/////////지훈////////////////
	getContestData(context) {
		var contest = []
		var project = []
		axios.get(`${SERVER_URL}/api/contents/readAll/contest`)
			.then(res => {
				for(var i=0; i<(res.data).length; i++) {
					if (res.data[i].category === 0) {
						contest.push(res.data[i])
					}
					else {
						project.push(res.data[i])
					}
				}
			context.commit('contestData', contest)
			context.commit('projectData', project)
			})
	},

	profile(context) {
		const email = cookies.get('auth-email')
		axios.get(`${SERVER_URL}/api/feed/${email}`)
			.then(response => {
				context.commit('INPUTDATA', response.data)
			})
			.catch(error => {
				console.log(error)
			})
	},

	userprofile(context, useremail) {
        axios.get(`${SERVER_URL}/api/feed/${useremail}`)
          .then(res => {
              context.commit('USERINPUT', res.data)
          })
          .catch(err => {
              console.log(err)
          })
	},
	follow(context) {
		var params = new URLSearchParams();
		if (context.state.email !== null) {
		params.append('email', context.state.email);
		params.append('following', context.state.userprofiledata.feeds[0].email)
		axios.post(`${SERVER_URL}/api/follow/follow`, params)
				.then(() => {
		context.dispatch('follwerCnt', context.state.userprofiledata.feeds[0].email)
		context.dispatch('myFollowerList', context.state.userprofiledata.feeds[0].email)
				})
				.catch(error => console.log(error.response.data))
		}
		else {
			alert('회원만 팔로우를 할 수 있습니다.')
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
			context.commit('INPUTFOLLOWER', data)
			})
	},

	myFollowList(context, res) {
		var params = new URLSearchParams();
		var data = []
		params.append('email', res)
		axios.post(`${SERVER_URL}/api/follow/followingList`, params)
			.then((response) => {
				for (var i=0; i<(response.data).length; i++) {
					data.push(response.data[i])
				}
			context.commit('INPUTFOLLOW', data)
			})
	},

	unfollow(context, res) {
		var params = new URLSearchParams();
		params.append('email', context.state.email)
		params.append('following', res)
		axios.post(`${SERVER_URL}/api/follow/unfollow`, params)
		.then(() => {
			context.dispatch('myFollowerList', res)
			context.dispatch('follwerCnt', context.state.email)
		})
		.catch(error => console.log(error.response.data))
	},

	follwerCnt(context, res) {
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
	},

	searchFollowFeed(context, res) {
		var params = new URLSearchParams();
		params.append('email', res)
		axios.post(`${SERVER_URL}/api/feed/search`, params)
			.then((response) => {
				context.commit('setCommunity', response.data)
			})
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
	},

	subEmail(context, res) {
		console.log(res)
		var params = new URLSearchParams();
		params.append('email', res)
		context.commit('chageIsFlag')
		axios.get(`${SERVER_URL}/api/user/pwd/?email=${res}`)
			.catch(error => {
				console.log(error.response.data)
			});
	},

	pushCode(context, res) {
		console.log(res)
		console.log(res.email)
		console.log(res.code)
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
			})
	},

	/////////지훈////////////////
	getTeamInfo(context) {
		const params = new URLSearchParams();
		params.append('email', context.state.email)
		axios.post(`${SERVER_URL}/api/team/myteamlist/`, params)
		.then(response => {
			context.commit('myTeamInfo', response.data)
		})
	}
}

