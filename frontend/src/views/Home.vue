<template>
  <div>
    <!-- /////////////////////////////    화면이 가장 클 때    //////////////////////////////////////// -->
    <div v-if="windowWidth >= 1266">
      <div class="mt-6">
        <h1 @click="goContest" class="Pjt_h1 mb-4">공모전</h1>
        <carousel :per-page="4" :mouse-drag="true" style="width:100%">
          <slide v-for="(club, index) in clubs" :key="index">
            <b-card :img-src="club.imagesrc" img-alt="Image" img-top tag="article" @click="gongmoDetail(club)" class="cursor" style="width: 85%;">
              <b-card-text @click="gongmoDetail(club)">
                <div v-if="club.title.length >= 15" >
                    <h5>{{ club.title.slice(0,13)}}...</h5>
                </div>
                <div v-else>
                    <h5>{{ club.title }}</h5>
                </div>
              </b-card-text>
            </b-card>
          </slide>
        </carousel>
      </div>
    </div>

    <!-- /////////////////////////////    화면이 작을 때(공모전 & 프로젝트)     //////////////////////////////////////// -->

    <div v-else-if="500 > windowWidth">
      <div class="d-flex">
        <span class="ml-auto cursor" @click="Tflag()">공모전</span>
        <span class="mx-3 cursor" @click="Fflag()">프로젝트</span>
      </div>
      <div v-if="flag">
        <v-card      
          class="mx-auto my-1 homeCard"
          max-width="344"
          outlined
          v-for="(club, index) in clubs" :key="index"
          @click="gongmoDetail(club)"
        >
          <v-list-item three-line>
            <v-list-item-content>
              <v-list-item-title class=" mb-1 font">{{ club.title }}</v-list-item-title>
              <v-list-item-subtitle>{{ club.start }} ~ {{ club.end }}</v-list-item-subtitle>
              <v-list-item-subtitle v-if="club.host.length <= 15">{{ club.host }}</v-list-item-subtitle>
              <v-list-item-subtitle v-else>{{ club.host.slice(0,15) }}...</v-list-item-subtitle>
            </v-list-item-content>

            <v-list-item-avatar
              tile
              size="90"
              color="grey"
              
            >
              <img
                :src="club.imagesrc"
                alt="프로필사진"
              >
            </v-list-item-avatar>
          </v-list-item>
        </v-card>
      </div>
      <div v-if="!flag">
        <v-card      
          class="mx-auto my-1 homeCard"
          max-width="344"
          outlined
          v-for="(club2, index) in clubs2" :key="index"
          @click="projectDetail(club); getTeamData(club.no)"
        >
          <v-list-item three-line>
            <v-list-item-content>
              <v-list-item-title class="text-center mb-1 font">{{ club2.title }}</v-list-item-title>              
              <hr class="cont15 my-3 mx-5">
              <v-list-item-subtitle class="text-center">{{ club2.start }} ~ {{ club2.end }}</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </v-card>
      </div>
    </div>

    <!-- /////////////////////////////    화면이 중간(작은)일 때     //////////////////////////////////////// -->

    <div v-else-if="windowWidth < 960 && windowWidth >=500">
      <div class="d-flex">
        <h3 class="ml-auto cursor" @click="Tflag()">공모전</h3>
        <h3 class="mx-3 cursor" @click="Fflag()">프로젝트</h3>
      </div>
      <h3 v-if="flag">공모전</h3>
      <h3 v-if="!flag">프로젝트</h3>
      <v-row class="mt-0" v-if="flag">
        <v-col cols="6" v-for="(club,index) in clubs" :key="index">
          <v-card      
            class="mx-auto my-1 homeCard"
            max-width="344"
            outlined
           @click="gongmoDetail(club)"
          >
            <v-list-item three-line>
              <v-list-item-content>
                <v-list-item-title class=" mb-1 font">{{ club.title }}</v-list-item-title>
                <v-list-item-subtitle>{{ club.start }} ~ {{ club.end }}</v-list-item-subtitle>
                <v-list-item-subtitle v-if="club.host.length <= 15">{{ club.host }}</v-list-item-subtitle>
                <v-list-item-subtitle v-else>{{ club.host.slice(0,15) }}...</v-list-item-subtitle>
              </v-list-item-content>

              <v-list-item-avatar
                tile
                size="90"
                color="grey"
              >
                <img
                  :src="club.imagesrc"
                  alt="프로필사진"
                >
              </v-list-item-avatar>
            </v-list-item>
          </v-card>
        </v-col>
      </v-row>
      <v-row class="mt-0" v-if="!flag">
        <v-col cols="6" v-for="(club,index) in clubs2" :key="index">
          <v-card      
            class="mx-auto my-1 homeCard"
            max-width="344"
            outlined
            @click="projectDetail(club); getTeamData(club.no)"     
          >
            <v-list-item three-line>
              <v-list-item-content>
                <v-list-item-title class=" mb-1 font">{{ club.title }}</v-list-item-title>
                <v-list-item-subtitle>{{ club.start }} ~ {{ club.end }}</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>

            <v-card-actions>
              <v-btn color="orange" text class="mx-auto" @click="projectDetail(club); getTeamData(club.no)">
                자세히보기
              </v-btn>  
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </div>

    <!-- /////////////////////////////    화면이 중간(큰)일 때     //////////////////////////////////////// -->

    <div v-else>
      <div class="mt-6">
        <h1 class="Pjt_h1 mb-4">공모전</h1>
        <carousel :per-page="3" :mouse-drag="false" >
          <slide v-for="(club,index) in clubs" :key="index">
            <b-card :img-src="club.imagesrc" img-alt="Image" img-top tag="article" @click="gongmoDetail(club)" class="cursor" style="width: 75%;">
              <b-card-text @click="gongmoDetail(club)">
                <div v-if="club.title.length >= 15" >
                    <h4>{{ club.title.slice(0,15)}}...</h4>
                </div>
                <div v-else>
                    <h4>{{ club.title }}</h4>
                </div>
              </b-card-text>
            </b-card>
          </slide>
        </carousel>
      </div>
    </div>

    <!-- /////////////////////////////    프로젝트 시작     //////////////////////////////////////// -->
    <!-- /////////////////////////////    화면이 클 때     //////////////////////////////////////// -->

    <div class="mt-9">
      <h1 @click="goPJT" class="Pjt_h1 mb-4" v-if="windowWidth > 960">프로젝트</h1>
      <div v-if="windowWidth >= 1266">      
        <carousel :per-page="4" :mouse-drag="false" >
          <slide v-for="(club2,index) in clubs2" :key="index">
            <!-- {{ club2 }} -->
            <b-card tag="article" class="cursor homeCard" @click="projectDetail(club2); getTeamData(club2.no)">
              <b-card-text @click="projectDetail(club2)">
                <div v-if="club2.title.length >= 15" >
                    <h3 class="mt-3 title">{{ club2.title.slice(0,8)}}...</h3>
                </div>
                <div v-else>
                  <h3 class="mt-3 title">{{ club2.title }}</h3>
                </div>
                <hr class="hrr">
                <p class="date"> {{ club2.start }} ~ {{ club2.end }} </p>
              </b-card-text>
            </b-card>
          </slide>
        </carousel>
        <hr class="mt-2">
      </div>

      <!-- /////////////////////////////    화면이 중간일 때     //////////////////////////////////////// -->

      <div v-else-if="960 < windowWidth && 1266 > windowWidth">
        <carousel :per-page="3" :mouse-drag="false" >
          <slide v-for="(club2, index) in clubs2" :key="index">
            <b-card tag="article" class="cursor homeCard" @click="projectDetail(club2); getTeamData(club2.no)">
              <b-card-text @click="projectDetail(club2)">
                <div v-if="club2.title.length >= 15" >
                    <h3 class="title mt-3">{{ club2.title.slice(0,8)}}...</h3>
                </div>
                <div v-else>
                  <h3 class="title mt-3">{{ club2.title }}</h3>
                </div>
                <hr class="hrr">
                <p class="date"> {{ club2.start }} ~ {{ club2.end }} </p>
              </b-card-text>
            </b-card>
          </slide>
        </carousel>
      </div>

      <!-- //////////////////////// 게시글 조회 ////////////////////// -->
      <h1 class="text-center mt-9"> 게시글 </h1>
    <v-row class="d-flex justify-center"> 
      <v-col sm="4" >
        <v-text-field
          v-model="searchData.search"
          append-icon="mdi-magnify"
          label="Search"
          hide-details
          @keypress.enter="searchTagFeed(searchData.search)"           
        ></v-text-field>
      </v-col>
    </v-row>
      <div class="text-box my-6">
        <div class="center">
          <button @click="searchFeed()">전체 게시글</button>
        </div>
        <div class="right" v-if="email">
          <button @click="searchFollowFeed(email)">팔로우 게시물</button>
        </div>
      </div>
      <div>
        <v-row class="cont10" v-if="windowWidth > 760">
          <v-col cols="4" v-for="(i, index) in community" :key="index">
            <div class="mx-2 detail_hover">
              {{ i.nickname }}
              <img :src="i.src"
                alt="안뜨는거야?"
                style="width:100%; height: 20vw;"
                :i="i"
                @click='feedDetail(i)'
              >
            </div>       
          </v-col>
        </v-row>
        <v-row v-else class="bg-gray">
          <v-col cols='12'  v-for="(i, index) in community" :key="index">
            <v-card
              class="mx-auto my-3"
            >
              <v-img
                height="300"
                :src="i.src"
                @click="feedDetail(i)"
              ></v-img>

              <v-card-text>
               <v-chip-group
                  active-class="deep-purple accent-4 white--text"
                  column
                >
                  <v-chip v-for="(tag,z) in i.tag" :key="z" @click="searchTagFeed(tag)">
                    #{{tag}}
                  </v-chip>
                </v-chip-group>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState, mapMutations } from 'vuex'
import { Carousel, Slide } from 'vue-carousel'

export default {
  name: 'Home',
  components: {
    Carousel,
    Slide
  },
  data() {
    return {
      windowWidth: window.innerWidth,
      txt: '',
      searchData: {
        search:'',
      },
      flag: true,
    }
  },

  // 페이지 크기가 바뀌는 것을 계속해서 주시한다.
  watch: {
    windowWidth(newWidth, oldWidth) {
     this.txt = `it changed to ${newWidth} from ${oldWidth}`;
    }
  },
  mounted() {
    this.$nextTick(() => {
      window.addEventListener('resize', this.onResize);
    })
    this.getContestData()
    this.searchFeed()
    this.myFollowList(this.email)
  },

  beforeDestroy() { 
    window.removeEventListener('resize', this.onResize); 
  },

  methods: {
    ...mapMutations(['feedDetail', 'gongmoDetail', 'projectDetail']),
    ...mapActions(['getContestData', 'searchFeed', 'myFollowList', 'searchFollowFeed', 'profile', 'getTeamData', 'searchTagFeed']),
    onResize() {
      this.windowWidth = window.innerWidth
    },
    goPJT(){
      this.$router.push('/projectlist')
    },
    goContest(){
      this.$router.push('/allcontest')
    },
    Tflag() {
      this.flag = true
    },
    Fflag() {
      this.flag = false
    }
  },

  computed: {
    ...mapState(['clubs', 'clubs2', 'community', 'email', 'followList', 'profileData']),
  },
}
</script>

<style scoped>
 *{ font-family: 'Jua', sans-serif;}
  .active{
    width:20px;
    height:20px;
    border-radius:20px;
  }

  .card-img-top {
    width: 250px;
    height: 350px;
    object-fit: cover;
  }

  .card-sm .card-img-top {
    width: 150px;
    height: 200px;
    object-fit: cover;
  }

  .bg {
    background-color: #e9e9e9;
  }

  .non-dec {
    text-decoration: none;
    color: black !important;
    font-weight: bold;
  }

  .detail_hover:hover {
    opacity: 0.5;
  }

  .text-box {
    width: 100%;
    text-align: center;
    margin-top: 1rem;
  }

  .center {
    display: inline-block;
    margin: 0 auto;
  }

  .right {
    float: right;
  }

  .cursor {
    cursor: pointer;
    font-weight: bold;
  }

  .row{
    margin-top:100px;
  }

  .cont10 {
    margin: 0 3%;
  }

  .homeCard{
    text-align: center;
    margin: auto ;
    border-top: thick solid rgb(92, 107, 192);
  }

  .date{
    color: black;
  }

  .hrr{
    width: 200px;
    margin: 1.5rem auto;
  }

  .title{
    color: rgb(92, 107, 192);
    font-weight: bold;
  }

  .Pjt_h1{
    cursor: pointer;
  }
  .font{
    font-family: myFont, sans-serif;
    font-size: 1.3rem;
    /* font-size: 30px; */
  }

</style>