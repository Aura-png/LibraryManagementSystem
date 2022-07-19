<template>

 <v-list three-line>
        <v-list-item v-for="(user) in users" :key="user.id">
          <v-list-item-content>
            <v-list-item-title v-html="user.name"></v-list-item-title>
            <v-list-item-subtitle v-html="user.email"></v-list-item-subtitle>
            <v-list-item-subtitle v-html="user.password"></v-list-item-subtitle>
            <v-list-item-subtitle v-html="user.address"></v-list-item-subtitle>

          </v-list-item-content>
        </v-list-item>
    </v-list>
</template>

<script>
  import axios from "axios";
  export default {
    name: 'users',
    data() {
      return {
        users: [],
      }
    },
    async created() {
      await this.getUsers();
    },
    methods:{
      async getUsers(){
      
          var result = await axios.get(`http://localhost:8081/admin/showUsers`);
          this.users = result.data; 
      },
    }
  }
</script>