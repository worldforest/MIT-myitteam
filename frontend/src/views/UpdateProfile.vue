<template>
  <div>
    <div class="text-center my-3">
      <h2> 프로필 수정 </h2>
    </div>
    <v-card color="#FAFAFA">
      <v-container>
        <div>
          <h3 class="ml-4">닉네임  </h3>
          <v-col xs="6" md="11" class="mx-auto">
            <v-text-field 
              v-model="updateProfile.nickname" label="닉네임" outlined id="nickname"></v-text-field>
          </v-col>
        </div>

        <div>
          <h3 class="ml-4">주소  </h3>
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
                  v-model="updateProfile.address" label="주소" outlined id="address"></v-text-field>
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

        <div>
          <h3 class="ml-4">자신을 한마디로 표현한다면?  </h3>
          <v-col xs="6" md="11" class="mx-auto">
            <v-text-field 
              v-model="updateProfile.description" label="ex) 어떤 개발자가 되고 싶은지 한 단어로 표현해주세요.(유쾌한, 행복한 등)" outlined id="description"></v-text-field>
          </v-col>
        </div>

        <div>
          <h3 class="ml-4"> 프로필 이미지 </h3>
          <v-col xs="6" md="11" class="mx-auto">
            <v-file-input
              label="프로필 이미지를 등록해주세요."
              filled
              prepend-icon="mdi-camera"
              accept = "image/*"
              v-model="updateProfile.src"
            ></v-file-input>
          </v-col>
        </div>

        <div>
          <h3 class="ml-4">비밀번호 : </h3>
          <v-col xs="6" md="11" class="mx-auto">
            <v-text-field class="passwordfont" :rules="[rules.required, rules.min]" v-model="updateData.pwd" label="비밀번호" outlined id="pwd" type="password"></v-text-field>
          </v-col>
        </div>

        <div>
          <h3 class="ml-4">비밀번호 확인 : </h3>
          <v-col xs="6" md="11" class="mx-auto">
            <v-text-field class="passwordfont" :rules="[rules.pwdcheck, rules.required]" v-model="updateData.pwd2" label="비밀번호 확인" outlined id="pwd2" type="password" @keypress.enter="ProfileUpdate(updateProfile, updateData)"></v-text-field>
          </v-col>
        </div>

         <v-col class="text-center mx-auto">
          <div class="my-2">
            <v-btn depressed x-large class="white--text" color="#5C6BC0" @click="ProfileUpdate(updateProfile, updateData)">수정하기</v-btn>
          </div>
        </v-col>

      </v-container>
    </v-card>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import axios from 'axios'
import Swal from 'sweetalert2'

// const SERVER_URL = 'http://localhost:9999/mit'
const SERVER_URL = 'https://i3b306.p.ssafy.io/mit'

export default {
  data () {
    return {
      updateData: {
        pwd: '',
        pwd2: '',
        email: ''
      },
      dialog: false,
      rules: {
        required: value => !!value || '필수 값 입니다.',
        min: v => v.length >= 4 || '비밀번호는 최소 8자리 이상 적어주세요.' ,
        emailMatch: () => ('이메일과 비밀번호가 일치하지 않습니다.'),
        pwdcheck: v => v == this.updateData.pwd || '비밀번호가 일치하지 않습니다',
      },
    }
  },
  computed : {
    ...mapState(['updateProfile', 'email'])
  },
  mounted() {
    window.scrollTo(0, 0)
  },
  methods: {
    getData(data) {
      // 클릭한 데이터를 address에 저장
      this.updateProfile.address = data.address;
      this.dialog = false;
    },
    ProfileUpdate(res,res2) {
      const formdata = new FormData();
      formdata.append('address', res.address)
      formdata.append('description', res.description)
      formdata.append('email', this.email)
      formdata.append('nickname', res.nickname)
      formdata.append('pwd', res2.pwd)
      formdata.append('file', res.src)
      axios.post(`${SERVER_URL}/api/user/update`, formdata)
        .then(() => {
          // alert('성공적으로 수정하였습니다.')
          Swal.fire({
					icon: 'success',
					text: '프로필 업데이트 완료!',
        })

          
          this.$router.push('/profile')
        })
    }
  }
}
</script>

<style>
  .passwordfont{
    font-family: Arial, Helvetica, sans-serif !important;
  }
</style>