<template>
  <div>
    <v-row class="d-flex justify-center"> 
      <v-col sm="4" >
        <v-text-field
          v-model="searchData.search"
          append-icon="mdi-magnify"
          label="Search"
          single-line="true"
          hide-details
          @keypress.enter="searchTagFeed(searchData.search)"           
        ></v-text-field>
      </v-col>
    </v-row>

    <!-- /////////////////////////////    화면이 가장 클 때    //////////////////////////////////////// -->

    <div v-if="windowWidth >= 1064">
      <div class="mt-6">
        <h1>공모전</h1>
        <carousel :per-page="4" :navigate-to="someLocalProperty" :mouse-drag="false" style="width:100%">
          <slide v-for="club in clubs" :key="club.no">
            <b-card :img-src="club.imagesrc" img-alt="Image" img-top tag="article" @click="gongmoDetail(club)" class="cursor" style="width: 85%;">
              <b-card-text @click="gongmoDetail(club)">
                <div v-if="club.title.length >= 10" >
                    {{ club.title.slice(0,10)}}...
                </div>
                <div v-else>
                    {{ club.title }}
                </div>
              </b-card-text>
            </b-card>
          </slide>
        </carousel>
      </div>
    </div>

    <!-- /////////////////////////////    화면이 작을 때     //////////////////////////////////////// -->

    <div v-else-if="400 > windowWidth">
      <div class="mt-6">
        <h1>공모전</h1>
        <carousel :per-page="1.5" :navigate-to="someLocalProperty" :mouse-drag="false" >
          <slide v-for="club in clubs" :key="club.no">
            <b-card :img-src="club.imagesrc" img-alt="Image" img-top tag="article" @click="gongmoDetail(club)" class="cursor" style="width: 85%;">
              <b-card-text @click="gongmoDetail(club)">
                <div v-if="club.title.length >= 15" >
                    {{ club.title.slice(0,15)}}...
                </div>
                <div v-else>
                    {{ club.title }}
                </div>
              </b-card-text>
            </b-card>
          </slide>
        </carousel>
      </div>
    </div>

    <!-- /////////////////////////////    화면이 중간일 때     //////////////////////////////////////// -->

    <div v-else>
      <div class="mt-6">
        <h1>공모전</h1>
        <carousel :per-page="3" :navigate-to="someLocalProperty" :mouse-drag="false" >
          <slide v-for="club in clubs" :key="club.no">
            <b-card :img-src="club.imagesrc" img-alt="Image" img-top tag="article" @click="gongmoDetail(club)" class="cursor" style="width: 75%;">
              <b-card-text @click="gongmoDetail(club)">
                <div v-if="club.title.length >= 15" >
                    {{ club.title.slice(0,15)}}...
                </div>
                <div v-else>
                    {{ club.title }}
                </div>
              </b-card-text>
            </b-card>
          </slide>
        </carousel>
      </div>
    </div>

    <!-- /////////////////////////////    프로젝트 시작     //////////////////////////////////////// -->
    <!-- /////////////////////////////    화면이 클 때     //////////////////////////////////////// -->

    <div>
      <h1>프로젝트</h1>
      <div v-if="windowWidth >= 1064">      
        <carousel :per-page="4" :navigate-to="someLocalProperty" :mouse-drag="false" >
          <slide v-for="club2 in clubs2" :key="club2.no">
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

      <!-- /////////////////////////////    화면이 작을 때     //////////////////////////////////////// -->

      <div v-else-if="400 > windowWidth">
        <carousel :per-page="1.5" :navigate-to="someLocalProperty" :mouse-drag="false" >
          <slide v-for="club2 in clubs2" :key="club2.no">
            <b-card tag="article" class="cursor homeCard" @click="projectDetail(club2); getTeamData(club2.no)">
              <b-card-text @click="projectDetail(club2)">
                <div v-if="club2.title.length >= 15" >
                    <h4 class="title mt-3">{{ club2.title.slice(0,8)}}...</h4>
                </div>
                <div v-else>
                  <h4 class="title mt-3">{{ club2.title }}</h4>
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

      <div v-else>
        <carousel :per-page="3" :navigate-to="someLocalProperty" :mouse-drag="false" >
          <slide v-for="club2 in clubs2" :key="club2.no">
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
        <hr class="mt-2">
      </div>

      <!-- //////////////////////// 게시글 조회 ////////////////////// -->

      <div class="text-box">
        <div class="center">
          <button @click="searchFeed()">전체 게시글</button>
        </div>
        <div class="right" v-if="email">
          <button @click="searchFollowFeed(email)">팔로우 게시물</button>
        </div>
      </div>
      <div>
        <v-row class="cont10">
          <v-col cols="4" v-for="i in community" :key="i.no">
            <div class="mx-2 detail_hover">
              {{ i.nickname }}
              <img :src="i.src"
                alt="안뜨는거야?"
                style="width:100%; height: 350px"
                :i="i"
                @click='feedDetail(i)'
                v-if="windowWidth >= 767"
              >
              <img :src="i.src"
                alt="안뜨는거라고!"
                style="width:100%; height: 150px;"
                :i="i"
                @click='feedDetail(i)'
                v-else
              >
            </div>       
          </v-col>
        </v-row>
      </div>
    </div>
    {{ currentPageClubs }}
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
  },

  computed: {
    ...mapState(['clubs', 'clubs2', 'community', 'email', 'followList', 'profileData']),
  }

}
</script>

<style scoped>

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
    height: 200px;
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

</style>