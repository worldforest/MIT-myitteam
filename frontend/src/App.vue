<template>
  <!-- <v-container>
    <v-layout> -->
      <v-card class="overflow-hidden">
        <div class="v-toolbar__content" style="height: 90px;">
          <a href="/">
            <img id="logo" src="./images/1.jpg" alt="logo">
          </a>
          <div>
            <router-link class="mr-3" to="#">공모전</router-link>
            <router-link class="mr-3" to="#">프로젝트</router-link>
          </div>
          <div class="spacer"></div>
          <!--로그인 안 된 상태-->
          <div v-if="!isLoggedIn">
            <router-link class="mr-3" to="/login">LOGIN</router-link>
            <router-link class="mr-3" to="/signup">SIGNUP</router-link>
          </div>
          <!--로그인 된 상태-->
          <span v-else>
            <router-link id="tokenTrue" to="/logout" @click.native="logout">LOGOUT</router-link>
          </span>

          <span>
            <a href="/profile">
              <v-icon class="accountIcon" x-large color="#5C6BC0">mdi-account-circle</v-icon>
            </a>
          </span>
        </div>
        <v-app color="#FAFAFA">
          <router-view/>
        </v-app>
      </v-card>
    <!-- </v-layout>
  </v-container> -->
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
  a { 
    text-decoration: none;
    color: rgb(92, 107, 192);
    margin-right: 2rem;
    font-size: 1.3rem;
    font-weight: bold;
  }
  a:hover{
    color: blue;
  }
  #logo{
    height: 70px;
    width: 120px;
    margin-top: 10px;
    margin-bottom: 10px;
    margin-left: 20px;
  }

</style>