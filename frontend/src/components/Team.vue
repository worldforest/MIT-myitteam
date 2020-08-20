<template>
  <div>
    <div class="container" v-if="windowWidth >= 900">
      <div>
        <v-row v-if="email">
          <v-spacer></v-spacer>
          <v-btn class="mr-4 white--text" color="#5C6BC0"> 
            <v-icon medium class="ml-auto">mdi-pencil-box-multiple</v-icon> <span @click="checkemail();">팀원 모집하러 가기</span>
          </v-btn>
        </v-row>
      </div>

      <!-- 웹 페이지 클 때-->
      <v-row v-if="windowWidth >= 1270">
        <v-card v-if="getTeamList.length === 0" class="text-center noCard mx-auto mt-15">
          <h3>등록된 팀원 모집이 없습니다.</h3>
        </v-card>
        <v-col cols="6" v-for="(i, index) in getTeamList" :key="index">
          <div class="teamCard py-5 px-5">
            <div>
              <div>
                <span class="local mb-3">{{ i.local }}</span>
                <h4 class="mx-auto mt-3" v-if="i.description.length >= 15">{{ i.description.slice(0,8)}}...</h4>
                <h4 class="mx-auto mt-3" v-else>{{ i.description }}</h4>

                <hr class="hrr">

                <v-row justify="center">
                  <router-link class="TeamButton white--text mr-5" color="#5C6BC0" :to="{ name:'teamInfoDetail', params:{dataList : i}}">상세보기 </router-link>
                  <v-btn v-if="i.email === email" color="red" class="white--text button" right @click="deleteSave(i); deleteTeam(deleteData);">
                    삭제하기
                  </v-btn> 
                </v-row>
              </div>
            </div>
          </div>      
        </v-col>
      </v-row>
      
      <v-row v-if="windowWidth < 1270 && windowWidth >= 900 ">
        <v-card v-if="getTeamList.length === 0" class="text-center noCard mx-auto mt-15">
          <h3>등록된 팀원 모집이 없습니다.</h3>
        </v-card>
        <v-col cols="6" class="px-6" v-for="(i, index) in getTeamList" :key="index">
          <div class="teamCard2 py-5 px-5">
            <div >
              <div>
                <h6><span class="local">{{ i.local }}</span></h6>
                <h4 class="mx-auto mt-3" v-if="i.description.length >= 15">{{ i.description.slice(0,8)}}...</h4>
                <h4 class="mx-auto mt-3" v-else>{{ i.description }}</h4>
                <hr class="hrr my-4">
                <v-row justify="center">
                  <router-link class="TeamButton white--text mr-5" color="#5C6BC0" :to="{ name:'teamInfoDetail', params:{dataList : i}}">상세보기 </router-link>
                  <v-btn v-if="i.email === email" color="red" class="white--text button" right @click="deleteSave(i); deleteTeam(deleteData);">
                    삭제하기
                  </v-btn> 
                </v-row>
              </div>
            </div>
          </div>    
        </v-col>
      </v-row>
    </div>

    <!-- 900보다 작을때 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! --> 
    <div v-if="windowWidth < 900">
      <div>
        <v-row v-if="email">
          <v-spacer></v-spacer>
          <v-btn class="mr-4 white--text" color="#5C6BC0"> 
            <v-icon medium class="ml-auto">mdi-pencil-box-multiple</v-icon> <span @click="checkemail();">팀원 모집하러 가기</span>
          </v-btn>
        </v-row>
      </div>

      <!-- 700이상 900미만 -->
      <v-row v-if="windowWidth < 900 && windowWidth >= 700 ">
        <v-card v-if="getTeamList.length === 0" class="text-center noCard mx-auto mt-15">
          <h3>등록된 팀원 모집이 없습니다.</h3>
        </v-card>
        <v-col cols="6" class="px-6" v-for="(i, index) in getTeamList" :key="index">
          <div class="teamCard3 py-5 px-5">
            <div>
              <h6><span class="local">{{ i.local }}</span></h6>
              <h4 class="mx-auto mt-3" v-if="i.description.length >= 15">{{ i.description.slice(0,8)}}...</h4>
              <h4 class="mx-auto mt-3" v-else>{{ i.description }}</h4>
              <hr class="hrr my-4">
              <v-row justify="center">
                <router-link class="TeamButton white--text mr-5" color="#5C6BC0" :to="{ name:'teamInfoDetail', params:{dataList : i}}">상세보기 </router-link>
                <v-btn v-if="i.email === email" color="red" class="white--text button" right @click="deleteSave(i); deleteTeam(deleteData);">
                  삭제하기
                </v-btn> 
              </v-row>
            </div>
          </div>        
        </v-col>
      </v-row>

      <!-- 550이상 700미만 -->
      <v-row v-if="windowWidth < 700 && windowWidth >= 550 ">
        <v-card v-if="getTeamList.length === 0" class="text-center noCard mx-auto mt-15">
          <h3>등록된 팀원 모집이 없습니다.</h3>
        </v-card>
        <v-col cols="6" class="px-6" v-for="(i, index) in getTeamList" :key="index">
          <div class="teamCard4 py-5 px-5">
            <div>
              <h6><span class="local">{{ i.local }}</span></h6>
              <h5 class="mx-auto mt-3" v-if="i.description.length >= 15">{{ i.description.slice(0,8)}}...</h5>
              <h5 class="mx-auto mt-3" v-else>{{ i.description }}</h5>
              <hr class="hrr my-4">
              <v-row justify="center">
                <router-link class="TeamButton white--text mr-5" color="#5C6BC0" :to="{ name:'teamInfoDetail', params:{dataList : i}}">상세보기 </router-link>
                <v-btn v-if="i.email === email" color="red" class="white--text button" right @click="deleteSave(i); deleteTeam(deleteData);">
                  삭제하기
                </v-btn> 
              </v-row>
            </div>
          </div>        
        </v-col>
      </v-row>

      <!-- 380이상 550미만 -->
      <v-row v-if="windowWidth < 550 && windowWidth > 380">
        <v-card v-if="getTeamList.length === 0" class="text-center noCard2 mx-auto mt-15">
          <h4>등록된 팀원 모집이 없습니다.</h4>
        </v-card>
        <v-col cols="8" class="px-6" v-for="(i, index) in getTeamList" :key="index">
          <div class="teamCard5 py-5 px-5">
            <div>
              <h6><span class="local">{{ i.local }}</span></h6>
              <h4 class="mx-auto mt-3" v-if="i.description.length >= 15">{{ i.description.slice(0,8)}}...</h4>
              <h4 class="mx-auto mt-3" v-else>{{ i.description }}</h4>
              <hr class="hrr my-4">
              <v-row justify="center">
                <router-link class="TeamButton white--text mr-5" color="#5C6BC0" :to="{ name:'teamInfoDetail', params:{dataList : i}}">상세보기 </router-link>
                <v-btn v-if="i.email === email" color="red" class="white--text button" right @click="deleteSave(i); deleteTeam(deleteData);">
                  삭제하기
                </v-btn> 
              </v-row>
            </div>
          </div>        
        </v-col>
      </v-row>

      <!-- 380미만 -->
      <v-row v-if="windowWidth <= 380">
        <v-card v-if="getTeamList.length === 0" class="text-center noCard2 mx-auto mt-15">
          <h4>등록된 팀원 모집이 없습니다.</h4>
        </v-card>
        <v-col cols="8" v-for="(i, index) in getTeamList" :key="index">
          <div class="teamCard6 py-5 px-5">
            <div>
              <h6><span class="local">{{ i.local }}</span></h6>
              <h5 class="mx-auto mt-3" v-if="i.description.length >= 15">{{ i.description.slice(0,8)}}...</h5>
              <h5 class="mx-auto mt-3" v-else>{{ i.description }}</h5>
              <hr class="hrr my-4">
              <v-row justify="center">
                <router-link class="TeamButton white--text mr-5" color="#5C6BC0" :to="{ name:'teamInfoDetail', params:{dataList : i}}">상세보기 </router-link>
                <v-btn v-if="i.email === email" color="red" class="white--text button" right @click="deleteSave(i); deleteTeam(deleteData);">
                  삭제하기
                </v-btn> 
              </v-row>
            </div>
          </div>      
        </v-col>
      </v-row>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'
import Swal from 'sweetalert2'

export default {
  name: 'Team',
  props: {
    club: Object,
    getTeamList: Array,
  },
  components: {
  },
  methods: {
    ...mapActions(['apply', 'deleteTeam']),
    gotoTeam() {
      this.$router.push('/teamregister')
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
    },
    checkemail(){
      for(var x=0; x < this.getTeamList.length; x++){
        if (this.getTeamList[x].email === this.email){
          Swal.fire({
            icon: 'error',
            title: '등록 된 팀원 모집이 있습니다.',
          })
          this.$router.push('/gongmoDetail')
          var flag = true
        }
      }
      if (flag !== true){
        this.$router.push({name:'TeamRegister', params:{no:this.club.no}})
      }
    },
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
 *{ font-family: 'Jua', sans-serif;}

  .teamCard {
    border: 2px solid rgb(92, 107, 192);
    border-top: thick solid rgb(92, 107, 192);
    width: 330px;
    text-align: center;
  }
  .teamCard2 {
    border: 2px solid rgb(92, 107, 192);
    border-top: thick solid rgb(92, 107, 192);
    width: 340px;
    text-align: center;
  }
  .teamCard3 {
    border: 2px solid rgb(92, 107, 192);
    border-top: thick solid rgb(92, 107, 192);
    width: 270px;
    text-align: center;
  }
  .teamCard4 {
    border: 2px solid rgb(92, 107, 192);
    border-top: thick solid rgb(92, 107, 192);
    width: 260px;
    text-align: center;
  }
  .teamCard5 {
    border: 2px solid rgb(92, 107, 192);
    border-top: thick solid rgb(92, 107, 192);
    width: 300px;
    text-align: center;
  }
  .teamCard6 {
    border: 2px solid rgb(92, 107, 192);
    border-top: thick solid rgb(92, 107, 192);
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
    font-size: 0.9rem;
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
  .button {
    border-radius: 0.5rem;
  }
  .noCard{
    padding: 2rem;
    border: 2px solid rgb(92, 107, 192)
  }
  .noCard2{
    padding: 1.5rem;
    border: 2px solid rgb(92, 107, 192)
  }
</style>