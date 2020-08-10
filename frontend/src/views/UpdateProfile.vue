<template>
  <div>
    <h2> 프로필 수정 </h2>
    {{updateProfile.nickname}}
    {{updateProfile.description}}
    {{updateProfile.src}}

    <hr>
    {{updateProfile}}
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  date () {
    return {

    }
  },
  computed : {
    ...mapState(['updateProfile'])
  }
}
</script>

<style>

</style>