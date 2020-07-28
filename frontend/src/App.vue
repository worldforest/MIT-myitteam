<template>
  <v-app>
    <span v-if="!isLoggedIn">
      <router-link class="black--text" to="/login">Login</router-link>
      <router-link class="black--text" to="/signup">SIGNUP</router-link>
    </span>
    <span v-else>
    <router-link id="tokenTrue" class="black--text" to="/logout" @click.native="logout">Logout</router-link>
    </span>
    <router-view />
  </v-app>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'App',
  components: {
  },
  methods: {
    ...mapActions(['logout'])
  },
  computed: {
    ...mapGetters(['isLoggedIn'])
  },
};
</script>

