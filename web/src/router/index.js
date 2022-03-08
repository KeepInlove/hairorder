import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "../views/login";
import home from "@/views/home";
import welcome from "@/views/welcome";
import list from "@/views/user/list";
import order from "@/views/user/order";
import barberTypeList from "@/views/barberType/list";
import barberList from "@/views/barber/list";
import barberAdd from "@/views/barber/add";
import barberEdit from "@/views/barber/edit"
import hairList from "@/views/hair/hairList";
import hairType from "@/views/hair/hairType";
import store from '@/store'
import storage from "@/store/storage";
Vue.use(VueRouter);



const router = new VueRouter({
    // mode:'history',
    routes : [
        {
            path: '/',
            redirect: '/login',
            component: Login,
        },
        {
            path: '/login',
            component: Login,
        },
        {
            path:'/home',
            component: home,
            redirect: '/sys/welcome',
            children:
                [
                {
                    path: '/sys/welcome',
                    name:'welcome',
                    component: welcome,
                    meta: {
                        requireAuth: true,
                    }
                },
                {
                    path: '/sys/list',
                    component: list
                },
                {
                    path:'/sys/order',
                    component:order
                },
                {
                    name:'/sys/add',
                    path:'/sys/barberAdd',
                    component:barberAdd
                },
                 {
                   path:'/sys/hairList',
                    component:hairList
                 },
                    {
                        path:'/sys/hairType',
                        component:hairType
                    },
                {
                    path:'/sys/barberList',
                    component:barberList
                },
                {
                    path:'/sys/barberTypeList',
                    component:barberTypeList
                },
                {
                    name:'barberEdit',
                    path: '/sys/:id',
                    component: barberEdit,
                }

            ]
        },
    ]
});



// 设置路由守卫，在进页面之前，判断有token，才进入页面，否则返回登录页面
if (storage.get("token")) {
    store.commit("set_token", storage.get("token"));
}
router.beforeEach((to, form, next) => {
        //要不要对mate.loginRequire属性做监控拦截
        if (to.matched.some(r => r.meta.requireAuth)) {
            if (store.state.token) {
                next(); //有token,进行request请求，后台还会验证token
            } else {
                next({
                    path: "/login",
                    // 将刚刚要去的路由path（却无权限）作为参数，方便登录成功后直接跳转到该路由，这要进一步在登陆页面判断
                    query: { redirect: to.fullPath }
                });
            }
        } else {
            next(); //如果无需token,那么随它去吧
        }
    }
);
export default router
