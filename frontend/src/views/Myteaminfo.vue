<template>
  <div>
    <!-- {{ saveInfo.applymembers }} -->
    <v-card v-for="apply in saveInfo.applymembers" :key="apply"
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
          @click="selectMember(apply)"
        >
          수락
        </v-btn>

        <v-btn
          color="orange"
          text
          @click="deleteMember(apply)"
        >
          거절
        </v-btn>
      </v-card-actions>
    </v-card>
    <v-layout row wrap>
      <v-flex xs12 sm6>
        <v-date-picker
          v-model="dateInfo.dates"
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
        </v-menu>
      </v-flex>
    </v-layout>
    <v-btn class="blue darken-1" @click.native="postDate(dateInfo)">확정</v-btn>
  </div>
  
  
</template>

<script>
import { mapState, mapActions } from 'vuex'


export default {
  name: 'Myteaminfo',
  data () {
    return {
      dateInfo : {
        dates: [],
        leaderemail: '',
        memberemail: '',
        no: ''
      },
      menu: false,
      saveInfo : null,
    }
  },
  computed: {
    ...mapState(['myTeamInfo', 'dates', 'email']),
  },
  methods : {
    ...mapActions(['getTeamInfo', 'postDate', 'selectMember', 'deleteMember']),
  },
  mounted() {
    // this.dates = this.$store.state.dates
    const infos = JSON.parse(sessionStorage.getItem('myTeam'))
    for (let i=0; i < infos.length; i++){
      if (this.$route.params.id === infos[i].no) {
        this.saveInfo = infos[i]
        this.dateInfo.leaderemail = infos[i].leaderemail
        this.dateInfo.memberemail = this.$store.state.email
        this.dateInfo.no = this.$route.params.id
      }
    }
    // if (!sessionStorage.teaminfo) {
    //   console.log("성공")
    //   sessionStorage.setItem('teaminfo', JSON.stringify(this.$route.params))
    //   this.$store.state.saveInfo = JSON.parse(sessionStorage.getItem('teaminfo'))
    // } else {
    //   console.log(this.saveInfo.id)
    //   if (this.$route.params.id !== this.saveInfo.id) {
    //     sessionStorage.setItem('teaminfo', JSON.stringify(this.$route.params))
    //     this.$store.state.saveInfo = JSON.parse(sessionStorage.getItem('teaminfo'))
    //   }
    // }
  }
}
</script>

<style>
.btn {
  text-align: right;
}

</style>