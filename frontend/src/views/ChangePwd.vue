<template>
  <div>
    <h2 class="text-center mt-2">비밀번호 변경</h2>
    <div>
      <h3 class="ml-4">변경할 비밀번호 : </h3>
      <v-col xs="6" md="11" class="mx-auto">
        <v-text-field class="passwordfont" :rules="[rules.required, rules.min]" v-model="pwdData.pwd" label="비밀번호" outlined id="pwd" type="password"></v-text-field>
      </v-col>
    </div>

    <div>
      <h3 class="ml-4">비밀번호 확인 : </h3>
      <v-col xs="6" md="11" class="mx-auto">
        <v-text-field class="passwordfont" :rules="[rules.pwdcheck, rules.required]" v-model="pwdData.pwd2" label="비밀번호 확인" outlined id="pwd2" type="password"></v-text-field>
      </v-col>
    </div>
    
    <v-col class="text-center mx-auto">
      <div class="my-2">
        <v-btn depressed x-large class="white--text" color="#5C6BC0" @click="check(pwdData.pwd, pwdData.pwd2, pwdToken)">비밀번호 변경</v-btn>
      </div>
    </v-col>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from 'axios'

// const SERVER_URL = 'http://localhost:9999/mit'
const SERVER_URL = 'http:/i3b306.p.ssafy.io:9999/mit'

export default {
  data() {
    return {
      pwdData: {
        pwd: "",
        pwd2: "",
        token: this.pwdToken
      },
       rules: {
        required: value => !!value || '필수 값 입니다.',
        min: v => v.length >= 4 || '비밀번호는 최소 8자리 이상 적어주세요.' ,
        pwdcheck: v => v == this.pwdData.pwd || '비밀번호가 일치하지 않습니다',
      },
    }
  },
  methods: {
    check(res, res2, res3) {
      if (res === res2) {
        if (res.length >= 8) {
          axios.put(`${SERVER_URL}/api/user/pwd?pwd=${res}&token=${res3}`)
            .then(() => {
              alert('정상적으로 변경되었습니다.')
              this.$router.push('/login')
            })
            .catch((err) => {
              console.log(err.data)
            })
        }
        else {
          alert('비밀번호를 8글자 이상 입력해주세요.')
        }
      }
      else {
        alert('비밀번호가 일치하지 않습니다.')
      }
    }
  },
  computed: {
    ...mapState(['pwdToken'])
  }
}

</script>

<style>
.passwordfont{
    font-family: Arial, Helvetica, sans-serif !important;
  }
</style>