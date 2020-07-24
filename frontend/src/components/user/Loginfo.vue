<template>
  <v-form>
    <v-container>
      <v-row>
        <v-col>
          <v-text-field
            label="Email"
            outlined
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <v-text-field
            label="Password"
            outlined
          ></v-text-field>
        </v-col>
      </v-row>
          <v-btn width="100%" large color="primary">Login</v-btn>
    </v-container>
  </v-form>
</template>

<script>

export default {
  name: 'Loginfo',
  data () {
    return {
      loginData: {
        username: null,
        password: null,
      }
    }
  }
};
</script>