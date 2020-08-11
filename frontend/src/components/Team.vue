<template>
  <div>
    <!-- {{ club.no }} -->
    <!-- {{ getTeamList }} -->
    {{ windowWidth }}
    <div class="container" v-if="windowWidth >= 900">
      <div class="ma-2">
        <v-row v-if="email">
          <router-link class="TeamButton" style="color:white" :to="{name: 'TeamRegister', params:{no:club.no}}">
          <v-icon medium color="#FFFFFF" class="ml-auto">mdi-pencil-box-multiple</v-icon> 팀원 모집하러 가기 </router-link>
        </v-row>
      </div>

      <!-- 웹 페이지 클 때-->
      <div>
        <v-row v-if="windowWidth >= 1270">
          <v-col cols="6" v-for="i in getTeamList" :key="i">
            <!-- {{ i }} -->
            <div class="teamCard py-5 px-5">
              <div class="d-flex">
                <div>
                  <span class="local">{{ i.local }}</span>
                  <h4 class="my-3">{{ i.description }}</h4>


                  <div class="text-center">
                    <v-dialog v-model="dialog" width="500">
                      <template v-slot:activator="{ on, attrs }">
                        <v-btn v-bind="attrs" v-on="on" class="white--text" color="#5C6BC0" @click="emailSave(i)">
                          상세보기
                        </v-btn>
                      </template>

                      <!-- {{ i.datalist }} -->
                      <v-card>
                        <li v-for="item in i.dataList" :key="item">
                          <div class="headline mb-2 white--text partTitle" >
                          <!-- {{ item.part }} -->
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
                          <v-row justify="center">
                            <v-btn class="mb-3 local" color="primary" dark @click.stop="dialog2 = true"  v-if="email" @click="applyLeader(item); apply(sendData);">
                              지원하기
                            </v-btn>

                            <v-dialog v-model="dialog2" max-width="450">
                              <v-card>
                                <v-card-title><h3>팀장에게 참여의사를 전송하였습니다.</h3> </v-card-title>
                                <v-card-actions>
                                  <v-spacer></v-spacer>
                                  <v-btn color="green darken-1" text @click="dialog2 = false">
                                    닫기
                                  </v-btn>
                                </v-card-actions>
                              </v-card>
                            </v-dialog>
                          </v-row>
                        </li>

                        <v-divider></v-divider>

                        <v-card-actions>
                          <v-spacer></v-spacer>
                          <v-btn class="local" color="primary" text @click="dialog = false">
                            닫기
                          </v-btn>
                        </v-card-actions>
                      </v-card>
                    </v-dialog>
                  </div>
                </div>
              </div>
            </div>      
          </v-col>
        </v-row>
      </div>

      <!-- 900이상 1270미만 -->
      <v-row v-if="windowWidth < 1270 && windowWidth >= 900 ">
        <v-col cols="6" class="px-6" v-for="i in getTeamList" :key="i">
          <div class="teamCard2 py-5 px-5">
            <div class="d-flex">
              <div>
                <h6><span class="local">{{ i.local }}</span></h6>
                <h4 class="mt-6 my-3 Black">{{ i.description }}</h4>
                <hr class="hrr my-4">
                <div class="text-center">
                  <v-dialog v-model="dialog" width="500">
                    <template v-slot:activator="{ on, attrs }">
                      <v-btn v-bind="attrs" v-on="on" class="white--text mx-auto" color="#5C6BC0" @click="emailSave(i)">
                        상세보기
                      </v-btn>
                      <v-btn v-if="i.email == email" class="ml-3 white--text" color="red" @click="deleteSave(i); deleteTeam(deleteData);">삭제하기</v-btn>
                    </template>

                    <!-- {{ i.datalist }} -->
                    <v-card>
                      <li v-for="item in i.dataList" :key="item">
                        <div class="headline mb-2 white--text partTitle" >
                        {{ item.part }}
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
                        <v-row justify="center">
                          <v-btn class="mb-3 local" color="primary" dark @click.stop="dialog2 = true" @click="applyLeader(item); apply(sendData);">
                            지원하기
                          </v-btn>

                          <v-dialog v-model="dialog2" max-width="450">
                            <v-card>
                              <v-card-title><h3>팀장에게 참여의사를 전송하였습니다.</h3> </v-card-title>
                              <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn color="green darken-1" text @click="dialog2 = false">
                                  닫기
                                </v-btn>
                              </v-card-actions>
                            </v-card>
                          </v-dialog>
                        </v-row>
                      </li>

                      <v-divider></v-divider>

                      <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn class="local" color="primary" text @click="dialog = false">
                          닫기
                        </v-btn>
                      </v-card-actions>
                    </v-card>
                  </v-dialog>
                </div>
              </div>
            </div>
          </div>    
        </v-col>
      </v-row>

      <!-- 900이상 1270미만 -->
      <v-row v-if="windowWidth < 1270 && windowWidth >= 900 ">
        <v-col cols="6" class="px-6" v-for="i in getTeamList" :key="i">
          <div class="teamCard2 py-5 px-5">
            <div>
              <h6><span class="local">{{ i.local }}</span></h6>
              <h5 class="mt-6 my-3 Black">{{ i.description }}</h5>
              <hr class="hrr my-4">
              <div class="text-center">
                <v-dialog v-model="dialog" width="500">
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn v-bind="attrs" v-on="on" class="white--text mx-auto" color="#5C6BC0" @click="emailSave(i)">
                      상세보기
                    </v-btn>
                    <v-btn v-if="i.email == email" class="ml-3 white--text" color="red" @click="deleteSave(i); deleteTeam(deleteData);">삭제하기</v-btn>
                  </template>

                  <!-- {{ i.datalist }} -->
                  <v-card>
                    <li v-for="item in i.dataList" :key="item">
                      <div class="headline mb-2 white--text partTitle" >
                      {{ item.part }}
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
                      <v-row justify="center">
                        <v-btn class="mb-3 local" color="primary" dark @click.stop="dialog2 = true" @click="applyLeader(item); apply(sendData);">
                          지원하기
                        </v-btn>

                        <v-dialog v-model="dialog2" max-width="450">
                          <v-card>
                            <v-card-title><h3>팀장에게 참여의사를 전송하였습니다.</h3> </v-card-title>
                            <v-card-actions>
                              <v-spacer></v-spacer>
                              <v-btn color="green darken-1" text @click="dialog2 = false">
                                닫기
                              </v-btn>
                            </v-card-actions>
                          </v-card>
                        </v-dialog>
                      </v-row>
                    </li>

                    <v-divider></v-divider>

                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn class="local" color="primary" text @click="dialog = false">
                        닫기
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </div>
            </div>
          </div>        
        </v-col>
      </v-row>
    </div>

    <!-- 900보다 작을때 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! --> 
    <div v-if="windowWidth < 900">
      <div class="ma-2">
        <v-row v-if="email">
          <router-link class="TeamButton" style="color:white" :to="{name: 'TeamRegister', params:{no:club.no}}">
          <v-icon  color="#FFFFFF" class="ml-auto">mdi-pencil-box-multiple</v-icon> 팀원 모집하러 가기 </router-link>
        </v-row>
      </div>

      <!-- 700이상 900미만 -->
      <v-row v-if="windowWidth < 900 && windowWidth >= 700 ">
        <v-col cols="6" class="px-6" v-for="i in getTeamList" :key="i">
          <div class="teamCard3 py-5 px-5">
            <div>
              <h6><span class="local">{{ i.local }}</span></h6>
              <h4 class="mt-6 my-3 Black">{{ i.description }}</h4>
              <hr class="hrr my-4">
              <div class="text-center">
                <v-dialog v-model="dialog" width="500">
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn v-bind="attrs" v-on="on" class="white--text mx-auto" color="#5C6BC0" @click="emailSave(i)">
                      상세보기
                    </v-btn>
                    <v-btn v-if="i.email == email" class="ml-3 white--text" color="red" @click="deleteSave(i); deleteTeam(deleteData);">삭제하기</v-btn>
                  </template>

                  <!-- {{ i.datalist }} -->
                  <v-card>
                    <li v-for="item in i.dataList" :key="item">
                      <div class="headline mb-2 white--text partTitle" >
                      {{ item.part }}
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
                      <v-row justify="center">
                        <v-btn class="mb-3 local" color="primary" dark @click.stop="dialog2 = true" @click="applyLeader(item); apply(sendData);">
                          지원하기
                        </v-btn>

                        <v-dialog v-model="dialog2" max-width="450">
                          <v-card>
                            <v-card-title><h3>팀장에게 참여의사를 전송하였습니다.</h3> </v-card-title>
                            <v-card-actions>
                              <v-spacer></v-spacer>
                              <v-btn color="green darken-1" text @click="dialog2 = false">
                                닫기
                              </v-btn>
                            </v-card-actions>
                          </v-card>
                        </v-dialog>
                      </v-row>
                    </li>

                    <v-divider></v-divider>

                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn class="local" color="primary" text @click="dialog = false">
                        닫기
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </div>
            </div>
          </div>        
        </v-col>
      </v-row>

      <!-- 550이상 700미만 -->
      <v-row v-if="windowWidth < 700 && windowWidth >= 550 ">
        <v-col cols="6" class="px-6" v-for="i in getTeamList" :key="i">
          <div class="teamCard4 py-5 px-5">
            <div>
              <h6><span class="local">{{ i.local }}</span></h6>
              <h5 class="mt-6 my-3 Black">{{ i.description }}</h5>
              <hr class="hrr my-4">
              <div class="text-center">
                <v-dialog v-model="dialog" width="500">
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn v-bind="attrs" v-on="on" class="white--text mx-auto" color="#5C6BC0" @click="emailSave(i)">
                      상세보기
                    </v-btn>
                    <v-btn v-if="i.email == email" class="ml-3 white--text" color="red" @click="deleteSave(i); deleteTeam(deleteData);">삭제하기</v-btn>
                  </template>

                  <v-card>
                    <li v-for="item in i.dataList" :key="item">
                      <div class="headline mb-2 white--text partTitle" >
                      {{ item.part }}
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
                      <v-row justify="center">
                        <v-btn class="mb-3 local" color="primary" dark @click.stop="dialog2 = true" @click="applyLeader(item); apply(sendData);">
                          지원하기
                        </v-btn>

                        <v-dialog v-model="dialog2" max-width="450">
                          <v-card>
                            <v-card-title><h3>팀장에게 참여의사를 전송하였습니다.</h3> </v-card-title>
                            <v-card-actions>
                              <v-spacer></v-spacer>
                              <v-btn color="green darken-1" text @click="dialog2 = false">
                                닫기
                              </v-btn>
                            </v-card-actions>
                          </v-card>
                        </v-dialog>
                      </v-row>
                    </li>

                    <v-divider></v-divider>

                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn class="local" color="primary" text @click="dialog = false">
                        닫기
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </div>
            </div>
          </div>        
        </v-col>
      </v-row>

      <!-- 380이상 550미만 -->
      <v-row v-if="windowWidth < 550 && windowWidth > 380">
        <v-col cols="8" class="px-6" v-for="i in getTeamList" :key="i">
          <div class="teamCard5 py-5 px-5">
            <div>
              <h6><span class="local">{{ i.local }}</span></h6>
              <h4 class="mt-6 my-3 Black">{{ i.description }}</h4>
              <hr class="hrr my-4">
              <div class="text-center">
                <v-dialog v-model="dialog" width="500">
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn v-bind="attrs" v-on="on" class="white--text mx-auto" color="#5C6BC0" @click="emailSave(i)">
                      상세보기
                    </v-btn>
                    <v-btn v-if="i.email == email" class="ml-3 white--text" color="red" @click="deleteSave(i); deleteTeam(deleteData);">삭제하기</v-btn>
                  </template>

                  <!-- {{ i.datalist }} -->
                  <v-card>
                    <li v-for="item in i.dataList" :key="item">
                      <div class="headline mb-2 white--text partTitle" >
                      {{ item.part }}
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
                      <v-row justify="center">
                        <v-btn class="mb-3 local" color="primary" dark @click.stop="dialog2 = true" @click="applyLeader(item); apply(sendData);">
                          지원하기
                        </v-btn>

                        <v-dialog v-model="dialog2" max-width="450">
                          <v-card>
                            <v-card-title><h3>팀장에게 참여의사를 전송하였습니다.</h3> </v-card-title>
                            <v-card-actions>
                              <v-spacer></v-spacer>
                              <v-btn color="green darken-1" text @click="dialog2 = false">
                                닫기
                              </v-btn>
                            </v-card-actions>
                          </v-card>
                        </v-dialog>
                      </v-row>
                    </li>

                    <v-divider></v-divider>

                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn class="local" color="primary" text @click="dialog = false">
                        닫기
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </div>
            </div>
          </div>        
        </v-col>
      </v-row>

      <!-- 380미만 -->
      <v-row v-if="windowWidth <= 380">
        <v-col cols="8" v-for="i in getTeamList" :key="i">
          <div class="teamCard6 py-5 px-5">
            <div>
              <h6><span class="local">{{ i.local }}</span></h6>
              <h5 class="mt-6 my-3 Black">{{ i.description }}</h5>
              <hr class="hrr my-4">
              <div class="text-center">
                <v-dialog v-model="dialog" width="500">
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn v-bind="attrs" v-on="on" class="white--text mx-auto" color="#5C6BC0" @click="emailSave(i)">
                      상세보기
                    </v-btn>
                    <v-btn v-if="i.email == email" class="ml-3 white--text" color="red" @click="deleteSave(i); deleteTeam(deleteData);">삭제하기</v-btn>
                  </template>

                  <!-- {{ i.datalist }} -->
                  <v-card>
                    <li v-for="item in i.dataList" :key="item">
                      <div class="headline mb-2 white--text partTitle" >
                      {{ item.part }}
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
                      <v-row justify="center">
                        <v-btn class="mb-3 local" color="primary" dark @click.stop="dialog2 = true" @click="applyLeader(item); apply(sendData);">
                          지원하기
                        </v-btn>

                        <v-dialog v-model="dialog2" max-width="450">
                          <v-card>
                            <v-card-title><h3>팀장에게 참여의사를 전송하였습니다.</h3> </v-card-title>
                            <v-card-actions>
                              <v-spacer></v-spacer>
                              <v-btn color="green darken-1" text @click="dialog2 = false">
                                닫기
                              </v-btn>
                            </v-card-actions>
                          </v-card>
                        </v-dialog>
                      </v-row>
                    </li>

                    <v-divider></v-divider>

                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn class="local" color="primary" text @click="dialog = false">
                        닫기
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </div>
            </div>
          </div>      
        </v-col>
      </v-row>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'
// import IntroVue from './Intro.vue';

export default {
  name: 'Team',
  props: {
    club: Object,
    getTeamList: Object,
  },
  components: {
  },
  methods: {
    ...mapActions(['apply', 'deleteTeam']),
    gotoTeam() {
      this.$router.push('/teamregister')
    },
		submitProfile(){
			alert('팀장에게 참여의사를 전달하였습니다.');
    },
    onResize() {
      this.windowWidth = window.innerWidth
    },
    applyLeader(item){
      this.sendData.part = item.part
      this.sendData.email = this.$store.state.email
    },
    emailSave(i){
      this.sendData.leaderemail = i.email
      this.sendData.no = i.no
    }, 
    deleteSave(i){
      this.deleteData.no = i.no
      this.deleteData.leaderemail = i.email
    }
  },
  data() {
    return {
      applyData: '',
      buttonvalue: '',
      dialog: false,
      dialog2: false,
      windowWidth: window.innerWidth,
      sendData: {
        no: '',
        leaderemail: '',
        email: '',
        part: '',
      },
      deleteData: {
        no: '',
        leaderemail: '',
      }
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
  computed : {
    ...mapState(['email']),
  },
  mounted () {
      this.$nextTick(() => {
      window.addEventListener('resize', this.onResize);
    })
	},
}
</script>
<style scoped>
  .teamCard {
    border: 2px solid rgb(92, 107, 192);
    width: 330px;
  }
  .teamCard2 {
    border: 2px solid rgb(92, 107, 192);
    width: 265px;
  }
  .teamCard3 {
    border: 2px solid rgb(92, 107, 192);
    width: 330px;
  }
  .teamCard4 {
    border: 2px solid rgb(92, 107, 192);
    width: 260px;
  }
  .teamCard5 {
    border: 2px solid rgb(92, 107, 192);
    width: 300px;
  }
  .teamCard6 {
    border: 2px solid rgb(92, 107, 192);
    width: 280px;
  }
  .localDiv {
    background-color: rgb(92, 107, 192);
    color: white;
    padding: 0.3rem;
    border-radius: 0.5rem;
  }
  .local {
    color: rgb(92, 107, 192);
    font-weight: bold;
    
  }
  .TeamButton {
    text-decoration: none;
    background-color: rgb(92, 107, 192);
    padding: 0.5rem 1rem;
    border-radius: 0.5rem;
    font-size: 0.8rem;
    font-weight: bold;
  }
  li {
    list-style: none;
  }
  .partTitle {
    background-color: rgb(92, 107, 192);
    padding: 0.8rem;
    font-weight: bold;
  }
  .Black{
    color: black;
    text-align: center;
  }
</style>