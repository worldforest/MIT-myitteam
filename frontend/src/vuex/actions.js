import axios from 'axios'
import router from '@/router'
import cookies from 'vue-cookies'

const SERVER_URL = 'http://localhost:9999/mit'


export default {
    postToken({commit}, info) {
        axios.post(`${SERVER_URL}/api/user/login/`, info.data)
        .then(response => {
            commit('SET_TOKEN', response.data)
            router.push({ name: "Home"})
        })
        .catch(error => console.log(error.response.data))

    },
    login({dispatch}, loginData) {
        const info = {
            data: loginData
        }
        dispatch('postToken', info)
    },
    logout() {
        cookies.remove('auth-token')
        router.go({ name: "Home" })
    },
    postSignup({dispatch}, signupInfo) {
        console.log(signupInfo.data)
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
    signup({dispatch}, signupData) {
        const signupInfo = {
            data: signupData
        }
        dispatch('postSignup', signupInfo)
		},
    checkNickname(context, signupData) {
        if (!context.state.isLoggedIn) {
            const nick = {
                data: signupData.nickname
            }
            axios.get(`http://localhost:9999/mit/api/user/checkNickname/${nick.data}`)
            .then(() => {
                console.log(nick.data)
                alert('사용가능한 별명입니다')
            })
            .catch(() => {
                alert('사용중인 별명입니다.')
                signupData.nickname= ''
            })
        }
    },
    profile(context) {
        axios.get(`${SERVER_URL}/api/feed/${context.state.email}`)
        .then(response => {
            context.commit('INPUTDATA', response.data)
        })
    },
    postEmailToken(context) {
        axios.post(`${SERVER_URL}/api/user/getEmail`, context.state.authToken)
        .then(res => {
            context.commit('POST_EMAIL', res)
        })
    }
}
