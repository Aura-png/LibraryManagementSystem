<template>
  <v-app>
    <v-app-bar
      app
      color="red"
      dark
    >
      <div class="d-flex align-center">
        <v-img
          alt="Vuetify Logo"
          class="shrink mr-2"
          contain
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-logo-dark.png"
          transition="scale-transition"
          width="40"
        />

        <v-img
          alt="Vuetify Name"
          class="shrink mt-1 hidden-sm-and-down"
          contain
          min-width="100"
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-name-dark.png"
          width="100"
        />
      </div>

      <v-spacer></v-spacer>
     <v-btn
     v-if="$store.getters.getRole == 'admin'"
        text
        @click="$router.push({name: 'addBook' })"
      >
        <span class="mr-2">Add book</span>
      </v-btn>

      <v-btn
        v-if="$store.getters.getRole == 'admin' || $store.getters.getRole == 'user'"
    
        text
        @click="$router.push({name: 'books' })"
      >
        <span class="mr-2">All books</span>
      </v-btn>
      <v-btn
        v-if="$store.getters.getRole == 'admin'"
    
        text
        @click="$router.push({name: 'users' })"
      >
        <span class="mr-2">All users</span>
      </v-btn>
      <v-btn
        v-if="$store.getters.getRole == 'admin'"
    
        text
        @click="$router.push({name: 'borrows' })"
      >
        <span class="mr-2">All borrows</span>
      </v-btn>
     <v-btn
     v-if="$store.getters.getRole == 'user'"
        text
        @click="$router.push({name: 'addReview' })"
      >
        <span class="mr-2">Add Review</span>
      </v-btn>
    <v-btn    
        text
        @click="$router.push({name: 'login' })"
      >
        <span class="mr-2">Logout</span>
      </v-btn>
      
    </v-app-bar>

    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>

export default {
  name: 'App',

  data: () => ({
    //
  }),
};
</script>
