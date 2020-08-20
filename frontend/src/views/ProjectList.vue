<template>
  <div>
    <div class="d-flex">
      <v-btn color="primary" class="ml-auto mb-3 mr-2"  @click="goTeam"><v-icon  color="#FFFFFF" >mdi-pencil-box-multiple</v-icon>프로젝트 등록</v-btn>
    </div>

    <v-row no-gutters>
      <li v-for="(i,index) in clubs2" :key="index" class="mx-auto">
        <v-col v-for="(n,index) in 1" :key="index" cols="sm"> 
          <div
            class="mx-auto projectCard cursor"
            max-width="500"
            v-on:click="projectDetail(i)">
            <div class="cardBar"> </div>
            <v-card-title>
              <h4 class="mx-auto mt-3" v-if="i.title.length >= 15">{{ i.title.slice(0,8)}}...</h4>
              <h4 class="mx-auto mt-3" v-else>{{ i.title }}</h4>
            </v-card-title>
            <hr class="hrr">
            <v-card-text class="text--primary mb-3"><h5>{{ i.start }} ~ {{ i.end }}</h5>
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
    window.scrollTo(0, 0)
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
    height: 5px;
  }
  h4 {
    font-family: 'Do Hyeon', sans-serif;
    color: rgb(92, 107, 192);
  }
  h5 {
    font-family: 'Do Hyeon', sans-serif;
    color: black;
  }
  li {
    list-style: none;
  }
  .cursor {
    cursor: pointer;
    font-weight: bold;
  }

</style>