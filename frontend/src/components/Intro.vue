<template>
  <div class="container">
    <h3>접수 기간</h3>
    <div class="pill ma-2"><span >D-23</span></div>
    <div class="ml-16 mt-2"><span >{{ club.start }} ~ {{ club.end }}</span></div>
    <h3 class="mt-10">주최</h3>
    <div class="ma-2"><span>{{ club.host }}</span></div>
    <h3 class="mt-10">총 시상금</h3>
    <div class="ma-2"><span>{{ club.reward }}</span></div>
    <div class="mt-10 text-center" >      
      <img :src="club.imagesrc" alt="" style='width:100%'>
    </div>
    <div class="mt-10" style="white-space:pre-line;">{{ club.description }}</div>
    
  </div>
</template>

<script>
export default {
  name: 'Intro',
  props: {
    club: Object,
  },
  data() {
    return {
      windowWidth: window.innerWidth,
      txt: '',
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
  },

  beforeDestroy() { 
    window.removeEventListener('resize', this.onResize); 
  },

  methods: {  
    onResize() {
      this.windowWidth = window.innerWidth
    },
  },

}
</script>

<style scoped>
  .pill {
    position: absolute;
    padding: 0 8px;
    line-height: 20px;
    border-radius: 10px;
    color: #fff;
    font-size: 14px;
    font-weight: bold;
    background-color: #a6a6a6;
  }

  .container {
    /* max-width: 50%; */
  }

</style>