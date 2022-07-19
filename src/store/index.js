import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    id : -1, role: ""
  },
  getters: {
    getId(state){
      return state.id;
    },
    getRole(state){
      return state.role;
    }
  },
  mutations: {
    setId(state, id){
      state.id = id;
    },
    setRole(state, role){
      state.role = role;
    }
  },
  actions: {

  },
  modules: {
  }
})
