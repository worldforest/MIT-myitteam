import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import router from '@/router'
import cookies from 'vue-cookies'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        authToken: cookies.get('auth-token'),
    },
    getters: {
        isLoggedIn(state) {
            return state.authToken
        }
    },
    mutations: {
        SET_TOKEN(state, token) {
            state.authToken = token
            cookies.set('auth-token', token)
        }
    },
    actions: {
        postToken({commit}, info) {
            const SERVER_URL = 'http://localhost:9999/mit'
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
            // const SERVER_URL = 'http://localhost:9999/mit'
            // axios.post(SERVER_URL)
            cookies.remove('auth-token')
            router.go({ name: 'Home' })
        }
    }
})