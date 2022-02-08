import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "../views/login";

Vue.use(VueRouter);

const routes = [
    {path: '/login', component: Login},
];

const router = new VueRouter({
    // mode:'history',
    routes
})
// router.beforeEach((to, from, next) => {
//     if (to.path == '/login') return next();
//     //获取token
//     const tokenStr = window.sessionStorage.getItem('token');
//     if (!tokenStr) return next('/login')
//     next()
// });
export default router
