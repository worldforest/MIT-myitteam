import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import actions from './actions'
import mutations from './mutations'
import cookies from 'vue-cookies'

Vue.use(Vuex)

const state = {
    authToken: cookies.get('auth-token'),
    isLoggedIn : false,
    email : cookies.get('auth-email'),
    profileData: [],
    clubs: [],
    clubs2: [],
    clubinfo: {},
    detailFeed: [],
    userprofiledata: [],
    followerList: [],
    followList: [],
    getTeams: [],
    followflag: true,
    followCnt: '',
    community: [],
    getTeamList: [],
    myTeamInfo: JSON.parse(sessionStorage.getItem('myTeam')),
    saveInfo: JSON.parse(sessionStorage.getItem('teaminfo')),
    club: [],
    club2: [],
    teamreg: [],
    allContest: [],
    isFlag: true,
    applyLeader: [],
    pwdToken: '',
    updateProfile: [],
    dates: [],
    tagData: [],
    keyword: '',
    likeCntnum: '',
    likeUserList: [],
    likeUserList2: [],
    followerList2: [],
    upFeed: [],
    myNick: '',
    privateChatTitle: '',
    alarmList : [],
    allChat : [],
    teamchatTitle: '',
}

export default new Vuex.Store({
    state,
    mutations,
    getters,
    actions,
})