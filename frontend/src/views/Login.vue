<template>
  <div class='cont15'>      
    <div  :class="[ {'login-box': windowWidth >= 767, 'login-box2' : windowWidth < 767 } ]">
      <h2>Login</h2>
      <form>
        <div class="user-box">
          <input type="text" v-model="loginData.email" :rules="emailRules" required outlined>
          <label>이메일을 입력해주세요.</label>
        </div>
        <div class="user-box">
          <input type="password" name="" required="" v-model="loginData.pwd" :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
              :rules="[rules.required, rules.min]"
              hint="비밀번호는 최소 4자리 이상입니다."
              class="input-group--focused passwordfont"
              @click:append="show2 = !show2"
              @keypress.enter="login(loginData)">
          <label>비밀번호를 입력해주세요.</label>
        </div>
        <div class="d-flex">
          <a @click="login(loginData)">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            로그인
          </a>
          <a class="ml-auto"  @click="gotofind()">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            비밀번호 찾기
          </a>
        </div>
        <div class="d-flex">
          <a class="mx-auto" href="/signup">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            회원가입하기
          </a>
        </div>
        <div>
          <KakaoLogin
            class="mt-5"
            :api-key="key"
            image="kakao_login_btn_small"
            :on-success=onSuccess
            :on-failure=onFailure
            />
        </div>
      </form>
    </div>
    
  </div>
</template>

<script>
import { mapActions } from "vuex";

import KakaoLogin from 'vue-kakao-login'

let onSuccess = () => {
  // console.log(data)
  // console.log("success")
}
let onFailure = () => {
  // console.log(data)
  // console.log("failure")
}


export default {
  name: "Loginfo",
  components: {KakaoLogin},
  data() {
    return {
      loginData: {
        email: "",
        pwd: "",
      },
      key : process.env.dcbbf2565c8cbfbd6437c6bd4e215c3d,
      windowWidth: window.innerWidth,
      show2: false,
      password: "Password",
      rules: {
        required: (value) => !!value || "비밀번호를 입력해주세요.",
        min: (v) => v.length >= 8 || "비밀번호는 8자리 이상입니다.",
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
    onSuccess,
    onFailure
  },
  mounted() {
    this.$nextTick(() => {
      window.addEventListener("resize", this.onResize);
    });
    window.scrollTo(0, 0)
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

  html {
    height: 100%;
  }
  body {
    margin:0;
    padding:0;
    font-family: sans-serif;
    background: linear-gradient(#141e30, #243b55);
  }

  .login-box {
    position: absolute;
    top: 50%;
    left: 50%;
    width: 400px;
    padding: 40px;
    transform: translate(-50%, -50%);
    background: rgba(0,0,0,.5);
    box-sizing: border-box;
    box-shadow: 0 15px 25px rgba(0,0,0,.6);
    border-radius: 10px;
  }

  .login-box2 {
    position: absolute;
    top: 40%;
    left: 50%;
    width: 300px;
    padding: 40px;
    transform: translate(-50%, -50%);
    background: rgba(0,0,0,.5);
    box-sizing: border-box;
    box-shadow: 0 15px 25px rgba(0,0,0,.6);
    border-radius: 10px;
  }

  .login-box h2 {
    margin: 0 0 30px;
    padding: 0;
    color: #fff;
    text-align: center;
  }

  .login-box2 h2 {
    margin: 0 0 30px;
    padding: 0;
    color: #fff;
    text-align: center;
  }

  

  .login-box .user-box {
    position: relative;
  }

  .login-box2 .user-box {
    position: relative;
  }

  .login-box .user-box input {
    width: 100%;
    padding: 10px 0;
    font-size: 16px;
    color: #fff;
    margin-bottom: 30px;
    border: none;
    border-bottom: 1px solid #fff;
    outline: none;
    background: transparent;
  }
  .login-box .user-box label {
    position: absolute;
    top:0;
    left: 0;
    padding: 10px 0;
    font-size: 16px;
    color: #fff;
    pointer-events: none;
    transition: .5s;
  }

  .login-box2 .user-box input {
    width: 100%;
    padding: 10px 0;
    font-size: 16px;
    color: #fff;
    margin-bottom: 30px;
    border: none;
    border-bottom: 1px solid #fff;
    outline: none;
    background: transparent;
  }
  .login-box2 .user-box label {
    position: absolute;
    top:0;
    left: 0;
    padding: 10px 0;
    font-size: 16px;
    color: #fff;
    pointer-events: none;
    transition: .5s;
  }

  .login-box .user-box input:focus ~ label,
  .login-box .user-box input:valid ~ label {
    top: -20px;
    left: 0;
    color: #03e9f4;
    font-size: 12px;
  }

  .login-box2 .user-box input:focus ~ label,
  .login-box2 .user-box input:valid ~ label {
    top: -20px;
    left: 0;
    color: #03e9f4;
    font-size: 12px;
  }

  .login-box form a {
    position: relative;
    display: inline-block;
    padding: 10px 20px;
    color: #03e9f4;
    font-size: 16px;
    text-decoration: none;
    text-transform: uppercase;
    overflow: hidden;
    transition: .5s;
    margin-top: 40px;
    letter-spacing: 4px
  }

  .login-box2 form a {
    position: relative;
    display: inline-block;
    padding: 10px 20px;
    color: #03e9f4;
    font-size: 16px;
    text-decoration: none;
    text-transform: uppercase;
    overflow: hidden;
    transition: .5s;
    margin-top: 40px;
    letter-spacing: 4px
  }

  .login-box a:hover {
    background: #03e9f4;
    color: #fff;
    border-radius: 5px;
    box-shadow: 0 0 5px #03e9f4,
                0 0 25px #03e9f4,
                0 0 50px #03e9f4,
                0 0 100px #03e9f4;
  }

  .login-box2 a:hover {
    background: #03e9f4;
    color: #fff;
    border-radius: 5px;
    box-shadow: 0 0 5px #03e9f4,
                0 0 25px #03e9f4,
                0 0 50px #03e9f4,
                0 0 100px #03e9f4;
  }

  .login-box a span {
    position: absolute;
    display: block;
  }

  .login-box2 a span {
    position: absolute;
    display: block;
  }

  .login-box a span:nth-child(1) {
    top: 0;
    left: -100%;
    width: 100%;
    height: 2px;
    background: linear-gradient(90deg, transparent, #03e9f4);
    animation: btn-anim1 1s linear infinite;
  }

  .login-box2 a span:nth-child(1) {
    top: 0;
    left: -100%;
    width: 100%;
    height: 2px;
    background: linear-gradient(90deg, transparent, #03e9f4);
    animation: btn-anim1 1s linear infinite;
  }

  @keyframes btn-anim1 {
    0% {
      left: -100%;
    }
    50%,100% {
      left: 100%;
    }
  }

  .login-box a span:nth-child(2) {
    top: -100%;
    right: 0;
    width: 2px;
    height: 100%;
    background: linear-gradient(180deg, transparent, #03e9f4);
    animation: btn-anim2 1s linear infinite;
    animation-delay: .25s
  }

  .login-box2 a span:nth-child(2) {
    top: -100%;
    right: 0;
    width: 2px;
    height: 100%;
    background: linear-gradient(180deg, transparent, #03e9f4);
    animation: btn-anim2 1s linear infinite;
    animation-delay: .25s
  }

  @keyframes btn-anim2 {
    0% {
      top: -100%;
    }
    50%,100% {
      top: 100%;
    }
  }

  .login-box a span:nth-child(3) {
    bottom: 0;
    right: -100%;
    width: 100%;
    height: 2px;
    background: linear-gradient(270deg, transparent, #03e9f4);
    animation: btn-anim3 1s linear infinite;
    animation-delay: .5s
  }

  .login-box2 a span:nth-child(3) {
    bottom: 0;
    right: -100%;
    width: 100%;
    height: 2px;
    background: linear-gradient(270deg, transparent, #03e9f4);
    animation: btn-anim3 1s linear infinite;
    animation-delay: .5s
  }

  @keyframes btn-anim3 {
    0% {
      right: -100%;
    }
    50%,100% {
      right: 100%;
    }
  }

  .login-box a span:nth-child(4) {
    bottom: -100%;
    left: 0;
    width: 2px;
    height: 100%;
    background: linear-gradient(360deg, transparent, #03e9f4);
    animation: btn-anim4 1s linear infinite;
    animation-delay: .75s
  }

  .login-box2 a span:nth-child(4) {
    bottom: -100%;
    left: 0;
    width: 2px;
    height: 100%;
    background: linear-gradient(360deg, transparent, #03e9f4);
    animation: btn-anim4 1s linear infinite;
    animation-delay: .75s
  }

  @keyframes btn-anim4 {
    0% {
      bottom: -100%;
    }
    50%,100% {
      bottom: 100%;
    }
  }
</style>