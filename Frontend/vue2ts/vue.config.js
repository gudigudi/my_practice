const TerserPlugin = require("terser-webpack-plugin");

const mockServerPort = 8090

const cdnConfig = {
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
      name: "@vue/composition-api",
      var: "vueCompositionApi",
      path: "dist/vue-composition-api.umd.js"
    },
    // {
    //   name: "vue-property-decorator",
    //   var: 'vue-property-decorator',
    //   path: 'lib/vue-property-decorator.umd.js'
    // },
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
      name: "ant-design-vue",
      var: "antd",
      path: "dist/antd.min.js",
      style: "dist/antd.min.css"
    },
    {
      name: "axios",
      var: "axios",
      path: "dist/axios.min.js"
    }
  ],
  optimize: true,
  publicPath: "/node_modules"
}

module.exports = {
  css: {
    loaderOptions: {
      less: {
        modifyVars: {
          // @primary-color: #1890ff; // 全局主色
          // @link-color: #1890ff; // 链接色
          // @success-color: #52c41a; // 成功色
          // @warning-color: #faad14; // 警告色
          // @error-color: #f5222d; // 错误色
          // @font-size - base: 14px; // 主字号
          // @heading-color: rgba(0, 0, 0, 0.85); // 标题色
          // @text-color: rgba(0, 0, 0, 0.65); // 主文本色
          // @text-color - secondary: rgba(0, 0, 0, 0.45); // 次文本色
          // @disabled-color: rgba(0, 0, 0, 0.25); // 失效色
          // @border-radius - base: 4px; // 组件/浮层圆角
          // @border-color - base: #d9d9d9; // 边框色
          // @box-shadow - base: 0 2px 8px rgba(0, 0, 0, 0.15); // 浮层阴影
          'primary-color': '#1DA57A',
          'link-color': '#1DA57A',
          'border-radius-base': '2px',
        },
        javascriptEnabled: true
      }
    }
  },
  chainWebpack: config => {
    if (process.env.NODE_ENV === 'production') {
      config.plugin('webpack-cdn')
        .use(require('webpack-cdn-plugin'), [cdnConfig])
      config.optimization.minimizer = [new TerserPlugin({ terserOptions: { compress: { drop_console: true } } })];
    } else {
      // config.devtool = 'source-map';
    }
  },
  devServer: {
    proxy: {
      [process.env.VUE_APP_BASE_API]: {
        target: `http://localhost:${mockServerPort}/mock-api/v1`,
        changeOrigin: true, // 如果接口跨域，需要进行这个参数配置
        // ws: true,// proxy websockets
        pathRewrite: { // pathRewrite方法重写url
          ['^' + process.env.VUE_APP_BASE_API]: ''
        }
      }
    }
  },
  pluginOptions: {
    i18n: {
      locale: 'en',
      fallbackLocale: 'en',
      localeDir: 'locales',
      enableInSFC: true
    }
  },
  productionSourceMap: false,
  runtimeCompiler: true
}
