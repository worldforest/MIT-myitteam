<template>
  <div  class="cont10">
    <!-- {{ myNick }} -->
    {{ privateData }}
    {{ privateChatTitle }}
    <v-row v-if="windowWidth >= 1270">
      <v-col col="2" sm="2" class="fg1">
        <div>
          <img  v-if="userprofiledata.src" :src="userprofiledata.src" class="box" style="width:150px; height:150px;">
        </div>
      </v-col>
      
      <v-col col="12" sm="10" class="fg2">
        <div class="ml-5">
          <span ><h3 class="my-3">{{ userprofiledata.nickname }}</h3></span>
            <!-- 현재 사용중인 유저 닉네임과 프로필 유저 닉네임이 같지 않고, 팔로우리스트안에 이메일이 없을 경우 -->
            <v-row>
              <span v-if="user !== email">
                <v-btn
                  v-if="!followerList2.includes(email)"
                  class="ml-3"
                  color="primary"
                  @click="follow(user)"
                >
                  팔로우
                </v-btn>

                <v-btn
                  v-else
                  class="ml-3"
                  color="primary"
                  @click="unfollow(user)"
                >
                  팔로우 취소
                </v-btn>
              </span>
              
              <!-- 채팅 좀 하겠습니다 ~~~~~~ -->
              <v-row justify="center" class="chatmodal">
                <v-btn color="primary" dark @click.stop="dialog = true">
                  채팅 하기
                </v-btn>

                <v-dialog v-model="dialog" max-width="550">
                  <v-card class="py-5 px-5 chatmodal">
                    <Chat :privateChatTitle="this.privateChatTitle"/>
                    <v-card-actions>  
                      <v-spacer></v-spacer>

                      <v-btn color="green darken-1" class="" text @click="dialog = false">
                        닫기
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </v-row>
            </v-row>
        </div>
        <div class="d-flex my-5 ml-5">
          <span><h4>팔로우| {{ userprofiledata.followingCnt }}명</h4></span>
          <span class="mx-auto"><h4>팔로워| {{ followerList.length }}명</h4></span>
        </div>
        <div class="d-flex ml-5">
          <span> <h3>{{ userprofiledata.description }} 개발자</h3> </span>
          <br>
        </div>
      </v-col>
    </v-row>

    <!-- 788 이상 /////////////////////////////////////// -->
    <v-row v-else-if="windowWidth >= 788">
      <v-col col="2" sm="2" class="fg1 mr-6">
        <div class="pf-box">
          <img  v-if="userprofiledata.src" :src="userprofiledata.src" class="box" style="width:150px; height:150px;">
        </div>
      </v-col>
      
      <v-col col="10" sm="10" class="fg2">
        <div class="ml-5">
          <span>{{ userprofiledata.nickname }}</span>
          <v-row>
            <span v-if="user !== email">
              <v-btn
                v-if="!followerList2.includes(email)"
                class="ml-3"
                color="primary"
                @click="follow(user)"
              >
                팔로우
              </v-btn>

              <v-btn
                v-else
                class="ml-3"
                color="primary"
                @click="unfollow(user)"
              >
                팔로우 취소
              </v-btn>
            </span>

            <!-- 채팅 좀 하겠습니다 ~~~~~~ -->
            <v-row justify="center" class="chatmodal">
              <v-btn color="primary" dark @click.stop="dialog = true">
                채팅 하기
              </v-btn>

              <v-dialog v-model="dialog" max-width="550">
                <v-card class="py-5 px-5 chatmodal">
                  <Chat :privateChatTitle="this.privateChatTitle"/>
                  <v-card-actions>  
                    <v-spacer></v-spacer>

                    <v-btn color="green darken-1" class="" text @click="dialog = false">
                      닫기
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-row>
          </v-row>
        </div>
        <div class="d-flex my-5 ml-5">
          <span>팔로우| {{ userprofiledata.followingCnt }}명</span>
          <span class="mx-auto">팔로워| {{ followerList.length }}명</span>
        </div>
        <div class="d-flex ml-5">
           <span> <h4>{{ userprofiledata.description }} 개발자</h4> </span>
          <br>
          
        </div>
      </v-col>
    </v-row>
    
    <!-- 작을 때 ///////////////////////////////////////////// -->
    <v-row v-else>
      <v-col col="2" sm="2" class="fg1 mr-6">
        <div class="pf-box">
          <img  v-if="userprofiledata.src" :src="userprofiledata.src" class="box2" style="width:77px; height:77px;">
        </div>
      </v-col>
      
      <v-col col="10" sm="10" class="fg2">
        <div class="ml-5">
          <h3 class="ml-5 mb-3">{{ userprofiledata.nickname }}</h3>
          <v-row>
            <span v-if="user.email !== email">
              <v-btn
                v-if="!followerList2.includes(email)"
                class="ml-3"
                color="primary"
                @click="follow(user)"
              >
                팔로우
              </v-btn>

              <v-btn
                v-else
                class="ml-3"
                color="primary"
                @click="unfollow(user)"
              >
                팔로우 취소
              </v-btn>
            </span>

            <!-- 채팅 좀 하겠습니다 ~~~~~~ -->
            <v-row justify="center" class="chatmodal">
              <span>
                <v-btn color="primary" dark @click.stop="dialog = true">
                  채팅 하기
                </v-btn>
              </span>

              <v-dialog v-model="dialog" max-width="550">
                <v-card class="py-5 px-5 chatmodal">
                  <Chat :privateChatTitle="this.privateChatTitle"/>
                  <v-card-actions>  
                    <v-spacer></v-spacer>

                    <v-btn color="green darken-1" class="" text @click="dialog = false">
                      닫기
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-row>
          </v-row>
        </div>
      </v-col>
    </v-row>
    <span v-if="windowWidth < 788">{{ userprofiledata.description }} 개발자</span>
    <hr v-if="windowWidth < 788">
    <v-row v-if="windowWidth < 788">
      <v-col cols="6">
        <h4>팔로우| {{ userprofiledata.followingCnt }}명 </h4>
      </v-col>
      <v-col cols="6">
        <h4> 팔로워| {{ followerList.length }}명 </h4>
      </v-col>
    </v-row>    
    <hr class="mb-3">
    <div class="text-center">
      <h3>피드</h3>
    </div>
    <router-link class="feed white--text"  to="/feedcreate" v-if="userprofiledata.nickname === profileData.nickname">
      피드등록
    </router-link>
    <v-row class="my-4" v-if="windowWidth > 788">
      <v-col cols="4" v-for="feed in userprofiledata.feeds" :key="feed.no">
        <div class="mx-2 detail_hover">         
          <img :src="feed.src" 
          alt="안나오는겁니다!" 
          style="width:100%; height: 20vw" 
          :feed="feed" 
          @click="feedDetail(feed)" >
        </div>
      </v-col>
    </v-row>
    <v-row v-else class="bg-gray">
      <v-col cols='12'  v-for="feed in userprofiledata.feeds" :key="feed.no">
        <v-card
          :loading="loading"
          class="mx-auto my-3"
        >
          <v-img
            height="300"
            :src="feed.src"
            @click="feedDetail(feed)"
          ></v-img>

          <v-card-text @click="feedDetail(feed)">
            <v-row
              align="center"
              class="mx-0"
            >
            </v-row>

            <div class="my-4 subtitle-1">
              {{feed.description}}
            </div>
          </v-card-text>

          <v-card-text>
            <v-chip-group
              v-model="selection"
              active-class="deep-purple accent-4 white--text"
              column
            >
              <v-chip v-for="(tag,i) in feed.tag" :key="i" @click="searchTagFeed(tag)">
                #{{tag}}
              </v-chip>
            </v-chip-group>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>

  </div>
</template>

<script>
import { mapState, mapActions, mapGetters, mapMutations } from 'vuex'
import Chatting from './Chat.vue'

export default {
  name: 'Profile',
  components: {
    Chat: Chatting
  },
  props: {

  },
  data() {
    return {
      windowWidth: window.innerWidth,
      followList : [],
      followconf: true,
      email : this.$store.state.email, 
      privateData: {
        myNickname : '',
        yourNickname : '',
      },
      dialog: false,
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
    onChange(e) {
      const file = e.target.files[0];
      this.profileData.src = URL.createObjectURL(file)
    },
    onResize() {
      this.windowWidth = window.innerWidth
    },
    ...mapMutations(['feedDetail', 'GET_EMAIL']),
    ...mapActions(['userprofile', 'profile', 'follow', 'myFollowerList', 'unfollow', 'follwerCnt', 'getNickname', 'privateChat',])
  },
  computed : {
    // ...mapGetter s(['isLoggedIn'])
    ...mapState(['userprofiledata', 'email', 'profileData', 'followerList', 'followflag', 'followCnt', 'followerList2', 'myNick', 'privateChatTitle', 'user']),
    ...mapGetters(['isLoggedIn',])
     
  },
  mounted() {
    setTimeout(() => {
        this.privateData.yourNickname = JSON.parse(sessionStorage.getItem('userprofileinfo')).nickname
        console.log(this.privateData.yourNickname)
        this.privateData.myNickname = this.$store.state.myNick
        this.privateChat(this.privateData)
        console.log(this.userprofiledata)
			}, 200)
    this.followList = this.$store.state.followerList
    this.$nextTick(() => {
      window.addEventListener('resize', this.onResize);
    })
    this.follwerCnt(this.user)
  },

  created () {
    this.myFollowerList(this.user)
    // 이 페이지의 유저 정보를 확인
    this.userprofile(this.user)
    // 로그인한 유저의 정보를 확인
    this.profile()
    this.getNickname(this.$store.state.email)
    
  },
}
</script>

<style scoped>
  @font-face {
    font-family: myFont;
    src: url("/src/font/BMJUA_ttf.ttf");
  }

  .h1 {
    font-size: 30px;
  }

  .filebox label 
    { display: inline-block;
      padding: .5em .75em;
      color: #999; font-size: inherit;
      line-height: normal; 
      vertical-align: middle; 
      background-color: #fdfdfd; 
      cursor: pointer; 
      border: 1px solid #ebebeb; 
      border-bottom-color: #e2e2e2; 
      border-radius: .25em; } 
      
  .filebox input[type="file"] {
    position: absolute; 
    width: 1px; 
    height: 1px; 
    padding: 0; 
    margin: -1px; 
    overflow: hidden; 
    clip:rect(0,0,0,0); 
    border: 0; }

  .detail_hover:hover {
    opacity: 0.5;
  }

  .cont10 {
    margin: 0 10%;
  }

  .box{
    width: 150px;
    height: 150px; 
    border-radius: 70%;
    overflow: hidden;
  }

  .fg1 {
    flex-grow: 1;
    flex-basis: 0;
  }

  .fg2 {
    flex-grow: 2;
    flex-basis: 30px;
  }

  .box2 {
    width: 77px;
    height: 77px; 
    border-radius: 70%;
    overflow: hidden;
  }

  .feed {
    background-color: rgb(92, 107, 192);
    padding: 0.8rem;
    font-weight: bold;
    text-decoration: none;
    border-radius: 0.5rem;
  }
  
  .chatBtn {
    padding: 0.65rem;
    background-color: rgb(92, 107, 192);
    color: white;
    border-radius: 0.3rem;
    text-decoration: none;
    margin-left: 2rem;
  }

  .chatmodal{
    font-family: myFont, sans-serif;
  }
</style>
