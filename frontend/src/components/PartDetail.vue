<template>
  <v-card>
		<div class="mx-auto my-2" max-width="800">
			<v-btn depressed class="mr-2" color="primary" @click="buttonClick">프론트엔드</v-btn>
			<v-btn depressed class="mr-2" color="primary" @click="buttonClick">백엔드</v-btn>
			<v-btn depressed class="mr-2" color="primary" @click="buttonClick">인공지능</v-btn>
			<v-btn depressed class="mr-2" color="primary" @click="buttonClick">빅데이터</v-btn>
			<v-btn depressed class="mr-2" color="primary" @click="buttonClick">블록체인</v-btn>
			<p>{{ this.buttonvalue }}</p>
			<p>{{ applyData }}</p>
		</div>
	</v-card>
</template>

<script>
export default {
	name: 'PartDetail',
	data(){
		return{
			buttonvalue: '',
			dialog: false,
		}
	},
	props: {
		applyData: Object,
	},
	methods: {
		buttonClick(event){
			console.log(event.target.innerText)
			this.buttonvalue = event.target.innerText
		},
		submitProfile(){
			alert('팀장에게 참여의사를 전달하였습니다.');
		}
	}
}
</script>

<style scoped>
	.detailLi {
		list-style: none;
	}
</style>