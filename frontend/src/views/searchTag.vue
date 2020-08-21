<template>
  <div>
    <div v-if="windowWidth > 760">
      <main>
        <h1>#{{ keyword }}</h1>
        <div class="flex-container">
          <figure v-for="(feed, index) in tagData" :key="index">
            <img :src="feed.src" alt="피드 이미지" @click='feedDetail(feed)'/>
            <figcaption>
              <div class="fig-author">by {{ feed.nickname }}</div>
            </figcaption>
          </figure>
        </div>            
      </main>
    </div>
    <div v-else  class="cont10">
      <h5 class="text-center">#{{ keyword }}</h5>
      <v-row  class="bg-gray">
      <v-col cols='12'  v-for="(feed, index) in tagData" :key="index">
        <v-card
          class="mx-auto my-3"
        >
          <v-img
            height="300"
            :src="feed.src"
            @click="feedDetail(feed)"
          ></v-img>

          <v-card-text @click="feedDetail(feed)">
            <v-row
              align="center"
              class="mx-0"
            >
            </v-row>

            <div class="my-4 subtitle-1">
              <span v-if="feed.description <= 30">{{feed.description}}</span>
              <span v-else>{{feed.description.slice(0,30)}}...</span>
            </div>
          </v-card-text>

          <v-card-text>
            <v-chip-group
              active-class="deep-purple accent-4 white--text"
              column
            >
              <v-chip v-for="(tag,i) in feed.tag" :key="i" @click="searchTagFeed(tag)">
                #{{tag}}
              </v-chip>
            </v-chip-group>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
    </div>
      
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from 'vuex'

export default {
  data() {
    return {
      windowWidth: window.innerWidth,
      txt: '',
    }
  },
  computed: {
    ...mapState(['tagData', 'email', 'keyword'])
  },

  methods: {
    ...mapActions(['searchFollowFeed', 'searchTagFeed']),
    ...mapMutations(['feedDetail']),
    onResize() {
      this.windowWidth = window.innerWidth
    },    
  },

  mounted() {
    this.$nextTick(() => {
      window.addEventListener('resize', this.onResize);
    })
    window.scrollTo(0, 0)
  },

   beforeDestroy() { 
    window.removeEventListener('resize', this.onResize); 
  },

}
</script>

<style scoped>
  .text-box {
    width: 100%;
    text-align: center;
    margin-top: 1rem;
  }

  .center {
    display: inline-block;
    margin: 0
  }

  .right {
    float: right;
  }

  .cont10 {
    margin: 0 3%;
  }

  .cursor {
    cursor: pointer;
    font-weight: bold;
  }

  /* 여기서 부터는 피드 */
  @import url("https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,700");
* {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
  }

  a {
    text-decoration: none;
    margin: 0;
    padding: 0;
  }

  body {
    background-color: #FAFAFA;
    font-family: 'Montserrat', sans-serif;
    color: #262626;
    font-size: 16px;
  }

  main {
    max-width: 1000px;
    background-color: #FAFAFA;
    margin: 0 auto;
  }
  main h1 {
    font-size: 3rem;
    text-transform: uppercase;
    margin: 0;
    text-align: center;
    padding: 2rem 5% 1rem 5%;
  }
  main h2 {
    font-size: 1.3rem;
    font-weight: 300;
    text-transform: uppercase;
    margin: 0;
    text-align: center;
    padding: 0 5% 3rem 5%;
  }
  main .flex-container {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-content: space-between;
    flex-wrap: wrap;
    padding: 0 5% 5% 5%;
  }
  main .flex-container figure {
    margin-bottom: 1rem;
    position: relative;
  }
  main .flex-container figure img {
    width: 100%;
    height: 25vw;
    opacity: 1;
    -webkit-transition: opacity 0.5s;
    /* For Safari 3.1 to 6.0 */
    transition: opacity 0.5s;
  }
  main .flex-container figure img:hover {
    opacity: 0.9;
  }
  main .flex-container figure figcaption {
    padding: 10px;
    line-height: 1.5;
    position: absolute;
    bottom: 0;
    right: 0;
    text-align: right;
    color: #fff;
    opacity: 0.7;
  }
  main .flex-container figure figcaption .fig-title {
    font-weight: 700;
  }
  main .flex-container figure figcaption .fig-author {
    font-weight: 300;
    font-size: 0.8rem;
  }
  main .more-container {
    margin: 0 auto;
    text-align: center;
    padding: 0 5%;
    margin-bottom: 5%;
  }
  main .more, main .more:link, main more:visited {
    color: #262626;
    font-weight: 700;
    padding: 10px;
    opacity: 0.5;
    -webkit-transition: opacity 0.5s;
    /* For Safari 3.1 to 6.0 */
    transition: opacity 0.5s;
  }
  main .more:hover {
    opacity: 1;
  }

  /* Media queries*/
  @media screen and (min-width: 701px) {
    figure {
      width: calc(33% - 0.5rem);
    }
  }
  @media screen and (max-width: 700px) {
    figure {
      width: calc(50% - 0.5rem);
    }
  }
  @media screen and (max-width: 550px) {
    figure {
      width: 100%;
    }
  }

</style>