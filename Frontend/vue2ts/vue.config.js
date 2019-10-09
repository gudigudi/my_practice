const WebpackCdnPlugin = require("webpack-cdn-plugin");

module.exports = {
  configureWebpack: {
    plugins: [
      new WebpackCdnPlugin({
        modules: [
          {
            name: "vue",
            var: "Vue",
            path: "dist/vue.runtime.min.js"
          },
          {
            name: "vue-router",
            var: "VueRouter",
            path: "dist/vue-router.min.js"
          },
          {
            name: "vuex",
            var: "Vuex",
            path: "dist/vuex.min.js"
          },
          {
            name: "vue-i18n",
            var: "VueI18n",
            path: "dist/vue-i18n.min.js"
          },
          {
            name: "vue-lazyload",
            var: "VueLazyload",
            path: "vue-lazyload.js"
          },
          {
            name: "element-ui",
            var: "ELEMENT",
            path: "lib/index.js",
            style: "lib/theme-chalk/index.css"
          },
          {
            name: "axios",
            var: "axios",
            path: "dist/axios.min.js"
          }
        ],
        publicPath: "/node_modules"
      })
    ]
  },

  pluginOptions: {
    i18n: {
      locale: 'en',
      fallbackLocale: 'en',
      localeDir: 'locales',
      enableInSFC: true
    }
  },

  runtimeCompiler: true
}
