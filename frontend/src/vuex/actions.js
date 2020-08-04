import axios from 'axios'
import router from '@/router'
import cookies from 'vue-cookies'
import 'url-search-params-polyfill'
// import { apply } from 'core-js/fn/reflect'

const SERVER_URL = 'http://localhost:9999/mit'

export default {
    postToken({ commit }, info) {

			var params = new URLSearchParams();
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
    postEmailToken(context) {
        axios.post(`${SERVER_URL}/api/user/getEmail`, context.state.authToken)
            .then(res => {
                context.commit('POST_EMAIL', res)
            })
    },
    getContestData({ commit }) {
			axios.get(`${SERVER_URL}/api/contents/readAll/contest`)
				.then(res => {
					commit('contestData', res.data)
				})
    },
    teamregister(context, applyData){
			console.log(context.state.email)
			console.log(applyData.no)
			const params = new URLSearchParams();
			params.append('no', applyData.no);
			params.append('email', context.state.email);
			params.append('local', applyData.local);
			params.append('description', applyData.description);
			console.log(applyData.dataList)
			params.append('datalist', JSON.stringify(applyData.datalist));
			console.log(params)
			axios.post(`${SERVER_URL}/api/team/contestteam`, applyData)
				.then(() => {
					// commit('SET_TOKEN', response.data.token)
					// commit('GET_EMAIL', response.data.email)
					// router.go({ name: "Home" })
					
					alert('성공적으로 등록하였습니다.')
				})
				.catch(error => console.log(error.response.data))
    }, 
    feedCreate(context, feedData) {
			console.log(feedData)
			console.log(context.state.email)
			var params = new URLSearchParams();
			params.append('category', feedData.category);
			params.append('description', feedData.description);
			params.append('email', context.state.email);
			params.append('file', feedData.file);
			params.append('tags', feedData.tags);
			console.log(params)
			axios.post(`${SERVER_URL}/api/feed/create/`, params)
				.then(response => {
					console.log(response)
					router.go({ name: "Profile" })
				})
				.catch(error => {
					console.log(error)
				})
    }
}

