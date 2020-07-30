import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Signup from '@/views/Signup.vue'
import Login from '@/views/Login.vue'
import Profile from '@/views/Profile.vue'
import GongmoDetail from '@/views/GongmoDetail.vue'
import TeamRegister from '@/views/TeamRegister.vue'
import ProjectRegister from '@/views/ProjectRegister.vue'
import test from '@/views/test.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
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
    component: Signup
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
    component: TeamRegister
  },
  {
    path: '/projectregister',
    name: 'ProjectRegister',
    component: ProjectRegister
  },
  {
    path: '/test',
    name: 'test',
    component: test
  },
  //////////다인////////////
  //////////지훈////////////
  {
    path: '/gongmoDetail',
    name: "GongmoDetail",
    component: GongmoDetail,
    props: true,
  },
  //////////지훈////////////
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
