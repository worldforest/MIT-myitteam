<template>
  <div class="mt-3">
    <div v-if="windowWidth >= 767" class="cont15">
      
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
      <v-row>
        <v-spacer></v-spacer>
        <v-icon large class="mr-4">mdi-heart-multiple-outline</v-icon>
        <v-icon large color="red" class="mr-4">mdi-heart-multiple</v-icon>
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
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from 'vuex'

export default {
  computed: {
    ...mapState(['detailFeed', 'email', 'userprofiledata']),
  },
  data() {
    return {
      windowWidth: window.innerWidth,
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
    ...mapActions(['userprofile', 'searchTagFeed'])
  },
  mounted() {
    this.$nextTick(() => {
      window.addEventListener('resize', this.onResize);
    })
    this.userprofile(this.detailFeed.email)
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

  .cont2 {
    margin: 0 2%;
  }
</style>
