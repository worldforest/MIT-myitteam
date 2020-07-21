<template>
  <v-app>
    <router-link class="black--text" to="/login">Login</router-link>
      <router-view/>
  </v-app>
</template>

<script>

export default {
  name: 'App',
  components: {

  },

};
</script>