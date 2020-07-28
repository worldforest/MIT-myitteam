<template>
	<v-card color="#FAFAFA">
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

			<v-col cols="12" md="11" class="mx-auto">
				<v-textarea
					outlined
					label="프로젝트 소개 "
					v-model="projectData.project_introduce"
				></v-textarea>
      </v-col>

			<div>
				<h3 class="ml-4">프로젝트 기간 : </h3>
				<v-row>
					<v-col class="d-flex mx-auto" cols="5">
							<v-expansion-panels class="v-expansion-panels">
								<v-expansion-panel>
									<v-expansion-panel-header>
										<v-text-field label="프로젝트 시작"
											outlined
											v-model="projectData.startdate"></v-text-field></v-expansion-panel-header>
									<v-expansion-panel-content>
										<v-flex >
											<v-date-picker v-model="projectData.startdate" color="green lighten-1"></v-date-picker>
										</v-flex>
									</v-expansion-panel-content>	
								</v-expansion-panel>
							</v-expansion-panels>
					</v-col>

					<v-col class="d-flex mx-auto" cols="5">
							<v-expansion-panels class="v-expansion-panels">
								<v-expansion-panel>
									<v-expansion-panel-header>
										<v-text-field label="프로젝트 종료"
											outlined
											v-model="projectData.enddate"></v-text-field></v-expansion-panel-header>
									<v-expansion-panel-content>
										<v-flex>
											<v-date-picker v-model="projectData.enddate" color="green lighten-1" header-color="primary"></v-date-picker>
										</v-flex>
									</v-expansion-panel-content>	
								</v-expansion-panel>
							</v-expansion-panels>
					</v-col>
				</v-row>

				<div>
					<h3 class="ml-4">지역 : </h3>
						<v-col class="d-flex mx-auto" cols="12" md="11">
							<v-select
								:items="selectRegion"
								label="지역 선택"
								outlined
								v-model="applyData.region"
							></v-select>
						</v-col>
				</div>

				<div>
					<h3 class="ml-4">팀 소개 : </h3>
					<v-col class="mx-auto" cols="12" md="11">
						<v-text-field
							label="팀 소개"
							outlined
							v-model="applyData.introduce"
						></v-text-field>
					</v-col>
				</div>
				</div>

    </v-card> 
  </v-card>
</template>

<script>
export default {
	name: "ProjectRegister",
	data() {
		return{
			projectData: {
				title: null,
				project_introduce: null,
				startdate: null,
				enddate: null,
			}
		}
	},
}
</script>

<style>
	.v-expansion-panels{
		max-width: 500px;
	}
</style>