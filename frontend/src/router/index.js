import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '@/views/Login.vue'
import Signup from '@/views/Signup.vue'
import Profile from '@/views/Profile.vue'
import TeamRegister from '@/views/TeamRegister.vue'
import ProjectRegister from '@/views/ProjectRegister.vue'

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
    component: Login
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
  {
    path: '/teamregister',
    name: "TeamRegister",
    component: TeamRegister
  },
  //////////다인////////////
  {
    path: '/projectregister',
    name: 'ProjectRegister',
    component: ProjectRegister
  }
  //////////다인////////////
  

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
