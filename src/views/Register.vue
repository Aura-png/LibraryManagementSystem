<template>
  <v-form
    ref="form"
    class="ml-6 mr-6"
  >
    <v-text-field
      v-model="name"
      label="Name"
      type="name"
      required
    ></v-text-field>

    <v-text-field
      v-model="email"
      label="Email"
      type="email"     
      required
    ></v-text-field>

    <v-text-field
      v-model="password"
      label="Password"
      type="password"
      required
    ></v-text-field>

    <v-text-field
      v-model="address"
      label="Address"
      type="addres"
      required
    ></v-text-field>

    <v-btn
      color="success"
      class="mr-4"
      @click="register"
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
        name: "",
        email: "",
        password: "",
        address: ""
      }
    },
    methods: {
      async register() {
        try { 
          var result = await axios.post("http://localhost:8081/user/register", { name: this.name, email: this.email, password: this.password, address: this.address});
          this.$router.push({ name: 'login' });
        } catch (e) {
          
        }
      }
    }
  }
</script>
