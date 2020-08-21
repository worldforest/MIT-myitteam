<template>
  <div>
    <div class="cont" v-if="windowWidth >= 800">

      <div class="ml-3">
        <h2 class="mb-3 text-center">{{ club2.title }}</h2>
      </div>
      
      <div>
        <v-row class="d-flex">
          <v-icon class="mx-4" large>mdi-account-circle-outline</v-icon> 
          <h3 class="mt-5"> {{ club2.nickname }}</h3>
          <v-spacer></v-spacer>
          <span class=" mr-5 local"> <li v-for="(i, index) in getTeamList" :key="index">{{ i.local }}</li></span>
        </v-row>
      </div>

      <hr>
      <div>
        <PjtIntro :club2="club2" :getTeamList="getTeamList"/>
      </div>
    </div>

    <div class="mx-7" v-if="windowWidth < 800">
      <div class="ml-3">
        <h2 class="mb-5 text-center">{{ club2.title }}</h2>
      </div>
      
      <div>
        <v-row class="d-flex">
          <v-icon class="mx-2" large>mdi-account-circle-outline</v-icon> 
          <h3 class="mt-3"> {{ club2.nickname }}</h3>
          <v-spacer></v-spacer>
          <span class=" mr-5 local2"> <li class="mt-2" v-for="(i, index) in getTeamList" :key="index">{{ i.local }}</li></span>
        </v-row>
      </div>
      <hr>
      <div>
        <PjtIntro :club2="club2" :getTeamList="getTeamList"/>
      </div>
    </div>
  </div>
</template>

<script scoped>
import PjtIntro from '@/components/PjtIntro.vue'

import { mapState, mapActions } from 'vuex'

export default {
  name: "ProjectDeetail",
  components: {
    PjtIntro,
  },
  data() {
    return {
      isIntro: true,
      isTeam: false,
      windowWidth: window.innerWidth,
      club2: {},
    }
  },
  mounted() {
    this.club2 = JSON.parse(sessionStorage.getItem('projectinfo'))
    this.getTeamData(this.club2.no)
    this.$nextTick(() => {
      window.addEventListener('resize', this.onResize);
    })
    window.scrollTo(0, 0)
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
    ...mapState([ 'getTeamList', 'email'])
  },
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
  .local {
    color: white;
    background-color: rgb(92, 107, 192);
    font-weight: bold;
    font-size: 1.3rem;
    border: 1px solid rgb(92, 107, 192);
    padding: 1rem 2rem;
    border-radius: 100%;
  }
  .local2 {
    color: white;
    background-color: rgb(92, 107, 192);
    font-weight: bold;
    font-size: 1rem;
    border: 1px solid rgb(92, 107, 192);
    padding: 0.5rem 0.8rem;
    border-radius: 100%;
  }
  li {
    list-style: none;
  }
</style>