<template>
  <div>
    <!-- {{ club.no }} -->
    {{ getTeamList }}
    {{ windowWidth }}
    <div class="container" v-if="windowWidth >= 900">
      <div>
        <v-row v-if="email">
          <v-spacer></v-spacer>
          <router-link class="TeamButton mr-4" style="color:white" :to="{name:'TeamRegister', params:{no:club.no}}">
          <v-icon medium color="#FFFFFF" class="ml-auto">mdi-pencil-box-multiple</v-icon> <span>팀원 모집하러 가기</span> </router-link>
        </v-row>
      </div>

      <!-- 웹 페이지 클 때-->
      <v-row v-if="windowWidth >= 1270">
        <v-col cols="6" v-for="i in getTeamList" :key="i">
          {{ i }}
          <div class="teamCard py-5 px-5">
            <div>
              <div>
                <span class="local mb-3">{{ i.local }}</span>
                <h4 class="mx-auto mt-3" v-if="i.description.length >= 15">{{ i.description.slice(0,8)}}...</h4>
                <h4 class="mx-auto mt-3" v-else>{{ i.description }}</h4>

                <hr class="hrr">

                <v-row justify="center">
                  {{ i.dataList }}
                  <v-btn color="primary" dark @click="dialog = true">
                    상세보기
                  </v-btn>
                  <v-dialog v-model="dialog" max-width="600">
                    <v-card class="cardModal">
                      {{ i.dataList }}
                      <li v-for="item in i.dataList" :key="item">
                        <div class="headline mb-2 white--text partTitle" >
                          <v-row class="cardModal">
                            <h3 class="ml-5 mt-2">{{ item.part }}</h3>
                            <v-spacer></v-spacer>
                            <h5 class="mr-5 mt-3"> 인원 : {{ item.headCount }}</h5>
                          </v-row>
                        </div>
                        <h3 class="ml-3">담당 업무</h3>
                        <v-card-text class="ml-2">
                          <v-list-item-title style="white-space:pre-line;">{{ item.task }}</v-list-item-title>
                        </v-card-text>
                        <hr class="mb-2">
                        <h3 class="ml-3">필수 역량</h3>
                        <v-card-text class="ml-2">
                          <v-list-item-title style="white-space:pre-line;">{{ item.ability }}</v-list-item-title>
                        </v-card-text>
                        <hr class="mb-2">
                        <h3 class="ml-3">우대 사항</h3>
                        <v-card-text class="ml-2">
                          <v-list-item-title style="white-space:pre-line;">{{ item.advantage }}</v-list-item-title>
                        </v-card-text>
                        <v-row justify="center">
                          <v-btn class="mb-3 local" color="primary" dark   v-if="email" @click="applyLeader(item); apply(sendData); submitProfile();">
                            지원하기
                          </v-btn>
                        </v-row>
                      </li>
                      <v-divider></v-divider>
                      <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn class="local" color="primary" text @click="dialog = false">
                          닫기
                        </v-btn>
                      </v-card-actions>
                    </v-card>
                  </v-dialog>
                </v-row>
              </div>
            </div>
          </div>      
        </v-col>
      </v-row>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'
// import IntroVue from './Intro.vue';
import Swal from 'sweetalert2'

export default {
  name: 'Team',
  props: {
    club: Object,
    getTeamList: Object,
  },
  components: {
  },
  methods: {
    ...mapActions(['apply', 'deleteTeam']),
    gotoTeam() {
      this.$router.push('/teamregister')
    },
		submitProfile(){
			Swal.fire({
				text: '팀장에게 성공적으로 전달하였습니다.',
			})
    },
    onResize() {
      this.windowWidth = window.innerWidth
    },
    applyLeader(item){
      this.sendData.part = item.part
      this.sendData.email = this.$store.state.email
    },
    emailSave(i){
      this.sendData.leaderemail = i.email
      this.sendData.no = i.no
    }, 
    deleteSave(i){
      this.deleteData.no = i.no
      this.deleteData.leaderemail = i.email
    }
  },
  data() {
    return {
      applyData: '',
      buttonvalue: '',
      dialog: false,
      // dialog2: false,
      windowWidth: window.innerWidth,
      sendData: {
        no: '',
        leaderemail: '',
        email: '',
        part: '',
      },
      deleteData: {
        no: '',
        leaderemail: '',
      }
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
    ...mapState(['email']),
  },
  mounted () {
      this.$nextTick(() => {
      window.addEventListener('resize', this.onResize);
    })
	},
}
</script>
<style scoped>
  @font-face {
    font-family: myFont;
    src: url("/src/font/BMJUA_ttf.ttf");
  }

  .teamCard {
    border: 2px solid rgb(92, 107, 192);
    width: 330px;
    text-align: center;
  }
  .teamCard2 {
    border: 2px solid rgb(92, 107, 192);
    width: 265px;
    text-align: center;
  }
  .teamCard3 {
    border: 2px solid rgb(92, 107, 192);
    width: 330px;
    text-align: center;
  }
  .teamCard4 {
    border: 2px solid rgb(92, 107, 192);
    width: 260px;
    text-align: center;
  }
  .teamCard5 {
    border: 2px solid rgb(92, 107, 192);
    width: 300px;
    text-align: center;
  }
  .teamCard6 {
    border: 2px solid rgb(92, 107, 192);
    width: 280px;
    text-align: center;
  }
  .localDiv {
    background-color: rgb(92, 107, 192);
    color: white;
    padding: 0.3rem;
    border-radius: 0.5rem;
  }
  .local {
    color: rgb(92, 107, 192);
    font-weight: bold;
  }
  .TeamButton {
    text-decoration: none;
    background-color: rgb(92, 107, 192);
    padding: 0.5rem 1rem;
    border-radius: 0.5rem;
    font-size: 0.8rem;
    font-weight: bold;
  }
  li {
    list-style: none;
  }
  .partTitle {
    background-color: rgb(92, 107, 192);
    padding: 0.8rem;
    font-weight: bold;
  }
  .Black{
    color: black;
    text-align: center;
  }
  .hrr{
    width: 200px;
    margin: 1rem auto;
  }
  .cardModal{
    font-family: myFont, sans-serif;
  }
</style>