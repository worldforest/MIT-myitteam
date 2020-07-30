import cookies from 'vue-cookies'

export default {
    SET_TOKEN(state, token) {
        state.authToken = token
        cookies.set('auth-token', token)
    },
    SET_NICKNAME(state, nick) {
        state.nickname = nick
    }
}