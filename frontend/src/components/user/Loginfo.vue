<template>
  <v-form>
    <v-container>
      <v-row>
        <v-col>
          <v-text-field
            label="Email"
            outlined
<<<<<<< HEAD
            v-model="loginData.email"
            :rules="emailRules"
            required
=======
>>>>>>> 514fb2ec95113211bdb42b774dfed3228edd3631
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <v-text-field
            label="Password"
            outlined
<<<<<<< HEAD
            v-model="loginData.pwd"
            :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="[rules.required, rules.min]"
            :type="show2 ? 'text' : 'password'"
            name="input-10-2"
            hint="At least 8 characters"
            class="input-group--focused"
            @click:append="show2 = !show2"
            @keypress.enter="$emit('login', loginData)"
          ></v-text-field>
        </v-col>
      </v-row>
          <v-btn width="100%" large color="#6200EA" @click="$emit('login', loginData)" >Login</v-btn>
=======
          ></v-text-field>
        </v-col>
      </v-row>
          <v-btn width="100%" large color="primary">Login</v-btn>
>>>>>>> 514fb2ec95113211bdb42b774dfed3228edd3631
    </v-container>
  </v-form>
</template>

<script>

export default {
  name: 'Loginfo',
  data () {
    return {
      loginData: {
<<<<<<< HEAD
        email: null,
        pwd: null,
      },
      show2: false,
      password: 'Password',
      rules: {
        required: value => !!value || 'Required.',
        min: v => v.length >= 4 || 'Min 8 characters' ,
        emailMatch: () => ('The email and password you entered don\'t match'),
      },
      emailRules: [
        v => !!v || "E-mail is required",
        v =>
          /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || "E-mail must be valid"
      ],
    }
  },
}

=======
        username: null,
        password: null,
      }
    }
  }
};
>>>>>>> 514fb2ec95113211bdb42b774dfed3228edd3631
</script>