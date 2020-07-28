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
const SERVER_URL = 'http://localhost:9999/mit'

export default {
  name: 'App',
  components: {

  },
  data () {
    return {
      isLoggedIn: false,

    }
  },
  methods: {
    onLogin(loginData) {
      axios.post(`${SERVER_URL}/api/user/login/`, loginData)
      .then(response => {
        this.$cookies.set('token', response.data)
        this.isLoggedIn = true
        this.$router.push('/')
      })
      .catch(error => {
        console.log(error.response)
      })
    },
    logout() {
      this.$cookies.remove('token')
      this.isLoggedIn = false
      this.$router.push('/')
    },
  },
  mounted() {
    this.isLoggedIn = this.$cookies.isKey('token')  
  },

};
</script>

