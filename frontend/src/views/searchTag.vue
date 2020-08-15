<template>
  <div>
    <div class="text-box">
      <div class="center">
        <button @click="searchTagFeed(keyword)">전체 게시글</button>
      </div>
      <div class="right" v-if="email">
        <button @click="searchFollowFeed(email)">팔로우 게시물</button>
      </div>
    </div>
    <div>
      <v-row class="cont10">
        <v-col cols="4" v-for="i in tagData" :key="i.no">
          <div class="mx-2 detail_hover">
            {{ i.nickname }}
            <img :src="i.src"
              alt="이미지를 찾을 수 없습니다."
              style="width:100%; height: 350px"
              :i="i"
              @click='feedDetail(i)'
              v-if="windowWidth >= 767"
            >
            <img :src="i.src"
              alt="이미지를 찾지 못했습니다."
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
</template>

<script>
import { mapState, mapActions, mapMutations } from 'vuex'

export default {
  data() {
    return {
      windowWidth: window.innerWidth,
      txt: '',
    }
  },
  computed: {
    ...mapState(['tagData', 'email', 'keyword'])
  },

  methods: {
    ...mapActions(['searchFollowFeed', 'searchTagFeed']),
    ...mapMutations(['feedDetail']),
    onResize() {
      this.windowWidth = window.innerWidth
    },    
  },

  mounted() {
    this.$nextTick(() => {
      window.addEventListener('resize', this.onResize);
    })
  },

   beforeDestroy() { 
    window.removeEventListener('resize', this.onResize); 
  },

}
</script>

<style scoped>
  .text-box {
    width: 100%;
    text-align: center;
    margin-top: 1rem;
  }

  .center {
    display: inline-block;
    margin: 0
  }

  .right {
    float: right;
  }

  .cursor {
    cursor: pointer;
    font-weight: bold;
  }

</style>