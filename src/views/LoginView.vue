<template>
  <v-form
    ref="form"
    class="ml-6 mr-6"
  >
    <v-text-field
      v-model="email"
      label="Email"
      required
    ></v-text-field>

    <v-text-field
      v-model="password"
      label="Password"
      type="password"
      required
    ></v-text-field>

    <v-btn
      color="success"
      class="mr-4"
      @click="login"
    >
      Login
    </v-btn>

    <v-btn
      color="error"
      class="mr-4"
      @click="$router.push({ name: 'register' })"
    >
      Register
    </v-btn>
  </v-form>
</template>

<script>
  import axios from "axios";
  export default {
    name: 'Login',
    data() {
      return {
        email: "",
        password: ""
      }
    },
    methods: {
      async login() {
        try { 
          var result = await axios.post("http://localhost:8081/admin/login", { email: this.email, password: this.password });
          if(result.data == null){
            alert("Credentiale incorecte!")
          }else{
            this.$store.commit('setId',result.data.id);
            this.$store.commit('setRole',result.data.role);
            this.$router.push({ name: 'books' });
          }
          ///verificare caz in care campurile sunt empty
          
        } catch (e) {
          alert("Eroare de server");
        }
      }
    }
  }
</script>
