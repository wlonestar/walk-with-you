import { createStore } from 'vuex'

export default createStore({
  state: {
    username: '',
    tokenName: '',
    tokenValue: ''
  },
  mutations: {
    setInfo (state, data) {
      state.username = data.loginId
      state.tokenName = data.tokenName
      state.tokenValue = data.tokenValue
    }
  },
  actions: {
  },
  modules: {
  }
})
