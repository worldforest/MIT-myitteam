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
				commit('GET_EMAIL', response.data.email)
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
	profile(context) {
		axios.get(`${SERVER_URL}/api/feed/${context.state.email}`)
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
	// postEmailToken(context) {
	// 	axios.post(`${SERVER_URL}/api/user/getEmail`, context.state.authToken)
	// 		.then(res => {
	// 			context.commit('POST_EMAIL', res)
	// 		})
	// },
	getContestData({ commit }) {
		axios.get(`${SERVER_URL}/api/contents/readAll/contest`)
			.then(res => {
				commit('contestData', res.data)
			})
	},
	teamregister(context, applyData){
		// const FormData = require('form-data');
		const formdata = new FormData();
		var params = new URLSearchParams();
		params.append('email', applyData.email)
		params.append('local', applyData.local)
		params.append('description', applyData.description) 
		params.append('no', applyData.no)
		formdata.append('datalist', JSON.stringify(applyData.datalist))
		axios.post(`${SERVER_URL}/api/team/contestteam`, { data: {formdata}, query: {params } } )
			.then(() => {
				alert('성공적으로 등록하였습니다.')
	
			})
			.catch(error => console.log(error.response.data))
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
	follow(context) {
        // console.log(context.state.email)
        // console.log(context.state.userprofiledata.feeds[0].email)
        var params = new URLSearchParams();
        params.append('email', context.state.email);
        params.append('following', context.state.userprofiledata.feeds[0].email)
        // params.append('pwd', info.data.pwd);
        axios.post(`${SERVER_URL}/api/follow/follow`, params)
            .then(() => {
                console.log('팔로우 완료')
            })
            .catch(error => console.log(error.response.data))
	},
	myFollowerList(context, res) {
        var params = new URLSearchParams();
        var data = []
        params.append('email', res);
        axios.post(`${SERVER_URL}/api/follow/followerList`, params)
          .then((response) => {
            for(var i=0; i<(response.data).length; i++) {
                data.push(response.data[i].email)
            }
          context.commit('INPUTFOLLOWER', data)
          })
    },
}

