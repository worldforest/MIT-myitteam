import cookies from 'vue-cookies'

export default {
    SET_TOKEN(state, token) {
        state.authToken = token
        cookies.set('auth-token', token)
    },
    GET_EMAIL(state, res) {
        state.email = res.email
    },
    POST_EMAIL(state, res) {
        state.email = res.data
    },
    INPUTDATA (state, res) {
        state.profileData = res
        console.log(state.profileData)
    }
}