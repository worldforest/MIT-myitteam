<template>
  <v-card color="#FAFAFA">
    <v-card id="card-apply" class="mx-auto py-5 px-3 my-8" outlined max-width="800">
    <h1 class="text-center mb-5 h1-apply">팀원을 구해보세요 :)</h1>
    
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
            label="명 수"
            outlined
            v-model="applyData.headcount"
          ></v-select>
        </v-col>
      </v-row>

      <v-col cols="12" md="11" class="mx-auto">
      <v-textarea
        outlined
        name="input-7-4"
        label="담당 업무"
        v-model="applyData.task"
      ></v-textarea>
      </v-col>

      <v-col cols="12" md="11" class="mx-auto">
      <v-textarea
        outlined
        name="input-7-4"
        label="필수 역량"
        class="mx-auto"
        v-model="applyData.ability"
      ></v-textarea>
      </v-col>

      <v-col cols="12" md="11" class="mx-auto">
      <v-textarea
        outlined
        name="input-7-4"
        label="우대 사항"
        v-model="applyData.advantage"
      ></v-textarea>
      </v-col>

      <v-btn @click="setApplyData">추가하기</v-btn>
    </div>

    <!-- <div>
      <ul v-for="list in this.dataList" :key="this.applyData.id">
        <li>{{list}}</li>
      </ul>
    </div> -->
  </v-card>
  </v-card>
</template>

<script>
// import ApplyInput from '@/components/ApplyInput'

export default {
  name: 'Apply',
  components: {
    // ApplyInput,
  },
  data() {
    return{
      selectRegion: ['서울특별시', '대전광역시', '대구광역시', '부산광역시', '경기도', '인천광역시', '광주광역시', '울산광역시', '세종특별시', '강원도', '경상남도', '경상북도', '전라남도', '전라북도', '충청남도', '충청북도', '제주도'],
      selectArea: ['프론트엔드', '백엔드', '인공지능', '빅데이터', '블록체인'],
      selectPeople: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
      applyData: {
        id: Date.now(),
        region: null,
        part: null,
        introduce: null,
        headcount: null,
        task: null,
        ability: null,
        advantage: null, 
      },
      dataList: [],
    }
  },
  methods: {
    setApplyData(){
      this.dataList = [...this.dataList, this.applyData]  
      console.log(this.dataList)
    },
    initialize(){
      for(var item in this.applyData){
        this.applyData[item] = null
      }
    },
  }
}
</script>

<style>
  #card-apply {
    border:1px solid rgb(92, 107, 192);
  }
  .h1-apply {
    color: rgb(92, 107, 192);
  }
</style>