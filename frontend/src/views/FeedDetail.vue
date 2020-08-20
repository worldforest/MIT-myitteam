<template>
  <div class="mt-3">
    <div v-if="windowWidth >= 767" class="cont15">
      <div class="d-flex">
        <v-avatar size="72">
          <img
            :src="userprofiledata.src"
            alt="프로필사진"
          >
        </v-avatar>
        <h2 v-if="email !== detailFeed.email"  class="mt-4 ml-2" style="color:black" @click="goUserProfile(detailFeed.email)">{{ detailFeed.nickname }}</h2>
        <router-link to='/profile' v-else><h2 style="color:black" class="mt-4 ml-2">{{ detailFeed.nickname }}</h2></router-link>
      </div>
      <hr>
      <div class="d-flex justify-end mb-4">
        <h4>작성일자: {{ detailFeed.writedate.slice(0,10) }}</h4>
      </div>

      <article class="col-md-12">
          <div class="section-gray">
            <div class="container">
              <div class="row">
                  <div class="card card-profile">
                    <div class="card-image">
                      <a href="#"> <img class="img" :src="detailFeed.src">
                        <div class="card-caption">by {{ detailFeed.nickname }} </div>
                      </a>
                    </div>
                    <div class="table">
                      <h6 class="category text-danger">
                        <v-chip-group
                          active-class="deep-purple accent-4 white--text"
                          column   
                        >
                          <v-chip
                            color="#FF6666"
                            label
                            text-color="white"
                            v-for="(tag,z) in detailFeed.tag"
                            @click="searchTagFeed(tag)"
                            :key="z"
                          >
                          <v-icon left>mdi-label</v-icon>
                            {{tag}}
                          </v-chip>
                        </v-chip-group></h6>
                      <h5 class="card-description my-10"> {{detailFeed.description}} </h5>
                      <div class="ftr"> 
                        <div class="d-flex cont2" v-if=" detailFeed.email === email">
                          <div class="ml-auto">
                            <span class="mr-3">
                              <v-chip
                                color="#5C6BC0"
                                text-color="white"
                                @click="updateFeed(detailFeed)"
                              >
                                수정
                              </v-chip>
                            </span>
                            <span>
                              <v-chip
                                color="#CCCCFF"
                                text-color="black"
                                @click="deleteFeed(detailFeed)"
                              >
                                삭제
                              </v-chip>
                            </span>
                          </div>
                        </div>
                        <hr>
                        <div class="text-center font">
                          <v-dialog v-model="dialog" width="300">
                            <template v-slot:activator="{ on, attrs }">
                              <span v-bind="attrs" v-on="on" class="cursor">
                                <span class="mr-4 fonts">{{ likeCntnum }}명이 좋아합니다. </span>
                              </span>
                            </template>

                            <v-card class="cardModal">
                              <h3 class="modaltitle mb-3"> 좋아요 누른 사람</h3>
                              
                              <li v-for="(item, index) in likeUserList" :key="index">
                                <h2 class="followa ml-3" style="color:black" @click="goUserProfile(item.email)">{{ item.nickname }}</h2>
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
                        
                        <v-icon v-if="!likeUserList2.includes(email) && email" large class="mr-4 likebtn" @click="like(likeData);">mdi-heart-multiple-outline</v-icon>
                        <v-icon v-if="likeUserList2.includes(email) && email" large class="mr-4 likebtn" color="red" @click="unlike(likeData);">mdi-heart-multiple</v-icon>
                      </div>
                    </div>
                  </div>
              </div>
            </div>
          </div>
      </article>
    </div>
    <div v-else class="cont2">
      <div class="d-flex">
        <v-avatar>
          <img
            :src="userprofiledata.src"
            alt="프로필사진"
          >
        </v-avatar>
        <h5 style="color:black" class="mt-3 ml-2" v-if="email !== detailFeed.email" @click="goUserProfile(detailFeed.email)">{{ detailFeed.nickname }}</h5>
        <router-link to='/profile' v-else><h5 style="color:black" class="mt-3 ml-2">{{ detailFeed.nickname }}</h5></router-link>
      </div>      
      <hr>
      <div class="d-flex justify-end mb-4">
        <span>작성일자: {{ detailFeed.writedate.slice(0,10) }}</span>
      </div>

      <div>      
        <img :src="detailFeed.src" alt="" class="mx-auto" style="width:100%">
      </div>
      <div class="cont2 mt-3">
        {{ detailFeed.description}}
      </div>
      <div class="cont2">
        <v-chip-group
          active-class="deep-purple accent-4 white--text"
          column   
        >
          <v-chip
            class="ma-2"
            color="#FF6666"
            label
            text-color="white"
            v-for="(tag,z) in detailFeed.tag"
            @click="searchTagFeed(tag)"
            :key="z"
          >
            <v-icon left>mdi-label</v-icon>
            {{tag}}
          </v-chip>
        </v-chip-group>
      </div>

      <div class="d-flex cont2" v-if=" detailFeed.email === email">
        <div class="ml-auto">
          <span class="mr-3">
            <v-chip
              color="#5C6BC0"
              text-color="white"
              @click="updateFeed(detailFeed)"
            >
              수정
            </v-chip>
          </span>
          <span>
            <v-chip
              color="#CCCCFF"
              text-color="black"
              @click="deleteFeed(detailFeed)"
            >
              삭제
            </v-chip>
          </span>
        </div>
      </div>

      <hr>
      <v-row>
        <v-spacer></v-spacer>

        <div class="text-center">
          <v-dialog v-model="dialog" width="300">
            <template v-slot:activator="{ on, attrs }">
              <span v-bind="attrs" v-on="on" class="cursor">
                <h5 class="mr-4">{{ likeCntnum }}명이 좋아합니다. </h5>
              </span>
            </template>

            <v-card class="cardModal">
              <h3 class="modaltitle mb-3"> 좋아요 누른 사람</h3>
              <li v-for="(item, index) in likeUserList" :key="index">
                <h4 class="followa ml-3" style="color:black" @click="goUserProfile(item.email)">{{ item.nickname }}</h4>
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

        <v-icon v-if="!likeUserList2.includes(email)" large class="mr-4 likebtn" @click="like(likeData);">mdi-heart-multiple-outline</v-icon>
        <v-icon v-if="likeUserList2.includes(email)" large class="mr-4 likebtn" color="red" @click="unlike(likeData);">mdi-heart-multiple</v-icon>
      </v-row>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from 'vuex'

export default {
  computed: {
    ...mapState(['detailFeed', 'email', 'likeCntnum', 'likeUserList', 'likeUserList2', 'userprofiledata']),
  },
  data() {
    return {
      windowWidth: window.innerWidth,
      likeData: {
        no: this.$store.state.detailFeed.no,
        email: this.$store.state.email, 
      },
      likeCntData: {
        no: this.$store.state.detailFeed.no,
      },
      dialog:false,
    }
  },
  beforeDestroy() { 
    window.removeEventListener('resize', this.onResize); 
  },
  methods: {
    onResize() {
      this.windowWidth = window.innerWidth
    },
    ...mapMutations(['getuseremail', 'updateFeed', 'goUserProfile']),
    ...mapActions(['like', 'likeCnt', 'likeUser', 'unlike','userprofile', 'searchTagFeed', 'deleteFeed']),
  },
  mounted() {
    setTimeout(() => {
      
      }, 200)  
    this.$nextTick(() => {
      window.addEventListener('resize', this.onResize);
    })
    this.userprofile(this.detailFeed.email)
    window.scrollTo(0, 0)
  },
  created(){
    this.likeCnt(this.likeCntData);
    this.likeUser(this.likeCntData);
  },
}
</script>

<style scoped>

  *{ font-family: 'Jua', sans-serif;}
  
  hr {
    height: 3px;
    background: #bbb;
    background-image: -webkit-linear-gradient(left, #eee, #777, #eee);
    background-image: -moz-linear-gradient(left, #eee, #777, #eee);
    background-image: -ms-linear-gradient(left, #eee, #777, #eee);
    background-image: -o-linear-gradient(left, #eee, #777, #eee);
  }

  .cont15 {
    margin: 0 15%;
  }
  .likebtn{
    cursor: pointer;
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

  .cont2 {
    margin: 0 2%;
  }

  
  .section-cards {
      z-index: 3;
      position: relative;
  }

  .card {
      display: inline-block;
      position: relative;
      width: 100%;
      margin-bottom: 30px;
      border-radius: 6px;
      color: rgba(0, 0, 0, 0.87);
      background: #fff;
      box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 3px 1px -2px rgba(0, 0, 0, 0.2), 0 1px 5px 0 rgba(0, 0, 0, 0.12);
  }

  .card .card-image {
      height: 60%;
      position: relative;
      overflow: hidden;
      margin-left: 15px;
      margin-right: 15px;
      margin-top: -30px;
      border-radius: 6px;
      box-shadow: 0 16px 38px -12px rgba(0, 0, 0, 0.56), 0 4px 25px 0px rgba(0, 0, 0, 0.12), 0 8px 10px -5px rgba(0, 0, 0, 0.2);
  }

  .card .card-image img {
      width: 100%;
      height: 100%;
      border-radius: 6px;
      pointer-events: none;
  }

  .card .card-image .card-caption {
      position: absolute;
      bottom: 15px;
      left: 15px;
      color: #fff;
      font-size: 1.3em;
      text-shadow: 0 2px 5px rgba(33, 33, 33, 0.5);
  }

  .card img {
      width: 100%;
      height: auto;
  }

  .img-raised {
      box-shadow: 0 16px 38px -12px rgba(0, 0, 0, 0.56), 0 4px 25px 0px rgba(0, 0, 0, 0.12), 0 8px 10px -5px rgba(0, 0, 0, 0.2);
  }

  .card .ftr {
      margin-top: 15px;
  }

  .card .ftr div {
      display: inline-block;
  }

  .card .ftr .author {
      color: #888;
  }

  .card .ftr .stats {
      float: right;
      line-height: 30px;
  }

  .card .ftr .stats {
      position: relative;
      top: 1px;
      font-size: 14px;
  }

  .table {
      margin-bottom: 0px;
  }

  .card .table {
      padding: 15px 30px;
  }

  .card .table-primary {
      background: linear-gradient(60deg, #ab47bc, #7b1fa2);
  }

  .card .table-info {
      background: linear-gradient(60deg, #26c6da, #0097a7);
  }

  .card .table-success {
      background: linear-gradient(60deg, #66bb6a, #388e3c);
  }

  .card .table-warning {
      background: linear-gradient(60deg, #ffa726, #f57c00);
  }

  .card .table-danger {
      background: linear-gradient(60deg, #ef5350, #d32f2f);
  }

  .card .table-rose {
      background: linear-gradient(60deg, #ec407a, #c2185b);
  }

  .card [class*="table-"] {
      color: #FFFFFF;
      border-radius: 6px;
  }

  .card [class*="table-"] .card-caption a,
  .card [class*="table-"] .card-caption,
  .card [class*="table-"] .icon i {
      color: #FFFFFF;
  }

  .card [class*="table-"] .icon i {
      border-color: rgba(255, 255, 255, 0.25);
  }

  .card [class*="table-"] .author a,
  .card [class*="table-"] .ftr .stats,
  .card [class*="table-"] .category,
  .card [class*="table-"] .card-description {
      color: rgba(255, 255, 255, 0.8);
  }

  .card [class*="table-"] .author a:hover,
  .card [class*="table-"] .author a:focus,
  .card [class*="table-"] .author a:active {
      color: #FFFFFF;
  }

  .card [class*="table-"] h1 small,
  .card [class*="table-"] h2 small,
  .card [class*="table-"] h3 small {
      color: rgba(255, 255, 255, 0.8);
  }

  .card-background {
      background-position: center;
      background-size: cover;
      text-align: center;
  }

  .card-background .table {
      position: relative;
      z-index: 2;
      min-height: 280px;
      padding-top: 40px;
      padding-bottom: 40px;
      max-width: 440px;
      margin: 0 auto;
  }

  .card-background .category,
  .card-background .card-description,
  .card-background small {
      color: rgba(255, 255, 255, 0.8);
  }

  .card-background .card-caption {
      color: #FFFFFF;
      margin-top: 10px;
  }

  .card-background:after {
      position: absolute;
      z-index: 1;
      width: 100%;
      height: 100%;
      display: block;
      left: 0;
      top: 0;
      table: "";
      background-color: rgba(0, 0, 0, 0.56);
      border-radius: 6px;
  }

  .card-profile {
      margin-top: 30px;
      text-align: center;
  }

  .text-warning {
      color: #ff9800;
  }

  .text-primary {
      color: #9c27b0;
  }

  .text-danger {
      color: #f44336;
  }

  .text-success {
      color: #4caf50;
  }

  .text-info {
      color: #00bcd4;
  }

  .text-rose {
      color: #e91e63;
  }

  .text-gray {
      color: #888;
  }

  .col-md-12 {
      padding-right: 0px;
      padding-left: 0px;
  }

  .fa {
      display: inline-block;
      font: normal normal normal 14px/1 FontAwesome;
      font-size: inherit;
      text-rendering: auto;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
  }

  a {
      color: #9c27b0;
      text-decoration: none;
  }

  a:hover {
      color: #9c27b0;
      text-decoration: underline;
  }

  p {
      color: #3C4857;
  }

  header {
      border-bottom: 1px solid #dedede;
      text-align: center;
  }

  .section-gray {
    background: #FAFAFA;
    padding: 60px 0 30px 0;
  }

  .fonts {
    font-size: 1.5rem
  }

</style>
