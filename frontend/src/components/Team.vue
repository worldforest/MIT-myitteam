<template>
  <div class="container">
    <div class="ma-2">
      <v-btn color="primary" @click="gotoTeam"><img src="@/assets/edit.png" alt="" style="width:20px"><span class="ml-2">팀원을 모집해요!</span></v-btn>
    </div>
    <v-row>
      <v-col cols="6" class="px-6" v-for="i in community" :key="i">
        <div class="bg">
          <div class="d-flex">
            <div>
              <p>{{ i.title }}</p>
              <p>{{ i.host }}</p>                  
              <p>{{ i.dday }}</p>                  
            </div>
            <div class="ml-auto">
              <img :src="i.imgUrl" alt="" style="width:100px; height:150px;">
            </div>
          </div>
        </div>        
      </v-col>
    </v-row>
  </div>
  
</template>

<script>

export default {
  name: 'Intro',
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
          title: '게시글1',
          host: '과학기술정보통신부',
          dday: "D-7",
          imgUrl: require('@/assets/공모1.png')
        },
        {
          title: '게시글2',
          host: '과학기술정보통신부',
          dday: "D-88",
          imgUrl: require('@/assets/user.png')
        }
      ]
    }
  },
  mounted() {
  } 
}
</script>

<style>

</style>