<template>
  <v-card >
		{{ dataList }}
		{{ sendData }}
		<li v-for="item in dataList.dataList" :key="item">
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
				<v-btn class="mb-3 local" color="primary" dark v-if="email" @click="applyLeader(item); apply(sendData); submitProfile();">
						지원하기
				</v-btn>
			</v-row>
		</li>
  </v-card>
</template>

<script>
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
    }
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
}
</script>

<style>
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