<template>
  <v-card class="overflow-hidden">
    <div class="v-toolbar__content" style="height: 56px;">
      <img id="logo" src="./images/1.png" alt="logo">

      <router-link to="/signup">SIGNUP</router-link>
      <div class="spacer"></div>

      <span>
        <v-btn icon>
          <v-icon large color="#5C6BC0">mdi-magnify</v-icon>
        </v-btn>
      </span>

      <span>
        <v-btn icon>
          <v-icon color="#5C6BC0">mdi-account</v-icon>
        </v-btn>
      </span>
    </div>

    <v-app>
      <router-view @submit-signup-data="signup"/>
    </v-app>
  </v-card>
</template>

<script>
import axios from 'axios'
const SERVER_URL = 'http://localhost:8000'

export default {
  name: 'App',
  components: {

  },
  methods: {
    setCookie(token) {
      this.$cookies.set('auth-token', token)
    },
    signup(signupData) {
      console.log(signupData)
      axios.post(SERVER_URL + '/signup/', signupData)
      .then(res => {
        console.log(res.data.key)
        this.setCookie(res.data.key)
      })
      .catch(err => this.errorMessages = err.response.data)
    }
  }
};
</script>