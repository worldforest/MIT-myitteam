<template>
	<v-card color="#FAFAFA">
    <v-container>
      <div>
				<v-card id="card-apply" class="mx-auto py-5 px-3 my-8" outlined max-width="800">
					<h1 class="text-center mb-5 h1-apply">프로젝트 팀원을 구해보세요 :)</h1>
					
					<div>
						<h3 class="ml-4">프로젝트 주제 : </h3>
						<v-col class="mx-auto" cols="12" md="11">
							<v-text-field
								label="프로젝트 주제"
								outlined
								v-model="projectData.title"
							></v-text-field>
						</v-col>
					</div>

					<h3 class="ml-4">프로젝트 소개 : </h3>
					<v-col cols="12" md="11" class="mx-auto">
						<v-textarea
							outlined
							label="프로젝트 소개 "
							v-model="projectData.project_introduce"
						></v-textarea>
					</v-col>

					<div class="mb-3">
						<h3 class="ml-4">프로젝트 기간 : </h3>
						<v-row>
							<v-col class="d-flex mx-auto" cols="5">
								<template>
									<div class="text-center">
										<v-menu offset-y>
											<template v-slot:activator="{ on, attrs }">
												<v-btn
													text
													v-bind="attrs"
													v-on="on"
													height="80px"
												>
													<v-text-field label="프로젝트 시작"
															outlined
															v-model="projectData.startdate"></v-text-field>
												</v-btn>
											</template>
										
											<v-flex>
												<v-date-picker v-model="projectData.startdate" color="green lighten-1"></v-date-picker>
											</v-flex>
										</v-menu>
									</div>
								</template>
							</v-col>

							<v-col class="d-flex mx-auto" cols="5">
								<template>
									<div class="text-center">
										<v-menu offset-y>
											<template v-slot:activator="{ on, attrs }">
												<v-btn
													text
													v-bind="attrs"
													v-on="on"
													height="80px"
												>
													<v-text-field label="프로젝트 종료"
															outlined
															class="d-flex mx-auto" cols="5"
															v-model="projectData.enddate"></v-text-field>
												</v-btn>
											</template>
											
												<v-flex>
													<v-date-picker v-model="projectData.enddate" color="blue lighten-1"></v-date-picker>
												</v-flex>
										</v-menu>
									</div>
								</template>
							</v-col>
						</v-row>

						<div>
							<h3 class="ml-4">지역 : </h3>
								<v-col class="d-flex mx-auto" cols="12" md="11">
									<v-select
										:items="selectRegion"
										label="지역 선택"
										outlined
										v-model="projectData.region"
									></v-select>
								</v-col>
						</div>

						<div>
							<h3 class="ml-4">팀 소개 : </h3>
							<v-col class="mx-auto" cols="12" md="11">	
								<v-text-field
									label="팀 소개"
									outlined
									v-model="projectData.introduce"
								></v-text-field>
							</v-col>
						</div>
					</div>

					<ProjectInput @add-project="addProject"/>

					<div>
						<li class="itemLi" v-for="item in this.projectData.dataList" :key="item.id">
							<v-col class="mx-auto" cols="12" md="11">
								<v-card color="#FAFAFA" class="mb-3 py-4 px-3">
									<h3 class="mb-3">{{ item.part }}</h3>
									<hr class="mb-3">

									<v-list-item>
										<v-list-item-content>
											<v-list-item-title style="white-space:pre-line;"><h4 class="mb-2">인원 : {{ item.headcount }}</h4></v-list-item-title>
										</v-list-item-content>
									</v-list-item>
									
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
								</v-card>
							</v-col>
						</li>
					</div>

					<v-col class="text-center mx-auto">
						<div class="my-2">
							<v-btn depressed large class="white--text" color="#5C6BC0" @click="apply">등록하기</v-btn>
						</div>
					</v-col>
				</v-card>
				<!-- {{ this.pojectSetData }} -->
				<ProjectPartDetail v-if="show" :pojectSetData="pojectSetData"/>
      </div>
    </v-container>
	</v-card>

</template>

<script>
import ProjectInput from '@/components/ProjectInput'
import ProjectPartDetail from '@/components/ProjectPartDetail'

export default {
	name: "ProjectRegister",
	components: {
		ProjectInput,
		ProjectPartDetail,
	},
	data() {
		return{
			selectRegion: ['서울특별시', '대전광역시', '대구광역시', '부산광역시', '경기도', '인천광역시', '광주광역시', '울산광역시', '세종특별시', '강원도', '경상남도', '경상북도', '전라남도', '전라북도', '충청남도', '충청북도', '제주도'],
			projectData: {
				id: Date.now(),
				title: '',
				project_introduce: '',
				startdate: '',
				enddate: '',
				region:'',
				introduce: '',
				dataList: [],
			},
			pojectSetData: {
				id: Date.now(),
				title: '',
				project_introduce: '',
				startdate: '',
				enddate: '',
				region: '',
				introduce: '',
				dataList: [],
			},
			show: false,	
		}
	},
	methods: {
		addProject(Data){
			this.projectData.dataList = [...this.projectData.dataList, Data]
		},
		apply(){
			this.pojectSetData.title = this.projectData.title,
			this.pojectSetData.project_introduce = this.projectData.project_introduce,
			this.pojectSetData.startdate = this.projectData.startdate,
			this.pojectSetData.enddata = this.projectData.enddate, 
			this.pojectSetData.region = this.projectData.region,
			this.pojectSetData.introduce = this.projectData.introduce,
			this.pojectSetData.dataList = this.projectData.dataList
			this.show = true

			this.$emit('submit-project-data', this.pojectSetData)
			console.log(this.pojectSetData)

			for(var item in this.projectData){
        this.projectData[item] = ''
      }
		}
	}
}
</script>

<style>
	.v-expansion-panels{
		max-width: 500px;
	}
	.h1-apply {
    color: rgb(92, 107, 192);
  }
  .itemLi{
    list-style: none;
  }
</style>