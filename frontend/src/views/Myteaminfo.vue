<template>
  <div>
    {{ saveInfo.no }}
    {{ saveInfo.leaderemail }}
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
        <hr>
        {{ teamchatTitle }}
        <router-link :to="{ name: 'Chat', params:{ privateChatTitle : teamchatTitle }}">
          <v-btn>팀 채팅하기</v-btn>
        </router-link>
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

        <hr>
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


    <!-- 내가 팀장이 아닐 때 -->
    <span v-else>
      {{ '나 팀장 아님' }}
      {{ teamChatData }}
      <hr>
      {{ teamchatTitle }}
      <router-link :to="{ name: 'Chat', params:{ privateChatTitle : teamchatTitle }}">
        <v-btn>팀 채팅하기</v-btn>
      </router-link>
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

    <v-btn text color="primary" @click="gotoMap(saveInfo)">장소 추천보기!</v-btn>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'

export default {
  name: 'Myteaminfo',
  data () {
    return {
      teamChatData: {
        no: '',
        leaderemail: '',
      },
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
      },
    }
  },
  computed: {
    ...mapState(['myTeamInfo', 'dates', 'email', 'memberNickname', 'meetDay', 'myDay','teamchatTitle']),

  },
  methods : {
    ...mapActions(['getTeamInfo', 'postDate', 'selectMember', 'deleteMember', 'selectDay', 'getNickname', 'getMyday', 'deleteDate', 'teamChat', 'gotoMap']),
    teamDataSave(){
      this.teamChatData.no = this.saveInfo.no,
      this.teamChatData.leaderemail = this.saveInfo.leaderemail
    }
  },
  created() {
  // this.getTeamInfo()
    // this.dates = this.$store.state.dates

  },
  mounted () {
    setTimeout(() => { 
      this.can.dates = this.$store.state.myDay
      }, 200),
    setTimeout(()=>{
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
    }, 350)
    // console.log(this.$store.state.myDay)
    this.teamDataSave();
    this.teamChat(this.teamChatData);
    // console.log(this.$store.state.myDay)
  }
}
</script>

<style>
.btn {
  text-align: right;
}
</style>