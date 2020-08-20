<template>
  <div class="container">
    <h4>접수 기간 : <span class="local">{{ club.start }} ~ {{ club.end }} </span></h4>
    <h4 class="mt-10">총 시상금 : {{ club.reward }} </h4>
    <div class="mt-10 text-center" >      
      <img :src="club.imagesrc" alt="" style='width:100%'>
    </div>
    <div class="mt-10" style="white-space:pre-line;">{{ club.description }}</div>
    
  </div>
</template>

<script scoped>
export default {
  name: 'Intro',
  props: {
    club: Array,
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

</style>