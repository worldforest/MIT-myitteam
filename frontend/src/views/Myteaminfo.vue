<template>
  <div>
    {{ id }}
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
            <v-btn color="orange" text @click="selectMember(apply)">
              수락
            </v-btn>

            <v-btn color="orange" text @click="deleteMember(apply)">
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
            <!-- {{ member }} -->
            <router-link :to="{name: 'UserProfile', params:{user:member.memberemail}}"><b-card-text>{{ member.memberemail }}</b-card-text></router-link>
            </b-card>
          </span>
        </b-card-group>
        
        <!-- 팀 채팅 하겠습니다 ~~ -->
        {{ teamchatTitle }}
        <roter-link :to="{ name: 'Chat', params:{ privateChatTitle : teamchatTitle }}">
          <v-btn>팀 채팅하기</v-btn>
        </roter-link>
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
        dates: [],
        leaderemail: '',
        memberemail: '',
        no: ''
      },
      menu: false,
      saveInfo : null,
      teamChatData: { 
        no : this.id,
        leaderemail : this.$store.state.email,
      }
    }
  },
  props: {
    id : String
  },
  computed: {
    ...mapState(['myTeamInfo', 'dates', 'email', 'teamchatTitle']),
  },
  methods : {
    ...mapActions(['getTeamInfo', 'postDate', 'selectMember', 'deleteMember', 'teamChat']),
  },
  mounted() {
    // this.dates = this.$store.state.dates
    this.getTeamInfo()
    this.teamChat(this.teamChatData)
    const infos = JSON.parse(sessionStorage.getItem('myTeam'))
    for (let i=0; i < infos.length; i++){
      if (this.$route.params.id === infos[i].no) {
        this.saveInfo = infos[i]
        this.dateInfo.leaderemail = infos[i].leaderemail
        this.dateInfo.memberemail = this.$store.state.email
        this.dateInfo.no = this.$route.params.id
      }
    }
  },
  
}
</script>

<style>
.btn {
  text-align: right;
}
</style>