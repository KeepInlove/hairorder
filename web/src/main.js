import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import './icons';
import router from './router'
import './assets/css/global.css'

// import hexMd5 from '../public/js/md5.js'
// eslint-disable-next-line no-unused-vars
import {hexMd5} from '../public/js/md5.js'

import store from './store'
import {Tool} from "@/utils/Tool";
Vue.prototype.hexMd5=hexMd5;
Vue.config.productionTip = false;
Vue.prototype.$axios=axios;

/**
 * axios拦截器
 */
axios.interceptors.request.use(function (config) {
  console.log('请求参数:',config);
  const token=store.state.token;
  if (Tool.isNotEmpty(token)){
    config.headers.token=token;
    console.log("请求headers增加token:",token);
  }
  return config;
},error => {
  return Promise.reject(error)
});

axios.interceptors.response.use(function (res) {
  console.log('响应结果:',res);
  return res;
},error => {
  console.log('返回错误:',error);
  return Promise.reject(error)
});


Vue.use(ElementUI);
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
