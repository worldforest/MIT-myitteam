<template>
  <div>
    <h2 class="text-center mt-2">비밀번호 찾기</h2>
    <v-form>
      <v-container>
        <v-row>
          <v-col>
            <v-text-field
              label="Email"
              outlined
              v-model="email"
              :rules="emailRules"
              required
            ></v-text-field>
          </v-col>
        </v-row>
    <v-btn
      width="100%"
      class="white--text"
      large
      color="#5C6BC0"
      @click="subEmail(email)"
    >Submit</v-btn>
      </v-container>
    </v-form>
    
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  data() {
    return {
      email: '',
    }
  },
  methods: {
    ...mapActions(["subEmail"]),
  },
}
</script>

<style>

</style>