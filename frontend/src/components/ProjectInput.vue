<template>
	<div>
      <h3 class="ml-4">모집 인원 : </h3>
      <v-row>
        <v-col class="d-flex mx-auto" cols="5">
          <v-select
          :items="selectArea"
          label="분야"
          outlined
          v-model="projectData.part"
          ></v-select>
        </v-col>

        <v-col class="d-flex mx-auto" cols="5">
          <v-select
            :items="selectPeople"
            label="인원"
            outlined
            v-model="projectData.headCount"
          ></v-select>
        </v-col>
      </v-row>

      <h3 class="ml-4">담당 업무 : </h3>
      <v-col cols="12" md="11" class="mx-auto">
      <v-textarea
        outlined
        label="담당 업무"
        v-model="projectData.task"
      ></v-textarea>
      </v-col>

      <h3 class="ml-4">필수 역량 : </h3>
      <v-col cols="12" md="11" class="mx-auto">
      <v-textarea
        outlined
        label="필수 역량"
        v-model="projectData.ability"
      ></v-textarea>
      </v-col>

      <h3 class="ml-4">우대 사항 : </h3>
      <v-col cols="12" md="11" class="mx-auto">
      <v-textarea
        outlined
        label="우대 사항"
        v-model="projectData.advantage"
      ></v-textarea>
      </v-col>

      <div color="transparent" class="mb-3 mr-8 d-flex justify-end">
        
        <h6 style="color:red;">입력 후 '+'버튼을 눌러주세요!</h6>
        <v-btn class="ml-2" @click="setProjectData">+</v-btn>
      </div>

  </div>
</template>

<script>
export default {
	data() {
		return{
			selectArea: ['프론트엔드', '백엔드', '인공지능', '빅데이터', '블록체인', 'IOT'],
			selectPeople: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
			projectData: {
				part: '',
				headCount: '',
				task: '',
				ability: '',
				advantage: '', 
			},
		}
	},
	methods: {
		setProjectData() {
			const setData = {
				part: this.projectData.part,
				headCount: this.projectData.headCount,
				task: this.projectData.task,
				ability: this.projectData.ability,
				advantage: this.projectData.advantage, 
      }
      
      if (this.projectData.task !== '' && this.projectData.ability !== '' && this.projectData.advantage !== ''){
        this.$emit('add-project', setData)

        for(var item in this.projectData){
          this.projectData[item] = ''
        }
      }
		}
	}
}

</script>

<style>

</style>