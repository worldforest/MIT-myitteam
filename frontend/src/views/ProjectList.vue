<template>
  <div>
		<h1 class="my-3 ml-3">프로젝트 목록</h1>
    <div class="d-flex">
      <v-btn color="primary" class="ml-auto mb-3"  @click="goTeam"><v-icon  color="#FFFFFF" class="mr-2">mdi-pencil-box-multiple</v-icon>프로젝트 등록하러 가기</v-btn>
    </div>

    <v-row no-gutters>
      <li v-for="pjt in clubs2" :key="pjt" class="mx-auto">
        <!-- {{ pjt }} -->
        <v-col v-for="n in 1" :key="n" cols="sm"> 
          <button @click="projectDetail(pjt); getTeamData(pjt.no)">
            <v-card class="mx-auto projectCard" max-width="300">
              <v-card-title><h3 class="mx-auto my-auto title">{{ pjt.title }}</h3></v-card-title>
              <hr class="hrr">
              <v-card-text> {{ pjt.start }} ~ {{ pjt.end }} </v-card-text>
            </v-card>
          </button>
        </v-col>
      </li>
    </v-row>

  </div>
</template>

<script>
import { mapActions, mapState, mapMutations } from 'vuex'

export default {
	name: 'ProjectList',
	methods: {
    ...mapActions(['getContestData', 'getTeamData']),
    ...mapMutations(['projectDetail', 'goTeam']),
    
  },
  mounted() {
    this.getContestData()
  },
  computed: {
    ...mapState(['clubs2', 'getTeamList']),
  }

}
</script>

<style>
  .projectCard{
    border: 3px solid rgb(92, 107, 192);
    width: 300px;
    height: 150px;
  }
  .title{
    color: rgb(92, 107, 192);
    font-weight: bold;
  }
  .hrr{
    width: 200px;
    margin: auto;
  }
</style>