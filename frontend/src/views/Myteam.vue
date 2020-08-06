<template>
  <div>
    <v-card v-for="info in myTeamInfo" :key="info"  
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
      <v-btn
        color="orange"
        text
      >
        Share
      </v-btn>

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
import { mapState, mapActions } from 'vuex'

export default {
  name: 'Myteam',
  data () {
    return {
      // email:'',
    }
  },
  methods :{
    checkDate (date) {
      this.day = date
      console.log(this.day)
      this.day.time
      },
    ...mapActions(['getTeamInfo'])
  },
  computed : {
    ...mapState(['myTeamInfo'])
  },
  mounted () {
    console.log(this.info)
    // this.email = this.$store.state.email
    this.getTeamInfo()
  }
}
</script>

<style>

</style>