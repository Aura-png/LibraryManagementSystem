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
      @click="updateBook"
    >
      Update
    </v-btn>
  </v-form>
</template>

<script>
  import axios from "axios";
  export default {
    name: 'updateBook',
    data() {
      return {
        title: this.$route.params.title,
        author: this.$route.params.author,
        genre: this.$route.params.genre,
      }
    },
    methods: {
      async updateBook() { 
          var result = await axios.put("http://localhost:8081/admin/updateBook", {title: this.title,author: this.author,genre: this.genre });
          this.$router.push({ name: 'books' });
         
      }
    }
  }
</script>
