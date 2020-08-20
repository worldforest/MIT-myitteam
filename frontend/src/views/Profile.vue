<template>
  <div :class="[ {'cont10': windowWidth >= 767 } ]">
    <v-row v-if="windowWidth >= 1270">
      <v-col col="2" sm="3" class="fg1">
        <div>
          <img  v-if="profileData.src" :src="profileData.src" class="box" style="width:150px; height:150px;">
        </div>
      </v-col>
      
      <v-col col="10" sm="9" class="fg2">
        <div class="ml-5">
          <span> <h3> {{ profileData.nickname }} </h3></span>
        </div>
        <div class="d-flex my-5 ml-5">
          <div class="text-center mr-15">
            <v-dialog v-model="dialog" width="300">
              <template v-slot:activator="{ on, attrs }">
                <span v-bind="attrs" v-on="on" class="cursor" @click="myFollowList(email);">
                  <h5>팔로잉 | {{ profileData.followingCnt }}</h5>
                </span>
              </template>

              <v-card class="cardModal">
                <h3 class="modaltitle mb-3"> 내가 팔로우한 리스트</h3>
                
                <li v-for="(item, index) in followList" :key="index">
                  <h4 class="followa ml-3 my-2" @click="goUserProfile(item.email)">{{ item.nickname }}</h4>
                  <hr>
                </li>

                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn class="local" color="primary" text @click="dialog = false">
                    <h5>닫기</h5>
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </div>

          <div class="text-center">
            <v-dialog v-model="dialog2" width="300">
              <template v-slot:activator="{ on, attrs }">
                <span v-bind="attrs" v-on="on" class="cursor" @click="myFollowerList(email);">
                  <h5>팔로워 | {{ profileData.followerCnt }}</h5>
                </span>
              </template>

              <v-card class="cardModal">
                <h3 class="modaltitle mb-3"> 나를 팔로우 하는 리스트</h3>
                
                <li v-for="(item, index) in followerList" :key="index">
                  <h4 class="followa ml-3 my-2" @click="goUserProfile(item.email)">{{ item.nickname }}</h4>
                  <hr>
                </li>

                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn class="local" color="primary" text @click="dialog2 = false">
                    <h5>닫기</h5>
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </div>
        </div>
        <div class="d-flex ml-5">
          <h5 ># <span class="fontcolor">{{ profileData.description }} 개발자</span></h5>
          <br>
        </div>
        <div class="d-flex">
          <div class="ml-auto TeamButton cursor" style="color:white" @click="updateProfile(profileData)">
            <img src="@/assets/edit.png" alt="" style="width:10px" class="mr-2" >프로필 수정
          </div>
        </div>
      </v-col>
    </v-row>

    <!--////////////////////// 윈도우 788 이상 //////////////////////-->
    <v-row v-else-if="windowWidth >= 767">
      <v-col col="2" sm="4" class="fg1 mr-6">
        <div class="pf-box">
          <img  v-if="profileData.src" :src="profileData.src" class="box" style="width:150px; height:150px;">
        </div>
      </v-col>
      
      <v-col col="10" sm="8" class="fg2">
        <div class="ml-5">
          <span><h3>{{ profileData.nickname }}</h3></span>
        </div>
        <div class="d-flex my-5 ml-5">
          <div class="text-center mr-15">
            <v-dialog v-model="dialog" width="300">
              <template v-slot:activator="{ on, attrs }">
                <span v-bind="attrs" v-on="on" class="cursor" @click="myFollowList(email);">
                  <h4>팔로잉| {{ profileData.followingCnt }}명</h4>
                </span>
              </template>

              <v-card class="cardModal">
                <h3 class="modaltitle mb-3"> 내가 팔로우 한 리스트</h3>
                
                <li v-for="(item, index) in followList" :key="index">
                  <h4 class="followa ml-3 my-2" @click="goUserProfile(item.email)">{{ item.nickname }}</h4>
                  <hr>
                </li>

                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn class="local" color="primary" text @click="dialog = false">
                    <h5>닫기</h5>
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </div>

          <div class="text-center">
            <v-dialog v-model="dialog2" width="300">
              <template v-slot:activator="{ on, attrs }">
                <span v-bind="attrs" v-on="on" class="cursor" @click="myFollowerList(email);">
                  <h4>팔로워| {{ profileData.followerCnt }}명</h4>
                </span>
              </template>

              <v-card class="cardModal">
                <h3 class="modaltitle mb-3"> 나를 팔로우 하는 리스트</h3>
                
                <li v-for="(item, index) in followerList" :key="index">
                  <h4 class="followa ml-3 my-2" @click="goUserProfile(item.email)">{{ item.nickname }}</h4>
                  <hr>
                </li>

                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn class="local" color="primary" text @click="dialog2 = false">
                    <h5>닫기</h5>
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </div>
        </div>
        <div class="d-flex ml-5">
          <br>
          <span> <h4>{{ profileData.description }} 개발자</h4> </span>
        </div>
        <div class="d-flex">
          <div class="ml-auto TeamButton cursor" style="color:white" @click="updateProfile(profileData)">
            <img src="@/assets/edit.png" alt="프로필 사진" style="width:10px" class="mr-2" >프로필 수정
          </div>
        </div>
      </v-col>
    </v-row>
    <!-- ####################### 시작 ######################################### -->
    <div class="wrapper mx-auto my-3" v-else>      
      <div class="profile">
        <img :src="profileData.src" class="thumbnail">
        <h3 class="name">{{ profileData.nickname }}</h3>
        <p class="title">{{ profileData.description }} 개발자</p>
        <button type="button" class="btn text-center" @click="updateProfile(profileData)">프로필 수정</button>
      </div>
      
      <div class="social-icons">
        <div class="icon">
          <v-dialog v-model="dialog2" width="300">
            <template v-slot:activator="{ on, attrs }">
              <span v-bind="attrs" v-on="on" class="cursor" @click="myFollowerList(email);">
                <h4>{{  profileData.followerCnt }}</h4>
              </span>
            </template>

            <v-card class="cardModal">
              <h3 class="modaltitle mb-3"> 나를 팔로우 하는 리스트</h3>
              
              <li v-for="(item, index) in followerList" :key="index">
                <h4 class="followa ml-3 my-2" @click="goUserProfile(item.email)">{{ item.nickname }}</h4>
                <hr>
              </li>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn class="local" color="primary" text @click="dialog2 = false">
                  <h5>닫기</h5>
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
          <p>Followers</p>
        </div>
        
        <div class="icon">
          <v-dialog v-model="dialog" width="300">
            <template v-slot:activator="{ on, attrs }">
              <span v-bind="attrs" v-on="on" class="cursor" @click="myFollowList(email);">
                <h4>{{  profileData.followingCnt }}</h4>
              </span>
            </template>

            <v-card class="cardModal">
              <h3 class="modaltitle mb-3"> 내가 팔로우 한 리스트</h3>
              
              <li v-for="(item, index) in followList" :key="index">
                <h4 class="followa ml-3 my-2" @click="goUserProfile(item.email)">{{ item.nickname }}</h4>
                <hr>
              </li>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn class="local" color="primary" text @click="dialog = false">
                  <h5>닫기</h5>
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
          <p>Followings</p>
        </div>
        
      </div>
    </div>
    <hr class="my-4">
    <div class="text-center" v-if="windowWidth <= 760">
      <h3>피드</h3>
    </div>
    <div class="mt-6">
      <router-link class="feed white--text my-3" to="/feedcreate">
        피드등록
      </router-link>
    </div>
    <div v-if="windowWidth > 760" class="bg-gray">
      <main>
        <h1>my Feed</h1>
        <div class="flex-container">
          <figure v-for="(feed, index) in profileData.feeds" :key="index">
            <img :src="feed.src" alt="피드 이미지" @click='feedDetail(feed)'/>
            <figcaption>
              <div class="fig-author">by {{ feed.nickname }}</div>
            </figcaption>
          </figure>
        </div>            
      </main>
    </div>
    <v-row v-else class="cont3">
      <v-col cols='12'  v-for="(feed, index) in profileData.feeds" :key="index">
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
              <span v-if="feed.description.length < 30">{{feed.description}}</span>
              <span v-else-if="feed.description.length >= 30">{{feed.description.slice(0,30)}}...</span>
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

export default {
  name: 'Profile',
  components: {

  },
  data() {
    return {
      windowWidth: window.innerWidth,
      dialog: false,
      dialog2: false,
      
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

    ...mapMutations(['feedDetail', 'updateProfile', 'goUserProfile']),
    ...mapActions(['profile', 'myFollowList', 'myFollowerList','searchTagFeed'])
  },
  computed : {
    ...mapState(['profileData', 'email', 'followList', 'followerList']),
    ...mapGetters(['isLoggedIn',])
  },
  mounted() {
    this.profile()
    this.$nextTick(() => {
      window.addEventListener('resize', this.onResize);
    })
    window.scrollTo(0, 0)
  }

}
</script>

<style scoped>
  *{ font-family: 'Jua', sans-serif;}

  .h1 {
    font-size: 30px;
  }
  .fontcolor{
    color: rgb(92, 107, 192);
  }
  .filebox label 
    { display: inline-block;
      padding: .5em .75em;
      color: #999; font-size: inherit;
      line-height: normal; 
      vertical-align: middle; 
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
  
  .cont3 {
    margin: 0 3%;
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

  .cursor {
    cursor: pointer;
    font-weight: bold;
  }

  .TeamButton {
    text-decoration: none;
    background-color: rgb(92, 107, 192);
    padding: 0.5rem 1rem;
    border-radius: 0.5rem;
    font-size: 0.8rem;
    font-weight: bold;
  }

  .TeamButton2 {
    text-decoration: none;
    background-color: rgb(92, 107, 192);
    padding: 0.5rem 0.5rem;
    border-radius: 0.5rem;
    font-size: 0.5rem;
    font-weight: bold;
  }

  .bg-gray {
    background-color: #FAFAFA !important;
  }

  .cardModal{
    font-family: myFont, sans-serif;
    width: 300px;
  }
  .modaltitle{
    padding: 0.7rem;
    background-color: rgb(92, 107, 192);
    color: white;
  }
  li{
    list-style: none;
  }
  .followa{
    text-decoration: none;
    color:black;

  }

  /* 프로필 데이터 */
  
  .wrapper {
    width: 320px;
    height: 540px;
    background: #FAFAFA;
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
    background: #FAFAFA;
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

  /* 여기서 부터는 피드 */
  @import url("https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,700");
* {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
  }

  a {
    text-decoration: none;
    margin: 0;
    padding: 0;
  }

  body {
    background-color: #FAFAFA;
    font-family: 'Montserrat', sans-serif;
    color: #262626;
    font-size: 16px;
  }

  main {
    max-width: 1000px;
    background-color: #FAFAFA;
    margin: 0 auto;
  }
  main h1 {
    font-size: 3rem;
    text-transform: uppercase;
    margin: 0;
    text-align: center;
    padding: 2rem 5% 1rem 5%;
  }
  main h2 {
    font-size: 1.3rem;
    font-weight: 300;
    text-transform: uppercase;
    margin: 0;
    text-align: center;
    padding: 0 5% 3rem 5%;
  }
  main .flex-container {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-content: space-between;
    flex-wrap: wrap;
    padding: 0 5% 5% 5%;
  }
  main .flex-container figure {
    margin-bottom: 1rem;
    position: relative;
  }
  main .flex-container figure img {
    width: 100%;
    height: 25vw;
    opacity: 1;
    -webkit-transition: opacity 0.5s;
    /* For Safari 3.1 to 6.0 */
    transition: opacity 0.5s;
  }
  main .flex-container figure img:hover {
    opacity: 0.9;
  }
  main .flex-container figure figcaption {
    padding: 10px;
    line-height: 1.5;
    position: absolute;
    bottom: 0;
    right: 0;
    text-align: right;
    color: #fff;
    opacity: 0.7;
  }
  main .flex-container figure figcaption .fig-title {
    font-weight: 700;
  }
  main .flex-container figure figcaption .fig-author {
    font-weight: 300;
    font-size: 0.8rem;
  }
  main .more-container {
    margin: 0 auto;
    text-align: center;
    padding: 0 5%;
    margin-bottom: 5%;
  }
  main .more, main .more:link, main more:visited {
    color: #262626;
    font-weight: 700;
    padding: 10px;
    opacity: 0.5;
    -webkit-transition: opacity 0.5s;
    /* For Safari 3.1 to 6.0 */
    transition: opacity 0.5s;
  }
  main .more:hover {
    opacity: 1;
  }

  /* Media queries*/
  @media screen and (min-width: 701px) {
    figure {
      width: calc(33% - 0.5rem);
    }
  }
  @media screen and (max-width: 700px) {
    figure {
      width: calc(50% - 0.5rem);
    }
  }
  @media screen and (max-width: 550px) {
    figure {
      width: 100%;
    }
  }


</style>
