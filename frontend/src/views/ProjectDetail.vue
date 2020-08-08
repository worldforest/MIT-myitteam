<template>
  <div >
    <!-- {{ club2 }} -->
    <!-- //////////////////////// 화면이 넓을 때 /////////////////////////////// -->
    <div class="cont" v-if="windowWidth >= 900">
      <div class="d-flex ma-2">
        <div>
          <img :src="club2.imagesrc" alt="제목부분 포스터" class="title-img">
        </div>
        <div class="ml-3">
          {{ club2 }}
          <h3 class="mb-3">{{ club2.title }}</h3>
          <h4>팀장 : {{ club2.nickname }}</h4>
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
            <span @click="onClick_Team(); getTeamData(club2.no);" class="cursor">팀원모집</span>  
          </div>        
        </v-col>
      </v-row>
      <hr>
      <div v-if="isIntro">
        <PjtIntro :club2="club2" />
      </div>
      <div v-else-if="isTeam">
        <PjtTeam :club2="club2" :getTeamList="getTeamList"/>
      </div>
    </div>

    <!-- //////////////////////// 화면이 좁을 때 /////////////////////////////// -->
    <div v-else>
      <div class="d-flex ma-2">
        <div>
          <img :src="club2.imagesrc" alt="제목부분 포스터" class="title-img">
        </div>
        <div class="ml-3">
          <h3>{{ club2.title }}</h3>
          <h4>{{ club2.nickname }}</h4>
        </div>
      </div>
      <div class="text-center">
        <span class="cursor">소개</span>  
      </div>       
      <hr>
      <img :src="club2.imagesrc" alt="본문내용 포스터" style="width:100%" class="mt-4">
      <p style="white-space:pre-line;">{{ club2.description }}</p>
    </div>
  </div>
</template>

<script>
import PjtIntro from '@/components/PjtIntro.vue'
import PjtTeam from '@/components/PjtTeam.vue'

import { mapState, mapActions } from 'vuex'

export default {
  name: "ProjectDeetail",
  components: {
    PjtIntro,
    PjtTeam
  },
  data() {
    return {
      isIntro: true,
      isTeam: false,
      windowWidth: window.innerWidth,
    }
  },
  mounted() {
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
    ...mapState(['club2', 'getTeamList'])
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
    color: rgb(92, 107, 192);
    font-weight: bold;
  }

  .cont {
    margin: 0 20%;
  }
</style>