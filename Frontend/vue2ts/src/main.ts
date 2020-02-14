import Vue from 'vue';
import App from '@/App.vue';
import router from '@/router';
import store from '@/store';
import i18n from '@/i18n';
import '@/plugins/composition_api';
import axios from '@/utils/https';
import urls from '@/utils/urls';
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.min.css';

Vue.use(Antd);

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
