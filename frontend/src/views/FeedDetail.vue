<template>
  <div>
    작성자: {{ detailFeed.nickname }}
    <hr>
    <div class="d-flex justify-end mb-4">
      <div>작성일자: {{ detailFeed.writedate }}</div>
      <div class="ml-4">조회수:  {{ detailFeed.views }}</div>
    </div>

    <div>      
      <img :src="detailFeed.src" alt="" class="mx-auto">
    </div>
    <div>
      {{ detailFeed.description}}
    </div>
    <div>
      {{ detailFeed.tag }}
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  computed: {
    ...mapState(['detailFeed']),
  },
}
</script>

<style>

</style>
