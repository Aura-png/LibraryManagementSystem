import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginView from '../views/LoginView.vue'
import Books from '../views/Books.vue'
import Users from '../views/Users.vue'
import Borrows from '../views/Borrows.vue'
import Register from '../views/Register.vue'
import AddBook from '../views/AddBook.vue'
import UpdateBook from '../views/UpdateBook.vue'
import AddReview from '../views/AddReview.vue'
import AddBorrow from '../views/AddBorrow.vue'
Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  
  {
    path: '/books',
    name: 'books',
    component: Books
  },
  {
    path: '/addBook',
    name: 'addBook',
    component: AddBook
  },
  {
    path: '/updateBook/:title',
    name: 'updateBook',
    component: UpdateBook
  },
  {
    path: '/register',
    name: 'register',
    component: Register
  },
  {
    path: '/users',
    name: 'users',
    component: Users
  },
  {
    path: '/borrows',
    name: 'borrows',
    component: Borrows
  },
  {
    path: '/addReview',
    name: 'addReview',
    component: AddReview
  },
  {
    path: '/addBorrow',
    name: 'addBorrow',
    component: AddBorrow
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
