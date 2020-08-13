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
        <button @click="googlelogin">login with google</button>
        <!-- google login -->
        <!-- <div class="g-signin2" data-onsuccess="onSignIn"></div> -->
        <div class="d-flex mt-3">
          <v-btn class="ml-auto" text-color="#5C6BC0" @click="gotofind()">비밀번호 찾기</v-btn>
        </div>
      </v-container>
    </v-form>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import firebase from 'firebase';

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
    googlelogin(){
      var provider = new firebase.auth.GoogleAuthProvider();
      provider.addScope('https://www.googleapis.com/auth/contacts.readonly');

      // firebase.auth().signInWithPopup(provider).then( result => {
      //   // This gives you a Google Access Token. You can use it to access the Google API.
      //   var token = result.credential.accessToken;
      //   // The signed-in user info.
      //  var user = result.user;
      //  var email = result.email;
       

      //   this.$router.push('/login');
      // }).catch( () => {
      //   // Handle Errors here.
      //   //var errorCode = error.code;
      //   //var errorMessage = error.message;
      //   // The email of the user's account used.
      //   var email = error.email;
      //   // The firebase.auth.Authedential type that was used.
      //   //var credential = error.credential;
      //   // ...
      // });
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