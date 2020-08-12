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
    // },
    // POST_EMAIL(state, res) {
    //     state.email = res.data
    // },
    feedDetail(state, feed) {
        state.detailFeed = feed
        router.push({ name: "FeedDetail" })
    },
    gongmoDetail(state, res) {
        console.log('공모')
        console.log(res)
        state.club = res
        router.push({ name: "GongmoDetail"})
    },
    INPUTDATA (state, res) {
        state.profileData = res
    },
    contestData(state, res) {
        state.clubs = res
    },
    projectData(state, res) {
        state.clubs2 = res
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
        state.tagData = res
			// if (state.followerList.includes(state.email)) {
			// 		state.followflag = true
			// }
    },
    GETTEAMDATA(state, res) {
      state.getTeamList = res
    },
    myTeamInfo(state, res) {
        state.myTeamInfo = res
    },
    projectDetail(state, res) {
        state.club2 = res
        console.log('Here~~~~~~~~~~~')
        console.log(res)
        router.push({ name: "ProjectDetail" })
    },
    goTeam(state) {
        if (state.email) {   
            router.push({name:'ProjectRegister'})
        } else {
            alert('로그인이 필요한 서비스입니다!')
        }
    },
    getAllContest(state, res) {
        state.allContest = res
    },
    chageIsFlag(state) {
        state.isFlag = !state.isFlag
    } ,
    getPwdToken(state, res) {
        state.pwdToken = res
        router.push({name:'ChangePwd'})
    },
    updateProfile(state, res) {
        state.updateProfile = res
        router.push({name: "UpdateProfile"})
    },
    setTag(state, data) {
        state.tagData = data.res
        state.keyword = data.keyword
        router.push({name: "searchTag"})
    },
    likeCnt(state, data) {
      state.likeCntnum = data
		},
		likeUser(state, data) {
			state.likeUserList = data
		},
}