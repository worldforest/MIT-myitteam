<template>
  <div>
		<h1 class="my-3 ml-3">프로젝트 목록</h1>
    <div class="d-flex">
      <v-btn color="primary" class="ml-auto mb-3"  @click="goTeam">팀 등록</v-btn>
    </div>
    <!-- {{ clubs2 }} -->
    <v-row no-gutters>
      <li v-for="pjt in clubs2" :key="pjt" class="mx-auto">
        <!-- {{ pjt }} -->
        <v-col v-for="n in 1" :key="n" cols="sm"> 
          <button @click="projectDetail(pjt)">
            <v-card class="mx-auto projectCard" max-width="300">
              <v-img
                class="white--text align-end"
                height="200px"
                :src="pjt.imagesrc"
              >
              </v-img>
              <v-card-title><h4>{{ pjt.title }}</h4></v-card-title>
              <v-card-text> {{ pjt.description }} </v-card-text>
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
    ...mapActions(['getContestData']),
    ...mapMutations(['projectDetail', 'goTeam']),
  },
  mounted() {
    this.getContestData()
  },
  computed: {
    ...mapState(['clubs2']),
  }

}
</script>

<style>
  .projectCard{
    border: 3px solid rgb(0, 0, 0 );
  }
</style>