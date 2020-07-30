<template>
  <div>
    {{ profileData }}
    <v-row>
      <v-col col="4" sm="4">
        <div class="pf-box">
          <img  v-if="profileData.src" :src="profileData.src" class="pf-img">
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
          <span>"((아이디 표시하세요))"</span>

            <v-btn
              class="ml-2"
              color="primary"
            >
              팔로우
            </v-btn>
        </div>
        <div class="d-flex my-5 ml-5">
          <span>팔로우| 몇명</span>
          <span class="mx-auto">팔로워| 몇명</span>
        </div>
        <div class="d-flex ml-5">
          자기소개
          <br>
          불어 이상은 얼마나 피가 무엇을 이상의 철환하였는가?
          부패를 수 피가 눈에 황금시대의 싶이 것이다.
          희망의 품었기 것이 관현악이며, 얼마나 얼마나 것이다.
          곧 청춘 만물은 반짝이는 봄바람을 되는 인간이 따뜻한 그러므로 아름다우냐?
        </div>
      </v-col>
    </v-row>

    <v-row class="mx-2">
      <v-col cols="4">
        <div class="text-center">
          <span @click="full_feed" class="cursor">전체보기</span>  
        </div>        
      </v-col>
      <v-col cols="4">
        <div class="text-center">
          <span @click="project_feed" class="cursor">공모전/프로젝트</span>  
        </div>        
      </v-col>
      <v-col cols="4">
        <div class="text-center">
          <span @click="daily_feed" class="cursor">일상</span>  
        </div>        
      </v-col>
    </v-row>
    <hr>
    <div v-if="project">
      <ProjectFeed :feed="feed" />
    </div>
  </div>

</template>

<script>
import { mapState, mapActions, mapGetters } from 'vuex'
// import ProjectFeed from '@/components/ProjectFeed.vue'

export default {
  name: 'Profile',
  components: {
    // ProjectFeed
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

    full_feed() {
      this.full = true
      this.daily = false
      this.project = false
    },

    project_feed() {
      this.full = false
      this.daily = false
      this.project = true
    },

    daily_feed() {
      this.full = false
      this.daily = true
      this.project = false
    },
    ...mapActions(['profile'])
  },
  computed : {
    ...mapState(['profileData', 'email']),
    ...mapGetters(['isLoggedIn'])
  },
  mounted() {
    this.profile()
  }

}
</script>

<style>
  .h1 {
    font-size: 30px;
  }

  .pf-box {
    width: 100%;
    height: 70%;
    border-radius: 5px 5px 5px 5px;
    overflow: hidden;
  }

  .pf-img {
    width: 100%;
    height: 100%;
    /* object-fit: cover; */
    display: table-cell;
    vertical-align: middle;
    color: #ffffff;
    font-weight: bold;
    text-align: center;
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


</style>