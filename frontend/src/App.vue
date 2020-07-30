<template>
  <v-app>
    <span v-if="!isLoggedIn">
      <router-link class="black--text" to="/login">Login</router-link><br>
      <router-link class="black--text" to="/signup">SIGNUP</router-link>
    </span>
    <span v-else>
      {{ nickname }}
      <router-link class="black--text" to="/" @click.native="logout">Logout</router-link>
    </span>
    <router-view />
  </v-app>
</template>

<script>
import { mapState, mapGetters, mapActions } from 'vuex'

export default {
  name: 'App',
  components: {
  },
  methods: {
    ...mapActions(['logout'])
  },
  computed: {
    ...mapState(['nickname']),
    ...mapGetters(['isLoggedIn'])
  },
};
</script>

<style scoped>
  #logo{
    height: 70px;
    width: 100px;
  }
</style>