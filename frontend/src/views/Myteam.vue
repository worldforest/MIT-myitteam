<template>
  <div>
    {{ myTeamInfo }}
    <v-card v-for="info in this.$store.state.myTeamInfo" :key="info"  
    class="mx-auto"
    max-width="400"
    >
    <v-img
      class="white--text align-end"
      height="200px"
      src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
    >
      <v-card-title>{{ info.title }}</v-card-title>
    </v-img>

    <v-card-subtitle class="pb-0">Number 10</v-card-subtitle>

    <v-card-text class="text--primary">
      <div>Whitehaven Beach</div>

      <div>Whitsunday Island, Whitsunday Islands</div>
    </v-card-text>

    <v-card-actions>
      <router-link :to="{name: 'Myteaminfo', params : {id:info.no, info:info}}">
        Share
      </router-link >

      <v-btn
        color="orange"
        text
      >
        Explore
      </v-btn>
    </v-card-actions>
  </v-card>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from 'vuex'

export default {
  name: 'Myteam',
  data () {
    return {
      // email:'',
    }
  },
  methods :{
    ...mapActions(['getTeamInfo']),
    ...mapMutations(['myTeamInfo'])
  },
  computed : {
    ...mapState(['myTeamInfo', 'saveInfo'])
  },
  mounted () {
    // this.email = this.$store.state.email
    this.getTeamInfo()
    // console.log(this.$store.state)
    // console.log(this.$store.state.myTeamInfo) 
    // this.myTeamInfo()

  }
}
</script>

<style>

</style>