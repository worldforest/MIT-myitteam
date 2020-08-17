<template>
  <div class="bg4">
    {{ myNick }}
    {{ alarmList }}
    {{ allChat }}
  
    <!-- 윈도우 브라우저가 767 이상일 때의 Navbar -->
    <div v-if="windowWidth >=767">
      <ul>
        <li>
          <router-link to="/">
            <img id="logo" src="./images/1.jpg" alt="logo">
          </router-link>
        </li>
        <li>
          <router-link to="/AllContest" class="mr-3"><h4>공모전</h4></router-link>
        </li>
        <li>
          <router-link class="mr-5 nav_a" to="/projectlist"><h4>프로젝트</h4></router-link>
        </li>
        <li style="float:right" class="mr-3 not-home" v-if="!isLoggedIn">
          <router-link class="mr-3" to="/signup">SIGNUP</router-link>
        </li>
        <li style="float:right" class="not-home" v-if="!isLoggedIn">
          <router-link to="/login">LOGIN</router-link>
        </li>
        <span style="float:right" v-else class="mr-3">
          <v-row>

            <!--채팅이다-->
            <div class="text-center dkanrjsk ">
              <v-menu offset-y>
                <template v-slot:activator="{ on, attrs }">
                  <v-icon
                    x-large color="#5C6BC0"
                    v-bind="attrs"
                    v-on="on"
                    class="accountIcon not-home mt-1 mr-4"
                    @click="getAllChat(myNick)"
                  >
                    mdi-comment-processing
                  </v-icon>
                  <!-- <v-icon>mdi-bell-check</v-icon> -->
                </template>
                <v-list v-for="chat in allChat" :key="chat">
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

            <!--알람이다-->
            <div class="text-center mr-4">
              <v-menu offset-y>
                <template v-slot:activator="{ on, attrs }">
                  <v-icon
                    v-if="alarmList.length === 0"
                    x-large color="#5C6BC0"
                    v-bind="attrs"
                    v-on="on"
                    class="accountIcon"
                    @click="getalarm(myNick)"
                  >
                    mdi-bell
                  </v-icon>
                  <v-icon
                    v-else
                    x-large color="#5C6BC0"
                    v-bind="attrs"
                    v-on="on"
                    class="accountIcon"
                    @click="getalarm(myNick)">mdi-bell-check</v-icon>
                </template>
                <v-list v-for="alarm in alarmList" :key="alarm">
                  <v-list-item>
                    <v-list-item-title class="not-home ppointer" @click="deleteAlarm(alarm)">{{ alarm.message }}</v-list-item-title>
                  </v-list-item>
                  <hr style="margin:0">
                </v-list>
              </v-menu>
            </div>

            <!-- 계정 -->
            <div class="text-center mr-4">
              <v-menu offset-y>
                <template v-slot:activator="{ on, attrs }">
                  <v-icon
                    x-large color="#5C6BC0"
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
                    <v-list-item-title class="not-home"><router-link id="tokenTrue" to="/" @click.native="logout">LOGOUT</router-link></v-list-item-title>
                  </v-list-item>
                </v-list>
              </v-menu>
            </div>
          </v-row>
        </span>
      </ul>
    </div>

    <!-- 윈도우 브라우저가 767 미만일 때의 Navbar  && Home.vue에서 적용안됨 -->
    <div v-if="windowWidth <767">
      <div class="bg">
         <div>
           <router-link to="/"><img src="../src/images/logo2.png" alt="MIT Logo" style="height:30px" class="logo2"></router-link>
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
                <v-list v-for="chat in allChat" :key="chat">
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
                    x-large color="#FFFFFF"
                    v-bind="attrs"
                    v-on="on"
                    class="accountIcon not-home mt-1 mr-2"
                  >
                    mdi-bell
                  </v-icon>
                  <!-- <v-icon>mdi-bell-check</v-icon> -->
                </template>
                <v-list v-for="alarm in alarmList" :key="alarm">
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
                    <v-list-item-title class="not-home"><router-link id="tokenTrue" to="/" @click.native="logout">LOGOUT</router-link></v-list-item-title>
                  </v-list-item>
                </v-list>
              </v-menu>
            </div>s
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
    <v-app color="#FAFAFA" class="container">
      <div>
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
      this.$router.push('/projectlist')
    },
    goCON(){
      this.$router.push('/AllContest')
    },
    goTEAM(){
      this.$router.push('/myteam')
    },
    goPRO(){
      this.$router.push('/profile')
    },
    goChat(chat){
      this.$router.push({name: 'Chat', params: { privateChatTitle : chat }});
    }

  },
  computed: {
    ...mapState(['email', 'myNick', 'alarmList', 'allChat']),
    ...mapGetters(['isLoggedIn', 'isEmail'])
  },
  mounted() {
    this.getContestData()
    if (this.$cookies.isKey('auth-token')) {
      this.postEmailToken()
      this.getTeamInfo()
      } 
    this.$nextTick(() => {
      window.addEventListener('resize', this.onResize);
    })
  },
  created () {
    this.getNickname(this.email)
    // this.getAllChat(this.myNick)
  },
};
</script>

<style scoped>
  @font-face {
    font-family: myFont;
    src: url("./font/BMJUA_ttf.ttf");
  }

  *{ font-family: myFont, sans-serif; }

  .not-home > a { 
    text-decoration: none;
    color: rgb(92, 107, 192);
    font-size: 1.3rem;
    font-weight: bold;
  }

  .cont5 {
    margin: 0 5%;
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

  ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: white;
    line-height: 90px;
    box-shadow: 0 0 5px 5px;
    margin-bottom: 5px;
  }

  li {
    float: left;
  }

  li a {
    display: block;
    color: rgb(92, 107, 192);
    text-align: center;
    padding: 0 5px;
    text-decoration: none;
  }

  li > a > h4 {
    line-height: 90px !important;
    margin: 0 !important;
  }

  li a:hover:not(.active) {
    background-color: white;
  }

  .active {
    background-color: #4CAF50;
  }

  .chat_img {
    float: left;
    width: 30px;
    height: 30px;
  }

</style>