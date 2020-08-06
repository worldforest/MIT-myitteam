<template>
  <div>
    <!-- {{ club.no }} -->
    {{ getTeamList }}
    {{ windowWidth }}
    <div class="container" v-if="windowWidth >= 900">
      <div class="ma-2">
        <router-link :to="{name: 'TeamRegister', params:{no:club.no}}"> 팀원 모집 </router-link>
      </div>

      <!-- 웹 페이지 클 때-->
      <v-row v-if="windowWidth >= 1270">
        <v-col cols="6" class="px-6" v-for="i in getTeamList" :key="i">
          <div class="teamCard py-5 px-5">
            <div class="d-flex">
              <div>
                <h2 class="mb-2">{{ i.description }}</h2>
                <hr class="mb-2">               
                <!-- <h5> 모집 기간 : {{ i.dday }}</h5>                   -->
              </div>
            </div>
          </div>        
        </v-col>
      </v-row>

      <!-- 900이상 1270미만 -->
      <v-row v-if="windowWidth < 1270 && windowWidth >= 900 ">
        <v-col cols="6" class="px-6" v-for="i in community" :key="i">
          <div class="teamCard2 py-5 px-5">
            <div class="d-flex">
              <div>
                <h2 class="mb-2">{{ i.title }}</h2>
                <hr class="mb-2">
                <h4 class="mb-3">{{ i.host }}</h4>                  
                <h5> 모집 기간 : {{ i.dday }}</h5>                  
              </div>
            </div>
          </div>        
        </v-col>
      </v-row>
    </div>



    <div v-if="windowWidth < 900">
      <div class="ma-2">
        <router-link :to="{name: 'TeamRegister', params:{no:club.no}}"> 팀원 모집 </router-link>
      </div>
      <!-- 700이상 900미만 -->
      <v-row v-if="windowWidth < 900 && windowWidth >= 700 ">
        <v-col cols="6" class="px-6" v-for="i in community" :key="i">
          <div class="teamCard3 py-5 px-5">
            <div class="d-flex">
              <div>
                <h2 class="mb-2">{{ i.title }}</h2>
                <hr class="mb-2">
                <h4 class="mb-4">{{ i.host }}</h4>                  
                <h5> 모집 기간 : {{ i.dday }}</h5>                  
              </div>
            </div>
          </div>        
        </v-col>
      </v-row>

      <!-- 550이상 700미만 -->
      <v-row v-if="windowWidth < 700 && windowWidth >= 550 ">
        <v-col cols="6" class="px-6" v-for="i in community" :key="i">
          <div class="teamCard4 py-5 px-5">
            <div class="d-flex">
              <div>
                <h3 class="mb-2">{{ i.title }}</h3>
                <hr class="mb-2">
                <h5 class="mb-4">{{ i.host }}</h5>                  
                <h6> 모집 기간 : {{ i.dday }}</h6>                  
              </div>
            </div>
          </div>        
        </v-col>
      </v-row>

      <!-- 400이상 550미만 -->
      <v-row v-if="windowWidth < 550 && windowWidth >= 400 ">
        <v-col cols="6" class="px-6" v-for="i in community" :key="i">
          <div class="teamCard5 py-5 px-5">
            <div class="d-flex">
              <div>
                <h4 class="mb-2">{{ i.title }}</h4>
                <hr class="mb-2">
                <h5 class="mb-4">{{ i.host }}</h5>                  
                <h6> 모집 기간 : {{ i.dday }}</h6>                  
              </div>
            </div>
          </div>        
        </v-col>
      </v-row>

      <!-- 400미만 -->
      <div v-if="windowWidth < 400">
        <v-col cols="6" class="px-6" v-for="i in community" :key="i">
          <div class="teamCard6 py-5 px-5">
            <div class="d-flex">
              <div>
                <h3 class="mb-1">{{ i.title }}</h3>
                <hr class="mb-1">
                <h4 class="mb-3">{{ i.host }}</h4>                  
                <h5> 모집 기간 : {{ i.dday }}</h5>                  
              </div>
            </div>
          </div>        
        </v-col>
      </div>
    </div>


  </div>
</template>
<script>
// import { mapActions } from 'vuex'
export default {
  name: 'Team',
  props: {
    club: Object,
    getTeamList: Object,
  },
  components: {
  },
  methods: {
    gotoTeam() {
      this.$router.push('/teamregister')
    },
    buttonClick(event){
			console.log(event.target.innerText)
			this.buttonvalue = event.target.innerText
		},
		submitProfile(){
			alert('팀장에게 참여의사를 전달하였습니다.');
    },

    onResize() {
      this.windowWidth = window.innerWidth
    },
  },
  data() {
    return {
      applyData: '',
      buttonvalue: '',
      dialog: false,
      windowWidth: window.innerWidth,
      community: [
        {
          title: '대전 팀 구합니다 :)',
          host: '열정만 있으면 누구나 환영입니다! 함께 공부해보아요 ~~~ ',
          dday: "2020-12-25 까지",

        },
        {
          title: '서울 팀 구합니다 :)',
          host: '좋은 분위기 속에서 공모전 하실 분 !! 어서오세요 ~~ ',
          dday: "2020-09-21 까지",

        }
      ]
    }
  },
  watch: {
    windowWidth(newWidth, oldWidth) {
      this.txt = `it changed to ${newWidth} from ${oldWidth}`;
    }
  },
  beforeDestroy() { 
    window.removeEventListener('resize', this.onResize); 
  },
  computed : {

  },
  mounted () {
      this.$nextTick(() => {
      window.addEventListener('resize', this.onResize);
    })
	},
}
</script>
<style>
  .teamCard {
    border: 2px solid rgb(92, 107, 192);
    width: 330px;
  }
  .teamCard2 {
    border: 2px solid rgb(92, 107, 192);
    width: 250px;
  }
  .teamCard3 {
    border: 2px solid rgb(92, 107, 192);
    width: 330px;
  }
  .teamCard4 {
    border: 2px solid rgb(92, 107, 192);
    width: 260px;
  }
  .teamCard5 {
    border: 2px solid rgb(92, 107, 192);
    width: 230px;
  }
  .teamCard6 {
    border: 2px solid rgb(92, 107, 192);
    width: 280px;
  }
</style>