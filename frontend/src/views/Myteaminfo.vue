<template>
  <div>
    <span v-if="saveInfo.leaderemail === email">
      <span v-if="(Number(saveInfo.allCnt)+1) != saveInfo.members.length">
        <div class="apply">
          <div class="card" v-for="(apply, index) in saveInfo.applymembers" :key="index">
            <h2 @click="goUserProfile(apply.teamemail)">{{ apply.teamnickname }}</h2>
            <div v-if="apply.part === '프론트엔드'" class="title title--epic">{{ apply.part }}</div>
            <div v-else-if="apply.part === '백엔드'" class="title title--legendary">{{ apply.part }}</div>
            <div v-else-if="apply.part === '인공지능'" class="title title--common">{{ apply.part }}</div>
            <div v-else-if="apply.part === '빅데이터'" class="title title--bigdata">{{ apply.part }}</div>
            <div v-else-if="apply.part === '블록체인'" class="title title--block">{{ apply.part }}</div>
            <svg viewBox="0 0 100 100">
              <path d="m38.977 59.074c0 2.75-4.125 2.75-4.125 0s4.125-2.75 4.125 0"></path>
              <path d="m60.477 59.074c0 2.75-4.125 2.75-4.125 0s4.125-2.75 4.125 0"></path>
              <path d="m48.203 69.309c1.7344 0 3.1484-1.4141 3.1484-3.1484 0-0.27734-0.22266-0.5-0.5-0.5-0.27734 0-0.5 0.22266-0.5 0.5 0 1.1836-0.96484 2.1484-2.1484 2.1484s-2.1484-0.96484-2.1484-2.1484c0-0.27734-0.22266-0.5-0.5-0.5-0.27734 0-0.5 0.22266-0.5 0.5 0 1.7344 1.4141 3.1484 3.1484 3.1484z"></path>
              <path d="m35.492 24.371c0.42187-0.35156 0.48047-0.98438 0.125-1.4062-0.35156-0.42188-0.98438-0.48438-1.4062-0.125-5.1602 4.3047-16.422 17.078-9.5312 42.562 0.21484 0.79688 0.85547 1.4062 1.6641 1.582 0.15625 0.035156 0.31641 0.050781 0.47266 0.050781 0.62891 0 1.2344-0.27344 1.6445-0.76562 0.82812-0.98828 2.0039-1.5391 2.793-1.8203 0.56641 1.6055 1.4766 3.3594 2.9727 4.9414 2.2852 2.4219 5.4336 3.9453 9.3867 4.5547-3.6055 4.5-3.8047 10.219-3.8086 10.484-0.011719 0.55078 0.42187 1.0078 0.97656 1.0234h0.023438c0.53906 0 0.98437-0.42969 1-0.97266 0-0.054688 0.17187-4.8711 2.9805-8.7773 0.63281 1.2852 1.7266 2.5 3.4141 2.5 1.7109 0 2.7578-1.2695 3.3398-2.6172 2.8867 3.9258 3.0586 8.8359 3.0586 8.8906 0.015625 0.54297 0.46094 0.97266 1 0.97266h0.023438c0.55078-0.015625 0.98828-0.47266 0.97656-1.0234-0.007812-0.26953-0.20703-6.0938-3.9141-10.613 7.0781-1.3086 10.406-5.4219 11.969-8.9766 1.0508 0.98828 2.75 2.1992 4.793 2.1992 0.078126 0 0.15625 0 0.23828-0.003906 0.47266-0.023438 1.5781-0.074219 3.4219-4.4219 1.1172-2.6406 2.1406-6.0117 2.8711-9.4922 4.8281-22.945-4.7852-30.457-9.1445-32.621-12.316-6.1172-22.195-3.6055-28.312-0.42188-0.48828 0.25391-0.67969 0.85938-0.42578 1.3477s0.85938 0.67969 1.3477 0.42578c5.7031-2.9688 14.934-5.3047 26.5 0.4375 7.1875 3.5703 9 11.586 9.2539 17.684 0.49609 11.93-4.2617 23.91-5.7344 25.062h-0.015626c-1.832 0-3.4102-1.5742-4.0352-2.2852 0.28906-0.99609 0.44531-1.8672 0.52734-2.5117 0.62891 0.16797 1.2812 0.27344 1.9727 0.27344 0.55469 0 1-0.44922 1-1 0-0.55078-0.44531-1-1-1-7.3203 0-10.703-13.941-10.734-14.082-0.097656-0.40625-0.4375-0.71094-0.85156-0.76172-0.43359-0.050781-0.82031 0.16406-1.0117 0.53906-1.8984 3.7188-1.4297 6.7539-0.67969 8.668-6.2383-2.2852-8.9766-8.6914-9.0078-8.7617-0.17969-0.43359-0.62891-0.68359-1.1016-0.60156-0.46094 0.082032-0.80469 0.47266-0.82422 0.94141-0.14062 3.3359 0.67188 5.75 1.5 7.3164-8.3125-2.4297-10.105-11.457-10.184-11.875-0.097656-0.51562-0.57422-0.86328-1.0898-0.8125-0.51953 0.054687-0.90625 0.50391-0.89062 1.0234 0.41406 13.465-1.8516 17.766-3.2383 19.133-0.66406 0.65625-1.1992 0.67188-1.2383 0.67188-0.53906-0.050781-1.0156 0.31641-1.0938 0.85156-0.078125 0.54688 0.29688 1.0547 0.84375 1.1328 0.03125 0.003906 0.11328 0.015625 0.23828 0.015625 0.36719 0 1.1016-0.09375 1.9414-0.66406 0.050781 0.38672 0.125 0.81641 0.21875 1.2656-1.0273 0.35156-2.6211 1.0781-3.7812 2.4648-0.015625 0.019532-0.054687 0.066406-0.15625 0.046875-0.039062-0.007812-0.13281-0.039062-0.16406-0.15234-2.1875-8.1094-5.7148-28.309 8.8867-40.496zm12.711 51.828c-1.0039 0-1.5898-1.207-1.8672-2.0117 0.48047 0.023438 0.95703 0.050781 1.4531 0.050781 0.74219 0 1.4453-0.035156 2.1289-0.082031-0.24219 0.83594-0.76172 2.043-1.7148 2.043zm-13.148-30.664c1.9531 3.6211 5.6367 7.9102 12.305 8.6992 0.43359 0.046875 0.83984-0.18359 1.0234-0.57422 0.18359-0.39062 0.089844-0.85938-0.22656-1.1523-0.074219-0.070312-1.2734-1.2227-1.9688-3.6367 2 2.6094 5.3359 5.6836 10.305 6.5664 0.42187 0.070312 0.83594-0.125 1.0469-0.49219 0.21094-0.36719 0.16406-0.82812-0.11719-1.1484-0.023437-0.027344-1.9414-2.2969-1.2891-5.8906 1.2227 3.5508 3.7461 9.2227 7.8945 11.551-0.03125 0.55859-0.14844 1.668-0.55078 3.0156-0.085937 0.13672-0.125 0.28516-0.13672 0.44531-1.3008 3.8906-5.0039 9.3281-15.547 9.3281-5.375 0-9.4414-1.418-12.086-4.2109-3.5664-3.7656-3.332-8.8477-3.332-8.8984v-0.011719c1.5898-2.7227 2.5-7.3203 2.6797-13.59z"></path>
            </svg>
            <div class="actions">
              <button class="actions__like" @click="selectMember(apply)">수락 &nbsp; <i class="fas fa-heart"></i> </button>
              <button class="actions__trade" @click="goUserProfile(apply.teamemail)">프로필 &nbsp; <i class="fas fa-exchange-alt"></i> </button>
              <button class="actions__cancel" @click="deleteMember(apply)">거절 &nbsp; <i class="fas fa-times"></i> </button>
            </div>
          </div>
        </div>
      </span>
      <span v-else>
        <div class="apply">
          <div class="card" v-for="(member, index) in saveInfo.members" :key="index">
            <span v-if="member.memberemail !== email">
              <h2 @click="goUserProfile(member.memberemail)">{{ member.membernickname }}</h2>
              <div v-if="member.part === '프론트엔드'" class="title title--epic">{{ member.part }}</div>
              <div v-else-if="member.part === '백엔드'" class="title title--legendary">{{ member.part }}</div>
              <div v-else-if="member.part === '인공지능'" class="title title--common">{{ member.part }}</div>
              <div v-else-if="member.part === '빅데이터'" class="title title--bigdata">{{ member.part }}</div>
              <div v-else-if="member.part === '블록체인'" class="title title--block">{{ member.part }}</div>
              <div v-else-if="member.part === '팀장'" class="title title--boss">{{ member.part }}</div>
              <svg viewBox="0 0 100 100">
                <path d="m38.977 59.074c0 2.75-4.125 2.75-4.125 0s4.125-2.75 4.125 0"></path>
                <path d="m60.477 59.074c0 2.75-4.125 2.75-4.125 0s4.125-2.75 4.125 0"></path>
                <path d="m48.203 69.309c1.7344 0 3.1484-1.4141 3.1484-3.1484 0-0.27734-0.22266-0.5-0.5-0.5-0.27734 0-0.5 0.22266-0.5 0.5 0 1.1836-0.96484 2.1484-2.1484 2.1484s-2.1484-0.96484-2.1484-2.1484c0-0.27734-0.22266-0.5-0.5-0.5-0.27734 0-0.5 0.22266-0.5 0.5 0 1.7344 1.4141 3.1484 3.1484 3.1484z"></path>
                <path d="m35.492 24.371c0.42187-0.35156 0.48047-0.98438 0.125-1.4062-0.35156-0.42188-0.98438-0.48438-1.4062-0.125-5.1602 4.3047-16.422 17.078-9.5312 42.562 0.21484 0.79688 0.85547 1.4062 1.6641 1.582 0.15625 0.035156 0.31641 0.050781 0.47266 0.050781 0.62891 0 1.2344-0.27344 1.6445-0.76562 0.82812-0.98828 2.0039-1.5391 2.793-1.8203 0.56641 1.6055 1.4766 3.3594 2.9727 4.9414 2.2852 2.4219 5.4336 3.9453 9.3867 4.5547-3.6055 4.5-3.8047 10.219-3.8086 10.484-0.011719 0.55078 0.42187 1.0078 0.97656 1.0234h0.023438c0.53906 0 0.98437-0.42969 1-0.97266 0-0.054688 0.17187-4.8711 2.9805-8.7773 0.63281 1.2852 1.7266 2.5 3.4141 2.5 1.7109 0 2.7578-1.2695 3.3398-2.6172 2.8867 3.9258 3.0586 8.8359 3.0586 8.8906 0.015625 0.54297 0.46094 0.97266 1 0.97266h0.023438c0.55078-0.015625 0.98828-0.47266 0.97656-1.0234-0.007812-0.26953-0.20703-6.0938-3.9141-10.613 7.0781-1.3086 10.406-5.4219 11.969-8.9766 1.0508 0.98828 2.75 2.1992 4.793 2.1992 0.078126 0 0.15625 0 0.23828-0.003906 0.47266-0.023438 1.5781-0.074219 3.4219-4.4219 1.1172-2.6406 2.1406-6.0117 2.8711-9.4922 4.8281-22.945-4.7852-30.457-9.1445-32.621-12.316-6.1172-22.195-3.6055-28.312-0.42188-0.48828 0.25391-0.67969 0.85938-0.42578 1.3477s0.85938 0.67969 1.3477 0.42578c5.7031-2.9688 14.934-5.3047 26.5 0.4375 7.1875 3.5703 9 11.586 9.2539 17.684 0.49609 11.93-4.2617 23.91-5.7344 25.062h-0.015626c-1.832 0-3.4102-1.5742-4.0352-2.2852 0.28906-0.99609 0.44531-1.8672 0.52734-2.5117 0.62891 0.16797 1.2812 0.27344 1.9727 0.27344 0.55469 0 1-0.44922 1-1 0-0.55078-0.44531-1-1-1-7.3203 0-10.703-13.941-10.734-14.082-0.097656-0.40625-0.4375-0.71094-0.85156-0.76172-0.43359-0.050781-0.82031 0.16406-1.0117 0.53906-1.8984 3.7188-1.4297 6.7539-0.67969 8.668-6.2383-2.2852-8.9766-8.6914-9.0078-8.7617-0.17969-0.43359-0.62891-0.68359-1.1016-0.60156-0.46094 0.082032-0.80469 0.47266-0.82422 0.94141-0.14062 3.3359 0.67188 5.75 1.5 7.3164-8.3125-2.4297-10.105-11.457-10.184-11.875-0.097656-0.51562-0.57422-0.86328-1.0898-0.8125-0.51953 0.054687-0.90625 0.50391-0.89062 1.0234 0.41406 13.465-1.8516 17.766-3.2383 19.133-0.66406 0.65625-1.1992 0.67188-1.2383 0.67188-0.53906-0.050781-1.0156 0.31641-1.0938 0.85156-0.078125 0.54688 0.29688 1.0547 0.84375 1.1328 0.03125 0.003906 0.11328 0.015625 0.23828 0.015625 0.36719 0 1.1016-0.09375 1.9414-0.66406 0.050781 0.38672 0.125 0.81641 0.21875 1.2656-1.0273 0.35156-2.6211 1.0781-3.7812 2.4648-0.015625 0.019532-0.054687 0.066406-0.15625 0.046875-0.039062-0.007812-0.13281-0.039062-0.16406-0.15234-2.1875-8.1094-5.7148-28.309 8.8867-40.496zm12.711 51.828c-1.0039 0-1.5898-1.207-1.8672-2.0117 0.48047 0.023438 0.95703 0.050781 1.4531 0.050781 0.74219 0 1.4453-0.035156 2.1289-0.082031-0.24219 0.83594-0.76172 2.043-1.7148 2.043zm-13.148-30.664c1.9531 3.6211 5.6367 7.9102 12.305 8.6992 0.43359 0.046875 0.83984-0.18359 1.0234-0.57422 0.18359-0.39062 0.089844-0.85938-0.22656-1.1523-0.074219-0.070312-1.2734-1.2227-1.9688-3.6367 2 2.6094 5.3359 5.6836 10.305 6.5664 0.42187 0.070312 0.83594-0.125 1.0469-0.49219 0.21094-0.36719 0.16406-0.82812-0.11719-1.1484-0.023437-0.027344-1.9414-2.2969-1.2891-5.8906 1.2227 3.5508 3.7461 9.2227 7.8945 11.551-0.03125 0.55859-0.14844 1.668-0.55078 3.0156-0.085937 0.13672-0.125 0.28516-0.13672 0.44531-1.3008 3.8906-5.0039 9.3281-15.547 9.3281-5.375 0-9.4414-1.418-12.086-4.2109-3.5664-3.7656-3.332-8.8477-3.332-8.8984v-0.011719c1.5898-2.7227 2.5-7.3203 2.6797-13.59z"></path>
              </svg>
              <div class="actions_profile">
                <button class="actions__trade" @click="goUserProfile(member.memberemail)">프로필 &nbsp; <i class="fas fa-exchange-alt"></i> </button>
              </div>
            </span>
            <span v-else>
              <router-link  :to="{ name: 'Chat', params:{ privateChatTitle : teamchatTitle }}">
                <button class="button" @click="saveChat"> Team Chat </button>
              </router-link>
            </span>
          </div>
        </div>
        <div class="cal">
          <div class="cal2">
            <div>
              <v-menu
                ref="menu"
                v-model="menu"
                :close-on-content-click="false"
                :return-value.sync="date"
                transition="scale-transition"
                offset-y
                min-width="290px"
                class= "cal_bar"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-model="dateInfo.date"
                    label="Date"
                    prepend-icon  
                    readonly
                    v-bind="attrs"
                    v-on="on"
                    class="cal_input"
                  ></v-text-field>
                </template>
                <v-date-picker v-model="dateInfo.date" multiple no-title scrollable>
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click.native="$refs.menu.save(date); postDate(dateInfo)">Add</v-btn>
                  <v-btn text color="primary" @click="menu = false; deleteDate(dateInfo)">Delete</v-btn>
                </v-date-picker>
              </v-menu>
            </div>
            <div>
              <div class="btn">
                <span class="text">Text</span>
                <span class="flip-front">장소</span>
                <span class="flip-back" @click="gotoMap(saveInfo)">추천</span>
              </div>
            </div>
          </div>
          <div class="mt-4 dot">
            <v-date-picker v-model="can.dates" readonly multiple :events="functionEvents"></v-date-picker>
            <div><img src="../assets/빨간점.png"> : 총 팀원의 공통 날짜</div>
          </div>
        </div>
      </span>
    </span>
    


    <!-- 내가 팀장이 아닐 때 -->
    <span v-else>
      <div class="apply">
        <div class="card" v-for="(member, index) in saveInfo.members" :key="index">
          <span v-if="member.memberemail !== email">
            <h2 @click="goUserProfile(member.memberemail)">{{ member.membernickname }}</h2>
            <div v-if="member.part === '프론트엔드'" class="title title--epic">{{ member.part }}</div>
            <div v-else-if="member.part === '백엔드'" class="title title--legendary">{{ member.part }}</div>
            <div v-else-if="member.part === '인공지능'" class="title title--common">{{ member.part }}</div>
            <div v-else-if="member.part === '빅데이터'" class="title title--bigdata">{{ member.part }}</div>
            <div v-else-if="member.part === '블록체인'" class="title title--block">{{ member.part }}</div>
            <div v-else-if="member.part === '팀장'" class="title title--boss">{{ member.part }}</div>
            <svg viewBox="0 0 100 100">
              <path d="m38.977 59.074c0 2.75-4.125 2.75-4.125 0s4.125-2.75 4.125 0"></path>
              <path d="m60.477 59.074c0 2.75-4.125 2.75-4.125 0s4.125-2.75 4.125 0"></path>
              <path d="m48.203 69.309c1.7344 0 3.1484-1.4141 3.1484-3.1484 0-0.27734-0.22266-0.5-0.5-0.5-0.27734 0-0.5 0.22266-0.5 0.5 0 1.1836-0.96484 2.1484-2.1484 2.1484s-2.1484-0.96484-2.1484-2.1484c0-0.27734-0.22266-0.5-0.5-0.5-0.27734 0-0.5 0.22266-0.5 0.5 0 1.7344 1.4141 3.1484 3.1484 3.1484z"></path>
              <path d="m35.492 24.371c0.42187-0.35156 0.48047-0.98438 0.125-1.4062-0.35156-0.42188-0.98438-0.48438-1.4062-0.125-5.1602 4.3047-16.422 17.078-9.5312 42.562 0.21484 0.79688 0.85547 1.4062 1.6641 1.582 0.15625 0.035156 0.31641 0.050781 0.47266 0.050781 0.62891 0 1.2344-0.27344 1.6445-0.76562 0.82812-0.98828 2.0039-1.5391 2.793-1.8203 0.56641 1.6055 1.4766 3.3594 2.9727 4.9414 2.2852 2.4219 5.4336 3.9453 9.3867 4.5547-3.6055 4.5-3.8047 10.219-3.8086 10.484-0.011719 0.55078 0.42187 1.0078 0.97656 1.0234h0.023438c0.53906 0 0.98437-0.42969 1-0.97266 0-0.054688 0.17187-4.8711 2.9805-8.7773 0.63281 1.2852 1.7266 2.5 3.4141 2.5 1.7109 0 2.7578-1.2695 3.3398-2.6172 2.8867 3.9258 3.0586 8.8359 3.0586 8.8906 0.015625 0.54297 0.46094 0.97266 1 0.97266h0.023438c0.55078-0.015625 0.98828-0.47266 0.97656-1.0234-0.007812-0.26953-0.20703-6.0938-3.9141-10.613 7.0781-1.3086 10.406-5.4219 11.969-8.9766 1.0508 0.98828 2.75 2.1992 4.793 2.1992 0.078126 0 0.15625 0 0.23828-0.003906 0.47266-0.023438 1.5781-0.074219 3.4219-4.4219 1.1172-2.6406 2.1406-6.0117 2.8711-9.4922 4.8281-22.945-4.7852-30.457-9.1445-32.621-12.316-6.1172-22.195-3.6055-28.312-0.42188-0.48828 0.25391-0.67969 0.85938-0.42578 1.3477s0.85938 0.67969 1.3477 0.42578c5.7031-2.9688 14.934-5.3047 26.5 0.4375 7.1875 3.5703 9 11.586 9.2539 17.684 0.49609 11.93-4.2617 23.91-5.7344 25.062h-0.015626c-1.832 0-3.4102-1.5742-4.0352-2.2852 0.28906-0.99609 0.44531-1.8672 0.52734-2.5117 0.62891 0.16797 1.2812 0.27344 1.9727 0.27344 0.55469 0 1-0.44922 1-1 0-0.55078-0.44531-1-1-1-7.3203 0-10.703-13.941-10.734-14.082-0.097656-0.40625-0.4375-0.71094-0.85156-0.76172-0.43359-0.050781-0.82031 0.16406-1.0117 0.53906-1.8984 3.7188-1.4297 6.7539-0.67969 8.668-6.2383-2.2852-8.9766-8.6914-9.0078-8.7617-0.17969-0.43359-0.62891-0.68359-1.1016-0.60156-0.46094 0.082032-0.80469 0.47266-0.82422 0.94141-0.14062 3.3359 0.67188 5.75 1.5 7.3164-8.3125-2.4297-10.105-11.457-10.184-11.875-0.097656-0.51562-0.57422-0.86328-1.0898-0.8125-0.51953 0.054687-0.90625 0.50391-0.89062 1.0234 0.41406 13.465-1.8516 17.766-3.2383 19.133-0.66406 0.65625-1.1992 0.67188-1.2383 0.67188-0.53906-0.050781-1.0156 0.31641-1.0938 0.85156-0.078125 0.54688 0.29688 1.0547 0.84375 1.1328 0.03125 0.003906 0.11328 0.015625 0.23828 0.015625 0.36719 0 1.1016-0.09375 1.9414-0.66406 0.050781 0.38672 0.125 0.81641 0.21875 1.2656-1.0273 0.35156-2.6211 1.0781-3.7812 2.4648-0.015625 0.019532-0.054687 0.066406-0.15625 0.046875-0.039062-0.007812-0.13281-0.039062-0.16406-0.15234-2.1875-8.1094-5.7148-28.309 8.8867-40.496zm12.711 51.828c-1.0039 0-1.5898-1.207-1.8672-2.0117 0.48047 0.023438 0.95703 0.050781 1.4531 0.050781 0.74219 0 1.4453-0.035156 2.1289-0.082031-0.24219 0.83594-0.76172 2.043-1.7148 2.043zm-13.148-30.664c1.9531 3.6211 5.6367 7.9102 12.305 8.6992 0.43359 0.046875 0.83984-0.18359 1.0234-0.57422 0.18359-0.39062 0.089844-0.85938-0.22656-1.1523-0.074219-0.070312-1.2734-1.2227-1.9688-3.6367 2 2.6094 5.3359 5.6836 10.305 6.5664 0.42187 0.070312 0.83594-0.125 1.0469-0.49219 0.21094-0.36719 0.16406-0.82812-0.11719-1.1484-0.023437-0.027344-1.9414-2.2969-1.2891-5.8906 1.2227 3.5508 3.7461 9.2227 7.8945 11.551-0.03125 0.55859-0.14844 1.668-0.55078 3.0156-0.085937 0.13672-0.125 0.28516-0.13672 0.44531-1.3008 3.8906-5.0039 9.3281-15.547 9.3281-5.375 0-9.4414-1.418-12.086-4.2109-3.5664-3.7656-3.332-8.8477-3.332-8.8984v-0.011719c1.5898-2.7227 2.5-7.3203 2.6797-13.59z"></path>
            </svg>
            <div class="actions_profile">
              <button class="actions__trade" @click="goUserProfile(member.memberemail)">프로필 &nbsp; <i class="fas fa-exchange-alt"></i> </button>
            </div>
          </span>
          <span v-else>
            <router-link :to="{ name: 'Chat', params:{ privateChatTitle : teamchatTitle }}">
              <button class="button" @click="saveChat"> Team Chat </button>
            </router-link>
          </span>
        </div>
      </div>
      <div class="cal">
        <div class="cal2">
          <div>
            <v-menu
              ref="menu"
              v-model="menu"
              :close-on-content-click="false"
              :return-value.sync="date"
              transition="scale-transition"
              offset-y
              min-width="290px"
              class= "cal_bar"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="dateInfo.date"
                  label="Date"
                  prepend-icon  
                  readonly
                  v-bind="attrs"
                  v-on="on"
                  class="cal_input"
                ></v-text-field>
              </template>
              <v-date-picker v-model="dateInfo.date" multiple no-title scrollable>
                <v-spacer></v-spacer>
                <v-btn text color="primary" @click.native="$refs.menu.save(date); postDate(dateInfo)">Add</v-btn>
                <v-btn text color="primary" @click="menu = false; deleteDate(dateInfo)">Delete</v-btn>
              </v-date-picker>
            </v-menu>
          </div>
          <div class="place">
            <div class="btn">
              <span class="text">Text</span>
              <span class="flip-front">장소</span>
              <span class="flip-back" @click="gotoMap(saveInfo)">추천</span>
            </div>
          </div>
        </div>
        <div class="mt-4 dot">
          <v-date-picker v-model="can.dates" readonly multiple :events="functionEvents"></v-date-picker>
          <div><img src="../assets/빨간점.png"> : 총 팀원의 공통 날짜</div>
        </div>
      </div>
    </span>
  </div>
  
</template>

<script scoped>
import { mapState, mapActions, mapMutations } from 'vuex'

export default {
  name: 'Myteaminfo',
  data () {
    return {
      teamChatData: {
        no: '',
        leaderemail: '',
      },
      dateInfo : {
        date: [],
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
    ...mapMutations(['goUserProfile']),
    teamDataSave() {
      this.teamChatData.no = sessionStorage.getItem('team_no')
      this.teamChatData.leaderemail = sessionStorage.getItem('teamleader')
    },
    functionEvents (date) {
      for (let i = 0; i < this.meetDay.length; i++){
        if( date === this.meetDay[i]) {
          return ['red']
        }
      }
    },
    saveChat() {
      console.log(this.$store.state.teamchatTitle)
      sessionStorage.setItem('chatinfo', this.$store.state.teamchatTitle)
    }
  },
  created() {
  },
  mounted () {
    setTimeout(()=>{
      const infos = JSON.parse(sessionStorage.getItem('myTeam'))
      for (let i=0; i < infos.length; i++) {
        if (this.$route.params.id === infos[i].no) {
          this.saveInfo = infos[i]
          this.dateInfo.leaderemail = infos[i].leaderemail
          this.dateInfo.memberemail = this.$store.state.email
          this.dateInfo.no = this.$route.params.id
          this.teamChatData.no = this.saveInfo.no,
          sessionStorage.setItem('team_no', this.saveInfo.no,)
          sessionStorage.setItem('teamleader', this.saveInfo.leaderemail)
          this.selectDay(this.dateInfo)
          this.getMyday(this.dateInfo)
        }
      }
      this.teamDataSave();
      this.teamChat(this.teamChatData);
      window.scrollTo(0, 0)
    }, 350)


    setTimeout(() => {
      this.can.dates = this.$store.state.myDay
    }, 550)
  }
}
</script>

<style scoped>
.btn {
  text-align: right;
}
@import url("https://fonts.googleapis.com/css?family=Roboto:400,400i,700");
*,
*::before,
*::after {
  margin: 0;
  padding: 0;
  box-sizing: inherit;
}

body {
  height: 100vh;
  box-sizing: border-box;
  font-family: 'Roboto', 'Sans-serif';
  background-color: whitesmoke;
  display: flex;
  /* flex-direction: column; */
  justify-content: center;
  align-items: center;
}

.apply {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  /* display: grid;
  grid-template-columns: repeat(3, 1fr); */
}

.card {
  margin: 1rem;
  width: 300px;
  height: 500px;
  padding: .5rem 1rem;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 12px 32px 4px rgba(0, 0, 0, 0.2);
  display: flex;
  /* flex-direction: column; */
  justify-content: center;
  align-items: center;
  transition: .2s;
}

.card span {
  margin: 1rem;
  width: 300px;
  height: 500px;
  padding: .5rem 1rem;
  background-color: #f9f9f9;
  border-radius: 8px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  transition: .2s;

}
.card:hover {
  transform: translateY(-5px);
}
.card h2 {
  cursor: pointer;

}
.card .title {
  color: white;
  margin: 1rem;
  padding: .5rem 1rem;
  border-radius: 50px;
  transition: .2s;
}
.card .title--legendary {
  background-color: #f4d03f;
}
.card .title--legendary:hover {
  box-shadow: 0 0 12px rgba(244, 208, 63, 0.8);
}
.card .title--epic {
  background-color: #8c14fc;
}
.card .title--epic:hover {
  box-shadow: 0 0 12px rgba(140, 20, 252, 0.8);
}
.card .title--common {
  background-color: #2ecc71;
}
.card .title--common:hover {
  box-shadow: 0 0 12px rgba(46, 204, 113, 0.8);
}
.card .title--bigdata {
  background-color: #eb4d4b;
}
.card .title--bigdata:hover {
  box-shadow: 0 0 12px rgba(235, 77, 75, 0.8);
}
.card .title--block {
  background-color: #3742fa;
}
.card .title--block:hover {
  box-shadow: 0 0 12px rgba(55, 66, 250, 0.8);
}
.card .title--boss {
  background-color: #1e272e;
}
.card .title--boss:hover {
  box-shadow: 0 0 12px rgba(30, 39, 46, 0.8);
}
.card .desc {
  text-align: center;
}
.card .actions {
  width: 100%;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
}

.card .actions_profile {
  width: 100%;
  /* display: flex; */
  /* justify-content: center; */
  display: grid;
  /* grid-template-columns: repeat(1, 1fr); */
}

.card .actions button {
  padding: .5rem .8rem;
  cursor: pointer;
  background-color: transparent;
  border: none;
  text-transform: uppercase;
  outline: 0;
  transition: background-color .4s, color .4s, transform .1s;
}
.card .actions button:hover {
  color: white;
  box-shadow: 0 0 24px rgba(0, 0, 0, 0.2);
}
.card .actions button:active {
  transform: scale(0.95);
  box-shadow: 0 0 16px rgba(0, 0, 0, 0.3);
}
.card .actions__like:hover {
  background-color: #00b16a;
}
.card .actions__trade:hover {
  background-color: #3498db;
}
.card .actions__cancel:hover {
  background-color: #c0392b;
}

p {
  margin-top: 1rem;
  color: #484848;
}

a:link,
a:visited {
  text-decoration: none;
  color: #2574a9;
}

.active {
  background-color: #00b16a;
}

button::-moz-focus-inner {
  border: 0;
}

.button {
  -webkit-transition: all 0.3s ease-in-out;
  transition: all 0.3s ease-in-out;
  width: 150px;
  height: 60px;
  border-radius: 50px;
  background-image: linear-gradient(135deg, #e2b0ff 0%, #9f44d3 100%);
  box-shadow: 0 20px 30px -6px rgba(171, 88, 219, 0.5);
  outline: none;
  border: none;
  cursor: pointer;
  font-size: 24px;
  color: white;
}

.button:hover {
  -webkit-transform: translatey(10px);
          transform: translatey(10px);
  box-shadow: none;
}

.button:active {
  opacity: 0.5;
}

.cal {
  display: flex;
  /* flex-direction: column; */
  justify-content: space-around;
  flex-wrap: wrap;
  align-content: center;
  margin: 10%;
}

.cal2 {
  display:flex;
  flex-direction: column;
  justify-content: space-around;
}

.cal_input {
  flex: none; 
}


.btn {
  height: 50px;
  line-height: 50px;
  text-align: center;
  position: relative;
  /* position: absolute; */
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  -webkit-transition: all .3s ease-out;
  -o-transition: all .3s ease-out;
  transition: all .3s ease-out;
}

.text {
  padding: 0 50px;
  visibility: hidden;
}

.flip-front, 
.flip-back {
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    -webkit-transform-style: flat;
    transform-style: flat;
    -webkit-transition: -webkit-transform .3s ease-out;
    -o-transition: -o-transform .3s ease-out;
    transition: transform .3s ease-out;
}

.flip-front {
    color: #313131;
    border: 2px solid #313131;
    background-color: transparent;
    -webkit-transform: rotateX(0deg) translateZ(25px);
    -ms-transform: rotateX(0deg) translateZ(25px);
    -o-transform: rotateX(0deg) translateZ(25px);
    transform: rotateX(0deg) translateZ(25px);
}

.flip-back {
  color: #fff;
  background-color: #313131;
  border: 2px solid #313131;
  -webkit-transform: rotateX(90deg) translateZ(25px);
  -ms-transform: rotateX(90deg) translateZ(25px);
  -o-transform: rotateX(90deg) translateZ(25px);
  transform: rotateX(90deg) translateZ(25px);
}

.btn:hover .flip-front {
    -webkit-transform: rotateX(-90deg) translateZ(25px);
    -ms-transform: rotateX(-90deg) translateZ(25px);
    -o-transform: rotateX(-90deg) translateZ(25px);
    transform: rotateX(-90deg) translateZ(25px);
}

.btn:hover .flip-back {
    -webkit-transform: rotateX(0deg) translateZ(25px);
    -ms-transform: rotateX(0deg) translateZ(25px);
    -o-transform: rotateX(0deg) translateZ(25px);
    transform: rotateX(0deg) translateZ(25px);
}

.dot {
  display: flex;
  flex-direction: column;
}

.dot img {
  width: 10px;
}

.place {
  margin: 7%;
}
</style>
