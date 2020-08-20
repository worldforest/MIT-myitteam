<template>
  <div>
    <!-- /////////////////////////////    화면이 가장 클 때    //////////////////////////////////////// -->
    <div v-if="windowWidth >= 1266">
      <div class="mt-6">
        <h1 @click="goContest()" class="Pjt_h1 mb-4">공모전</h1>
        <carousel :per-page="4" :mouse-drag="true" style="width:100%">
          <slide v-for="(club, index) in clubs" :key="index">
            <b-card :img-src="club.imagesrc" img-alt="Image" img-top tag="article" @click="gongmoDetail(club)" class="cursor" style="width: 85%;">
              <b-card-text class="py-2" @click="gongmoDetail(club)">
                <div v-if="club.title.length >= 15" >
                    <h5 class="ml-3">{{ club.title.slice(0,13)}}...</h5>
                </div>
                <div v-else>
                    <h5 class="ml-3">{{ club.title }}</h5>
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
        <span class="ml-auto cursor" @click="Tflag()"><h5>공모전  | </h5></span>
        <span class="mx-3 cursor" @click="Fflag()"><h5>프로젝트</h5></span>
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
              <v-list-item-title class=" mb-1 ml-3 font">{{ club.title }}</v-list-item-title>
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
          class="mx-auto my-2 homeCard"
          style="height:130px;"
          max-width="344"
          outlined
          v-for="(club2, index) in clubs2" :key="index"
          @click="projectDetail(club); getTeamData(club.no)"
        >
          <v-list-item three-line>
            <v-list-item-content>
              <v-list-item-title class="text-center mt-8 font">{{ club2.title }}</v-list-item-title>              
              <hr class="cont15 my-2 mx-5 hrr">
              <v-list-item-subtitle class="text-center my-3">{{ club2.start }} ~ {{ club2.end }}</v-list-item-subtitle>
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
      <v-row class="mt-0" v-if="flag">
        <v-col cols="6" v-for="(club, index) in clubs" :key="index">
          <v-card      
            class="mx-auto my-1 homeCard"
            max-width="344"
            outlined
           @click="gongmoDetail(club)"
          >
            <v-list-item three-line>
              <v-list-item-content>
                <v-list-item-title class="mb-1font">{{ club.title }}</v-list-item-title>
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
        <v-col cols="6" v-for="(club, index) in clubs2" :key="index">
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
          </v-card>
        </v-col>
      </v-row>
    </div>

    <!-- /////////////////////////////    화면이 중간(큰)일 때     //////////////////////////////////////// -->

    <div v-else>
      <div class="mt-6">
        <h1 @click="goContest()" class="Pjt_h1 mb-4">공모전</h1>
        <carousel :per-page="3" :mouse-drag="false" >
          <slide v-for="(club,index) in clubs" :key="index">
            <b-card :img-src="club.imagesrc" img-alt="Image" img-top tag="article" @click="gongmoDetail(club)" class="cursor" style="width: 75%;">
              <b-card-text class="py-2" @click="gongmoDetail(club)">
                <div v-if="club.title.length >= 15" >
                    <h5 class="ml-3">{{ club.title.slice(0,15)}}...</h5>
                </div>
                <div v-else>
                    <h4 class="ml-3">{{ club.title }}</h4>
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
            <b-card tag="article" class="cursor homeCard" @click="projectDetail(club2); getTeamData(club2.no)">
              <b-card-text @click="projectDetail(club2)">
                <div v-if="club2.title.length >= 15" >
                    <h3 class="mt-8 title">{{ club2.title.slice(0,8)}}...</h3>
                </div>
                <div v-else>
                  <h3 class="mt-8 title">{{ club2.title }}</h3>
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
                    <h3 class="title mt-8">{{ club2.title.slice(0,8)}}...</h3>
                </div>
                <div v-else>
                  <h3 class="title mt-8">{{ club2.title }}</h3>
                </div>
                <hr class="hrr">
                <p class="date"> {{ club2.start }} ~ {{ club2.end }} </p>
              </b-card-text>
            </b-card>
          </slide>
        </carousel>
      </div>

      <!-- //////////////////////// 게시글 조회 ////////////////////// -->
    <v-row class="d-flex justify-center"> 
      <v-col sm="4" >
        <v-text-field
          v-model="searchData.search"
          append-icon="mdi-magnify"
          label="태그로 게시글을 검색해보세요."
          hide-details
          @keypress.enter="searchTagFeed(searchData.search)"           
        ></v-text-field>
      </v-col>
    </v-row>
      <div class="text-box mt-15">
        <v-row style="font-size:1.3rem" class="cont10">
          <div class="center">
            <button @click="searchFeed()"><h5 class="fontcolor">전체 게시글</h5></button>
          </div>
          <div class="center" v-if="email">
            <button @click="searchFollowFeed(email)"><h5 class="fontcolor">팔로우 게시물</h5></button>
          </div>
        </v-row>
        <hr class="my-4 cont10">
      </div>
      <div>
        <div v-if="windowWidth > 760">
          <main>
            <h1>Other people's day</h1>
            <div class="flex-container">
              <figure v-for="(i, index) in community" :key="index">
                <img :src="i.src" alt="피드 이미지" @click='feedDetail(i)'/>
                <figcaption>
                  <div class="fig-author">by {{ i.nickname }}</div>
                </figcaption>
              </figure>
            </div>            
          </main>
        </div>
        <v-row v-else class="bg-gray cont10">
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

var searchBox = document.querySelectorAll('.search-box input[type="text"] + span');

searchBox.forEach(elm => {
  elm.addEventListener('click', () => {
    elm.previousElementSibling.value = '';
  });
});

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
    window.scrollTo(0, 0)
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
    },
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
  .fontcolor{
    color: rgb(92, 107, 192);
  }
  .Pjt_h1{
    cursor: pointer;
  }
  .font{
    font-size: 1.3rem;
  }
  /* serim */
  span.center{
    text-align: center;
  }
  h1{
    font-size: 30px;
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
    font-family: 'Montserrat', sans-serif;
    color: #262626;
    font-size: 16px;
  }

  main {
    max-width: 1000px;
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