<template>
  <div class="mt-3">
    {{detailFeed}}
    <div v-if="windowWidth >= 767" class="cont15">
      {{ windowWidth }}
      <router-link :to="{name: 'UserProfile', params:{user:detailFeed.email}}" v-if="email !== detailFeed.email"><h3>작성자: {{ detailFeed.nickname }}</h3></router-link>
      <h3><router-link to='/profile' v-if="email === detailFeed.email"> {{ detailFeed.nickname }}</router-link></h3>
      <hr>
      <div class="d-flex justify-end mb-4">
        <h4>작성일자: {{ detailFeed.writedate.slice(0,10) }}</h4>
        <h4 class="ml-4">조회수:  {{ detailFeed.views }}</h4>
      </div>

      <div>      
        <img :src="detailFeed.src" alt="" class="mx-auto" style="width:100%">
      </div>
      <div>
        {{ detailFeed.description}}
      </div>
      <div>
        {{ detailFeed.tag }}
      </div>
      <hr>
      <!-- 좋아요를 시작해보지 -->
      {{ likeUserList }}
      <v-row>
        <v-spacer></v-spacer>

        <div class="text-center">
          <v-dialog v-model="dialog" width="500">
            <template v-slot:activator="{ on, attrs }">
              <span v-bind="attrs" v-on="on" class="cursor">
                <h5 class="mr-4">{{ likeCntnum }}명이 좋아합니다. </h5>
              </span>
            </template>

            <!-- {{ i.datalist }} -->
            <v-card class="cardModal">
              <!-- {{ followList }} -->
              <h3 class="modaltitle mb-3"> 좋아요 누른 사람</h3>
              
              <li v-for="item in likeUserList" :key="item">
                <router-link :to="{name: 'UserProfile', params:{user:item.email}}" class="followa"><h4 class="ml-3">{{ item.nickname }}</h4></router-link>
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
        <!-- <h5 v-if="likeUserList.includes(email)" class="mr-4">본인을 포함한 외 {{ likeCntnum }}명이 좋아합니다. </h5> -->
        <v-icon v-if="likeUserList2.includes(email)" large class="mr-4 likebtn" color="red" @click="unlike(likeData);">mdi-heart-multiple</v-icon>
      </v-row>
    </div>
    <div v-else>
      <div class="d-flex">
        <v-avatar>
          <img
            :src="userprofiledata.src"
            alt="프로필사진"
          >
        </v-avatar>
        <router-link :to="{name: 'UserProfile', params:{user:detailFeed.email}}" v-if="email !== detailFeed.email" class="mt-3 ml-2"><h5 style="color:black">{{ detailFeed.nickname }}</h5></router-link>
        <router-link to='/profile' v-else><h5 style="color:black" class="mt-3 ml-2">{{ detailFeed.nickname }}</h5></router-link>
      </div>      
      <hr>
      <div class="d-flex justify-end mb-4">
        <span>작성일자: {{ detailFeed.writedate.slice(0,10) }}</span>
        <span class="ml-4">조회수:  {{ detailFeed.views }}</span>
      </div>

      <div>      
        <img :src="detailFeed.src" alt="" class="mx-auto" style="width:100%">
      </div>
      <div class="cont2 mt-3">
        {{ detailFeed.description}}
      </div>
      <div class="cont2">
        <v-chip-group
          v-model="selection"
          active-class="deep-purple accent-4 white--text"
          column
          
        >
          <v-chip
            class="ma-2"
            color="pink"
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
              color="red"
              text-color="white"
              @click="updateFeed(detailFeed)"
            >
              수정
            </v-chip>
          </span>
          <span>
            <v-chip
              color="red"
              text-color="white"
              @click="deleteFeed(detailFeed)"
            >
              삭제
            </v-chip>
          </span>
        </div>
      </div>

      <hr>
      <!-- 좋아요를 시작해보지 -->
      {{ likeUserList }}
      <v-row>
        <v-spacer></v-spacer>

        <div class="text-center">
          <v-dialog v-model="dialog" width="500">
            <template v-slot:activator="{ on, attrs }">
              <span v-bind="attrs" v-on="on" class="cursor">
                <h5 class="mr-4">{{ likeCntnum }}명이 좋아합니다. </h5>
              </span>
            </template>

            <!-- {{ i.datalist }} -->
            <v-card class="cardModal">
              <!-- {{ followList }} -->
              <h3 class="modaltitle mb-3"> 좋아요 누른 사람</h3>
              
              <li v-for="item in likeUserList" :key="item">
                <router-link :to="{name: 'UserProfile', params:{user:item.email}}" class="followa"><h4 class="ml-3">{{ item.nickname }}</h4></router-link>
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
      }
    }
  },
  beforeDestroy() { 
    window.removeEventListener('resize', this.onResize); 
  },
  methods: {
    onResize() {
      this.windowWidth = window.innerWidth
    },
    ...mapMutations(['getuseremail', 'updateFeed']),
    ...mapActions(['like', 'likeCnt', 'likeUser', 'unlike','userprofile', 'searchTagFeed', 'deleteFeed']),
  },
  mounted() {
    this.$nextTick(() => {
      window.addEventListener('resize', this.onResize);
    })
    this.userprofile(this.detailFeed.email)
  },
  created(){
    this.likeCnt(this.likeCntData);
    this.likeUser(this.likeCntData);
  },
}
</script>

<style scoped>
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
</style>
