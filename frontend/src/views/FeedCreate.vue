<template>
  <v-form>
    <v-container>
      <v-row>
        <v-col cols="12">
          <v-text-field
            v-model="feedData.category"
            label="Category"
            outlined
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
          <v-text-field
            v-model="feedData.description"
            label="Description"
            outlined
          ></v-text-field>
        </v-col>
      </v-row>

      <!-- <v-file-input
        label="Image input"
        filled
        prepend-icon="mdi-camera"
        accept = "image/*"
        v-model="feedData.file"
      ></v-file-input> -->

      <!-- <v-row>
        <v-col cols="12">
          <v-text-field
            accept = "image/*"
            v-model="feedData.file"
            label="File"
            outlined
            @change="fileSelect"
            type='file'
            ref="photoimage"
          ></v-text-field>
        </v-col>
      </v-row> -->

      <form @change="fileSelect" enctype="multipart/form-data">
        <input type="file" name="photo" ref="photoimage" accept="image/*">
      </form>
      
      <v-row>
        <v-col cols="12">
          <v-text-field
            v-model="feedData.tags"
            label="Tags"
            outlined
          ></v-text-field>
        </v-col>
      </v-row>
      <v-btn width="100%" large color="#6200EA" @click="feedCreate(feedData)">피드등록</v-btn>
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
        category: "",
        description: "",
        email : "",
        file: "",
        tags: "",
        imageUrl : "",
      },
      dialog: false
    }
  },
  methods:{
    ...mapActions(['feedCreate']),
    fileSelect() {
      // const reader = new FileReader()
      // reader.readAsDataURL(this.feedData.file)
      // reader.onload = () => {
      //   this.imageUrl = reader.result
      // }
      // console.log(this.$refs.feedData.file.files[0])
      this.feedData.file = this.$refs.photoimage.files[0]
    },
  },
  computed: {
    ...mapState(['email'])
  },
  mounted () {
    this.feedData.email = this.$store.state.email
  }
  
}
</script>
<style>
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