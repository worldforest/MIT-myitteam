<template>
  <div :class="[ {'cont15': windowWidth >= 767 } ]">
    <v-form>
      <v-container>
        <v-row>
          <v-col>
            <v-text-field
              label="Email"
              outlined
              v-model="loginData.email"
              :rules="emailRules"
              required
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-text-field
              label="Password"
              outlined
              v-model="loginData.pwd"
              :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
              :rules="[rules.required, rules.min]"
              :type="show2 ? 'text' : 'password'"
              name="input-10-2"
              hint="비밀번호는 최소 4자리 이상입니다."
              class="input-group--focused passwordfont"
              @click:append="show2 = !show2"
              @keypress.enter="login(loginData)"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-btn
          width="100%"
          class="white--text"
          large
          color="#5C6BC0"
          @click="login(loginData)"
        >Login</v-btn>
        <div class="d-flex mt-3">
          <v-btn class="ml-auto" text-color="#5C6BC0" @click="gotofind()">비밀번호 찾기</v-btn>
        </div>
      </v-container>
    </v-form>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "Loginfo",
  data() {
    return {
      loginData: {
        email: "",
        pwd: "",
      },
      windowWidth: window.innerWidth,
      show2: false,
      password: "Password",
      rules: {
        required: (value) => !!value || "비밀번호를 입력해주세요.",
        min: (v) => v.length >= 4 || "비밀번호는 4자리 이상입니다.",
        emailMatch: () => "이메일과 비밀번호가 일치하지 않습니다.",
      },
      emailRules: [
        (v) => !!v || "이메일을 입력해주세요.",
        (v) =>
          /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) ||
          "이메일 형식에 맞지 않습니다.",
      ],
    };
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.onResize);
  },
  methods: {
    ...mapActions(["login"]),
    onResize() {
      this.windowWidth = window.innerWidth;
    },
    /// 비밀번호 찾기 페이지//
    gotofind() {
      this.$router.push('/findpwd')
    },
  },
  mounted() {
    this.$nextTick(() => {
      window.addEventListener("resize", this.onResize);
    });
  },
};
</script>

<style scoped>
.cont15 {
  margin: 0 15%;
}
.passwordfont{
  font-family: Arial, Helvetica, sans-serif !important;
}
</style>