// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Vuex from 'vuex'
import App from './App'
import router from './router'
import lodash from './plugins/lodash'
import axios from './plugins/axios'

Vue.config.productionTip = false

Vue.use(Vuex)
Vue.use(lodash)
Vue.use(axios)

// defined modules
const ModuleA = {
  state: {
    count: 0
  },
  getters: {
    countFilterA (state, getters, rootState) {
      if (state.count > 5) {
        return state.count
      }
    }
  },
  mutations: {
    increateA (state) {
      state.count++
    }
  },
  actions: {
    increateAsyncA ({state, commit, rootState}) {
      if ((state.count + rootState.count) % 2 === 1) {
        commit('increateA')
      }
    }
  }
}

const ModuleB = {
  state: {
    count: 0
  },
  getters: {
    countFilterB (state, getters, rootState) {
      if (state.count > 5) {
        return state.count + rootState.count
      }
    }
  },
  mutations: {
    increateB (state) {
      state.count++
    }
  }
}

// use modules
const store = new Vuex.Store({
  state: {
    count: 10
  },
  modules: {
    a: ModuleA,
    b: ModuleB
  }
})

// Vue.use(plugins, {someOption: true})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: {App}
})
