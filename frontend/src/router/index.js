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
import Myteaminfo from '@/views/Myteaminfo.vue'
import ProjectDetail from '@/views/ProjectDetail.vue'
import FindPwd from '@/views/FindPwd.vue'
import AllContest from '@/views/AllContest.vue'
import Chat from '@/views/Chat.vue'
import ChangePwd from '@/views/ChangePwd.vue'
import UpdateProfile from '@/views/UpdateProfile.vue'
import searchTag from '@/views/searchTag.vue'
import dmap from '@/views/dmap.vue'
import FeedUpdate from '@/views/FeedUpdate.vue'
import teamInfoDetail from '@/views/teamInfoDetail.vue'
import PageNotFound from '@/views/PageNotFound.vue'
import RealHome from '@/views/RealHome.vue'
import '@/App.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/home',
    name: 'Home',
    component: Home,
  },
  {
    path: '/',
    name: 'RealHome',
    component: RealHome
  },
  {
    path: '/feedcreate',
    name: "FeedCreate",
    component: FeedCreate,
    beforeEnter(to, from, next) {
      if (!Vue.$cookies.isKey('auth-token')) {
        next('/login')
      }
      else {
        next()
      }
    }
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
    beforeEnter(to, from, next) {
      if (Vue.$cookies.isKey('auth-token')) {
        next('/home')
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
        next('/home')
      }
      else {
        next()
      }
    }
  },
  {
    path: '/profile',
    name: "Profile",
    component: Profile,
    beforeEnter(to, from, next) {
      if (!Vue.$cookies.isKey('auth-token')) {
        next('/login')
      }
      else {
        next()
      }
    }
  },
  //////////다인////////////
  {
    path: '/teamregister',
    name: "TeamRegister",
    component: TeamRegister,
    props: true,
    beforeEnter(to, from, next) {
      if (!Vue.$cookies.isKey('auth-token')) {
        next('/login')
      }
      else {
        next()
      }
    }
  },
  {
    path: '/projectregister',
    name: 'ProjectRegister',
    component: ProjectRegister,
    beforeEnter(to, from, next) {
      if (!Vue.$cookies.isKey('auth-token')) {
        next('/login')
      }
      else {
        next()
      }
    }
  },
  {
    path: '/projectlist',
    name: 'ProjectList',
    component: ProjectList
  },
  {
    path: '/teamInfoDetail',
    name: 'teamInfoDetail',
    component: teamInfoDetail,
    props: true,
    beforeEnter(to, from, next) {
      if (!Vue.$cookies.isKey('auth-token')) {
        next('/login')
      }
      else {
        next()
      }
    }
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
    beforeEnter(to, from, next) {
      if (!Vue.$cookies.isKey('auth-token')) {
        next('/login')
      }
      else {
        next()
      }
    }
  },
  {
    path: '/searchtag',
    name: 'searchTag',
    component: searchTag,
  },
  {
    path: '/dmap',
    name: 'dmap',
    component: dmap,
    beforeEnter(to, from, next) {
      if (!Vue.$cookies.isKey('auth-token')) {
        next('/login')
      }
      else {
        next()
      }
    }
  },
  {
    path: '/feedupdate',
    name: 'FeedUpdate',
    component: FeedUpdate,
    beforeEnter(to, from, next) {
      if (!Vue.$cookies.isKey('auth-token')) {
        next('/login')
      }
      else {
        next()
      }
    }
  },
  {
    path: '*',
    redirect: '/404'
  },
  {
    path: '/404',
    name: 'PageNotFound',
    component: PageNotFound
  },
  //////////지훈////////////
  {
    path: '/myteam',
    name: "Myteam",
    component: Myteam,
    beforeEnter(to, from, next) {
      if (!Vue.$cookies.isKey('auth-token')) {
        next('/login')
      }
      else {
        next()
      }
    }
  },
  {
    path: '/myteam/:id',
    name: "Myteaminfo",
    component: Myteaminfo,
    props: true,
    beforeEnter(to, from, next) {
      if (!Vue.$cookies.isKey('auth-token')) {
        next('/login')
      }
      else {
        next()
      }
    }
  },
  {
    path: '/chat',
    name: "Chat",
    component: Chat,
    props: true,
    beforeEnter(to, from, next) {
      if (!Vue.$cookies.isKey('auth-token')) {
        next('/login')
      }
      else {
        next()
      }
    }
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
});

export default router;
