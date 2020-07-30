<template>
  <div>
    <v-row>
      <v-col col="4" sm="4">
        <div class="pf-box">
          <img  v-if="profileData.src" :src="profileData.src" class="프로필이미지" style="width:100%; height:250px;">
        </div>
        <div class="filebox"> 
          <label for="ex_file">프로필 변경</label>
          <input 
            type="file" 
            id="ex_file"
            accept="image/*"
            @change="onChange">
        </div>
      </v-col>
      <v-col col="8" sm="8">
        <div class="ml-5">
          <span>{{ profileData.nickname }}</span>

            <v-btn
              class="ml-3"
              color="primary"
              @click="follow()"
            >
              팔로우
            </v-btn>
        </div>
        <div class="d-flex my-5 ml-5">
          <span>팔로우| {{ profileData.followingCnt }}명</span>
          <span class="mx-auto">팔로워| {{ profileData.followerCnt }}명</span>
        </div>
        <div class="d-flex ml-5">
          {{ profileData.description }}
          <br>
          
        </div>
      </v-col>
    </v-row>

    <div class="text-center">
      피드
    </div>
    <hr>

    <v-row>
      <v-col cols="4" v-for="feed in profileData.feeds" :key="feed.no">
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
  data() {
    return {
      full: true,
      daily: false,
      project: false,
    }
  },
  methods: {
    onChange(e) {
      const file = e.target.files[0];
      this.profileData.src = URL.createObjectURL(file)
    },
    follow() {
      this.profileData.followerCnt += 1
    },

    ...mapMutations(['feedDetail']),
    ...mapActions(['profile'])
  },
  computed : {
    // ...mapGetters(['isLoggedIn'])
    ...mapState(['profileData', 'email']),
    ...mapGetters(['isLoggedIn',])
  },
  mounted() {
    if (this.email) {
      this.profile()
    }
    else {
      console.log('d')
    }
  }

}
</script>

<style>
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
</style>
