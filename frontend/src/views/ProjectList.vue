<template>
  <div>
		<h1 v-if="windowWidth > 800" class="my-3 ml-3">프로젝트 목록</h1>
    <h3 v-if="windowWidth < 800" class="my-3 ml-3">프로젝트 목록</h3>
    <div class="d-flex">
      <v-btn color="primary" class="ml-auto mb-3"  @click="goTeam"><v-icon  color="#FFFFFF" class="mr-2">mdi-pencil-box-multiple</v-icon>프로젝트 등록</v-btn>
    </div>

    <v-row no-gutters>
      <li v-for="(i, index) in clubs2" :key="index" class="mx-auto">

        <v-col v-for="n in 1" :key="n" cols="sm"> 
          <div
            class="mx-auto projectCard"
            max-width="500">
            <div class="cardBar"> </div>
            <v-card-title>
              <h4 class="mx-auto mt-3" v-if="i.title.length >= 15">{{ i.title.slice(0,8)}}...</h4>
              <h4 class="mx-auto mt-3" v-else>{{ i.title }}</h4>
            </v-card-title>
            <hr class="hrr">
            <v-card-text class="text--primary mb-3">{{ i.start }} ~ {{ i.end }}
               <v-card-actions>
                  <v-btn color="orange" text class="ml-auto" @click="projectDetail(i); getTeamData(i.no)">
                    자세히보기
                  </v-btn>  
                </v-card-actions>
            </v-card-text>
          </div>
        </v-col>
      </li>
    </v-row>
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
    ...mapState(['clubs2', 'getTeamList', 'email']),
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

<style scoped>
  .projectCard{
    border: 2px solid rgb(92, 107, 192);
    width: 320px;
    height: 200px;
    text-align: center;
    border-top: thick solid rgb(92, 107, 192);
  }
  .title{
    color: rgb(92, 107, 192);
    font-weight: bold;
  }
  .hrr{
    width: 200px;
    margin: 0 auto;
  }
  h4 {
    color: rgb(92, 107, 192);
    font-weight: bold;
  }
  li {
    list-style: none;
  }

</style>