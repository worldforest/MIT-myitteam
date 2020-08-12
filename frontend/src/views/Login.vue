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
              hint="At least 8 characters"
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
        <div class="g-signin2" data-onsuccess="onSignIn"></div>
        <div class="d-flex mt-3">
          <v-btn class="ml-auto" color="primary" @click="gotofind()">비밀번호 찾기</v-btn>
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
        required: (value) => !!value || "Required.",
        min: (v) => v.length >= 4 || "Min 4 characters",
        emailMatch: () => "The email and password you entered don't match",
      },
      emailRules: [
        (v) => !!v || "E-mail is required",
        (v) =>
          /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) ||
          "E-mail must be valid",
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
    ///소셜 로그인///
    onSignIn(googleUser) {
      var profile = googleUser.getBasicProfile();
      console.log("ID: " + profile.getId()); // Do not send to your backend! Use an ID token instead.
      console.log("Name: " + profile.getName());
      console.log("Image URL: " + profile.getImageUrl());
      console.log("Email: " + profile.getEmail()); // This is null if the 'email' scope is not present.
    },
    ///소셜 로그인///
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