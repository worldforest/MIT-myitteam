<template>
  <v-app>
    <span v-if="!isLoggedIn">
      <router-link class="black--text" to="/login">Login</router-link>
      <router-link class="black--text" to="/signup">SIGNUP</router-link>
    </span>
    <span v-else>
      <router-link class="black--text" to="/logout" @click.native="logout">Logout</router-link>
    </span>
    <router-view/>
  </v-app>
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

