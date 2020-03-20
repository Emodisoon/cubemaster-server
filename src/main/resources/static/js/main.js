import Vue from 'vue'
import VueResource from 'vue-resource'
import router from "./router/router";
import App from 'pages/App.vue'
import vuetify from "./plugins/vuetify";
import 'vuetify/dist/vuetify.min.css'
import '@babel/polyfill'
import store from 'store/store.js'
import VueMoment from 'vue-moment'

Vue.use(VueResource)
Vue.use(require('vue-moment'));


new Vue({
    el: '#app',
    vuetify,
    router,
    store,



    render: a=> a(App)

})




