<template>
  <div class="mt-3">
    <div v-if="windowWidth >= 767" class="cont15">
      {{ detailFeed }}
      {{ windowWidth }}
      <router-link :to="{name: 'UserProfile', params:{user:detailFeed.email}}" v-if="email !== detailFeed.email"><h3>작성자: {{ detailFeed.nickname }}</h3></router-link>
      <router-link to='/profile' v-else><h3>작성자: {{ detailFeed.nickname }}</h3></router-link>
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
        <h5 class="mr-4">{{ likeCntnum }}명이 좋아합니다. </h5>
        <v-icon v-if="!likeUserList.includes(email)" large class="mr-4 likebtn" @click="like(likeData);">mdi-heart-multiple-outline</v-icon>
        <!-- <h5 v-if="likeUserList.includes(email)" class="mr-4">본인을 포함한 외 {{ likeCntnum }}명이 좋아합니다. </h5> -->
        <v-icon v-if="likeUserList.includes(email)" large class="mr-4 likebtn" color="red" @click="unlike(likeData);">mdi-heart-multiple</v-icon>
      </v-row>
    </div>
    <div v-else>
      <router-link :to="{name: 'UserProfile', params:{user:detailFeed.email}}" v-if="email !== detailFeed.email"><h3>작성자: {{ detailFeed.nickname }}</h3></router-link>
      <router-link to='/profile' v-else><h3>작성자: {{ detailFeed.nickname }}</h3></router-link>
      <hr>
      <div class="d-flex justify-end mb-4">
        <h4>작성일자: {{ detailFeed.writedate }}</h4>
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
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from 'vuex'

export default {
  computed: {
    ...mapState(['detailFeed', 'email', 'likeCntnum', 'likeUserList']),
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
    ...mapMutations(['getuseremail']),
    ...mapActions(['like', 'likeCnt', 'likeUser', 'unlike']),
  },
  mounted() {
    this.$nextTick(() => {
      window.addEventListener('resize', this.onResize);
    })
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
</style>
