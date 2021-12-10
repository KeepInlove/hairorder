import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import './icons';
import router from './router'
import './assets/css/global.css'

Vue.config.productionTip = false;
Vue.prototype.$axios=axios;
Vue.use(ElementUI);
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
