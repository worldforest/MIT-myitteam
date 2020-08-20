<template>
	<div>
      <h3 class="ml-4">모집 인원 : </h3>
      <v-row>
        <v-col class="d-flex mx-auto" cols="5">
          <v-select
          :items="selectArea"
          label="분야"
          outlined
          v-model="applyData.part"
          ></v-select>
        </v-col>

        <v-col class="d-flex mx-auto" cols="5">
          <v-select
            :items="selectPeople"
            label="인원"
            outlined
            v-model="applyData.headCount"
          ></v-select>
        </v-col>
      </v-row>

      <h3 class="ml-4">담당 업무 : </h3>
      <v-col cols="12" md="11" class="mx-auto">
        <v-textarea
          outlined
          label="담당 업무"
          v-model="applyData.task"
        ></v-textarea>
      </v-col>

      <h3 class="ml-4">필수 역량 : </h3>
      <v-col cols="12" md="11" class="mx-auto">
        <v-textarea
          outlined
          label="필수 역량"
          v-model="applyData.ability"
        ></v-textarea>
      </v-col>

      <h3 class="ml-4">우대 사항 : </h3>
      <v-col cols="12" md="11" class="mx-auto">
        <v-textarea
          outlined
          label="우대 사항"
          v-model="applyData.advantage"
        ></v-textarea>
      </v-col>

      <div color="transparent" class="mb-3 mr-8 d-flex justify-end">
        <h6 style="color:red;">입력 후 '+'버튼을 눌러주세요!</h6>
        <v-btn clas="ml-2" @click="setApplyData">+</v-btn>
      </div>

  </div>

</template>

<script>
  export default {
    name: 'TeamInput',
    data() {
      return{
        selectArea: ['프론트엔드', '백엔드', '인공지능', '빅데이터', '블록체인', 'IOT'],
        selectPeople: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
        applyData: {
          part: '',
          headCount: '',
          task: '',
          ability: '',
          advantage: '', 
        },
        plus: false,
      }
    },
    methods: {
      setApplyData() {
        const setData = {
          part: this.applyData.part,
          headCount: this.applyData.headCount,
          task: this.applyData.task,
          ability: this.applyData.ability,
          advantage: this.applyData.advantage, 
        }
        
        if (this.applyData.task !== '' && this.applyData.ability !== '' && this.applyData.advantage !== ''){
          this.$emit('add-apply', setData)

          for(var item in this.applyData){
            this.applyData[item] = ''
          }
        }
      }
    },
    computed: {
      
    }
  }
</script>

<style scoped>

</style>