<template>
  <v-app>
    <router-link class="black--text" to="/signup">SIGNUP</router-link>
    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>

export default {
  name: 'App',
  components: {

  },

};
</script>
