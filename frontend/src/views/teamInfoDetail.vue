<template>
  <div>
    <div class="cont" v-if="windowWidth >= 900">
      <v-row class="d-flex">
        <h3 class="ml-4 mt-3">{{ dataList.title }}</h3>
        <v-spacer></v-spacer>
        <span class=" mr-5 local">{{ dataList.local }}</span>
      </v-row>
      <hr>
      <li v-for="(item, index) in dataList.dataList" :key="index" class="bord">
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
          <v-btn class="mb-3 applybtn" color="primary" dark v-if="email && email !== dataList.email" @click="applyLeader(item); apply(sendData); submitProfile;">
              지원하기
          </v-btn>
        </v-row>
      </li>
    </div>
    <div class="cont2" v-if="windowWidth < 900">
      <v-row class="d-flex mx-3">
        <h4 class="mt-3">{{ dataList.title }}</h4>
        <v-spacer></v-spacer>
        <span class="local2">{{ dataList.local }}</span>
      </v-row>
      <hr>
      <li v-for="(item, index) in dataList.dataList" :key="index" class="bord mx-3">
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
          <v-btn class="mb-3 applybtn" color="primary" dark v-if="email && email !== dataList.email" @click="applyLeader(item); apply(sendData); submitProfile;">
              지원하기
          </v-btn>
        </v-row>
      </li>
    </div>
  </div>
</template>

<script scoped>
import { mapActions, mapState } from 'vuex'

export default {
	data() {
		return {
			sendData: {
        no: '',
        leaderemail: '',
        email: '',
        part: '',
      },
      windowWidth: window.innerWidth,
		}
	},
	methods: {
		...mapActions(['apply', 'deleteTeam']),
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
    onResize() {
    this.windowWidth = window.innerWidth
    },
	},
	computed : {
    ...mapState(['email']),
  },
	props: {
		dataList: Object,
	},
	created() {
		this.emailSave(this.dataList);
  },
  watch: {
    windowWidth(newWidth, oldWidth) {
      this.txt = `it changed to ${newWidth} from ${oldWidth}`;
    }
  },
  beforeDestroy() { 
    window.removeEventListener('resize', this.onResize); 
  },
  mounted () {
    this.$nextTick(() => {
    window.addEventListener('resize', this.onResize);
    })
    window.scrollTo(0, 0)
  },
}
</script>

<style scoped>
  .applybtn {
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
    font-family: 'jua', sans-serif;
  }
  .cont{
    margin: 1rem 20%;

  }
  .cont2 {
    margin: 0 0;
  }
  .bord {
    border: 2px solid rgb(92, 107, 192);
    margin-bottom: 1rem;
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
</style>