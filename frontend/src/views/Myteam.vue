<template>
  <div>
    <v-row class="d-flex justify-center">
      <v-col cols='6'  v-for="(info, index) in this.$store.state.myTeamInfo" :key="index">
        <v-card
          class="mx-auto my-12"  
          max-width="480px"   
        >
          <v-card-title>{{ info.title }}</v-card-title>
          <v-card-text>
            <div class="my-4 subtitle-1">
              카테고리:
              <span v-if="info.category === '0'"> 공모전</span>
              <span v-else-if="info.category === '1'"> 프로젝트</span>
            </div>

            <div>{{ info.description }}</div>
          </v-card-text>

          <v-divider class="mx-4"></v-divider>

          <v-card-title>활동 기간</v-card-title>

          <v-card-text>
            <v-chip-group
              active-class="deep-purple accent-4 white--text"
              column
            >
              <v-chip>{{ info.start }}</v-chip> ~
              <v-chip>{{ info.end }}</v-chip>
            </v-chip-group>
          </v-card-text>

          <v-card-actions>
            <router-link class="text-decoration-none mx-auto" :to="{name: 'Myteaminfo', params : {id:info.no}}">
              <v-btn
                color="deep-purple lighten-2"
                text
              >
                자세히 보기
              </v-btn>
            </router-link >
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import { mapState, mapActions, } from 'vuex'

export default {
  name: 'Myteam',
  data () {
    return {
      // email:'',
    }
  },
  methods :{
    ...mapActions(['getTeamInfo']),
  },
  computed : {
    ...mapState(['saveInfo'])
  },
  mounted () {
    this.getTeamInfo()
    this.$store.state.myTeamInfo = JSON.parse(sessionStorage.getItem('myTeam'))
  }
 }
</script>

<style>

</style>