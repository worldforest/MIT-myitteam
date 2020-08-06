<template>
  <div >
    {{ clubinfo }}
    <div class="cont" v-if="windowWidth >= 900">
      <div class="d-flex ma-2">
        <div>
          <img :src="club.imagesrc" alt="제목부분 포스터" class="title-img">
        </div>
        <div class="ml-3">
          {{ club.no }}
          <h3>{{ club.title }}</h3>
          <h4>{{ club.host }}</h4>
        </div>
      </div>
      <v-row class="mx-2">
        <v-col cols="6">
          <div class="text-center">
            <span @click="onClick_intro" class="cursor">소개</span>  
          </div>        
        </v-col>
        <v-col cols="6">
          <div class="text-center">
            <span @click="onClick_Team(); getTeamData(club.no);" class="cursor">팀원모집</span>  
          </div>        
        </v-col>
      </v-row>
      <hr>
      <div v-if="isIntro">
        <Intro :club="club" />
      </div>
      <div v-else-if="isTeam">
        <Team :club="club" :getTeamList="getTeamList"/>
      </div>
    </div>
    <div v-else>
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
            <span @click="onClick_Team(); getTeamData(club.no);" class="cursor">팀원모집</span>  
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
        <Team :club="club" :getTeamList="getTeamList"/>
      </div>
    </div>
  </div>
</template>

<script>
import Intro from '@/components/Intro.vue'
import Team from '@/components/Team.vue'
import { mapActions, mapState } from 'vuex'

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
      windowWidth: window.innerWidth,
    }
  },
  watch: {
    windowWidth(newWidth, oldWidth) {
    this.txt = `it changed to ${newWidth} from ${oldWidth}`;
    }
  },
  created () {
    this.$store.state.clubinfo = this.club
  },
  mounted() {
    this.info = this.$store.state.club
    this.$nextTick(() => {
      window.addEventListener('resize', this.onResize);
    })
  },

  beforeDestroy() { 
    window.removeEventListener('resize', this.onResize); 
  },

  methods: {
    ...mapActions(['getTeamData']),
    onClick_intro() {
      this.isIntro = true
      this.isTeam = false
    },
    
    onClick_Team() {
      this.isTeam = true
      this.isIntro = false
    },

    onResize() {
      this.windowWidth = window.innerWidth
    },
  },
  computed: { 
    ...mapState(['getTeamList'])
  }
}
</script>

<style scoped>
  .title-img {
    width: 80px;
    height: 114px;
  }

  .bg {
    background-color:#c0c0c0;
  }

  .cursor {
    cursor: pointer;
    color: rgb(92, 107, 192);
    font-weight: bold;
  }

  .cont {
    margin: 0 20%;
  }


</style>
