<template>
  <div>
    {{ windowWidth }}
    <div class="cont" v-if="windowWidth >= 800">
      <div class="d-flex ma-2">
        <div class="ml-3">
          <!-- {{ club2 }} -->
          <!-- {{ getTeamList }} -->
          <h1 class="mb-3">{{ club2.title }}</h1>
          <h4>팀장 : {{ club2.nickname }}</h4>
          <h4 class="mt-3 mb-4 local"> <li v-for="i in getTeamList" :key="i">{{ i.local }}</li></h4>
        </div>
      </div>
      <hr>
      <div>
        <PjtIntro :club2="club2" :getTeamList="getTeamList"/>
      </div>
    </div>

    <div class="mx-7" v-if="windowWidth < 800">
      <div class="d-flex ma-2">
        <div class="">
          <!-- {{ club2 }} -->
          <!-- {{ getTeamList }} -->
          <h4 class="mb-3">{{ club2.title }}</h4>
          <h5>팀장 : {{ club2.nickname }}</h5>
          <h5 class="mt-4 mb-4 local"> <li v-for="i in getTeamList" :key="i">{{ i.local }}</li></h5>
        </div>
      </div>
      <hr>
      <div>
        <PjtIntro :club2="club2" :getTeamList="getTeamList"/>
      </div>
    </div>
  </div>
</template>

<script>
import PjtIntro from '@/components/PjtIntro.vue'
// import PjtTeam from '@/components/PjtTeam.vue'

import { mapState, mapActions } from 'vuex'

export default {
  name: "ProjectDeetail",
  components: {
    PjtIntro,
    // PjtTeam
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
    ...mapState(['club2', 'getTeamList', 'email'])
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
  .local {
    color: rgb(92, 107, 192);
    font-weight: bold;
  }
  
  li {
    list-style: none;
  }
</style>