<template>
  <div>
    {{ saveInfo }}
    <span v-if="saveInfo.leaderemail === email">
      {{ saveInfo.allCnt}}
      {{ saveInfo.members.length}}
      <span v-if="saveInfo.allCnt != saveInfo.members.length">
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
      </span>
      <span v-else>
        <b-card-group deck v-for="member in saveInfo.members" :key="member">
          <span v-if="member.memberemail !== email">
            <b-card
              border-variant="primary"
              header
              header-bg-variant="primary"
              header-text-variant="white"
              align="center"
            >
            <router-link :to="{name: 'UserProfile', params:{user:member.memberemail}}"><b-card-text>{{ member.membernickname }}</b-card-text></router-link>
            </b-card>
          </span>
        </b-card-group>
        <v-row>
          <div>
            날짜 추가
          </div>
          <v-col cols="12" sm="6" md="4">
            <v-menu
              ref="menu"
              v-model="menu"
              :close-on-content-click="false"
              :return-value.sync="date"
              transition="scale-transition"
              offset-y
              min-width="290px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="dateInfo.date"
                  label="Picker in menu"
                  prepend-icon
                  readonly
                  v-bind="attrs"
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker v-model="dateInfo.date" no-title scrollable>
                <v-spacer></v-spacer>
                <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
                <v-btn text color="primary" @click.native="$refs.menu.save(date); postDate(dateInfo)">OK</v-btn>
              </v-date-picker>
            </v-menu>
          </v-col>
        </v-row>
        <v-row>
          <div>
            날짜 빼기
          </div>
          <v-col cols="12" sm="6" md="4">
            <v-menu
              ref="menu2"
              v-model="menu2"
              :close-on-content-click="false"
              :return-value.sync="date"
              transition="scale-transition"
              offset-y
              min-width="290px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="dateInfo.date"
                  label="Picker in menu"
                  prepend-icon
                  readonly
                  v-bind="attrs"
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker v-model="dateInfo.date" no-title scrollable>
                <v-spacer></v-spacer>
                <v-btn text color="primary" @click="menu2 = false">Cancel</v-btn>
                <v-btn text color="primary" @click.native="$refs.menu2.save(date); deleteDate(dateInfo)">OK</v-btn>
              </v-date-picker>
            </v-menu>
          </v-col>
        </v-row>
        <v-row justify="center">
          <v-date-picker v-model="can.dates" readonly multiple></v-date-picker>
        </v-row>
        <div v-for="day in meetDay" :key="day">
          {{ day }}
        </div>
      </span>
    </span>
    <span v-else>
        <b-card-group deck v-for="member in saveInfo.members" :key="member">
          <span v-if="member.memberemail !== email">
            <b-card
              border-variant="primary"
              header
              header-bg-variant="primary"
              header-text-variant="white"
              align="center"
            >
            <router-link :to="{name: 'UserProfile', params:{user:member.memberemail}}"><b-card-text>{{ member.memberemail }}</b-card-text></router-link>
            </b-card>
          </span>
        </b-card-group>
        <v-row>
          <div>
            날짜 추가
          </div>
          <v-col cols="12" sm="6" md="4">
            <v-menu
              ref="menu"
              v-model="menu"
              :close-on-content-click="false"
              :return-value.sync="date"
              transition="scale-transition"
              offset-y
              min-width="290px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="dateInfo.date"
                  label="Picker in menu"
                  prepend-icon
                  readonly
                  v-bind="attrs"
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker v-model="dateInfo.date" no-title scrollable>
                <v-spacer></v-spacer>
                <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
                <v-btn text color="primary" @click.native="$refs.menu.save(date); postDate(dateInfo)">OK</v-btn>
              </v-date-picker>
            </v-menu>
          </v-col>
        </v-row>
        <v-row>
          <div>
            날짜 빼기
          </div>
          <v-col cols="12" sm="6" md="4">
            <v-menu
              ref="menu2"
              v-model="menu2"
              :close-on-content-click="false"
              :return-value.sync="date"
              transition="scale-transition"
              offset-y
              min-width="290px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="dateInfo.date"
                  label="Picker in menu"
                  prepend-icon
                  readonly
                  v-bind="attrs"
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker v-model="dateInfo.date" no-title scrollable>
                <v-spacer></v-spacer>
                <v-btn text color="primary" @click="menu2 = false">Cancel</v-btn>
                <v-btn text color="primary" @click.native="$refs.menu2.save(date); deleteDate(dateInfo)">OK</v-btn>
              </v-date-picker>
            </v-menu>
          </v-col>
        </v-row>
        <v-row justify="center">
          <v-date-picker v-model="can.dates" readonly multiple></v-date-picker>
        </v-row>
        <div v-for="day in meetDay" :key="day">
          {{ day }}
        </div>
    </span>
  </div>
  
  
  
</template>

<script>
import { mapState, mapActions } from 'vuex'


export default {
  name: 'Myteaminfo',
  data () {
    return {
      dateInfo : {
        date: new Date().toISOString().substr(0, 10),
        leaderemail: '',
        memberemail: '',
        no: ''
      },
      menu: false,
      menu2: false,
      saveInfo : null,
      dialog: false,
      mememail: [],
      can : {
        dates: [],
        menu: false
      }
    }
  },
  computed: {
    ...mapState(['myTeamInfo', 'dates', 'email', 'memberNickname', 'meetDay', 'myDay']),

  },
  methods : {
    ...mapActions(['getTeamInfo', 'postDate', 'selectMember', 'deleteMember', 'selectDay', 'getNickname', 'getMyday', 'deleteDate']),
  },
  created() {
    // this.dates = this.$store.state.dates
    this.getTeamInfo()
    const infos = JSON.parse(sessionStorage.getItem('myTeam'))
    for (let i=0; i < infos.length; i++) {
      if (this.$route.params.id === infos[i].no) {
        this.saveInfo = infos[i]
        this.dateInfo.leaderemail = infos[i].leaderemail
        this.dateInfo.memberemail = this.$store.state.email
        this.dateInfo.no = this.$route.params.id
        this.selectDay(this.dateInfo)
        this.getMyday(this.dateInfo)
      }
    }
  },
  mounted () {
    // console.log(this.$store.state.myDay)
    setTimeout(() => {
        this.can.dates = this.$store.state.myDay
			}, 200)
  }
}
</script>

<style>
.btn {
  text-align: right;
}
</style>