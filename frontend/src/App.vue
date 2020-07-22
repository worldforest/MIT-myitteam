<template>
  <v-app>
    <v-container>
      <router-link class="black--text" to="/signup">SIGNUP</router-link>
      <router-link to="/profile">Profile</router-link>
      <router-link to="/">Home</router-link>
      <v-main>
        <router-view/>
      </v-main>
    </v-container>
  </v-app>
</template>

<script>

export default {
  name: 'App',
  components: {

  },

};
</script>
