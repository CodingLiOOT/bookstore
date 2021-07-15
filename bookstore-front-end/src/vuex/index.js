import Vue from 'vue'

import Vuex from 'vuex'
Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    userID: undefined,
    token: undefined,
    state:undefined
  },
  mutations: {
    login (state, payload) {
      state.userID = payload.userID
      state.token = payload.token
      state.state=payload.state
      sessionStorage.token = payload.token
      sessionStorage.userID = payload.userID
      sessionStorage.state=payload.state
    },
    logout (state) {
      state.userID = undefined
      state.token = undefined
      state.state=undefined
      sessionStorage.removeItem('token')
      sessionStorage.removeItem('userID')
      sessionStorage.removeItem('state')
    },
    setToken (state, payload) {
      state.token = payload
    },
    removeToken (state) {
      state.token = ''
    }
  },
  actions: {
    login (context, payload) {
      context.commit('login', payload)
    },
    logout (context, payload) {
      context.commit('logout', payload)
    },
    setToken (context, payload) {
      context.commit('login', payload)
    },
    removeToken (context, payload) {
      context.commit('logout', payload)
    }
  }
})

export default store
