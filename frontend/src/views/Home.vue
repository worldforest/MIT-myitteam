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
    <div v-if="windowWidth >= 1064">
      <div>
        <!-- 공모전 -->
        <h1>공모전</h1>
        <v-row>
          <!-- 캐로셀 -->
          <v-col cols="12">
            <div class="animated fadeIn">
              <b-card-group  deck class="d-flex justify-center">
                <b-card v-for="(club,index) in currentPageClubs" :key="index"
                        :img-src="club.imgUrl"
                        img-alt="Img"
                        img-top
                        :title="club.name"
                        class="mx-2" >
                  <b-card-text>
                    {{ club }}
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
                  <div class="page-index" v-for="i in nbPages" :key="i"  @click="goto(i)" :class={active:currentPage(i)}></div>
              </div>
            </div>
          </v-col>
        </v-row>
      </div>      
    </div>
    <div v-else>
      김지훈
    </div>
    
  </div>
</template>

<script>
// import { VueperSlides, VueperSlide } from 'vueperslides'
// import 'vueperslides/dist/vueperslides.css'

export default {
  name: 'Home',
  components: {
    // VueperSlides, VueperSlide,
  },
  data() {
    return {
      windowWidth: window.innerWidth,
      txt: '',
      paginatedClubs:[],
      nbPages:0,
      // 페이지네이션 한칸에 들어갈 공모전 숫자를 지정해줄 수 있음.
      nbRowPerPage:4,
      currentPageIndex:0,
      // 추후에 우리가 데이터를 받아올 곳
      clubs: [
        {
          name: "공모전1",
          dday: "D-3",
          imgUrl: require("@/assets/공모1.png")
        },
        {
          name: "공모전2",
          dday: "D-166",
          imgUrl: require("@/assets/공모2.png")
        },
        {
          name: "공모전3",
          dday: "D-123",
          imgUrl: require("@/assets/공모3.png")
        },
        {
          name: "공모전4",
          dday: "D-45",
          imgUrl: require("@/assets/공모4.png")
        },
        {
          name: "공모전5",
          dday: "D-21",
          imgUrl: require("@/assets/user.png")
        },
        {
          name: "공모전1",
          dday: "D-3",
          imgUrl: require("@/assets/공모1.png")
        },
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
    goto(i){

      this.currentPageIndex=i-1;
    }
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
      console.log(this.paginatedClubs[this.currentPageIndex])
      return this.paginatedClubs[this.currentPageIndex];
    }
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
</style>