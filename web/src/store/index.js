import Vue from 'vue'
import Vuex from 'vuex'
import storage from "@/store/storage";
Vue.use(Vuex);


export default new Vuex.Store({
  state: {
    // 存储token
    token: storage.get('token') ? storage.get('token') : '',
    // token: "",
    user: storage.get('user') ? storage.get('user') : {}, // 可选
  },
  getters: {
    get_token(state){
      return state.token ||storage.get("token") || null;
    },
    get_user(state){
      return state.user ||storage.get("user") ||null;
    }
  },
  mutations: {
    // 修改token，并将token存入localStorage
    set_token(state,token) {
      state.token = token;
      storage.set('token', token);
      console.log('store、localstorage保存token成功！');
    },
    del_token(state) {
      state.token = "";
      storage.remove("token");
    },
    // 可选
    setUserInfo(state, user) {
      state.user = user;
      storage.set('user', user);
      console.log('store、localstorage保存user成功！');
    }
    },
  actions: {
  },
  modules: {
  }
})
