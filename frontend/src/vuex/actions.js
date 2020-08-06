import axios from 'axios'
import router from '@/router'
import cookies from 'vue-cookies'
import 'url-search-params-polyfill'
// import { apply } from 'core-js/fn/reflect'

const SERVER_URL = 'http://localhost:9999/mit'

export default {
	postToken({ commit }, info) {
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
			.catch(error => console.log(error.response.data))
	},

	login({ dispatch }, loginData) {
		const info = {
			data: loginData
		}

		dispatch('postToken', info)
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
				dispatch("postToken", loginInfo)
			})
			.catch(() => {
				alert("사용중인 아이디가 존재합니다.");
			})
	},
	signup({ dispatch }, signupData) {
		const signupInfo = {
			data: signupData
		}
		dispatch('postSignup', signupInfo)
	},
	checkNickname(context, signupData) {
		console.log()
		if (!context.state.isLoggedIn) {
			const nick = {
				data: signupData.nickname
			}
			axios.get(`http://localhost:9999/mit/api/user/checkNickname/${nick.data}`)
				.then(() => {
					alert('사용가능한 별명입니다')
				})
				.catch(() => {
					alert('사용중인 별명입니다.')
					signupData.nickname = ''
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
			})
			.catch(error => console.log(error.response.data))
	},
	projectregister(context, projectData){
		/// 아직 완료 아님  ///
		console.log(context)
		console.log(projectData)
		axios.post(`${SERVER_URL}/api/team/projectteam`, projectData)
		.then(() => {
			alert('성공적으로 등록하였습니다.')
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
		context.commit('conffollowflag')
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
			context.commit('conffollowflag')
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

	subEmail(context, res) {
		console.log(res)
		var params = new URLSearchParams();
		params.append('email', res)
		axios.get(`${SERVER_URL}/api/user/pwd/?email=${res}`)
			.then((response) => {
				console.log(response)
			})
			.catch(error => {
				console.log(error.response.data)
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

