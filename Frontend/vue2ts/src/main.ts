import Vue from 'vue';
import App from '@/App.vue';
import router from '@/router';
import store from '@/store';
import i18n from '@/i18n';
import '@/plugins/composition_api';
import axios from '@/utils/https';
import urls from '@/utils/urls';
import VueLazyload from 'vue-lazyload';
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';

Vue.use(Antd);

Vue.use(VueLazyload, {
  preLoad: 1.3,
  error: 'dist/error.png',
  loading: 'dist/loading.gif',
  attempt: 1,
});

Vue.prototype.$https = axios; // 其他页面在使用 axios 的时候直接  this.$http 就可以了
Vue.prototype.$urls = urls; // 其他页面在使用 urls 的时候直接  this.$urls 就可以了

Vue.config.productionTip = false;
Vue.config.performance = process.env.NODE_ENV !== 'production';

new Vue({
  router,
  store,
  i18n,
  render: (h) => h(App),
}).$mount('#app');
