<template>
  <v-card color="#FAFAFA">
    <v-container>
      <div>
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
                v-model="applyData.description"
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
      </div>
    </v-container>
  </v-card>
</template>

<script>
import TeamInput from '@/components/TeamInput'
// import axios from 'axios'


export default {
  name: 'TeamRegister',
  components: {
    TeamInput,
  },
  data() {
    return{        
      selectRegion: ['서울특별시', '대전광역시', '대구광역시', '부산광역시', '경기도', '인천광역시', '광주광역시', '울산광역시', '세종특별시', '강원도', '경상남도', '경상북도', '전라남도', '전라북도', '충청남도', '충청북도', '제주도'],
      applyData: {
        // no => 공모전 넘버, email => 팀장 정보, 등록하는 사람 정보 
        no : '',
        leaderemail : '',
        region: '',
        description: '',
        dataList: [],
      },
      contestSetData: {
        id: Date.now(),
        no : '',
        leaderemail : '',
				region: '',
				description: '',
				dataList: [],
      },
      show: false,
    }
  },
  methods: {
    addApply(Data){
      this.applyData.dataList = [...this.applyData.dataList, Data]
    },
    apply(){
      this.contestSetData.no = this.applyData.no,
      this.contestSetData.email = this.applyData.email,
      this.contestSetData.region = this.applyData.region,
      this.contestSetData.description = this.applyData.description,
      this.contestSetData.dataList = this.applyData.dataList

      this.$emit('submit-apply-data', this.contestSetData)
      console.log(this.contestSetData)
      
      // axios.post('http://localhost:9999/mit/api/content/create/contest', {
      //   "category": 0,
      //   "description": this.applyData.description,
      //   "email": "admin@naver.com",
      //   "end": "내일",
      //   "host": this.applyData.region,
      //   "imagesrc": "",
      //   "nickname": "단짠단짱",
      //   "no": 1,
      //   "reward": "100만원",
      //   "start": "오늘",
      //   "title": "다인팀",
      //   "url": "string",
      //   "views": 0
      // })

      for(var item in this.applyData){
          this.applyData[item] = ''
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
  .itemLi{
    list-style: none;
  }
</style>