<template>
  <v-card color="#FAFAFA">
    <v-card id="card-signup" class="mx-auto py-5 px-2 my-8" outlined max-width="800">
    <h2 class="text-center mb-5 h1-signup">회원가입해서 팀원을 만나보세요 :)</h2>

    <div>
      <v-col md="11" class="mx-auto">
        <v-text-field :rules="emailRules" v-model="signupData.email" label="아이디" outlined id="email"></v-text-field>
      </v-col>
    </div>

    <div>
      <v-col md="11" class="mx-auto">
        <v-text-field :rules="[rules.required, rules.min]" v-model="signupData.pwd" label="비밀번호" outlined id="pwd" type="password"></v-text-field>
      </v-col>
    </div>

    <div>
      <v-col md="11" class="mx-auto">
        <v-text-field :rules="[rules.pwdcheck]" v-model="signupData.pwd2" label="비밀번호 확인" outlined id="pwd2" type="password"></v-text-field>
      </v-col>
    </div>

    <div>
      <v-col md="11" class="mx-auto">
        <v-text-field 
          v-model="signupData.name" label="이름" outlined id="name"></v-text-field>
      </v-col>
    </div>

    <div class="mx-7">
      <v-row class="mx-auto">
        <v-col md="10" class="mx-auto">
        <v-text-field v-model="signupData.nickname" label="닉네임" outlined id="nickname"></v-text-field>
        </v-col>
        <v-col md="2" class="mx-auto">
        <v-btn depressed class="white--text" color="#5C6BC0" @click="checkNickname(signupData)">중복 검사</v-btn>
        </v-col>
      </v-row>
      <p v-if="usedNickName">사용중인 닉네임 입니다.</p>
    </div>

    <div>
      <v-col md="11" class="mx-auto">
        <v-text-field :rules="ageRules" v-model="signupData.age" label="나이" outlined id="age"></v-text-field>
      </v-col>
    </div>

    <div>
      <v-col class="d-flex mx-auto" cols="12" sm="6" md="11">
        <v-select
          :items="selectGender"
          label="성별"
          outlined
          v-model="signupData.gender"
        ></v-select>
      </v-col>
    </div>

    <span>
      <v-col md="11" class="mx-auto">
        <v-text-field v-model="signupData.major" label="전공" outlined id="major"></v-text-field>
      </v-col>
    </span>

    <div>
      <v-row class="mx-auto">
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
              v-model="signupData.address" label="주소" outlined id="address"></v-text-field>
            </template>

            <v-card class="py-5">
              <vue-daum-postcode@complete="getData" />
              <v-divider></v-divider>
            </v-card>
            </v-dialog>
          </div>
        </v-col>
      </v-row>
    </div>

    <v-col class="text-center mx-auto">
      <div class="my-2">
        <v-btn depressed large class="white--text" color="#5C6BC0" @click="signup(signupData)">가입하기</v-btn>
      </div>
    </v-col>
    </v-card>
  </v-card>

</template>

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
import axios from 'axios'
const SERVER_URL = 'http://localhost:8000'
import { mapActions } from 'vuex'

export default {
  name: "Signup",
  data() {
    return {
      signupData: {
        email: "",
        pwd: "",
        pwd2: "",
        name: "",
        nickname: "",
        age: "",
        gender: "",
        major: "",
        address: "",
      },
      dialog: false,
      selectGender: ['남', '여'],
      rules: {
        required: value => !!value || '필수 값 입니다.',
        min: v => v.length >= 4 || '비밀번호는 최소 8자리 이상 적어주세요.' ,
        emailMatch: () => ('The email and password you entered don\'t match'),
        pwdcheck: v => v == this.signupData.pwd || '비밀번호가 일치하지 않습니다',
      },
      emailRules: [
        v => !!v || "이메일은 필수 값 입니다.",
        v =>
          /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || "이메일 형식으로 작성해주세요."
      ],
      ageRules: [
        v => /^[0-9]+$/.test(v) || "숫자만 작성해주세요."
      ],
    }
  },
  methods:{
    ...mapActions(['signup']),
    // ...mapActions(['checkNickname']),
    // checkNickname(){
    //   axios.post('http://localhost:9999/mit/api/user/checkNickname',{
    //     nickname: this.signupData.nickname,
    //   }).then(res => {
    //     alert("사용중인 닉네임이 존재합니다.");
    //   })
    //   .catch(error => {
    //     this.$router.push("/signup");
    //     alert("사용 가능한 닉네임입니다.");
    //   })
    // },
    getData(data) {
      // 클릭한 데이터를 address에 저장
      this.signupData.address = data.address;
      this.dialog = false;
    },
    isNumber(event){
      if(event.keyCode < 48 || event.keyCode > 57){
        event.returnValue = false;
      }
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