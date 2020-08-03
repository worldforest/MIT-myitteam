<template>
  <div class="container">
    {{ club }}
    <div class="ma-2">
      <!-- <v-btn color="#5C6BC0" @click="gotoTeam"><img src="@/assets/edit.png" alt="" style="width:20px"><span class="ml-2 white--text">팀원을 모집해요!</span></v-btn> -->
      <router-link :to="{name: 'TeamRegister', params:{no:club.no}}"> 팀원 모집 </router-link>
      
    </div>
    <v-row>
      <v-col cols="6" class="px-6" v-for="i in community" :key="i">
        <div class="teamCard py-5 px-5">
          <div class="d-flex">
            <div>
              <h2 class="mb-4">{{ i.title }}</h2>
              <h3 class="mb-3">팀 설명 : {{ i.host }}</h3>                  
              <h4> 모집 기간 : {{ i.dday }}</h4>                  
            </div>
            <div class="ml-auto">
              <img :src="i.imgUrl" alt="" style="width:100px; height:150px;">
            </div>
          </div>
          <!-- {{ teamData }} -->
          <div class="mx-auto my-2" max-width="800">
            <v-btn depressed class="mr-2 white--text" color="#5C6BC0" @click="buttonClick">프론트엔드</v-btn>
            <v-btn depressed class="mr-2 white--text" color="#5C6BC0" @click="buttonClick">백엔드</v-btn>
            <v-btn depressed class="mr-2 white--text" color="#5C6BC0" @click="buttonClick">인공지능</v-btn>
            <v-btn depressed class="mr-2 white--text" color="#5C6BC0" @click="buttonClick">빅데이터</v-btn>
            <v-btn depressed class="mr-2 white--text" color="#5C6BC0" @click="buttonClick">블록체인</v-btn>
          </div>
        </div>        
      </v-col>
    </v-row>
  </div>
</template>
<script>
import { mapActions } from 'vuex'
export default {
  name: 'Team',
  props: {
    club: Object,
  },
  components: {
  },
  methods: {
    ...mapActions(['team']),
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
    teamData(Data){
      console.log(Data)
      this.applyData = Data
    }
  },
  data() {
    return {
      applyData: '',
      buttonvalue: '',
			dialog: false,
      community: [
        {
          title: '대전 팀 구합니다 :)',
          host: '열정만 있으면 누구나 환영입니다! 함께 공부해보아요 ~~~ ',
          dday: "2020-12-25 까지",
          imgUrl: require('@/assets/공모1.png')
        },
        {
          title: '서울 팀 구합니다 :)',
          host: '좋은 분위기 속에서 공모전 하실 분 !! 어서오세요 ~~ ',
          dday: "2020-09-21 까지",
          imgUrl: require('@/assets/user.png')
        }
      ]
    }
  }, 
}
</script>
<style>
  .teamCard {
    border: 2px solid rgb(92, 107, 192);
  }
</style>