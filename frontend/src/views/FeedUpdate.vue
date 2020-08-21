<template>
  <div>
    <v-form>
      <v-container>
        <v-row>
          <v-col cols="12">
            <v-text-field
              v-model="upFeed.description"
              label="Description"
              outlined
            ></v-text-field>
          </v-col>
        </v-row>
        
        <v-file-input
          label="Image input"
          filled
          prepend-icon="mdi-camera"
          accept = "image/*"
          v-model="upFeed.src"
        ></v-file-input>
        
        <v-btn width="100%" large class="white--text" color="#6200EA" @click="feedUpdate(upFeed)">피드등록</v-btn>
      </v-container>
    </v-form>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'

export default {
  data() {
    return {

    }
  },

  methods: {
    ...mapActions(['feedUpdate'])    
  },

  mounted() {
    window.scrollTo(0, 0)
  },

  
  
  computed: {
    ...mapState(['upFeed'])
  }
}
</script>

<style>

</style>