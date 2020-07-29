<template>
  <v-card color="#FAFAFA">
    <v-card id="card-apply" class="mx-auto py-5 px-3 my-8" outlined max-width="800">
      <h1 class="text-center mb-5 h1-apply">공모전 팀원을 구해보세요 :)</h1>
      
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

      <TeamInput @add-apply="addApply"/>

      <div>
        <li class="itemLi" v-for="item in this.applyData.dataList" :key="item.id">
          <v-col class="mx-auto" cols="12" md="11">
            <v-card color="#FAFAFA" class="mb-3 py-4 px-3">
              <h3 class="mb-3">{{ item.part }}</h3>
              <hr class="mb-3">
              <p>인원 : {{item.headcount}} </p>
              <p>담당 업무 : {{item.task}} </p>
              <p>필수 역량 : {{item.ability}} </p>
              <p>우대 사항 : {{item.advantage}} </p>
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
  </v-card>
</template>

<script>
import TeamInput from '@/components/TeamInput'

export default {
  name: 'TeamRegister',
  components: {
    TeamInput,
  },
  data() {
    return{        
      selectRegion: ['서울특별시', '대전광역시', '대구광역시', '부산광역시', '경기도', '인천광역시', '광주광역시', '울산광역시', '세종특별시', '강원도', '경상남도', '경상북도', '전라남도', '전라북도', '충청남도', '충청북도', '제주도'],
      applyData: {
        region: null,
        introduce: null,
        dataList: [],
      },
    }
  },
  methods: {
    addApply(Data){
      this.applyData.dataList = [...this.applyData.dataList, Data]
    },
    apply(){
      console.log(this.applyData)
      this.$emit('submit-apply-data', this.applyData)
    }
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
  .itemLi{
    list-style: none;
  }
</style>