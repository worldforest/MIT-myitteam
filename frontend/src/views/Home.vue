<template>
  <div>
    <br> Window width: {{ windowWidth }} <br/>
    {{ txt }}
    <!-- 웹 환경: 400px보다 화면이 더 넓을 때 -->
    <div>
      <!-- Search Bar -->
      <v-row class="d-flex justify-center"> 
        <v-col sm="4" >
          <v-text-field
            v-model="search"
            append-icon="mdi-magnify"
            label="Search"
            single-line="true"
            hide-details              
          ></v-text-field>
        </v-col>
      </v-row>
    </div>
    
    <div>
      <!-- 공모전 -->
      <h1>공모전</h1>
      <!-- 화면이 클 때 -->
      <div v-if="windowWidth >= 1064">
        <v-row>
          <!-- 캐로셀 -->
          <v-col cols="12">
            <div class="animated fadeIn">
              <b-card-group  deck class="d-flex justify-center">
                <b-card v-for="(club,index) in currentPageClubs" :key="index"
                        :img-src="club.imagesrc"
                        img-alt="Img"
                        img-top
                        :title="club.title"
                        class="mx-2"
                         >
                  <b-card-text>
                    {{ club.dday }}
                  </b-card-text>
                  <!-- 여기에 지원하기 추가할 예정 혹은 공모전 상세보기 -->
                  <template v-slot:footer>
                    <router-link :to="{name: 'GongmoDetail', params:{club:club}}">
                      자세히 보기
                    </router-link>
                  </template>
                </b-card>
              </b-card-group>

              <!-- 인기 공모전 10개로 제한할 예정이니까 페이지네이션 동그라미로 했음 -->
              <div class="card-pagination">
                  <div class="page-index" v-for="i in nbPages" :key="i"  @click="goto(i)" :class={active:currentPage(i)}></div>
              </div>
            </div>
          </v-col>
        </v-row>
      </div>
      <!-- 화면이 작을 때 -->
      <div v-else-if="850 > windowWidth">
        <v-row>
          <!-- 캐로셀 -->
          <v-col cols="12">
            <div class="animated fadeIn">
              <b-card-group  deck class="d-flex justify-space-around card-sm">
                <b-card v-for="(club,index) in currentPageClubs3" :key="index"
                        :img-src="club.imagesrc"
                        img-alt="Img"
                        img-top
                        :title="club.title"
                        class="mx-2" >
                  <b-card-text>

                    {{ club.dday }}
                  </b-card-text>
                  <!-- 여기에 지원하기 추가할 예정 혹은 공모전 상세보기 -->
                  <template v-slot:footer>
                    <router-link :to="{name: 'GongmoDetail', params:{club:club}}">
                      자세히 보기
                    </router-link>
                  </template>
                </b-card>
              </b-card-group>

              <!-- 인기 공모전 10개로 제한할 예정이니까 페이지네이션 동그라미로 했음 -->
              <div class="card-pagination">
                  <div class="page-index" v-for="i in nbPages" :key="i"  @click="goto(i)" :class={active:currentPage(i)}></div>
              </div>
            </div>
          </v-col>
        </v-row>
      </div>
      <!-- 화면이 중간일 때 -->
      <div v-else>
        <v-row>
          <!-- 캐로셀 -->
          <v-col cols="12">
            <div class="animated fadeIn">
              <b-card-group  deck class="d-flex justify-center">
                <b-card v-for="(club,index) in currentPageClubs2" :key="index"
                        :img-src="club.imagesrc"
                        img-alt="Img"
                        img-top
                        :title="club.title"
                        class="mx-2" >
                  <b-card-text>
                    {{ club.dday }}
                  </b-card-text>
                  <!-- 여기에 지원하기 추가할 예정 혹은 공모전 상세보기 -->
                  <template v-slot:footer>
                    <router-link :to="{name: 'GongmoDetail', params:{club:club}}">
                      자세히 보기
                    </router-link>
                  </template>
                </b-card>
              </b-card-group>

              <!-- 인기 공모전 10개로 제한할 예정이니까 페이지네이션 동그라미로 했음 -->
              <div class="card-pagination">
                  <div class="page-index" v-for="i in nbPages" :key="i"  @click="goto(i)" :class={active:currentPage(i)}></div>
              </div>
            </div>
          </v-col>
        </v-row>
      </div>
    </div>
    <div>
      <h1>프로젝트</h1>
      <div v-if="windowWidth >= 1064">
        <v-row>
          <!-- 캐로셀 -->
          <v-col cols="12">
            <div class="animated fadeIn">
              <b-card-group  deck class="d-flex justify-center">
                <b-card v-for="(club,index) in currentPageClubs_pjt" :key="index"
                        :img-src="club.imgUrl"
                        img-alt="Img"
                        img-top
                        :title="club.title"
                        class="mx-2" >
                  <b-card-text>
                    {{ club.dday }}
                  </b-card-text>
                  <!-- 여기에 지원하기 추가할 예정 혹은 공모전 상세보기 -->
                  <template v-slot:footer>
                    <b-btn variant="primary" block>Add</b-btn>
                  </template>
                </b-card>
              </b-card-group>

              <!-- 인기 공모전 10개로 제한할 예정이니까 페이지네이션 동그라미로 했음 -->
              <div class="card-pagination">
                  <div class="page-index" v-for="i in nbPages_pjt" :key="i"  @click="goto_pjt(i)" :class={active:currentPage_pjt(i)}></div>
              </div>
            </div>
          </v-col>
        </v-row>
        <hr class="mt-2">
        <div class="my-2 text-center">
          게시글
        </div>
        <v-row>
          <v-col cols="4"  class="px-6" v-for="i in community" :key="i">
            <div class="bg">
              <div class="d-flex">
                <div>
                  <p>{{ i.title }}</p>
                  <p>{{ i.host }}</p>                  
                  <p>{{ i.dday }}</p>                  
                </div>
                <div class="ml-auto">
                  <img :src="i.imgUrl" alt="" style="width:100px; height:150px;">
                </div>
              </div>
            </div>        
          </v-col>
        </v-row>
      </div>
      <div v-else-if="850 > windowWidth">
        <v-row >
          <!-- 캐로셀 -->
          <v-col cols="12" class="bg">
            <div class="animated fadeIn">
              <b-card-group  deck class="d-flex justify-space-around card-sm">
                <b-card v-for="(club,index) in currentPageClubs3_pjt" :key="index"
                        :img-src="club.imgUrl"
                        img-alt="Img"
                        img-top
                        :title="club.title"
                        class="mx-2" >
                  <b-card-text>

                    {{ club.dday }}
                  </b-card-text>
                  <!-- 여기에 지원하기 추가할 예정 혹은 공모전 상세보기 -->
                  <template v-slot:footer>
                    <b-btn variant="primary" block>Add</b-btn>
                  </template>
                </b-card>
              </b-card-group>

              <!-- 인기 공모전 10개로 제한할 예정이니까 페이지네이션 동그라미로 했음 -->
              <div class="card-pagination">
                  <div class="page-index" v-for="i in nbPages_pjt" :key="i"  @click="goto_pjt(i)" :class={active:currentPage_pjt(i)}></div>
              </div>
            </div>
          </v-col>
        </v-row>
        <hr class="mt-2">
        <div class="my-2 text-center">
          게시글
        </div>
        <v-row>
          <v-col cols="12" class="px-6" v-for="i in community" :key="i">
            <div class="bg">
              <div class="d-flex">
                <div>
                  <p>{{ i.title }}</p>
                  <p>{{ i.host }}</p>                  
                  <p>{{ i.dday }}</p>                  
                </div>
                <div class="ml-auto">
                  <img :src="i.imgUrl" alt="" style="width:100px; height:150px;">
                </div>
              </div>
            </div>        
          </v-col>
        </v-row>
      </div>
      <div v-else>
        <v-row >
          <!-- 캐로셀 -->
          <v-col cols="12">
            <div class="animated fadeIn">
              <b-card-group  deck class="d-flex justify-center">
                <b-card v-for="(club,index) in currentPageClubs2_pjt" :key="index"
                        :img-src="club.imgUrl"
                        img-alt="Img"
                        img-top
                        :title="club.title"
                        class="mx-2" >
                  <b-card-text>
                    {{ club.dday }}
                  </b-card-text>
                  <!-- 여기에 지원하기 추가할 예정 혹은 공모전 상세보기 -->
                  <template v-slot:footer>
                    <b-btn variant="primary" block>Add</b-btn>
                  </template>
                </b-card>
              </b-card-group>

              <!-- 인기 공모전 10개로 제한할 예정이니까 페이지네이션 동그라미로 했음 -->
              <div class="card-pagination">
                  <div class="page-index" v-for="i in nbPages" :key="i"  @click="goto_pjt(i)" :class={active:currentPage_pjt(i)}></div>
              </div>
            </div>
          </v-col>
        </v-row>
        <hr class="mt-2">
        <div class="my-2 text-center">
          게시글
        </div>
        <v-row>
          <v-col cols="6" class="px-6" v-for="i in community" :key="i">
            <div class="bg">
              <div class="d-flex">
                <div>
                  <p>{{ i.title }}</p>
                  <p>{{ i.host }}</p>                  
                  <p>{{ i.dday }}</p>                  
                </div>
                <div class="ml-auto">
                  <img :src="i.imgUrl" alt="" style="width:100px; height:150px;">
                </div>
              </div>
            </div>        
          </v-col>
        </v-row>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'Home',
  components: {

  },
  data() {
    return {
      windowWidth: window.innerWidth,
      txt: '',
      paginatedClubs:[],
      paginatedClubs_pjt:[],
      nbPages:0,
      nbPages_pjt:0,
      // 페이지네이션 한칸에 들어갈 공모전 숫자를 지정해줄 수 있음.
      nbRowPerPage:4,
      RowPerpage_md:3,
      RowPerpage_md_pjt:3,
      RowPerpage_sm:2,
      RowPerpage_sm_pjt:2,
      currentPageIndex:0,
      currentPageIndex_pjt:0,
      // 추후에 우리가 데이터를 받아올 곳
      clubs: [
        {
          title: "공모전1",
          category: 1,
          reward: "100만원",
          host: "싸피",
          dday: "D-3",
          imagesrc: require("@/assets/공모1.png"),
          description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nisl tincidunt eget nullam non. Quis hendrerit dolor magna eget est lorem ipsum dolor sit. Volutpat odio facilisis mauris sit amet massa. Commodo odio aenean sed adipiscing diam donec adipiscing tristique. Mi eget mauris pharetra et. Non tellus orci ac auctor augue. Elit at imperdiet dui accumsan sit. Ornare arcu dui vivamus arcu felis. Egestas integer eget aliquet nibh praesent. In hac habitasse platea dictumst quisque sagittis purus. Pulvinar elementum integer enim neque volutpat ac."
        },
        {
          title: "공모전2",
          category: 1,
          reward: "100만원",
          host: "싸피",
          dday: "D-166",
          imagesrc: require("@/assets/공모2.png"),
          description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nisl tincidunt eget nullam non. Quis hendrerit dolor magna eget est lorem ipsum dolor sit. Volutpat odio facilisis mauris sit amet massa. Commodo odio aenean sed adipiscing diam donec adipiscing tristique. Mi eget mauris pharetra et. Non tellus orci ac auctor augue. Elit at imperdiet dui accumsan sit. Ornare arcu dui vivamus arcu felis. Egestas integer eget aliquet nibh praesent. In hac habitasse platea dictumst quisque sagittis purus. Pulvinar elementum integer enim neque volutpat ac."
        },
        {
          title: "공모전3",
          category: 1,
          reward: "100만원",
          host: "싸피",
          dday: "D-123",
          imagesrc: require("@/assets/공모3.png"),
          description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nisl tincidunt eget nullam non. Quis hendrerit dolor magna eget est lorem ipsum dolor sit. Volutpat odio facilisis mauris sit amet massa. Commodo odio aenean sed adipiscing diam donec adipiscing tristique. Mi eget mauris pharetra et. Non tellus orci ac auctor augue. Elit at imperdiet dui accumsan sit. Ornare arcu dui vivamus arcu felis. Egestas integer eget aliquet nibh praesent. In hac habitasse platea dictumst quisque sagittis purus. Pulvinar elementum integer enim neque volutpat ac."
        },
        {
          title: "공모전4",
          category: 1,
          reward: "100만원",
          host: "싸피",
          dday: "D-45",
          imagesrc: require("@/assets/공모4.png"),
          description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nisl tincidunt eget nullam non. Quis hendrerit dolor magna eget est lorem ipsum dolor sit. Volutpat odio facilisis mauris sit amet massa. Commodo odio aenean sed adipiscing diam donec adipiscing tristique. Mi eget mauris pharetra et. Non tellus orci ac auctor augue. Elit at imperdiet dui accumsan sit. Ornare arcu dui vivamus arcu felis. Egestas integer eget aliquet nibh praesent. In hac habitasse platea dictumst quisque sagittis purus. Pulvinar elementum integer enim neque volutpat ac."
        },
        {
          title: "공모전5",
          category: 1,
          reward: "100만원",
          host: "싸피",
          dday: "D-21",
          imagesrc: require("@/assets/user.png"),
          description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nisl tincidunt eget nullam non. Quis hendrerit dolor magna eget est lorem ipsum dolor sit. Volutpat odio facilisis mauris sit amet massa. Commodo odio aenean sed adipiscing diam donec adipiscing tristique. Mi eget mauris pharetra et. Non tellus orci ac auctor augue. Elit at imperdiet dui accumsan sit. Ornare arcu dui vivamus arcu felis. Egestas integer eget aliquet nibh praesent. In hac habitasse platea dictumst quisque sagittis purus. Pulvinar elementum integer enim neque volutpat ac."
        },
        {
          title: "공모전1",
          category: 1,
          reward: "100만원",
          host: "싸피",
          dday: "D-3",
          imagesrc: require("@/assets/공모1.png"),
          description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nisl tincidunt eget nullam non. Quis hendrerit dolor magna eget est lorem ipsum dolor sit. Volutpat odio facilisis mauris sit amet massa. Commodo odio aenean sed adipiscing diam donec adipiscing tristique. Mi eget mauris pharetra et. Non tellus orci ac auctor augue. Elit at imperdiet dui accumsan sit. Ornare arcu dui vivamus arcu felis. Egestas integer eget aliquet nibh praesent. In hac habitasse platea dictumst quisque sagittis purus. Pulvinar elementum integer enim neque volutpat ac."
        },
      ],
      clubs2: [
        {
          name: "프로젝트1",
          dday: "D-3",
          imgUrl: require("@/assets/프로젝트1.png")
        },
        {
          name: "프로젝트2",
          dday: "D-166",
          imgUrl: require("@/assets/프로젝트2.png")
        },
        {
          name: "프로젝트3",
          dday: "D-123",
          imgUrl: require("@/assets/프로젝트3.png")
        },
        {
          name: "프로젝트4",
          dday: "D-45",
          imgUrl: require("@/assets/프로젝트4.png")
        },
        {
          name: "프로젝트5",
          dday: "D-21",
          imgUrl: require("@/assets/프로젝트5.png")
        },
        {
          name: "프로젝트6",
          dday: "D-3",
          imgUrl: require("@/assets/프로젝트6.png")
        },
      ],
      community: [
        {
          title: '게시글1',
          host: '과학기술정보통신부',
          dday: "D-7",
          imgUrl: require('@/assets/공모1.png')
        },
        {
          title: '게시글2',
          host: '과학기술정보통신부',
          dday: "D-88",
          imgUrl: require('@/assets/user.png')
        }
      ]
      
    }
  },

  // 페이지 크기가 바뀌는 것을 계속해서 주시한다.
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

  methods: {  
    onResize() {
      this.windowWidth = window.innerWidth
    },
    currentPage(i){
        return i-1===this.currentPageIndex;
    },

    currentPage_pjt(i) {
      return i-1 === this.currentPageIndex_pjt;
    },
    
    createPages() {

    let lengthAll = Object.keys(this.clubs).length;

    this.nbPages = 0;
      for (let i = 0; i < lengthAll; i = i + this.nbRowPerPage) {
      this.paginatedClubs[this.nbPages] = this.clubs.slice(
        i,
        i + this.nbRowPerPage
      );
      this.nbPages++;
      }
    },

    createPages_pjt() {

    let lengthAll = Object.keys(this.clubs2).length;

    this.nbPages_pjt = 0;
      for (let i = 0; i < lengthAll; i = i + this.nbRowPerPage) {
      this.paginatedClubs_pjt[this.nbPages_pjt] = this.clubs2.slice(
        i,
        i + this.nbRowPerPage
      );
      this.nbPages_pjt++;
      }
    },

    createPages2() {

    let lengthAll = Object.keys(this.clubs).length;
    this.nbPages = 0;
      for (let i = 0; i < lengthAll; i = i + this.RowPerpage_md) {
      this.paginatedClubs[this.nbPages] = this.clubs.slice(
        i,
        i + this.RowPerpage_md
      );
      this.nbPages++;
      }
    },

    createPages2_pjt() {

    let lengthAll = Object.keys(this.clubs2).length;

    this.nbPages_pjt = 0;
      for (let i = 0; i < lengthAll; i = i + this.RowPerpage_md_pjt) {
      this.paginatedClubs_pjt[this.nbPages_pjt] = this.clubs2.slice(
        i,
        i + this.RowPerpage_md_pjt
      );
      this.nbPages_pjt++;
      }
    },

    createPages3() {

    let lengthAll = Object.keys(this.clubs).length;
    this.nbPages = 0;
      for (let i = 0; i < lengthAll; i = i + this.RowPerpage_sm) {
      this.paginatedClubs[this.nbPages] = this.clubs.slice(
        i,
        i + this.RowPerpage_sm
      );
      this.nbPages++;
      }
    },

    createPages3_pjt() {

    let lengthAll = Object.keys(this.clubs2).length;

    this.nbPages_pjt = 0;
      for (let i = 0; i < lengthAll; i = i + this.RowPerpage_sm_pjt) {
      this.paginatedClubs_pjt[this.nbPages_pjt] = this.clubs2.slice(
        i,
        i + this.RowPerpage_sm_pjt
      );
      this.nbPages_pjt++;
      }
    },

    goto(i){

      this.currentPageIndex=i-1;
    },
    goto_pjt(i) {
      this.currentPageIndex_pjt = i-1;
    },
  },

  computed: {
    formattedClubs() {
          return this.clubs.reduce((c, n, i) => {
            // 여기서도 페이지네이션에 들어갈 숫자를 지정해주어야 한다.
              if (i % 4 === 0) c.push([]);
              c[c.length - 1].push(n);
              return c;
          }, []);
      },
    currentPageClubs(){
      this.createPages();
      return this.paginatedClubs[this.currentPageIndex];
    },
    currentPageClubs_pjt(){
      this.createPages_pjt();
      return this.paginatedClubs_pjt[this.currentPageIndex_pjt];
    },
    currentPageClubs2(){
      this.createPages2();
      return this.paginatedClubs[this.currentPageIndex];
    },
    currentPageClubs2_pjt(){
      this.createPages2_pjt();
      return this.paginatedClubs_pjt[this.currentPageIndex_pjt];
    },
    currentPageClubs3(){
      this.createPages3();
      return this.paginatedClubs[this.currentPageIndex];
    },
    currentPageClubs3_pjt(){
      this.createPages3_pjt();
      return this.paginatedClubs_pjt[this.currentPageIndex_pjt];
    },

  }

}
</script>

<style>
  .card-pagination{
  display:flex;
  align-items: center;
  justify-content: center;
  padding:20px;
  }

  .page-index{
    margin-left:10px;
    width:15px;
    height:15px;
    border-radius:15px;
    background:#007bff
  }

  .active{
    width:20px;
    height:20px;
    border-radius:20px;
  }

  .card-img-top {
    width: 250px;
    height: 350px;
    object-fit: cover;
  }

  .card-sm .card-img-top {
    width: 150px;
    height: 200px;
    object-fit: cover;
  }

  .bg {
    background-color:#c0c0c0;
  }

  
</style>