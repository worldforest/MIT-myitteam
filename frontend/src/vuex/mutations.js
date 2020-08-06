import cookies from 'vue-cookies'
import router from '@/router'

// 
export default {
    SET_TOKEN(state, token) {
        state.authToken = token
        cookies.set('auth-token', token)
    },
    // GET_EMAIL(state, res) {
    //     state.email = res.email
    //     cookies.set('auth-email', res.data)
    // },
    // POST_EMAIL(state, res) {
    //     state.email = res.data
    //     cookies.set('auth-email', res.data)
    // },
    feedDetail(state, feed) {
        state.detailFeed = feed
        router.push({ name: "FeedDetail" })
    },
    INPUTDATA (state, res) {
        state.profileData = res
    },
    contestData(state, res) {
        state.clubs = res
    },
    USERINPUT(state, res) {
        state.userprofiledata = res
    },
    INPUTFOLLOWER(state, res) {
        state.followerList = res
    },
    INPUTFOLLOW(state, res) {
        state.followList = res
    },
    getTeam(state, res) {
        console.log("mutations")
        state.getTeams = res 
    },
    followflag(state) {
        state.followflag = false

        if (state.followerList.includes(state.email)) {
            state.followflag = true
        }
    },
    setCommunity(state, res) {
        state.community = res
    }
}