<template>

  <div>
    <div class="d-flex ma-2">
      <div>
        <img :src="club.imagesrc" alt="제목부분 포스터" class="title-img">
      </div>
      <div class="ml-3">
        <h3>{{ club.title }}</h3>
        <h4>{{ club.host }}</h4>
      </div>
    </div>
    <v-row class="mx-2">
      <v-col cols="4">
        <div class="text-center">
          <span @click="onClick_intro" class="cursor">소개</span>  
        </div>        
      </v-col>
      <v-col cols="4">
        <div class="text-center">
          <span @click="onClick_Team" class="cursor">팀원모집</span>  
        </div>        
      </v-col>
      <v-col cols="4">
        <div class="text-center">
          <span>뭐 넣을까?</span>  
        </div>        
      </v-col>
    </v-row>
    <hr>
    <div v-if="isIntro">
      <Intro :club="club" />
    </div>
    <div v-else-if="isTeam">
      <Team />
    </div>
  </div>
</template>

<script>
import Intro from '@/components/Intro.vue'
import Team from '@/components/Team.vue'

export default {
  name: "GongmoDetail",
  props: {
    club: Object,
  },

  components: {
    Intro,
    Team,
  },

  data() {
    return {
      isIntro: true,
      isTeam: false,
    }
  },

  methods: {
    onClick_intro() {
      this.isIntro = true
      this.isTeam = false
    },
    
    onClick_Team() {
      this.isTeam = true
      this.isIntro = false
    }
  }
}
</script>

<style>
  .title-img {
    width: 80px;
    height: 114px;
  }

  .bg {
    background-color:#c0c0c0;
  }

  .cursor {
    cursor: pointer;
  }
</style>
