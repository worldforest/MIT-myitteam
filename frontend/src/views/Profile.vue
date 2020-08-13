<template>
  <div  class="cont10">
    {{profileData}}
    {{ windowWidth }}
    <v-row v-if="windowWidth >= 1270">
      <v-col col="2" sm="3" class="fg1">
        <div>
          <img  v-if="profileData.src" :src="profileData.src" class="box" style="width:150px; height:150px;">
        </div>
      </v-col>
      
      <v-col col="10" sm="9" class="fg2">
        <div class="ml-5">
          <span> <h3> {{ profileData.nickname }} </h3></span>
        </div>
        <div class="d-flex my-5 ml-5">
          <div class="text-center mr-15">
            <v-dialog v-model="dialog" width="500">
              <template v-slot:activator="{ on, attrs }">
                <span v-bind="attrs" v-on="on" class="cursor" @click="myFollowList(email);">
                  <h3>팔로잉| {{ profileData.followingCnt }}명</h3>
                </span>
              </template>

              <!-- {{ i.datalist }} -->
              <v-card class="cardModal">
                <!-- {{ followList }} -->
                <h3 class="modaltitle mb-3"> 내가 팔로우 한 리스트</h3>
                
                <li v-for="item in followList" :key="item">
                  <router-link :to="{name: 'UserProfile', params:{user:item.email}}" class="followa"><h4 class="ml-3">{{ item.nickname }}</h4></router-link>
                  <hr>
                </li>

                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn class="local" color="primary" text @click="dialog = false">
                    <h5>닫기</h5>
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </div>

          

          <div class="text-center">
            <v-dialog v-model="dialog2" width="500">
              <template v-slot:activator="{ on, attrs }">
                <span v-bind="attrs" v-on="on" class="cursor" @click="myFollowerList(email);">
                  <h3>팔로워| {{ profileData.followerCnt }}명</h3>
                </span>
              </template>

              <!-- {{ i.datalist }} -->
              <v-card class="cardModal">
                <!-- {{ followerList }} -->
                <h3 class="modaltitle mb-3"> 나를 팔로우 하는 리스트</h3>
                
                <li v-for="item in followerList" :key="item">
                  <router-link :to="{name: 'UserProfile', params:{user:item.email}}" class="followa"><h4 class="ml-3">{{ item.nickname }}</h4></router-link>
                  <hr>
                </li>

                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn class="local" color="primary" text @click="dialog2 = false">
                    <h5>닫기</h5>
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </div>
          <!-- <span class="cursor" @click="myFollowList(email);"><h3>팔로우| {{ profileData.followingCnt }}명</h3></span> -->
          <!-- <span class="mx-auto"><h3>팔로워| {{ profileData.followerCnt }}명</h3></span> -->
        </div>
        <div class="d-flex ml-5">
          <span> <h3>{{ profileData.description }} 개발자</h3> </span>
          <br>
          
        </div>
        <div class="d-flex">
          <div class="ml-auto TeamButton cursor" style="color:white" @click="updateProfile(profileData)">
            <img src="@/assets/edit.png" alt="" style="width:10px" class="mr-2" >프로필 수정
          </div>
        </div>
      </v-col>
    </v-row>

    <!--////////////////////// 윈도우 788 이상 //////////////////////-->
    <v-row v-else-if="windowWidth >= 788">
      <v-col col="2" sm="4" class="fg1 mr-6">
        <div class="pf-box">
          <img  v-if="profileData.src" :src="profileData.src" class="box" style="width:150px; height:150px;">
        </div>
      </v-col>
      
      <v-col col="10" sm="8" class="fg2">
        <div class="ml-5">
          <span><h3>{{ profileData.nickname }}</h3></span>
        </div>
        <div class="d-flex my-5 ml-5">
          <div class="text-center mr-15">
            <v-dialog v-model="dialog" width="500">
              <template v-slot:activator="{ on, attrs }">
                <span v-bind="attrs" v-on="on" class="cursor" @click="myFollowList(email);">
                  <h4>팔로잉| {{ profileData.followingCnt }}명</h4>
                </span>
              </template>

              <!-- {{ i.datalist }} -->
              <v-card class="cardModal">
                <!-- {{ followList }} -->
                <h3 class="modaltitle mb-3"> 내가 팔로우 한 리스트</h3>
                
                <li v-for="item in followList" :key="item">
                  <router-link :to="{name: 'UserProfile', params:{user:item.email}}" class="followa"><h4 class="ml-3">{{ item.nickname }}</h4></router-link>
                  <hr>
                </li>

                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn class="local" color="primary" text @click="dialog = false">
                    <h5>닫기</h5>
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </div>

          <div class="text-center">
            <v-dialog v-model="dialog2" width="500">
              <template v-slot:activator="{ on, attrs }">
                <span v-bind="attrs" v-on="on" class="cursor" @click="myFollowerList(email);">
                  <h4>팔로워| {{ profileData.followerCnt }}명</h4>
                </span>
              </template>

              <!-- {{ i.datalist }} -->
              <v-card class="cardModal">
                <!-- {{ followerList }} -->
                <h3 class="modaltitle mb-3"> 나를 팔로우 하는 리스트</h3>
                
                <li v-for="item in followerList" :key="item">
                  <router-link :to="{name: 'UserProfile', params:{user:item.email}}" class="followa"><h4 class="ml-3">{{ item.nickname }}</h4></router-link>
                  <hr>
                </li>

                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn class="local" color="primary" text @click="dialog2 = false">
                    <h5>닫기</h5>
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </div>
        </div>
        <div class="d-flex ml-5">
          <br>
          <span> <h4>{{ profileData.description }} 개발자</h4> </span>
        </div>
        <div class="d-flex">
          <div class="ml-auto TeamButton cursor" style="color:white" @click="updateProfile(profileData)">
            <img src="@/assets/edit.png" alt="" style="width:10px" class="mr-2" >프로필 수정
          </div>
        </div>
      </v-col>
    </v-row>
    
    <v-row v-else>
      <v-col col="2" sm="3" class="fg1 mr-6">
        <div class="pf-box">
          <img  v-if="profileData.src" :src="profileData.src" class="box2" style="width:77px; height:77px;">
        </div>
      </v-col>
      
      <v-col col="10" sm="9" class="fg2">
        <div class="ml-5">
          <h4 class="ml-5">{{ profileData.nickname }}</h4>
          <span>{{ profileData.description }}개발자</span>
        </div>
        <div class="d-flex">
          <div class="ml-auto TeamButton2 cursor mt-2" style="color:white" @click="updateProfile(profileData)">
            <img src="@/assets/edit.png" alt="" style="width:10px" class="mr-2" >프로필 수정
          </div>
        </div>
      </v-col>
    </v-row>
    <hr v-if="windowWidth < 788">
    <v-row v-if="windowWidth < 788">
      <v-col cols="6">
        <div class="text-center">
          <v-dialog v-model="dialog" width="500">
            <template v-slot:activator="{ on, attrs }">
              <span v-bind="attrs" v-on="on" class="cursor" @click="myFollowList(email);">
                <h5>팔로잉| {{ profileData.followingCnt }}명</h5>
              </span>
            </template>

            <!-- {{ i.datalist }} -->
            <v-card class="cardModal">
              <!-- {{ followList }} -->
              <h3 class="modaltitle mb-3"> 내가 팔로우 한 리스트</h3>
              
              <li v-for="item in followList" :key="item">
                <router-link :to="{name: 'UserProfile', params:{user:item.email}}" class="followa"><h4 class="ml-3">{{ item.nickname }}</h4></router-link>
                <hr>
              </li>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn class="local" color="primary" text @click="dialog = false">
                  <h5>닫기</h5>
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </div>
      </v-col>

      <v-col cols="6">
        <div class="text-center">
            <v-dialog v-model="dialog2" width="500">
              <template v-slot:activator="{ on, attrs }">
                <span v-bind="attrs" v-on="on" class="cursor" @click="myFollowerList(email);">
                  <h5>팔로워| {{ profileData.followerCnt }}명</h5>
                </span>
              </template>

              <!-- {{ i.datalist }} -->
              <v-card class="cardModal">
                <!-- {{ followerList }} -->
                <h3 class="modaltitle mb-3"> 나를 팔로우 하는 리스트</h3>
                
                <li v-for="item in followerList" :key="item">
                  <router-link :to="{name: 'UserProfile', params:{user:item.email}}" class="followa"><h4 class="ml-3">{{ item.nickname }}</h4></router-link>
                  <hr>
                </li>

                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn class="local" color="primary" text @click="dialog2 = false">
                    <h5>닫기</h5>
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </div>
      </v-col>
    </v-row>

    <hr class="mb-2">
    <div class="text-center">
      <h3>피드</h3>
    </div>
    <router-link class="feed white--text mb-3" to="/feedcreate">
      피드등록
    </router-link>
    <v-row class="mt-4" v-if="windowWidth > 788">
      <v-col cols="4" v-for="feed in profileData.feeds" :key="feed.no">
        <div class="mx-2 detail_hover">         
          <img :src="feed.src" 
          alt="" 
          style="width:100%;" 
          :feed="feed" 
          @click="feedDetail(feed)" >
        </div>
      </v-col>
    </v-row>
    <v-row v-else class="bg-gray">
      <v-col cols='12'  v-for="feed in profileData.feeds" :key="feed.no">
        <v-card
          :loading="loading"
          class="mx-auto my-3"
        >
          <v-img
            height="300"
            :src="feed.src"
            @click="feedDetail(feed)"
          ></v-img>

          <v-card-text @click="feedDetail(feed)">
            <v-row
              align="center"
              class="mx-0"
            >
            </v-row>

            <div class="my-4 subtitle-1">
              {{feed.description}}
            </div>
          </v-card-text>

          <v-card-text>
            <v-chip-group
              v-model="selection"
              active-class="deep-purple accent-4 white--text"
              column
            >
              <v-chip v-for="(tag,i) in feed.tag" :key="i" @click="searchTagFeed(tag)">
                #{{tag}}
              </v-chip>
            </v-chip-group>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>


  </div>

</template>

<script>
import { mapState, mapActions, mapGetters, mapMutations } from 'vuex'

export default {
  name: 'Profile',
  components: {

  },
  data() {
    return {
      windowWidth: window.innerWidth,
      email: this.$store.state.email,
      dialog: false,
      dialog2: false,
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
    onChange(e) {
      const file = e.target.files[0];
      this.profileData.src = URL.createObjectURL(file)
    },

    onResize() {
      this.windowWidth = window.innerWidth
    },

    ...mapMutations(['feedDetail', 'updateProfile']),
    ...mapActions(['profile', 'myFollowList', 'myFollowerList','searchTagFeed'])
  },
  computed : {
    // ...mapGetter s(['isLoggedIn'])
    ...mapState(['profileData', 'email', 'followList', 'followerList']),
    ...mapGetters(['isLoggedIn',])
  },
  mounted() {
    this.profile()
    this.$nextTick(() => {
      window.addEventListener('resize', this.onResize);
    })
  }

}
</script>

<style scoped>
  @font-face {
    font-family: myFont;
    src: url("/src/font/BMJUA_ttf.ttf");
  }

  .h1 {
    font-size: 30px;
  }

  .filebox label 
    { display: inline-block;
      padding: .5em .75em;
      color: #999; font-size: inherit;
      line-height: normal; 
      vertical-align: middle; 
      background-color: #fdfdfd; 
      cursor: pointer; 
      border: 1px solid #ebebeb; 
      border-bottom-color: #e2e2e2; 
      border-radius: .25em; } 
      
  .filebox input[type="file"] {
    position: absolute; 
    width: 1px; 
    height: 1px; 
    padding: 0; 
    margin: -1px; 
    overflow: hidden; 
    clip:rect(0,0,0,0); 
    border: 0; }

  .detail_hover:hover {
    opacity: 0.5;
  }

  .cont10 {
    margin: 0 10%;
  }

  .box{
    width: 150px;
    height: 150px; 
    border-radius: 70%;
    overflow: hidden;
  }

  .fg1 {
    flex-grow: 1;
    flex-basis: 0;
  }

  .fg2 {
    flex-grow: 2;
    flex-basis: 30px;
  }

  .box2 {
    width: 77px;
    height: 77px; 
    border-radius: 70%;
    overflow: hidden;
  }

  .feed {
    background-color: rgb(92, 107, 192);
    padding: 0.8rem;
    font-weight: bold;
    text-decoration: none;
    border-radius: 0.5rem;
  }

  .cursor {
    cursor: pointer;
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

  .TeamButton2 {
    text-decoration: none;
    background-color: rgb(92, 107, 192);
    padding: 0.5rem 0.5rem;
    border-radius: 0.5rem;
    font-size: 0.5rem;
    font-weight: bold;
  }

  .bg-gray {
    background-color: #e9e9e9;
  }

  .cardModal{
    font-family: myFont, sans-serif;
    width: 300px;
  }
  .modaltitle{
    padding: 0.7rem;
    background-color: rgb(92, 107, 192);
    color: white;
  }
  li{
    list-style: none;
  }
  .followa{
    text-decoration: none;
    color:black;
  }
</style>
