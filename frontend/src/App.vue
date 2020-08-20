<template>
  <div class="bg4">
    <div v-if="windowWidth >=767 && currentRouteName !== 'RealHome'" >
      <div v-if="!isLoggedIn">
        <nav role="navigation" class="primary-navigation">
          <ul>
            <li><router-link to="/home"><span>Home</span></router-link></li>
            <li><router-link to="/AllContest"><span>공모전</span></router-link></li>
            <li><router-link to="/projectlist"><span>프로젝트</span></router-link></li>
            <li><router-link to="/signup"><span>회원가입</span></router-link></li>
            <li><router-link to="/login"><span>로그인</span></router-link></li>
          </ul>
        </nav>
      </div>
      <div v-if="isLoggedIn">
        <nav role="navigation" class="primary-navigation">
          <ul>
            <li><router-link to="/home"><span>Home</span></router-link></li>
            <li><router-link to="/AllContest"><span>공모전</span></router-link></li>
            <li><router-link to="/projectlist"><span>프로젝트</span></router-link></li>
            <li><div class="text-center ">
              <v-menu offset-y>
                <template v-slot:activator="{ on, attrs }">
                  <v-icon
                    middle
                    v-bind="attrs"
                    v-on="on"
                    @click="getAllChat(myNick)"
                  >
                    mdi-comment-processing
                  </v-icon>
                </template>
                <v-list v-for="(chat, index) in allChat" :key="index">
                  <v-list-item>
                    <v-row>
                      <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil" class="chat_img mr-3">
                      <span class="not-home ppointer" @click="goChat(chat)">{{ chat }}</span>
                    </v-row>
                  </v-list-item>
                  <hr style="margin:0">
                </v-list>
              </v-menu>
            </div></li>
            <li>
              <div>
                <v-menu offset-y>
                  <template v-slot:activator="{ on, attrs }">
                    <v-icon
                      v-if="alarmList.length === 0"
                      middle
                      v-bind="attrs"
                      v-on="on"
                      @click="getalarm(myNick)"
                    >
                      mdi-bell
                    </v-icon>
                    <v-icon
                      v-else
                      middle
                      v-bind="attrs"
                      v-on="on"
                      @click="getalarm(myNick)">mdi-bell-check</v-icon>
                  </template>
                  <v-list v-for="(alarm, index) in alarmList" :key="index">
                    <v-list-item>
                      <v-list-item-title class="not-home ppointer" @click="deleteAlarm(alarm)">{{ alarm.message }}</v-list-item-title>
                    </v-list-item>
                    <hr style="margin:0">
                  </v-list>
                </v-menu>
              </div>
            </li>
            <li>
              <div>
                <v-menu offset-y>
                  <template v-slot:activator="{ on, attrs }">
                    <v-icon
                      middle
                      v-bind="attrs"
                      v-on="on"
                      class="accountIcon"
                    >
                      mdi-account-circle
                    </v-icon>
                  </template>
                  <v-list>
                    <v-list-item>
                      <v-list-item-title class="not-home"><router-link to="/profile">마이페이지</router-link></v-list-item-title>
                    </v-list-item>
                    <v-list-item >                    
                      <v-list-item-title class="not-home"><router-link to="/myteam" @click="getTeamInfo">나의 팀 관리</router-link></v-list-item-title>
                    </v-list-item>
                    <v-list-item>
                      <v-list-item-title class="not-home"><router-link id="tokenTrue" to="/home" @click.native="logout">LOGOUT</router-link></v-list-item-title>
                    </v-list-item>
                  </v-list>
                </v-menu>
              </div>
            </li>
          </ul>
        </nav>
      </div>
    </div>

    <!-- 윈도우 브라우저가 767 미만일 때의 Navbar  && Home.vue에서 적용안됨 -->
    <div v-if="windowWidth <767 && currentRouteName !== 'RealHome'">
      <div class="bg">
         <div>
           <router-link to="/home"><img src="../src/images/logo2.png" alt="MIT Logo" style="height:30px" class="logo2"></router-link>
         </div>
         <div  v-if="!isLoggedIn">
          <router-link to="/login" class="test">LOGIN</router-link>
          <router-link to="/signup" class="test">SIGNUP</router-link>
         </div>
         <div v-else>
           <v-row>

             <!--채팅이다-->
            <div class="text-center dkanrjsk ">
              <v-menu offset-y>
                <template v-slot:activator="{ on, attrs }">
                  <v-icon
                    x-large color="#FFFFFF"
                    v-bind="attrs"
                    v-on="on"
                    class="accountIcon not-home mt-1 mr-2"
                    @click="getAllChat(myNick)"
                  >
                    mdi-comment-processing
                  </v-icon>
                  <!-- <v-icon>mdi-bell-check</v-icon> -->
                </template>
                <v-list v-for="(chat, index) in allChat" :key="index">
                  <v-list-item>
                    <v-row>
                      <img src="https://ptetutorials.com/images/user-profile.png" alt="sunil" class="chat_img mr-3">
                      <span class="not-home ppointer" @click="goChat(chat)">{{ chat }}</span>
                    </v-row>
                  </v-list-item>
                  <hr style="margin:0">
                </v-list>
              </v-menu>
            </div>

             <!--알림이다-->
            <div class="text-center dkanrjsk ">
              <v-menu offset-y>
                <template v-slot:activator="{ on, attrs }">
                  <v-icon
                  v-if="alarmList.length === 0"
                    x-large color="#FFFFFF"
                    v-bind="attrs"
                    v-on="on"
                    class="accountIcon not-home mt-1 mr-2"
                  >
                    mdi-bell
                  </v-icon>
                  <v-icon
                      v-else
                      x-large color="#FFFFFF"
                      v-bind="attrs"
                      v-on="on"
                      class="accountIcon not-home mt-1 mr-2"
                      @click="getalarm(myNick)">mdi-bell-check</v-icon>
                </template>
                <v-list v-for="(alarm, index) in alarmList" :key="index">
                  <v-list-item>
                    <v-list-item-title class="not-home ppointer" @click="deleteAlarm(alarm)">{{ alarm.message }}</v-list-item-title>
                  </v-list-item>
                  <hr style="margin:0">
                </v-list>
              </v-menu>
            </div>

            <!--계정이다-->
            <div class="text-center test">
              <v-menu offset-y>
                <template v-slot:activator="{ on, attrs }">
                  <v-icon
                    x-large color="#FFFFFF"
                    v-bind="attrs"
                    v-on="on"
                    class="accountIcon not-home"
                  >
                    mdi-account-circle
                  </v-icon>
                </template>
                <v-list>
                  <v-list-item>
                    <v-list-item-title class="not-home"><router-link id="tokenTrue" to="/home" @click.native="logout">LOGOUT</router-link></v-list-item-title>
                  </v-list-item>
                </v-list>
              </v-menu>
            </div>
           </v-row>
        </div>
      </div>
      <div class="bg2 px-3" v-if="email">
        <div class="bg3 container" >
          <img src="../src/images/project.png" alt="" style="height:100%;" class="ml-2" @click="goPJT">
          <img src="../src/images/contest.png" alt="" style="height:100%;" @click="goCON">
          <img src="../src/images/Team.png" alt="" style="height:100%;" @click="goTEAM" v-if="email">
          <img src="../src/images/profile.png" alt="" style="height:100%;" class="mr-2" @click="goPRO" v-if="email">          
        </div>
      </div>

      <div class="bg2 px-3" v-else>
        <div class="bg5 container" >
          <img src="../src/images/project.png" alt="" style="height:100%;" class="ml-2" @click="goPJT">
          <img src="../src/images/contest.png" alt="" style="height:100%;" @click="goCON">
          <img src="../src/images/Team.png" alt="" style="height:100%;" @click="goTEAM" v-if="email">
          <img src="../src/images/profile.png" alt="" style="height:100%;" class="mr-2" @click="goPRO" v-if="email">          
        </div>
      </div>
    </div>
    <v-app :class="[ {'container': currentRouteName !== 'RealHome' } ]" style="padding:0px; background-color:#FAFAFA;">
      <div style="background-color:#FAFAFA;" :class="[ {'mt-10': currentRouteName !== 'RealHome' } ]">
        <router-view :privateChatTitle="$route.fullPath"/>
      </div>
    </v-app>
  </div>

</template>

<script>
import { mapGetters, mapActions, mapState } from 'vuex'

export default {
  name: 'App',
  components: {
  },
  data() {
    return {
      windowWidth: window.innerWidth,
      isChecked: false,
    }
  },
  watch: {
    windowWidth(newWidth, oldWidth) {
    this.txt = `it changed to ${newWidth} from ${oldWidth}`;
    }
  },
  beforeDestroy() { 
    window.removeEventListener('resize', this.onResize); 
  },
  methods: {
    ...mapActions(['logout', 'profile', 'postEmailToken', 'getTeamInfo', 'getContestData', 'getNickname', 'getalarm', 'deleteAlarm', 'getAllChat']),
    onResize() {
      this.windowWidth = window.innerWidth
    },
    goPJT(){
      this.$router.push('/projectlist').catch(()=>{})
        
    },
    goCON(){
      this.$router.push('/AllContest').catch(()=>{})
    },
    goTEAM(){
      this.$router.push('/myteam').catch(()=>{})
    },
    goPRO(){
      this.$router.push('/profile').catch(()=>{})
    },
    goChat(chat){
      this.$router.push({name: 'Chat', params: { privateChatTitle : chat }}).catch(()=>{});
    }

  },
  computed: {
    ...mapState(['email', 'myNick', 'alarmList', 'allChat']),
    ...mapGetters(['isLoggedIn', 'isEmail']),
    currentRouteName() {
        return this.$route.name;
    }
  },
  mounted() {
    this.getContestData()
    if (this.$cookies.isKey('auth-token') && !this.$cookies.isKey('auth-email')) {
      this.postEmailToken()
      } 
    if (this.$cookies.isKey('auth-token')) {
      this.getTeamInfo()
    }
    this.$nextTick(() => {
      window.addEventListener('resize', this.onResize);
    })
  },
  created () {
    if (this.$cookies.isKey('auth-token')) {
      this.getNickname(this.email)
    }
    this.currentRouteName
  },
};
</script>

<style scoped>
  *{ font-family: 'Jua', sans-serif; }

  .not-home > a { 
    text-decoration: none;
    color: rgb(92, 107, 192);
    font-size: 1.3rem;
    font-weight: bold;
  }

  .nav_a{
    position: relative;
    text-decoration: none;
    color: rgb(92, 107, 192);
    margin-right: 2rem;
    font-size: 1.3rem;
    font-weight: bold;
  }
  
  a:hover{
    color: blue;
  }

  #logo{
    height: 70px;
    width: 120px;
    margin: 10px 20px;
  }

  .bg {
    background-color: rgb(92, 107, 192);
    height: 50px;
    display: flex;
    justify-content: space-between;
  }

  .logo2 {
    margin-left: 20px;    
    margin-top: 10px;
  }

  .test {
    margin-right: 1rem;
    line-height: 50px;
    color: white;
    text-decoration: none;
  }

  .bg2 {
    background-color: rgb(92, 107, 192);
    height: 100px;
    /* display: flex; */
  }

  .bg3 {
    background-color: white;
    height: 80px;
    width: 100%;
    display: flex;
    border-radius: 0.5rem;
    justify-content: space-between;
  }

  .bg4 {
    background-color: #fafafa;
  }

  .bg5 {
    background-color: white;
    height: 80px;
    width: 100%;
    display: flex;
    border-radius: 0.5rem;
    justify-content: space-around;
  }
  .ppointer{
    cursor: pointer;
  }

  nav.primary-navigation {
    margin: 0 auto;
    display: block;
    padding: 27px 0 0 0;
    text-align: center;
    font-size: 16px;
  }
  nav.primary-navigation ul li {
    list-style: none;
    margin: 0 auto;
    border-left: 2px solid #3ca0e7;
    display: inline-block;
    padding: 0 30px;
    position: relative;
    text-decoration: none;
    text-align: center;
    font-family: arvo;
  }
  nav.primary-navigation li a {
    color: black;
  }
  nav.primary-navigation li a:hover {
    color: #3ca0e7;
  }
  nav.primary-navigation li:hover {
    cursor: pointer;
  }
  nav.primary-navigation ul li ul {
    visibility: hidden;
    opacity: 0;
    position: absolute;
    padding-left: 0;
    left: 0;
    display: none;
    background: white;
  }
  nav.primary-navigation ul li:hover > ul,
  nav.primary-navigation ul li ul:hover {
    visibility: visible;
    opacity: 1;
    display: block;
    min-width: 250px;
    text-align: left;
    padding-top: 20px;
    box-shadow: 0px 3px 5px -1px #ccc;
  }
  nav.primary-navigation ul li ul li {
    clear: both;
    width: 100%;
    text-align: left;
    margin-bottom: 20px;
    border-style: none;
  }
  nav.primary-navigation ul li ul li a:hover {
    padding-left: 10px;
    border-left: 2px solid #3ca0e7;
    transition: all 0.3s ease;
  }

  a {
    text-decoration: none;
  }
  a:hover {
    color: #3ca0e7;
  }

  ul li ul li a {
    transition: all 0.5s ease;
  }


  .chat_img {
    float: left;
    width: 30px;
    height: 30px;
  }
</style>
