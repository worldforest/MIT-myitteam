<template>
  <v-card class="mx-auto py-5 my-8" max-width="500">
    <h1 class="text-md-center mb-2">Signup</h1>
    <div>
      <v-col cols="12" sm="6" md="11" class="mx-auto">
        <v-text-field label="아이디" outlined></v-text-field>
      </v-col>
    </div>

    <div>
      <v-col cols="12" sm="6" md="11" class="mx-auto">
        <v-text-field label="비밀번호" outlined></v-text-field>
      </v-col>
    </div>

    <div>
      <v-col cols="12" sm="6" md="11" class="mx-auto">
        <v-text-field label="비밀번호 확인" outlined></v-text-field>
      </v-col>
    </div>

    <div>
      <v-col cols="12" sm="6" md="11" class="mx-auto">
        <v-text-field label="나이" outlined></v-text-field>
      </v-col>
    </div>

    <div>
      <v-col cols="12" sm="6" md="11" class="mx-auto">
        <v-text-field label="성별" outlined></v-text-field>
      </v-col>
    </div>

    <div>
      <v-col cols="12" sm="6" md="11" class="mx-auto">
        <v-text-field label="전공" outlined></v-text-field>
      </v-col>
    </div>

    <div>
      <v-col cols="12" sm="6" md="11" class="mx-auto">
        <v-text-field label="주소" outlined></v-text-field>
      </v-col>
    </div>

    <v-col class="text-center mx-auto" cols="12" sm="4">
      <div class="my-2">
        <v-btn depressed large color="primary">가입하기</v-btn>
      </div>
    </v-col>
  </v-card>
</template>

<script>
export default {
  name: "Signup",
  data() {
    return {
      signupData: {
        userid: null,
        password1: null,
        password2: null,
        age: null,
        sex: null,
        major: null,
        address: null,
      }
    }
  },
  methods:{
    signup() {
      this.$emit('', this.signupData)
    }
  }

}
</script>

<style>

</style>