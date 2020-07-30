import cookies from 'vue-cookies'
import router from '@/router'

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
        cookies.set('auth-email', res.data)
    },
    feedDetail(state, feed) {
        state.detailFeed = feed
        router.push({ name: "FeedDetail" })
    },
    INPUTDATA (state, res) {
        state.profileData = res
    },
    contestData(state, res) {
        state.clubs = res
    }

}