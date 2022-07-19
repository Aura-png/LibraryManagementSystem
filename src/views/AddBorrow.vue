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
      v-model="period"
      label="Period"
      required
    ></v-text-field>
    <v-btn
      color="success"
      class="mr-4"
      @click="addBorrow"
    >
      Borrow
    </v-btn>
  </v-form>
</template>

<script>
  import axios from "axios";
  import io from "socket.io-client"

  const socket = io.connect("http://localhost:8083")
  const sendMessage = () => {
    socket.emit("send_message",{message : "Book was borrowed!"})
  }

socket.on("receive_message",(data) => {
        alert(data.message)
})
  export default {
    name: 'addBorrow',
    data() {
      
      return {
        selectedBook: null,
        books: [],
        period: "",
        author: "",
        genre:""
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
      async addBorrow() {
        try { 
          if(this.selectedBook!=null){
            var result = await axios.post(`http://localhost:8081/user/borrowBook`,{userId: this.$store.getters.getId, bookId: this.selectedBook.id, period: this.period }); 
            if(result!=null){
              alert("Succes!");
              sendMessage();
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
