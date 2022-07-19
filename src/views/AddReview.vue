<template>
  <v-form
    ref="form"
    class="ml-6 mr-6"
  >
    <v-autocomplete label="Title" :items="books" item-text="title" return-object v-model="selectedBook">
      
    </v-autocomplete>

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

    <v-text-field
      v-model="reviewMessage"
      label="Message"
      required
    ></v-text-field>
    <v-btn
      color="success"
      class="mr-4"
      @click="addReview"
    >
      Add Review
    </v-btn>
  </v-form>
</template>

<script>
  import axios from "axios";
  export default {
    name: 'addReview',
    data() {
      
      return {
        selectedBook: null,
        books: [],
        reviewMessage: "",
        author: "",
        genre: ""
      }
    },
    watch:{
      selectedBook(newValue){
        console.log(newValue);
        this.author = newValue.author;
        this.genre = newValue.genre;
      }
    },
    async created(){
      await this.getBooks();
    },
    methods: {
      async addReview() {
        try { 
          if(this.selectedBook!=null){
            var result = await axios.post(`http://localhost:8081/user/addReview`,{bookId: this.selectedBook.id, userId: this.$store.getters.getId, reviewMessage: this.reviewMessage }); 
            if(result!=null){
              alert("Succes!");
            }
            this.$router.push({ name: 'books' });
          }
        } catch (e) {
          console.log(e);
          console.log("adaugare esuata");
        }
      },
      async getBooks(){
          var result = await axios.get(`http://localhost:8081/user/showBooks`);
          this.books = result.data;
           
      },
    }
  }
</script>
