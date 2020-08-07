<template>
  <div>
    {{pushData.email}}
    {{pushData.code}}
    <div v-if="isFlag">
      <h2 class="text-center mt-2">비밀번호 찾기</h2>
      <v-form>
        <v-container>
          <v-row>
            <v-col>
              <v-text-field
                label="Email"
                outlined
                v-model="pushData.email"
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
            @click="subEmail(pushData.email)"
          >Submit</v-btn>
        </v-container>
      </v-form>  
    </div>
    <div v-else>
      <h2 class="text-center mt-2">비밀번호 찾기</h2>
      <v-form>
        <v-container>
          <v-row>
            <v-col>
              <v-text-field
                label="Email"
                outlined
                v-model="pushData.email"
                :rules="emailRules"
                required
              ></v-text-field>
            </v-col>
          </v-row>
           <v-text-field v-model="pushData.code" label="코드" outlined id="code"></v-text-field>
           <v-btn
            width="100%"
            class="white--text"
            large
            color="#5C6BC0"
            @click="pushCode(pushData)"
          >Submit</v-btn>
        </v-container>
      </v-form>  
     
    </div>  
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";

export default {
  data() {
    return {
      pushData: {
        email: '',
        code: '',
      },
      emailRules: [
        v => !!v || "아이디는 필수 값 입니다.",
        v =>
          /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || "이메일 형식으로 작성해주세요."
      ],
    }
  },
  methods: {
    ...mapActions(["subEmail", "pushCode"]),
  },
  computed: {
    ...mapState(['isFlag'])
  }
}
</script>

<style>

</style>