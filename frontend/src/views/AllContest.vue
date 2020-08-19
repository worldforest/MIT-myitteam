<template>
  <div>     
    <div>
    <v-row no-gutters>
      <li v-for="(i, index) in allContest" :key="index" class="mx-auto">
        <!-- {{ pjt }} -->
        <v-col v-for="n in 1" :key="n" cols="sm" > 
          <v-card
            class="mx-auto contestCard"
            max-width="400"
            v-on:click="gongmoDetail(i)"
          >
            <v-img
              class="white--text align-end"
              height="200px"
              :src="i.imagesrc"
            >
            </v-img>

            <v-card-subtitle class="pb-0" v-if="windowWidth > 800"><h4>{{i.title.slice(0,15)}}...</h4></v-card-subtitle>
            <v-card-subtitle class="pb-0" v-else><p>{{i.title.slice(0,10)}}...</p></v-card-subtitle>

            <v-card-actions >
              <v-btn
                color="orange"
                text
                class="ml-auto"
                @click="gongmoDetail(i)"
              >
                자세히보기
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
      </li>
    </v-row>
    </div>    
  </div>    
</template>

<script>
import { mapActions, mapState, mapMutations } from "vuex";

export default {
  data() {
    return {
      perPage: 6,
      currentPage: 1,
      contestanother: [],
      ans: false,
      windowWidth: window.innerWidth,
    }
  },
  methods: {
    ...mapActions(['getAllContest']),
    ...mapMutations(['gongmoDetail']),
     onResize() {
      this.windowWidth = window.innerWidth
    },
  },
  watch: {
    windowWidth(newWidth, oldWidth) {
    this.txt = `it changed to ${newWidth} from ${oldWidth}`;
    }
  },

  beforeDestroy() { 
    window.removeEventListener('resize', this.onResize); 
  },


  mounted() {
    this.getAllContest()
    this.$nextTick(() => {
      window.addEventListener('resize', this.onResize);
    })
  },
  
  computed: {
    ...mapState(['allContest']),
    rows() {
        return this.allContest.length
      },
  },
}
</script>

<style scoped>
  .contestCard{
    width: 320px
  }

  li{
    list-style: none;
  }
</style>