<template>
  <v-app id="inspire">
    <v-navigation-drawer
      v-model="drawer"
      app
			color="#5C6BC0"
    >
      <v-list dense>
        <v-list-item link>
          <v-list-item-content class="text--white">
            <router-link color="#FFFFFF" class="mr-3" to="#">공모전</router-link>
          </v-list-item-content>
        </v-list-item>
        <v-list-item link>
          <v-list-item-content>
            <router-link class="mr-3" to="#">프로젝트</router-link>
          </v-list-item-content>
        </v-list-item>
        <v-list-item link> <!--v-if="!isLoggedIn"-->
          <v-list-item-content>
            <router-link class="mr-3" to="/login">LOGIN</router-link>
          </v-list-item-content>
        </v-list-item>
        <v-list-item link> <!--v-if="!isLoggedIn"-->
          <v-list-item-content>
            <router-link class="mr-3" to="/signup">SIGNUP</router-link>
          </v-list-item-content>
        </v-list-item>
        <v-list-item link>
          <v-list-item-content> <!--v-if="isLoggedIn"-->
            <router-link id="tokenTrue" to="/logout" @click.native="logout">LOGOUT</router-link>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar
      app
      color="white"
      dark
    >
      <v-app-bar-nav-icon color="black" @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title>
				<a href="/">
					<img id="logo" src="/views/images/1.jpg" alt="logo">
				</a>
      </v-toolbar-title>
			<div class="spacer"></div>
			<span>
				<a href="/profile">
					<v-icon class="accountIcon" x-large color="#5C6BC0">mdi-account-circle</v-icon>
				</a>
			</span>
    </v-app-bar>

    <v-main>
      <v-container
        class="fill-height"
        fluid
      >
        <v-row
          align="center"
          justify="center"
        >
          <v-col class="text-center">
            <v-tooltip left>
              <template v-slot:activator="{ on }">
                <v-btn
                  :href="source"
                  icon
                  large
                  target="_blank"
                  v-on="on"
                >
                  <v-icon large>mdi-code-tags</v-icon>
                </v-btn>
              </template>
              <!-- <span>Source</span> -->
            </v-tooltip>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
  export default {
    props: {
      source: String,
    },
    data: () => ({
      drawer: null,
    }),
  }
</script>

<style scoped>
	a { 
    text-decoration: none;
    color: rgb(255, 255, 255);
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