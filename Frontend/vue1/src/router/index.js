import Vue from 'vue'
import Router from 'vue-router'

// import Layouts from '../layouts/layouts.vue'
import TreeDemo from '../pages/TreeDemo.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Layouts',
      component: TreeDemo
    }
  ]
})
