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
import hairEdit from "@/views/hair/hairEdit";
import hairAdd from "@/views/hair/hairAdd";
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
                        name:'/sys/hairAdd',
                        path:'/sys/hairAdd',
                        component:hairAdd
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
                    path: '/sys/barberId=:id',
                    component: barberEdit,
                },
                    {
                        name:'hairEdit',
                        path: '/sys/hairId=:id',
                        component: hairEdit,
                    }
            ]
        },
    ]
});



// ???????????????????????????????????????????????????token?????????????????????????????????????????????
if (storage.get("token")) {
    store.commit("set_token", storage.get("token"));
}
router.beforeEach((to, form, next) => {
        //????????????mate.loginRequire?????????????????????
        if (to.matched.some(r => r.meta.requireAuth)) {
            if (store.state.token) {
                next(); //???token,??????request???????????????????????????token
            } else {
                next({
                    path: "/login",
                    // ????????????????????????path?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
                    query: { redirect: to.fullPath }
                });
            }
        } else {
            next(); //????????????token,??????????????????
        }
    }
);
export default router
