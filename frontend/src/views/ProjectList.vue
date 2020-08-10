<template>
  <div>
		<h1 v-if="windowWidth > 800" class="my-3 ml-3">프로젝트 목록</h1>
    <h3 v-if="windowWidth < 800" class="my-3 ml-3">프로젝트 목록</h3>
    <!-- {{ windowWidth }} -->
    <div class="d-flex">
      <v-btn color="primary" class="ml-auto mb-3"  @click="goTeam"><v-icon  color="#FFFFFF" class="mr-2">mdi-pencil-box-multiple</v-icon>프로젝트 등록하러 가기</v-btn>
    </div>

    <v-row no-gutters>
      <li v-for="i in clubs2" :key="i" class="mx-auto">
        {{ pjt }}
        <v-col v-for="n in 1" :key="n" cols="sm"> 
          <v-card
            class="mx-auto projectCard"
            max-width="500">
            <v-card-title>
              <h4 v-if="i.title.length >= 15">{{ i.title.slice(0,8)}}...</h4>
              <h4 v-else>{{ i.title }}</h4>
            </v-card-title>

            <v-card-text class="text--primary">{{ i.start }} ~ {{ i.end }}
               <v-card-actions>
                  <v-btn
                    color="orange"
                    text
                    class="ml-auto"
                    @click="projectDetail(i); getTeamData(i.no)"
                  >
                    자세히보기
                  </v-btn>
                </v-card-actions>
            </v-card-text>
          </v-card>
        </v-col>
      </li>
    </v-row>

    <!-- <div>
      <v-row >
        <v-col cols="4" v-for="i in clubs2" :key="i.no" >
          
        </v-col>
      </v-row>
    </div> -->

  </div>
</template>

<script>
import { mapActions, mapState, mapMutations } from 'vuex'

export default {
  name: 'ProjectList',
  data() {
    return{
      windowWidth: window.innerWidth,
    }
  },
	methods: {
    ...mapActions(['getContestData', 'getTeamData']),
    ...mapMutations(['projectDetail', 'goTeam']),
    onResize() {
      this.windowWidth = window.innerWidth
    },
  },
  mounted() {
    this.getContestData(),
    this.$nextTick(() => {
      window.addEventListener('resize', this.onResize);
    })
  },
  computed: {
    ...mapState(['clubs2', 'getTeamList']),
  },
  watch: {
    windowWidth(newWidth, oldWidth) {
     this.txt = `it changed to ${newWidth} from ${oldWidth}`;
    }
  },
  beforeDestroy() { 
    window.removeEventListener('resize', this.onResize); 
  },
}
</script>

<style>
  .projectCard{
    border: 3px solid rgb(92, 107, 192);
    width: 350px;
    height: 150px;
    text-align: center;
  }
  .title{
    color: rgb(92, 107, 192);
    font-weight: bold;
  }
  .hrr{
    width: 200px;
  }

</style>