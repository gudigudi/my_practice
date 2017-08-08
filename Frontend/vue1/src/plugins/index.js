import appbar from './components/Appbar.vue'

export default {
  /**
   * create plugin by method: install()
   * @param {Vue} [Object] Vue instance constructor
   * @param {options} [Object] option object, Vue.use(pluginName, options)
   */
  install (Vue, options) {
    Vue.directive('focus', {
      bind (el, binding, vnode, oldVnode) {

      },
      inserted (el, binding, vnode, oldVnode) {
        el.focus()
      }
    })

    Vue.mixin({
      components: {
        appbar
      }
    })

    VUe.prototype.$userinfo = {
      name: 'appbar',
      age: 24,
      say () {
        alert('hello' + this.age)
      }
    }
  }
}
