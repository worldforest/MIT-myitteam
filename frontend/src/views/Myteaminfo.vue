<template>
  <div>
    {{ saveInfo }}
    <v-card v-for="apply in saveInfo.info.applymembers" :key="apply"
      class="mx-auto"
      max-width="400"
    >
      <router-link :to="{name: 'UserProfile', params:{user:apply.teamemail}}">{{ apply.teamemail }}</router-link>

      <v-card-text class="text--primary">
        <div>{{ apply.part }}</div>
      </v-card-text>

      <v-card-actions>
        <v-btn
          color="orange"
          text
          @click='plusMember'
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
    <v-layout row wrap>
      <v-flex xs12 sm6>
        <v-date-picker
          v-model="dates"
          multiple
        ></v-date-picker>
      </v-flex>
      <v-flex xs12 sm6>
        <v-menu
          ref="menu"
          :close-on-content-click="false"
          v-model="menu"
          :nudge-right="40"
          :return-value.sync="dates"
          lazy
          transition="scale-transition"
          offset-y
          full-width
          min-width="290px"
        >
          <v-combobox
            slot="activator"
            v-model="dates"
            multiple
            chips
            small-chips
            label="Multiple picker in menu"
            prepend-icon="event"
            readonly
          ></v-combobox>
          <v-date-picker v-model="dates" multiple no-title scrollable>
            <v-spacer></v-spacer>
            <v-btn flat color="primary" @click="menu = false">Cancel</v-btn>
            <v-btn flat color="primary" @click="$refs.menu.save(dates)">OK</v-btn>
          </v-date-picker>
        </v-menu>
      </v-flex>
    </v-layout>
  </div>
  
  
</template>

<script>
import { mapState, mapActions } from 'vuex'


export default {
  name: 'myTeamInfo',
  props: {
    info: Object
  },
  data () {
    return {
      dates: [],
      menu: false
    }
  },
  computed: {
    ...mapState(['saveInfo', 'myTeamInfo']),
    teamInfo() {
      return this.$route.params.info
    },
  },
  methods : {
    ...mapActions(['getTeamInfo']),
    plusMember () {
    }
   },
  mounted() {
    console.log(typeof(this.$route.params.id))
    if (!sessionStorage.teaminfo) {
      console.log("성공")
      sessionStorage.setItem('teaminfo', JSON.stringify(this.$route.params))
      this.$store.state.saveInfo = JSON.parse(sessionStorage.getItem('teaminfo'))
    } else {
      console.log(this.saveInfo.id)
      if (this.$route.params.id !== this.saveInfo.id) {
        sessionStorage.setItem('teaminfo', JSON.stringify(this.$route.params))
        this.$store.state.saveInfo = JSON.parse(sessionStorage.getItem('teaminfo'))
      }
    }
  }
}
</script>

<style>

</style>