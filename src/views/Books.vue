<template>

 <v-list three-line>
   <header>
    <input v-model="searchtitle" type="text" id="myInput"  placeholder="Search for titles..">
    <v-btn
      color="error"
      class="mr-4"
      @click="search"
    >
      Search
    </v-btn>
  </header>
        <v-list-item v-for="(book) in books" :key="book.id">
          <v-list-item-content>
            <v-list-item-title v-html="book.title"></v-list-item-title>
            <v-list-item-subtitle v-html="book.author"></v-list-item-subtitle>
            <v-list-item-subtitle v-html="book.genre"></v-list-item-subtitle>
          </v-list-item-content>

          <v-list-item-action>
          <v-btn 
            v-if="$store.getters.getRole == 'admin'"
            text
            icon @click="$router.push({name: 'updateBook',params: {title: book.title,author: book.author, genre: book.genre} })"> 
            <v-icon color="grey lighten-1">mdi-pencil</v-icon>
          </v-btn>
          
          <v-btn 
            v-if="$store.getters.getRole == 'admin'"
            text
            icon @click="deleteBook(book.title)">       
          <v-icon color="grey lighten-1">mdi-delete</v-icon>
          </v-btn>
          
          <v-btn 
            v-if="$store.getters.getRole == 'user'"
            text
            icon @click="$router.push({name: 'addBorrow'})"> 
            <v-icon dark>mdi-plus</v-icon>
          </v-btn>
        </v-list-item-action>
        </v-list-item>
    </v-list>
</template>

<script>
  import axios from "axios";
  export default {
    name: 'books',
    data() {
      return {
        books: [],
        searchtitle: ""
      }
    },
    async created() {
      await this.getBooks();
    },
    methods:{
      async search(){
        if(this.searchtitle == ""){
          this.getBooks();
          return;
        }
        console.log("vvvv");
          var result = await axios.get(`http://localhost:8081/admin/findBookByTitle/${this.searchtitle}`);
          this.books = result.data;
      },
      async getBooks(){
      
          var result = await axios.get(`http://localhost:8081/admin/showBooks`);
          this.books = result.data; 
      },
      async deleteBook(title){
        await axios.delete(`http://localhost:8081/admin/deleteBook/${title}`);
        this.getBooks();
      }
    }
  }
</script>