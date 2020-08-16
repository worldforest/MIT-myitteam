<template>
  <div>
    <!-- {{ windowWidth }} -->

    <div class="container" v-if="windowWidth >= 800">
      <h4 class="mt-4">모집 기간 : <span class="local">{{ club2.start }} ~ {{ club2.end }}</span></h4>
      <h4 class="mt-8">프로젝트 소개 : </h4>
      <v-card class="mt-3 px-3 py-3" color="#FAFAFA" style="white-space:pre-line;">{{ club2.description }}</v-card>
    
      <div>
        {{ club2 }}
        {{ sendData }}
        <!-- {{ getTeamList }} -->
        <h4 class="mt-8 mb-4">팀원 모집 : </h4>
        <v-row v-if="windowWidth >= 1270">
          <v-col v-for="i in getTeamList" :key="i">
              <div class="d-flex mx-auto">
                <div>
                  <li v-for="item in i.dataList" :key="item">
                    <div class="pjtCard mx-auto mb-6">
                      <div class="mb-2 white--text partTitle" >
                        <v-row>
                          <h3 class="ml-5 mt-2 cardModal">{{ item.part }}</h3>
                          <v-spacer></v-spacer>
                          <h5 class="mr-5 mt-3 cardModal"> 인원 : {{ item.headCount }}</h5>
                        </v-row>
                      </div>
                      <h3 class="ml-3 mt-3">담당 업무</h3>
                      <v-card-text class="ml-2">
                        <v-list-item-title style="white-space:pre-line;">{{ item.task }}</v-list-item-title>
                      </v-card-text>
                      <hr class="mb-2">
                      <h3 class="ml-3">필수 역량</h3>
                      <v-card-text class="ml-2">
                        <v-list-item-title style="white-space:pre-line;">{{ item.ability }}</v-list-item-title>
                      </v-card-text>
                      <hr class="mb-2">
                      <h3 class="ml-3">우대 사항</h3>
                      <v-card-text class="ml-2">
                        <v-list-item-title style="white-space:pre-line;">{{ item.advantage }}</v-list-item-title>
                      </v-card-text>
                      <v-row v-if="club2.email !== email" justify="center">
                        <v-btn class="mb-3 local" color="primary" dark @click.stop="dialog2 = true" @click="applyLeader(item); emailSave(club2); apply(sendData);">
                          지원하기
                        </v-btn>
                      </v-row>
                    </div>
                  </li>
                </div>
              </div>
              <v-row>
                <v-spacer></v-spacer>
                <v-btn v-if="club2.email === email" color="red" class="white--text" right @click="deletePjt(deletePjtData);">
                  삭제하기
                </v-btn> 
              </v-row>
          </v-col>
        </v-row>

        <v-row v-if="windowWidth < 1270 && windowWidth >= 800">
          <v-col v-for="i in getTeamList" :key="i">
              <div class="d-flex mx-auto">
                <div>
                  <li v-for="item in i.dataList" :key="item">
                    <div class=" pjtCard2 mx-auto mb-6">
                      <div class="headline mb-2 white--text partTitle" >
                        <v-row>
                          <h3 class="ml-5 mt-2 cardModal">{{ item.part }}</h3>
                          <v-spacer></v-spacer>
                          <h5 class="mr-5 mt-3 cardModal"> 인원 : {{ item.headCount }}</h5>
                        </v-row>
                      </div>
                      <h3 class="ml-3">담당 업무</h3>
                      <v-card-text class="ml-2">
                        <v-list-item-title style="white-space:pre-line;">{{ item.task }}</v-list-item-title>
                      </v-card-text>
                      <hr class="mb-2">
                      <h3 class="ml-3">필수 역량</h3>
                      <v-card-text class="ml-2">
                        <v-list-item-title style="white-space:pre-line;">{{ item.ability }}</v-list-item-title>
                      </v-card-text>
                      <hr class="mb-2">
                      <h3 class="ml-3">우대 사항</h3>
                      <v-card-text class="ml-2">
                        <v-list-item-title style="white-space:pre-line;">{{ item.advantage }}</v-list-item-title>
                      </v-card-text>
                      <v-row v-if="club2.email !== email" justify="center">
                        <v-btn class="mb-3 local" color="primary" dark @click.stop="dialog2 = true" @click="applyLeader(item); emailSave(club2); apply(sendData);">
                          지원하기
                        </v-btn>
                      </v-row>
                    </div>
                  </li>
                </div>
              </div>
              <v-row>
                <v-spacer></v-spacer>
                <v-btn v-if="club2.email === email" color="red" class="white--text" right @click="deletePjt(deletePjtData);">
                  삭제하기
                </v-btn> 
              </v-row>
          </v-col>
        </v-row>
      </div>
    </div>

    <!-- 웹 화면 작을 때 !!!!!!!!!!!!!!!!!!!!! -->
    <div v-if="windowWidth < 800">
      <h5 class="mt-4">접수 기간 : <span class="local">{{ club2.start }} ~ {{ club2.end }}</span></h5>
      <h5 class="mt-8">프로젝트 소개 : </h5>
      <v-card class="mt-3 px-3 py-3" color="#FAFAFA" style="white-space:pre-line;">{{ club2.description }}</v-card>
      <h5 class="mt-8 mb-4">팀원 모집 : </h5>
      <v-row class="mx-auto" v-if="windowWidth < 800 && windowWidth >= 600">
        <v-col v-for="i in getTeamList" :key="i">
          <!-- {{ i }} -->
            <div class="d-flex mx-auto">
              <div>
                <li v-for="item in i.dataList" :key="item">
                  <div class="pjtCard3 mx-auto mb-6">
                    <div class="headline mb-2 white--text partTitle" >
                      <v-row>
                        <h3 class="ml-5 mt-2 cardModal">{{ item.part }}</h3>
                        <v-spacer></v-spacer>
                        <h5 class="mr-5 mt-3 cardModal"> 인원 : {{ item.headCount }}</h5>
                      </v-row>
                    </div>
                    <h3 class="ml-3">담당 업무</h3>
                    <v-card-text class="ml-2">
                      <v-list-item-title style="white-space:pre-line;">{{ item.task }}</v-list-item-title>
                    </v-card-text>
                    <hr class="mb-2">
                    <h3 class="ml-3">필수 역량</h3>
                    <v-card-text class="ml-2">
                      <v-list-item-title style="white-space:pre-line;">{{ item.ability }}</v-list-item-title>
                    </v-card-text>
                    <hr class="mb-2">
                    <h3 class="ml-3">우대 사항</h3>
                    <v-card-text class="ml-2">
                      <v-list-item-title style="white-space:pre-line;">{{ item.advantage }}</v-list-item-title>
                    </v-card-text>
                    <v-row v-if="club2.email !== email" justify="center">
                      <v-btn class="mb-3 local" color="primary" dark @click.stop="dialog2 = true" @click="applyLeader(item); emailSave(club2); apply(sendData);">
                        지원하기
                      </v-btn>
                    </v-row>
                  </div>
                </li>
              </div>
            </div>
            <v-row>
              <v-spacer></v-spacer>
              <v-btn v-if="club2.email === email" color="red" class="white--text" right @click="deletePjt(deletePjtData);">
                삭제하기
              </v-btn> 
            </v-row>
        </v-col>
      </v-row>

      <v-row class="mx-auto" v-if="windowWidth < 600 && windowWidth >= 430">
        <v-col v-for="i in getTeamList" :key="i">
          <!-- {{ i }} -->
            <div class="d-flex mx-auto">
              <div>
                <li v-for="item in i.dataList" :key="item">
                  <div class="pjtCard5 mx-auto mb-6">
                    <div class="headline mb-2 white--text partTitle" >
                      <v-row>
                        <h3 class="ml-5 mt-2 cardModal">{{ item.part }}</h3>
                        <v-spacer></v-spacer>
                        <h5 class="mr-5 mt-3 cardModal"> 인원 : {{ item.headCount }}</h5>
                      </v-row>
                    </div>
                    <h3 class="ml-3">담당 업무</h3>
                    <v-card-text class="ml-2">
                      <v-list-item-title style="white-space:pre-line;">{{ item.task }}</v-list-item-title>
                    </v-card-text>
                    <hr class="mb-2">
                    <h3 class="ml-3">필수 역량</h3>
                    <v-card-text class="ml-2">
                      <v-list-item-title style="white-space:pre-line;">{{ item.ability }}</v-list-item-title>
                    </v-card-text>
                    <hr class="mb-2">
                    <h3 class="ml-3">우대 사항</h3>
                    <v-card-text class="ml-2">
                      <v-list-item-title style="white-space:pre-line;">{{ item.advantage }}</v-list-item-title>
                    </v-card-text>
                    <v-row v-if="club2.email !== email" justify="center">
                      <v-btn class="mb-3 local" color="primary" dark @click.stop="dialog2 = true" @click="applyLeader(item); emailSave(club2); apply(sendData);">
                        지원하기
                      </v-btn>
                    </v-row>
                  </div>
                </li>
              </div>
            </div>
            <v-row>
              <v-spacer></v-spacer>
              <v-btn v-if="club2.email === email" color="red" class="white--text" right @click="deletePjt(deletePjtData);">
                삭제하기
              </v-btn> 
            </v-row>     
        </v-col>
      </v-row>

      <v-row v-if="windowWidth < 430">
        <v-col v-for="i in getTeamList" :key="i">
            <div class="d-flex mx-auto">
              <div>
                <li v-for="item in i.dataList" :key="item">
                  <div class="pjtCard4 mx-auto mb-6">
                    <div class="mb-2 white--text partTitle" >
                      <v-row>
                        <h3 class="ml-5 mt-2 cardModal">{{ item.part }}</h3>
                        <v-spacer></v-spacer>
                        <h5 class="mr-5 mt-3 cardModal"> 인원 : {{ item.headCount }}</h5>
                      </v-row>
                    </div>
                    <h4 class="ml-3">담당 업무</h4>
                    <v-card-text class="ml-2">
                      <v-list-item-title style="white-space:pre-line;">{{ item.task }}</v-list-item-title>
                    </v-card-text>
                    <hr class="mb-2">
                    <h4 class="ml-3">필수 역량</h4>
                    <v-card-text class="ml-2">
                      <v-list-item-title style="white-space:pre-line;">{{ item.ability }}</v-list-item-title>
                    </v-card-text>
                    <hr class="mb-2">
                    <h4 class="ml-3">우대 사항</h4>
                    <v-card-text class="ml-2">
                      <v-list-item-title style="white-space:pre-line;">{{ item.advantage }}</v-list-item-title>
                    </v-card-text>
                    <v-row v-if="club2.email !== email" justify="center">
                      <v-btn class="mb-3 local" color="primary" v-if="email" dark @click.stop="dialog2 = true" @click="applyLeader(item); emailSave(club2); apply(sendData);">
                        지원하기
                      </v-btn>
                    </v-row>
                  </div>
                </li>
              </div>
            </div>
            <v-row>
              <v-spacer></v-spacer>
              <v-btn v-if="club2.email === email" color="red" class="white--text" right @click="deletePjt(deletePjtData);">
                삭제하기
              </v-btn> 
            </v-row> 
        </v-col>
      </v-row>
    </div>
  </div>  
</template>

<script>
import { mapState, mapActions } from 'vuex'

export default {
  name: 'Intro',
  props: {
    club2: Object,
    getTeamList: Object,
  },
  data() {
    return {
      windowWidth: window.innerWidth,
      txt: '',
      dialog: false,
      dialog2: false,
      applyData: '',
      sendData: {
        no: '',
        leaderemail: '',
        email: '',
        part: '',
      },
      deletePjtData: {
        no : this.club2.no
      },
    }
  },
  watch: {
    windowWidth(newWidth, oldWidth) {
     this.txt = `it changed to ${newWidth} from ${oldWidth}`;
    }
  },
  mounted() {
    this.$nextTick(() => {
      window.addEventListener('resize', this.onResize);
    })
  },
  beforeDestroy() { 
    window.removeEventListener('resize', this.onResize); 
  },
  computed : {
    ...mapState(['email', ]),
  },
  methods: {
    ...mapActions(['apply', 'deletePjt']),
    onResize() {
      this.windowWidth = window.innerWidth
    },
    applyLeader(item){
      this.sendData.part = item.part
      this.sendData.email = this.$store.state.email
      console.log(this.sendData)
    },
    emailSave(i){
      this.sendData.leaderemail = i.email
      this.sendData.no = i.no
    }
  },
}
</script>

<style scoped>
  @font-face {
    font-family: myFont;
    src: url("/src/font/BMJUA_ttf.ttf");
  }

  .pill {
    position: absolute;
    padding: 0 8px;
    line-height: 20px;
    border-radius: 10px;
    color: #fff;
    font-size: 14px;
    font-weight: bold;
    background-color: #a6a6a6;
  }

  li {
    list-style: none;
  }
  .partTitle {
    background-color: rgb(92, 107, 192);
    padding: 0.6rem;
    font-weight: bold;
  }
  .pjtCard {
    border: 2px solid lightgrey;
    width : 700px;
  }
  .pjtCard2 {
    border: 2px solid lightgrey;
    width: 500px;
  }
  .pjtCard3 {
    border: 2px solid lightgrey;
    width: 400px;
  }
  .pjtCard4 {
    border: 2px solid lightgrey;
    width: 300px;
  }
  .pjtCard5 {
    border: 2px solid lightgrey;
    width: 330px;
  }
  .cardModal{
    font-family: myFont, sans-serif;
  }
</style>