<template>
  <div>
    {{ email }}
    <ul>
      <li v-for="member in members" :key="member">
        {{ member }}
      </li>
    </ul>
    <div class="pa-5">
      <v-row>
          <v-col cols="12" md="6" class="mb-4">
              <v-sheet height="500">
                  <v-calendar
                    ref="calendar"
                    color="primary"
                    refs="day"
                    @click:date="checkDate"
                  ></v-calendar>
              </v-sheet>
          </v-col>
      </v-row>
    </div>
  </div>
</template>

<script>
import { mapState, } from 'vuex'

export default {
  name: 'Myteam',
  data () {
    return {
      members : ['김지훈', '이다인', '홍정민', '이세림'],
      email:'',
      day:'',
      isClicked : false
    }
  },
  methods :{
    checkDate (date) {
      this.day = date
      console.log(this.day)
      this.day.time = []
      console.log(typeof(this.day.time))
      // if (!isClicked && ) {

      // }
      },
  },
  computed : {
    ...mapState(['email'])
  },
  mounted () {
    this.email = this.$store.state.email
  }
}
</script>

<style>

</style>