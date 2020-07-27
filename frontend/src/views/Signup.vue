<template>
  <v-card color="#FAFAFA">
    <v-card id="card-signup" class="mx-auto py-5 px-2 my-8" outlined max-width="800">
    <h3 class="text-md-center mb-5 h1-signup">회원가입해서 팀원을 만나보세요 :)</h3>

    <div>
      <v-col md="11" >
        <v-text-field v-model="signupData.email" label="아이디" outlined id="email"></v-text-field>
      </v-col>
    </div>

    <div>
      <v-col md="11">
        <v-text-field v-model="signupData.pwd" label="비밀번호" outlined id="pwd" type="password"></v-text-field>
      </v-col>
    </div>

    <div>
      <v-col md="11">
        <v-text-field v-model="signupData.pwd2" label="비밀번호 확인" outlined id="pwd2" type="password"></v-text-field>
        <p class="warning" v-if="signupData.pwd != signupData.pwd2"> 비밀번호가 일치하지 않습니다. </p>
      </v-col>
    </div>

    <div>
      <v-col md="11">
        <v-text-field v-model="signupData.name" label="이름" outlined id="name"></v-text-field>
      </v-col>
    </div>

    <div>
      <v-col md="11">
        <v-text-field v-model="signupData.nickname" label="닉네임" outlined id="nickname"></v-text-field>
        <v-btn depressed large class="white--text" color="#5C6BC0" @click="checkNickname">닉네임 중복 검사</v-btn>
      </v-col>
    </div>

    <div>
      <v-col md="11">
        <v-text-field v-model="signupData.age" label="나이" outlined id="age"></v-text-field>
      </v-col>
    </div>

    <p>성별 : </p>
    <select v-model="signupData.gender" name="gender" id="selectGender">
      <option value="male">남자</option>
      <option value="female">여자</option>
    </select>

    <span>
      <v-col md="11">
        <v-text-field v-model="signupData.major" label="전공" outlined id="major"></v-text-field>
      </v-col>
    </span>

    <span>
      <v-col md="11">
        <v-text-field class="d-inline" v-model="signupData.address" label="주소" outlined id="major"></v-text-field>
          <div class="text-center d-inline">
            <v-dialog
              v-model="dialog"
              width="500"
            >
            <template v-slot:activator="{ on, attrs }">
              <v-btn icon
              color="#5C6BC0"
              dark
              v-bind="attrs"
              v-on="on"
              class="d-inline"
              >
              <v-icon large color="#5C6BC0">mdi-magnify</v-icon>
              </v-btn>
            </template>

            <v-card class="py-5">
              <vue-daum-postcode@complete="getData" />
              <v-divider></v-divider>
            </v-card>
          </v-dialog>
        </div>
      </v-col>
    </span>

    <v-col class="text-center mx-auto">
      <div class="my-2">
        <v-btn depressed large class="white--text" color="#5C6BC0" @click="signup">가입하기</v-btn>
      </div>
    </v-col>
    </v-card>
  </v-card>

</template>

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
import axios from 'axios'
const SERVER_URL = 'http://localhost:8000'

export default {
  name: "Signup",
  data() {
    return {
      signupData: {
        email: null,
        pwd: null,
        pwd2: null,
        name: null,
        nickname: null,
        age: null,
        gender: null,
        major: null,
        address: null,
      },
      dialog: false
    }
  },
  methods:{
    signup() {
      console.log(this.signupData);
      this.$emit('submit-singup-data', this.signupData)
    },
    getData(data) {
      // 클릭한 데이터를 address에 저장
      this.signupData.address = data.address;
      this.dialog = false;
    },
    checkNickname(){

    },
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