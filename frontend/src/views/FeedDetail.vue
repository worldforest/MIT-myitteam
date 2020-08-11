<template>
  <div class="mt-3">
    <div v-if="windowWidth >= 767" class="cont15">
      {{ windowWidth }}
      <router-link :to="{name: 'UserProfile', params:{user:detailFeed.email}}"><h3>작성자: {{ detailFeed.nickname }}</h3></router-link>
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
      <router-link :to="{name: 'UserProfile', params:{user:detailFeed.email}}"><h3>작성자: {{ detailFeed.nickname }}</h3></router-link>
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
import { mapState, mapMutations } from 'vuex'

export default {
  computed: {
    ...mapState(['detailFeed']),
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
  },
  mounted() {
    this.$nextTick(() => {
      window.addEventListener('resize', this.onResize);
    })
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
</style>
