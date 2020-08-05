<template>
  <div>
    <!-- {{ email }} -->
    {{ applyData.no }}

    <br>
    <!-- {{ windowWidth }} -->
    <div v-if="windowWidth >= 730">
      <v-card id="card-apply" class="py-5 px-3 my-8 mx-auto" outlined max-width="900px">
        <h1 class="text-center mb-5 h1-apply">공모전 팀원을 구해보세요 :)</h1>
        <div>
          <h3 class="ml-4">지역 : </h3>
            <v-col class="d-flex mx-auto" cols="12" md="11">
              <v-select
                :items="selectRegion"
                label="지역 선택"
                outlined
                v-model="applyData.local"
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
          <li class="itemLi" v-for="item in this.applyData.datalist" :key="item.id">
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
            <v-btn depressed large class="white--text" color="#5C6BC0" @click="teamregister(applyData)">등록하기</v-btn>
          </div>
        </v-col>
      </v-card>
    </div>

  <!-- winow 크기 730보다 작을 때-->
  <div v-if="windowWidth < 730 && windowWidth >= 400">
    <div id="card-apply" class="py-5 px-3 my-8 cont730" outlined >
      <h2 class="text-center mb-5 h1-apply">공모전 팀원을 구해보세요 :)</h2>
      <div>
        <h3 class="ml-4">지역 : </h3>
          <v-col class="d-flex mx-auto" cols="12" md="11">
            <v-select
              :items="selectRegion"
              label="지역 선택"
              outlined
              v-model="applyData.local"
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
        <li class="itemLi" v-for="item in this.applyData.datalist" :key="item.id">
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
          <v-btn depressed large class="white--text" color="#5C6BC0" @click="teamregister(applyData)">등록하기</v-btn>
        </div>
      </v-col>
    </div>
  </div>

  <!--400보다 작을 때-->
  <div v-if="windowWidth < 400">
    <div id="card-apply" class="py-5 px-3 my-8 cont730" outlined>
      <h3 class="text-center mb-5 h1-apply">공모전 팀원을 구해보세요 :)</h3>
      <div>
        <h3 class="ml-4">지역 : </h3>
          <v-col class="d-flex mx-auto" cols="12" md="11">
            <v-select
              :items="selectRegion"
              label="지역 선택"
              outlined
              v-model="applyData.local"
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
        <li class="itemLi" v-for="item in this.applyData.datalist" :key="item.id">
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
          <v-btn depressed large class="white--text" color="#5C6BC0" @click="teamregister(applyData)">등록하기</v-btn>
        </div>
      </v-col>
    </div>
  </div>

  </div>
</template>

<script>
import TeamInput from '@/components/TeamInput'
// import PartDetail from '@/components/PartDetail'
// import Team from '@/components/Team'

import { mapState, mapActions } from 'vuex'
// import axios from 'axios'

export default {
  name: 'TeamRegister',
  components: {
    TeamInput,
    // PartDetail,
  },
  props: {
    no: String,
  },
  data() {
    return{        
      selectRegion: ['서울특별시', '대전광역시', '대구광역시', '부산광역시', '경기도', '인천광역시', '광주광역시', '울산광역시', '세종특별시', '강원도', '경상남도', '경상북도', '전라남도', '전라북도', '충청남도', '충청북도', '제주도'],
      applyData: {
        // no => 공모전 넘버, email => 팀장 정보, 등록하는 사람 정보
        local : '',
        email: '',
        description: '',
        no: this.no,
        datalist: [],
      },
      show: false,
      windowWidth: window.innerWidth,
    }
  },
  watch: {
    windowWidth(newWidth, oldWidth) {
      this.txt = `it changed to ${newWidth} from ${oldWidth}`;
    }
  },
  beforeDestroy() { 
    window.removeEventListener('resize', this.onResize); 
  },

  methods: {
    ...mapActions(['teamregister']),
    addApply(Data){
      this.applyData.datalist = [...this.applyData.datalist, Data]
    },
    onResize() {
      this.windowWidth = window.innerWidth
    },
  },
  mounted () {
      this.applyData.email = this.$store.state.email, 
      this.$nextTick(() => {
      window.addEventListener('resize', this.onResize);
    })
  },
  computed : {
    ...mapState(['email']),
  }, 

    // apply(){
    //   console.log(this.applyData)
    //   this.$emit('teamData', this.applyData)
    //   console.log(this.applyData.no)
      
    //   axios.post('http://localhost:9999/mit/api/team/contestteam', {
    //     description: this.applyData.description,
    //     email: this.applyData.email,
    //     local: this.applyData.local,
    //     no: this.applyData.no,
    //     dataList: this.applyData.dataList,
    //   }).then(() => {
    //     this.$router.push('/');
    //   })
    //   .catch(() => {
    //     alert('망햇어,,,,');
    //   })
    // },

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

  .cont15 {
    margin: 0 15%;
  }
  
  .cont730 {
    margin: 0 3%;
  }
</style>