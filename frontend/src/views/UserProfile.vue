<template>
  <div :class="[ {'cont10': windowWidth >= 767 } ]">
    <v-row v-if="windowWidth >= 1270">
      <v-col col="2" sm="2" class="fg1">
        <div>
          <img  v-if="userprofiledata.src" :src="userprofiledata.src" class="box" style="width:150px; height:150px;">
        </div>
      </v-col>
      
      <v-col col="12" sm="10" class="fg2">
        <div class="ml-5">
          <v-row>
            <span><h3 class="my-3">{{ userprofiledata.nickname }}</h3></span>
            <span class="mt-3 ml-13" v-if="user !== email">
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
            <span justify="center" class="chatmodal mt-3 ml-11 ">
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
            </span>
          </v-row>
            <!-- 현재 사용중인 유저 닉네임과 프로필 유저 닉네임이 같지 않고, 팔로우리스트안에 이메일이 없을 경우 -->
        </div>
        <div class="d-flex my-5 ml-3">
          <span><h5>팔로우| {{ userprofiledata.followingCnt }}명</h5></span>
          <span class="ml-15"><h5>팔로워| {{ followerList.length }}명</h5></span>
        </div>
        <div class="d-flex ml-3">
          <span><h5 class="fontcolor">#{{ userprofiledata.description }} 개발자</h5> </span>
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
    <div class="wrapper mx-auto" v-else>      
      <div class="profile">
        <img :src="userprofiledata.src" class="thumbnail">
        <h3 class="name">{{ userprofiledata.nickname }}</h3>
        <p class="title">{{ userprofiledata.description }} 개발자</p>
        <button type="button" class="btn text-center my-2" v-if="!followerList2.includes(email)"  @click="follow(user)">팔로우</button>
        <button type="button" class="btn text-center my-2" v-else-if="followerList2.includes(email)" @click="unfollow(user)">팔로우 취소</button>
        <div class="chatmodal">
          <button type="button" class="btn text-center"  @click.stop="dialog = true">채팅하기</button>
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
        </div>
      </div>

      <div class="social-icons">
        <div class="icon"> <h4>{{ followerList.length }}명</h4> <p>Followers</p> </div>
        <div class="icon"> <h4>{{ userprofiledata.followingCnt }}명</h4> <p>Followings</p></div>
      </div>
    </div>
    <hr class="mb-3">
    <div class="text-center">
      <h3>피드</h3>
    </div>
    <router-link class="feed white--text"  to="/feedcreate" v-if="userprofiledata.nickname === profileData.nickname">
      피드등록
    </router-link>
    <v-row class="my-4" v-if="windowWidth > 788">
      <v-col cols="4" v-for="(feed, index) in userprofiledata.feeds" :key="index">
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
      <v-col cols='12'  v-for="(feed, index) in userprofiledata.feeds" :key="index">
        <v-card
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
    ...mapActions(['userprofile', 'profile', 'follow', 'myFollowerList', 'unfollow', 'followerCnt', 'getNickname', 'privateChat',])
  },
  computed : {
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
    this.followerCnt(this.user)
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
 *{ font-family: 'Jua', sans-serif;}

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

  /* 프로필 데이터 */
  
  .wrapper {
    width: 320px;
    height: 540px;
    background: #FDFEFF;
    -webkit-transition: background .6s ease;
    transition: background .6s ease;
    border-radius: 10px;
    padding: 20px 20px 20px 20px;
    box-shadow: 0 8px 40px rgba(0, 0, 0, 0.2);
  }

  .wrapper .profile {
    margin-top: 2.2em;
    position: relative;
  }
  .wrapper .profile:after {
    width: 100%;
    height: 1px;
    content: ' ';
    display: block;
    margin-top: 1.3em;
    background: #E9EFF6;
  }
  .wrapper .profile .check {
    position: absolute;
    right: 5em;
    bottom: 12.7em;
  }
  .wrapper .profile .check i {
    color: #fff;
    width: 20px;
    height: 20px;
    font-size: 12px;
    line-height: 20px;
    text-align: center;
    border-radius: 100%;
    background: -webkit-gradient(linear, left top, right bottom, from(#C90A6D), to(#FF48A0));
    background: linear-gradient(to bottom right, #C90A6D, #FF48A0);
  }
  .wrapper .profile .thumbnail {
    width: 124px;
    height: 124px;
    display: -webkit-box;
    display: flex;
    margin-left: auto;
    margin-right: auto;
    margin-bottom: 1.5em;
    border-radius: 100%;
    box-shadow: 0 13px 26px rgba(0, 0, 0, 0.2), 0 3px 6px rgba(0, 0, 0, 0.2);
  }
  .wrapper .profile .name {
    color: #2D354A;
    font-size: 24px;
    font-weight: 600;
    text-align: center;
  }
  .wrapper .profile .title {
    color: #7C8097;
    font-size: .75em;
    font-weight: 300;
    text-align: center;
    padding-top: .5em;
    padding-bottom: .7em;
    letter-spacing: 1.5px;
    text-transform: uppercase;
  }
  .wrapper .profile .description {
    color: #080911;
    font-size: 14px;
    font-weight: 300;
    text-align: center;
    margin-bottom: 1.3em;
  }
  .wrapper .profile .btn {
    color: #fff;
    width: 130px;
    height: 42px;
    outline: none;
    border: none;
    display: block;
    cursor: pointer;
    font-weight: 300;
    margin-left: auto;
    margin-right: auto;
    border-radius: 70px;
    box-shadow: 0 13px 26px rgba(0, 0, 0, 0.16), 0 3px 6px rgba(0, 0, 0, 0.16);
    background: -webkit-gradient(linear, left top, right bottom, from(#C90A6D), to(#FF48A0));
    background: linear-gradient(to bottom right, #C90A6D, #FF48A0);
  }
  .wrapper .social-icons {
    display: -webkit-box;
    display: flex;
    margin-top: 1.2em;
    -webkit-box-pack: justify;
            justify-content: space-around;
  }
  .wrapper .social-icons .icon {
    display: -webkit-box;
    display: flex;
    -webkit-box-align: center;
            align-items: center;
    -webkit-box-orient: vertical;
    -webkit-box-direction: normal;
            flex-direction: column;
  }
  .wrapper .social-icons .icon a {
    color: #fff;
    width: 60px;
    height: 60px;
    font-size: 28px;
    line-height: 60px;
    text-align: center;
    border-radius: 30px;
    box-shadow: 0 13px 26px rgba(0, 0, 0, 0.2), 0 3px 6px rgba(0, 0, 0, 0.2);
  }
  .wrapper .social-icons .icon:nth-of-type(1) a {
    background: -webkit-gradient(linear, left top, right bottom, from(#C90A6D), to(#FF48A0));
    background: linear-gradient(to bottom right, #C90A6D, #FF48A0);
  }
  .wrapper .social-icons .icon:nth-of-type(2) a {
    background: -webkit-gradient(linear, left top, right bottom, from(#5E5AEC), to(#3F9EFC));
    background: linear-gradient(to bottom right, #5E5AEC, #3F9EFC);
  }
  .wrapper .social-icons .icon:nth-of-type(3) a {
    background: -webkit-gradient(linear, left top, right bottom, from(#6452E9), to(#639FF9));
    background: linear-gradient(to bottom right, #6452E9, #639FF9);
  }
  .wrapper .social-icons .icon h4 {
    color: #080911;
    font-size: 1em;
    margin-top: 1.3em;
    margin-bottom: .2em;
  }
  .wrapper .social-icons .icon p {
    color: #666B7D;
    font-size: 12px;
  }

  .concept {
    position: absolute;
    bottom: 25px;
    color: #AAB0C4;
    font-size: .9em;
    font-weight: 400;
  }
  .concept a {
    color: #ac1966;
    text-decoration: none;
  }
</style>
