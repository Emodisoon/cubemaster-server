import Vue from 'vue'
import VueRouter from 'vue-router'
import login from 'components/login.vue'
import timeRecords  from "../components/timeRecords.vue";
import register from "../components/register.vue";
import user from "../components/user.vue";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        component: timeRecords,
        meta: { requiresAuth: true }
    },
    {
        path: '/login',
        name: "Login",
        component: login
    },
    {
        path: '/reg',
        name: "registration",
        component: register
    },
    {
        path: '/user/:username',
        name: 'user',
        component: user
    }
]



const router =  new VueRouter({
    mode: 'history',
    routes,

})

router.beforeEach((to, from, next) => {
    if(to.matched.some(record => record.meta.requiresAuth)){
        if(localStorage.getItem('auth')==='false'){
            next({path: "/login"})
        }
        else{
            next()
        }
    }
    else{
        next()
    }
})

export default router;