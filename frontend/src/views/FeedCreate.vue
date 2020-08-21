<template>
  <v-form>
    <v-container>
      <v-row>
        <v-col cols="12">
          <v-text-field
            v-model="feedData.description"
            label="피드 설명을 입력해주세요."
            outlined
          ></v-text-field>
        </v-col>
      </v-row>
      
      <v-file-input
        label="피드 이미지를 입력해주세요."
        filled
        prepend-icon="mdi-camera"
        accept = "image/*"
        v-model="feedData.file"
      ></v-file-input>

      <v-row>
        <v-col cols="12">
          <v-text-field
            v-model="feedData.tags"
            label="#으로 태그를 입력해주세요."
            outlined
          ></v-text-field>
        </v-col>
      </v-row>
      <v-btn width="100%" large class="white--text" color="#6200EA" @click="feedCreate(feedData)">피드 등록</v-btn>
    </v-container>
  </v-form>
</template>
<script>
import { mapState, mapActions } from 'vuex'

export default {
  name: "FeedCreate",
  data() {
    return {
      feedData: {
        description: "",
        email : "",
        file: "",
        tags: "",
      },
      dialog: false
    }
  },
  methods:{
    ...mapActions(['feedCreate']),
  },
  computed: {
    ...mapState(['email'])
  },
  mounted () {
    this.feedData.email = this.$store.state.email
    window.scrollTo(0, 0)
  }
  
}
</script>
<style scoped>
  .h1-signup {
    color: rgb(92, 107, 192);
  }
  #card-signup {
    border:1px solid rgb(92, 107, 192);
  }
  #selectGender{
    width: 100px;
    border: 1px solid black;
    border-radius: 0.5rem;
  }
  #selectGender > option {
    border: 1px solid black;
  }
  .warning{
    color : red;
    background-color: white;
  }
</style>