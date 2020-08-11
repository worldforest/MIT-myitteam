<template>
  <v-card>
		<div class="mx-auto my-2" max-width="800">
			<v-btn depressed large class="mr-2" color="primary" @click="buttonClick">프론트엔드</v-btn>
			<v-btn depressed large class="mr-2" color="primary" @click="buttonClick">백엔드</v-btn>
			<v-btn depressed large class="mr-2" color="primary" @click="buttonClick">인공지능</v-btn>
			<v-btn depressed large class="mr-2" color="primary" @click="buttonClick">빅데이터</v-btn>
			<v-btn depressed large class="mr-2" color="primary" @click="buttonClick">블록체인</v-btn>
			<p>{{ this.buttonvalue }}</p>
			<p>{{ pojectSetData }}</p>
		</div>

		<div>	
			<li class="detailLi" v-for="item in pojectSetData.dataList" :key="item.id">
				<v-card class="mx-auto py-5 px-5" max-width="800" v-if="item.part == buttonvalue">
					<h1 class="mb-4 text-center">{{ pojectSetData.title }}</h1>
					<h2 class="mb-2">{{ item.part }}</h2>
					<hr class="mb-3">
					<v-list-item>
						<v-list-item-content>
							<h4 class="mb-2">담당 업무</h4>
							<v-list-item-title style="white-space:pre-line;">{{ item.task }}</v-list-item-title>
						</v-list-item-content>
					</v-list-item>

					<v-list-item>
						<v-list-item-content>
							<h4 class="mb-2">필수 역량</h4>
							<v-list-item-title style="white-space:pre-line;">{{ item.ability }}</v-list-item-title>
						</v-list-item-content>
					</v-list-item>

					<v-list-item>
						<v-list-item-content>
							<h4 class="mb-2">우대 사항</h4>
							<v-list-item-title style="white-space:pre-line;">{{ item.advantage }}</v-list-item-title>
						</v-list-item-content>
					</v-list-item>

					<v-row>
						<div class="mx-auto my-2" max-width="800">
							<v-dialog
							v-model="dialog"
							width="500"
							>	
								<template v-slot:activator="{ on, attrs }">
									<v-btn
										color="blue lighten-2"
										dark
										v-bind="attrs"
										v-on="on"
									>
										지원하기
									</v-btn>
								</template>

								<v-card class="py-5 px-5">
									<v-card-title>
										<h3>팀장에게 참여의사를 전송하였습니다.</h3> 
									</v-card-title>
									<v-card-actions>
										<v-spacer></v-spacer>
										<v-btn
											color="primary"
											text
											@click="dialog = false"
										>
											close
										</v-btn>
									</v-card-actions>
								</v-card>
							</v-dialog>
						</div>

						<div class="mx-auto my-2" max-width="800">
							<v-btn depressed large color="#ECEFF1">돌아가기</v-btn>
						</div>
					</v-row>
				</v-card>
			</li>
		</div>
	</v-card>
</template>

<script>
export default {
	name: 'ProjectPartDetail',
	data(){
		return{
			buttonvalue: '인공지능',
			dialog: false,
		}
	},
	props: {
		pojectSetData:{
			type: Array,
			required: true,
		}
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