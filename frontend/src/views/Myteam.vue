<template>
  <div>
    <div id="content">
      <p class="team_title">My team</p>
      <div class="dohee" v-for="(info, index) in this.$store.state.myTeamInfo" :key="index">
        <div class="flipper">
          <div class="front">
            <img v-if="index%3===0" src="https://picsum.photos/250/250" alt="cherry blossoms">
            <img v-else-if="index%3===1" src="https://source.unsplash.com/random/250x250/?fruit" alt="cherry blossoms">
            <img v-else src="https://source.unsplash.com/random/250x250/?animal" alt="cherry blossoms">
            <span class="caption"><h1>{{ info.title }}</h1></span>
          </div>
          <div class="back">
            <router-link class="text-decoration-none mx-auto" :to="{name: 'Myteaminfo', params : {id:info.no}}">
              <h1>Go Team</h1>
            </router-link>
            <h1 v-if="info.category === '0'"> 공모전</h1>
            <h1 v-else-if="info.category === '1'"> 프로젝트</h1>
            <h1>{{ info.start }}</h1> 
            ~ <h1> {{ info.end }} </h1>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, } from 'vuex'

export default {
  name: 'Myteam',
  data () {
    return {
      // email:'',
    }
  },
  methods :{
    ...mapActions(['getTeamInfo']),
  },
  computed : {
    ...mapState(['saveInfo'])
  },
  mounted () {
    this.getTeamInfo()
    this.$store.state.myTeamInfo = JSON.parse(sessionStorage.getItem('myTeam'))
    window.scrollTo(0, 0)
  }
 }
</script>

<style scoped>
@import url(https://fonts.googleapis.com/css?family=Raleway|Reenie+Beanie);

* {
  box-sizing: border-box;
}

html,
body {
  margin: 5%;
  font-family: "Raleway", sans-serif;
  line-height: 1.5em;
}

img {
  width: 100%;
}

a {
  color: black;
}
.team_title {
  font-family: 'Nanum Pen Script', cursive;
  font-size: 5em;
}

.caption,
h1
{
  margin: 0;
  text-align: center;
}

.caption,
h1 {
  font-family: 'Nanum Pen Script', cursive;
  font-size: 2em;
  padding: 10px 10px 0 10px;
}

.dohee {
  perspective: 1000px;
  display: inline-block;
  margin: 6%;
}

.dohee:hover .flipper {
  transform: rotateY(180deg);
}

.dohee,
.front,
.back {
  width: 250px;
  height: 250px;
}

.flipper {
  transition: 0.6s;
  transform-style: preserve-3d;
  position: relative;
}

.front,
.back {
  backface-visibility: hidden;
  position: absolute;
  top: 0;
  left: 0;
  width: 250px;
  height: 250px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}

.front {
  z-index: 2;
}

.back {
  transform: rotateY(180deg);
}

#content {
  text-align: center;
}

</style>