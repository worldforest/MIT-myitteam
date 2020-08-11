import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Signup from '@/views/Signup.vue'
import Login from '@/views/Login.vue'
import Profile from '@/views/Profile.vue'
import GongmoDetail from '@/views/GongmoDetail'
import TeamRegister from '@/views/TeamRegister.vue'
import ProjectRegister from '@/views/ProjectRegister.vue'
import FeedDetail from '@/views/FeedDetail.vue'
import FeedCreate from '@/views/FeedCreate.vue'
import UserProfile from '@/views/UserProfile.vue'
import ProjectList from '@/views/ProjectList.vue'
import Myteam from '@/views/Myteam.vue'
import ProjectDetail from '@/views/ProjectDetail.vue'
import FindPwd from '@/views/FindPwd.vue'
import AllContest from '@/views/AllContest.vue'
import Chat from '@/views/Chat.vue'
import ChangePwd from '@/views/ChangePwd.vue'
import UpdateProfile from '@/views/UpdateProfile.vue'
import searchTag from '@/views/searchTag.vue'


Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/feedcreate',
    name: "FeedCreate",
    component: FeedCreate
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next('/')
      }
      else {
        next()
      }
    }
  },
  {
    path: '/signup',
    name: 'Signup',
    component: Signup,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next('/')
      }
      else {
        next()
      }
    }
  },
  {
    path: '/profile',
    name: "Profile",
    component: Profile
  },
  //////////다인////////////
  {
    path: '/teamregister',
    name: "TeamRegister",
    component: TeamRegister,
    props: true,
  },
  {
    path: '/projectregister',
    name: 'ProjectRegister',
    component: ProjectRegister
  },
  {
    path: '/projectlist',
    name: 'ProjectList',
    component: ProjectList
  },
  //////////다인////////////
  //////////지훈////////////
  {
    path: '/gongmoDetail',
    name: "GongmoDetail",
    component: GongmoDetail,
    props: true,
  },
  {
    path: '/feed_detail',
    name: "FeedDetail",
    component: FeedDetail,
  },
  { 
    path: '/userprofile',
    name: "UserProfile",
    component: UserProfile,
    props: true,
  },
  {
    path: '/projectdetail',
    name: 'ProjectDetail',
    component: ProjectDetail,
    props: true,
  },
  {
    path: '/findpwd',
    name: 'FindPwd',
    component: FindPwd,
  },
  {
    path: '/allcontest',
    name: 'AllContest',
    component: AllContest,
  },
  {
    path: '/changepwd',
    name: 'ChangePwd',
    component: ChangePwd,
  },
  {
    path: '/updateprofile',
    name: 'UpdateProfile',
    component: UpdateProfile,
  },
  {
    path: '/searchtag',
    name: 'searchTag',
    component: searchTag,
  },
  //////////지훈////////////
  {
    path: '/myteam',
    name: "Myteam",
    component: Myteam,
  },
  {
    path: '/chat',
    name: "Chat",
    component: Chat,
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
