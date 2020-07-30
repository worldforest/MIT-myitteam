<template>
  <v-app>
    <v-container>
      <span v-if="!isLoggedIn">
        <router-link class="black--text" to="/login">Login</router-link>
        <router-link class="black--text" to="/signup">SIGNUP</router-link>
        <router-link class="black--text" to="/">Home</router-link>
      </span>
      <span v-else>
        {{ email }}
        <router-link class="black--text" to="/">Home</router-link>
        <router-link class="black--text mx-3" to="/profile" @click.native="profile">Profile</router-link>
        <router-link id="tokenTrue" class="black--text" to="/" @click.native="logout">Logout</router-link>
      </span>
      <router-view />
    </v-container>
  </v-app>
</template>

<script>
import { mapGetters, mapActions, mapState } from 'vuex'

export default {
  name: 'App',
  components: {
  },
  methods: {
    ...mapActions(['logout', 'profile', 'postEmailToken']),
  },
  computed: {
    ...mapState(['email']),
    ...mapGetters(['isLoggedIn', 'isEmail'])
  },
  mounted() {
    if (this.$cookies.isKey('auth-token')) {
      this.postEmailToken()
      }}
  
};
</script>

<style scoped>
  #logo{
    height: 70px;
    width: 100px;
  }
</style>