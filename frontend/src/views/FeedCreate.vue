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
      <v-row>
        <v-col cols="12">
          <v-text-field
            v-model="feedData.email"
            label="Email"
            outlined
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
          <v-text-field
            v-model="feedData.file"
            label="File"
            outlined
            @change="fileSelect"
            type='file'
            ref="feedData.file"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
          <v-text-field
            v-model="feedData.nickname"
            label="Nickname"
            outlined
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
          <v-text-field
            v-model="feedData.tags"
            label="Nickname"
            outlined
          ></v-text-field>
        </v-col>
      </v-row>
        <!-- <v-row>
          <v-col md="11" class="mx-auto" >
            <div class="text-center">
              <v-dialog
                v-model="dialog"
                width="500" 
              >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field 
                v-bind="attrs"
                v-on="on"
                v-model="feedData.tags" label="해쉬태그" outlined id="address"></v-text-field>
              </template> -->
              <!-- <v-card class="py-5">
                <v-text-field label="Legal first name*"></v-text-field>
                <v-divider></v-divider>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
                  <v-btn color="blue darken-1" text @click="getTag()">Save</v-btn>
                </v-card-actions>
              </v-card> -->
              <!-- </v-dialog>
            </div>
          </v-col>
        </v-row> -->
      <v-btn width="100%" large color="#6200EA" @click="feedCreate(feedData)">피드등록</v-btn>
    </v-container>
  </v-form>
</template>
<script>
import { mapActions } from 'vuex'
export default {
  name: "FeedCreate",
  data() {
    return {
      feedData: {
        category: "",
        description: "",
        email: "",
        file: "",
        nickname: "",
        tags: "",
      },
      dialog: false
    }
  },
  methods:{
    ...mapActions(['feedCreate']),
    fileSelect() {
      this.feedData.file = this.$refs.feedData.file.files[0]
    },
    getTag(data) {
      this.feedData.tags = data
      console.log(data)
    }
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