<template>
  <div>
    <div class="bg_img1" v-if="windowWidth >= 620">
      <div class="homelogo">
        <img src="@/images/logo2.png" alt="" style="width:160px; height:70px">
      </div>
      <v-row>
        <div class="moongu barobtn">
          나와 맞는 <br>
          팀원을  <br>
          찾고 싶을 때 <br>
        </div>
        <router-link to="/home" style="color:white; text-decoration:none; margin-top:12%" class="mx-2">
          <v-btn fab dark color="black" style="width:15vw; height:15vw;">          
            <span style="font-size:2.5vw;">모집하러 가기</span>
          </v-btn>
        </router-link>
      </v-row>
    </div>
    <div class="bg_img2" v-else>
      <div class="moongu2 text-center">
        <img src="@/images/logo2.png" class="mb-4" alt="" style="width:160px; height:70px"> <br>
        나와 맞는 <br>
        팀원을 <br>
        찾고 싶으면 <br>
      </div>
      <div>
        <router-link to="/home" style="color:white; text-decoration:none; margin-top:12%" class="mx-2">
          <div class="my-2 text-center">
            <v-btn large color="black" style="width:50vw; color:white; font-size:5vw">모집하러 가기</v-btn>
          </div>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      windowWidth: window.innerWidth,
    }
  },
  watch: {
    windowWidth(newWidth, oldWidth) {
     this.txt = `it changed to ${newWidth} from ${oldWidth}`;
    }
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
  methods: {
    onResize() {
      this.windowWidth = window.innerWidth
    },
  }
}
</script>

<style scoped>
  .bg_img1 {
    /* z-index: -1024; */
    position: fixed;
    background: linear-gradient( rgba(1, 1, 1, 0.3), rgba(1, 1, 1, 0.3) ), url('../images/Team.jpg');
    background-size: cover;
    height: 100vh;
    width: 100vw;
    background-color: black;
    background-attachment: fixed;
    /* overflow-y: scroll; */
  }

  .bg_img2 {
    /* z-index: -1024; */
    position: fixed;
    background: linear-gradient( rgba(1, 1, 1, 0.3), rgba(1, 1, 1, 0.3) ), url('../images/Team_mobile.jpg');
    background-size: cover;
    height: 100vh;
    width: 100vw;
    background-color: black;
    background-attachment: fixed;
    /* overflow-y: scroll; */
  }

  .moongu {
    margin-top: 10%;
    margin-left: 15%;
    font-size: 5rem;
    color: white;
  }

  .moongu2 {
    font-size: 3rem;
    color: white;
    margin-top: 50%;
  }

  .homelogo{
    margin-top: 5%;
    margin-left: 15%;
  }
  
  .barobtn{
    margin-right: 15%;
  }

</style>