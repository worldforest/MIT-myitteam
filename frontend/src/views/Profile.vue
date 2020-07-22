<template>


  <div>
    <v-layout no wrap>
      <v-flex xs3 class="box">
        <v-avatar
          width="100%"
          height="80%"
        >
          <img src="../assets/user.png" alt=""/>
        </v-avatar>
      </v-flex>
      <v-flex xs9 class="box">
        <div class="ml-5">
          <span>"((아이디 표시하세요))"</span>

            <v-btn
              class="ml-2"
              color="primary"
            >
              팔로우
            </v-btn>
        </div>
        <div class="mt-4 ml-5">
          <span>팔로워</span>
          <span>팔로우</span>
        </div>
      </v-flex>
    </v-layout>
  </div>
  <!-- <v-container>
    <div class="d-flex flex-row mg">
      
          <div class="ml-10">
            <v-avatar size="200">
            <img
                src="../assets/user.png"
                alt="등록된 프로필 이미지"
            >
            </v-avatar>
          </div>
        <div class="ml-16">
          <span>"((아이디 표시하세요))"</span>

          <v-btn
            class="mx-5"
            color="primary"
          >
            팔로우
          </v-btn>
          <br>
          <div class="mt-4">
            <span>팔로워</span>
            <span>팔로우</span>
          </div>
        </div>
    </div>
  </v-container>
   -->
</template>

<script>
export default {
name: 'Profile',
components: {

}
}
</script>

<style>
  img::before {
    border: 1px solid rgba(var(--jb7,0,0,0),.0975);
    border-radius: 50%;
  }

  .h1 {
    font-size: 30px;
  }
</style>