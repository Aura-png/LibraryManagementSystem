<template>
  <v-form
    ref="form"
    class="ml-6 mr-6"
  >
    <v-text-field
      v-model="title"
      label="Title"
      required
    ></v-text-field>

    <v-text-field
      v-model="author"
      label="Author"
      required
    ></v-text-field>

    <v-text-field
      v-model="genre"
      label="Genre"
      required
    ></v-text-field>

    <v-btn
      color="success"
      class="mr-4"
      @click="addBook"
    >
      Add
    </v-btn>
  </v-form>
</template>

<script>
  import axios from "axios";
  export default {
    name: 'addBook',
    data() {
      return {
        title: "",
        author: "",
        genre: ""
      }
    },
    methods: {
      async addBook() {
        try { 
          var result = await axios.post(`http://localhost:8081/admin/addBook`, { title: this.title, author: this.author, genre: this.genre });
          if(result != null){
          this.$router.push({ name: 'books' });
          }else{
            alert("Book already exist!");
          }
        } catch (e) {
          console.log(e);
        }
      }
    }
  }
</script>
