<template>

 <v-list three-line>
        <v-list-item v-for="(borrow) in borrows" :key="borrow.id">
          <v-list-item-content>
            <v-list-item-title v-html="borrow.userId"></v-list-item-title>
            <v-list-item-subtitle v-html="borrow.bookId"></v-list-item-subtitle>
            <v-list-item-subtitle v-html="borrow.period"></v-list-item-subtitle>
          </v-list-item-content>
          <v-list-item-action>
            <v-btn 
            v-if="$store.getters.getRole == 'admin'"
            text
            icon @click="deleteBorrow(borrow.userId)">       
          <v-icon color="grey lighten-1">mdi-delete</v-icon>
          </v-btn>
          </v-list-item-action>
        </v-list-item>
    </v-list>
</template>

<script>
  import axios from "axios";
  export default {
    name: 'borrows',
    data() {
      return {
        borrows: [],
      }
    },
    async created() {
      await this.getBorrows();
    },
    methods:{
      async getBorrows(){
      
          var result = await axios.get(`http://localhost:8081/admin/showBorrows`);
          this.borrows = result.data; 
      },
      async deleteBorrow(userId){
        await axios.delete(`http://localhost:8081/admin/deleteBorrow/${userId}`);
        this.getBorrows();
      }
    }
  }
</script>