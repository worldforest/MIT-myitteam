<template>
  <div  class="cont10">
    <p>{{ email }}</p>
    <!-- {{ (followList.includes(email)) }} -->
    {{ followerList }}

    <v-row v-if="windowWidth >= 1270">
      <v-col col="2" sm="2" class="fg1">
        <div>
          <img  v-if="userprofiledata.src" :src="userprofiledata.src" class="box" style="width:150px; height:150px;">
        </div>
        <div class="filebox ml-4"> 
          <label for="ex_file"><img src="@/assets/edit.png" alt="" style="width:10px">수정</label>
          <input 
            type="file" 
            id="ex_file"
            accept="image/*"
            @change="onChange">
        </div>
      </v-col>
      
      <v-col col="10" sm="10" class="fg2">
        <div class="ml-5">
          <span>{{ userprofiledata.nickname }}</span>
            <!-- 현재 사용중인 유저 닉네임과 프로필 유저 닉네임이 같지 않고, 팔로우리스트안에 이메일이 없을 경우 -->
            <v-btn
              v-if="((userprofiledata.nickname !== profileData.nickname) && !(followerList.includes(email)))"
              class="ml-3"
              color="primary"
              @click="follow"
            >
              팔로우
            </v-btn>

            <v-btn
              v-else
              class="ml-3"
              color="primary"
              @click="unfollow(userprofiledata.feeds[0].email)"
            >
              팔로우 취소
            </v-btn>
        </div>
        <div class="d-flex my-5 ml-5">
          <span>팔로우| {{ userprofiledata.followingCnt }}명</span>
          <span class="mx-auto">팔로워| {{ userprofiledata.followerCnt }}명</span>
        </div>
        <div class="d-flex ml-5">
          {{ userprofiledata.description }}
          <br>
          
        </div>
      </v-col>
    </v-row>

    <v-row v-else-if="windowWidth >= 788">
      <v-col col="2" sm="2" class="fg1 mr-6">
        <div class="pf-box">
          <img  v-if="userprofiledata.src" :src="userprofiledata.src" class="box" style="width:150px; height:150px;">
        </div>
        <div class="filebox ml-4"> 
          <label for="ex_file"><img src="@/assets/edit.png" alt="" style="width:10px">수정</label>
          <input 
            type="file" 
            id="ex_file"
            accept="image/*"
            @change="onChange">
        </div>
      </v-col>
      
      <v-col col="10" sm="10" class="fg2">
        <div class="ml-5">
          <span>{{ userprofiledata.nickname }}</span>

            <v-btn
              v-if="userprofiledata.nickname !== profileData.nickname"
              class="ml-3"
              color="primary"
              @click="follow()"
            >
              팔로우
            </v-btn>
        </div>
        <div class="d-flex my-5 ml-5">
          <span>팔로우| {{ userprofiledata.followingCnt }}명</span>
          <span class="mx-auto">팔로워| {{ userprofiledata.followerCnt }}명</span>
        </div>
        <div class="d-flex ml-5">
          {{ userprofiledata.description }}
          <br>
          
        </div>
      </v-col>
    </v-row>
    
    <v-row v-else>
      <v-col col="2" sm="2" class="fg1 mr-6">
        <div class="pf-box">
          <img  v-if="userprofiledata.src" :src="userprofiledata.src" class="box2" style="width:77px; height:77px;">
        </div>
        <div class="filebox ml-4"> 
          <label for="ex_file"><img src="@/assets/edit.png" alt="" style="width:10px"></label>
          <input 
            type="file" 
            id="ex_file"
            accept="image/*"
            @change="onChange">
        </div>
      </v-col>
      
      <v-col col="10" sm="10" class="fg2">
        <div class="ml-5">
          <p class="ml-5">{{ userprofiledata.nickname }}</p>

            <v-btn
              v-if="userprofiledata.nickname !== profileData.nickname"
              class="ml-3"
              color="primary"
              @click="follow()"
            >
              팔로우
            </v-btn>
        </div>
      </v-col>
    </v-row>
    <span v-if="windowWidth < 788">{{ userprofiledata.description }}</span>
    <hr>
    <v-row v-if="windowWidth < 788">
      <v-col cols="6">
        팔로우| {{ userprofiledata.followingCnt }}명
      </v-col>
      <v-col cols="6">
        팔로워| {{ userprofiledata.followerCnt }}명
      </v-col>
    </v-row>    
    <hr>
    <div class="text-center">
      피드
    </div>
    <router-link to="/feedcreate">
      피드등록
    </router-link>
    <v-row>
      <v-col cols="4" v-for="feed in userprofiledata.feeds" :key="feed.no">
        <div class="mx-2 detail_hover">         
          <img src="https://t1.daumcdn.net/cfile/tistory/9976523D5AD95B6627" 
          alt="" 
          style="width:100%;" 
          :feed="feed" 
          @click="feedDetail(feed)" >
        </div>
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
  props: {
    user: String
  },
  data() {
    return {
      windowWidth: window.innerWidth,
      followList : []
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
    ...mapMutations(['feedDetail']),
    ...mapActions(['userprofile', 'profile', 'follow', 'myFollowerList', 'unfollow'])
  },
  computed : {
    // ...mapGetter s(['isLoggedIn'])
    ...mapState(['userprofiledata', 'email', 'profileData', 'followerList']),
    ...mapGetters(['isLoggedIn',])
     
  },
  mounted() {
    this.followList = this.$store.state.followerList
    this.$nextTick(() => {
      window.addEventListener('resize', this.onResize);
    })
    this.myFollowerList(this.user)
  },

  created () {
    // 이 페이지의 유저 정보를 확인
    this.userprofile(this.user)
    // 로그인한 유저의 정보를 확인
    this.profile()
    },
  updated () {
    this.followerList
  }
}
</script>

<style scoped>
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
</style>