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
    // clubs: JSON.parse(sessionStorage.getItem('contest')),
    // clubs2: JSON.parse(sessionStorage.getItem('project')),
    clubs: [],
    clubs2: [],
    clubinfo: {},
    user: JSON.parse(sessionStorage.getItem('user')),
    detailFeed: JSON.parse(sessionStorage.getItem('feedinfo')),
    userprofiledata: JSON.parse(sessionStorage.getItem('userprofileinfo')),
    followerList: [],
    followList: [],
    getTeams: [],
    followflag: true,
    followCnt: '',
    community: [],
    getTeamList: JSON.parse(sessionStorage.getItem('pjtteaminfo')),
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
    tagData: [],
    keyword: '',
    likeCntnum: '',
    likeUserList: [],
    likeUserList2: [],
    followerList2: [],
    upFeed: [],
    myNickname: '',
    meetDay: [],
    myDay:[],
    myNick: '',
    privateChatTitle: '',
    alarmList : [],
    allChat : [],
    teamchatTitle: '',
    mapX: '',
    mapY: '',
}

export default new Vuex.Store({
    state,
    mutations,
    getters,
    actions,
})