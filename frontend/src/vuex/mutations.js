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
        sessionStorage.setItem('contestinfo', JSON.stringify(res))
        router.push({ name: "GongmoDetail"})
    },
    INPUTDATA (state, res) {
        for (let i=0; i<res.feeds.length; i++) {
            res.feeds[i].tag = res.feeds[i].tag.split('#');
            let data = [];
            for (let z=0; z<res.feeds[i].tag.length; z++) {
                if (res.feeds[i].tag[z] !== '') {
                    data.push(res.feeds[i].tag[z])
                }
            }
            res.feeds[i].tag = data
        }
        state.profileData = res
    },
    contestData(state, res) {
        state.clubs = res
        // sessionStorage.setItem('contest', JSON.stringify(res))
    },
    projectData(state, res) {
        state.clubs2 = res
        // sessionStorage.setItem('project', JSON.stringify(res))
    },
    USERINPUT(state, res) {
        sessionStorage.setItem('userprofileinfo', JSON.stringify(res))
        state.userprofiledata = res
    },
    INPUTFOLLOWER(state, res) {
        state.followerList = res
    },
    INPUTFOLLOWER2(state, res) {
        state.followerList2 = res
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
        // state.tagData = res
        for (let i=0; i<res.length; i++) {
            res[i].tag = res[i].tag.split('#');
            let data = [];
            for (let z=0; z<res[i].tag.length; z++) {
                if (res[i].tag[z] !== '') {
                    data.push(res[i].tag[z])
                }
            }
            res[i].tag = data
        }
        state.community = res
    },
    GETTEAMDATA(state, res) {
      state.getTeamList = res
    },
    myTeamInfo(state, res) {
        state.myTeamInfo = res
    },
    projectDetail(state, res) {
        sessionStorage.setItem('projectinfo', JSON.stringify(res))
        state.club2 = res
        router.push({ name: "ProjectDetail" })
    },
    updateFeed(state, res) {
        state.upFeed = res
        router.push({name: "FeedUpdate"})
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
    likeUser2(state, data) {
        state.likeUserList2 = data
    },
    getNick(state, data) {
        state.myNick = data
    },
    privateChatSave(state, data){
        state.privateChatTitle = data
    },
    getDay(state, res) {
        state.myDay = res
    },
    selectDay(state, res) {
        state.meetDay = res
    },
    getalarmList(state, data){
        state.alarmList = data
    },
    getallList(state, data){
        state.allChat = data
    },
    getteamChat(state, data){
        state.teamchatTitle = data
    }
}