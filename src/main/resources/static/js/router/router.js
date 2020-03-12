import Vue from 'vue'
import VueRouter from 'vue-router'
import MessagesList from 'components/messages/MessageList.vue'
import login from 'components/login.vue'
import register from "../components/register.vue";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        component: MessagesList,
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
    }
]



const router =  new VueRouter({
    mode: 'history',
    routes,

})

router.beforeEach((to, from, next) => {
    if(to.matched.some(record => record.meta.requiresAuth)){
        console.log('matched')
        if(localStorage.getItem('auth')==='false'){
            next({path: "/login"})
            console.log('not authed')
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