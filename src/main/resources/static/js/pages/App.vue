<template>
    <v-app>

        <v-app-bar color="blue" dense app>
            <v-toolbar-title><router-link to='/'>CubeMaster</router-link></v-toolbar-title>
            <v-spacer></v-spacer>
            <v-toolbar-title class="px-5" v-if="getAuth">
                <router-link v-if="getAdmin" :to="{name: 'admin'}">Админ панель</router-link>

                <router-link :to="{ name: 'user', params: { username: getUserName }}"> Добро пожаловать, {{getUserName}}</router-link>
            </v-toolbar-title>



            <v-toolbar-title class="exit px-5" @click="logoutNow"  v-if="getAuth">

                    (Выход)

            </v-toolbar-title>


            <v-toolbar-title v-if="!getAuth">(</v-toolbar-title>

                <router-link v-if="!getAuth"to="/login">
                    <v-toolbar-title>
                        Авторизация
                    </v-toolbar-title>
                </router-link> <v-toolbar-title v-if="!getAuth">|</v-toolbar-title>

                <router-link v-if="!getAuth" to="/reg">
                    <v-toolbar-title>
                        Регистрация
                    </v-toolbar-title>
                </router-link>
            <v-toolbar-title v-if="!getAuth">)</v-toolbar-title>


        </v-app-bar>


        <v-content>

            <router-view></router-view>
        </v-content>

        <v-footer color="blue" padless>
            <v-col
                    class="text-center"
                    cols="12"
            >
                {{ new Date().getFullYear() }} — <strong><router-link to='/'>CubeMaster</router-link></strong>
            </v-col>
        </v-footer>

    </v-app>
</template>

<script>

    import {mapActions, mapGetters, mapMutations} from 'vuex'
    import router from "../router/router";
    import Vue from "vue";

    export default {

        computed: mapGetters(['getToken', 'getAuth', 'getUserName', 'getAdmin', 'getAdmin']),
            ...mapActions(['getTokenAction', 'getTimeRecordsAction']),
            ...mapMutations(['setTokenMutation', "setAdminMutationTrue", "setAdminMutationFalse",'setAdminMutationFalse', 'setAdminMutationTrue']),

        data(){
            return{
                isAdmin: false,
            }
        },

        methods:{
            logoutNow(){
                console.log('Logout')
                this.$store.commit('logOutMutation')
                router.push('/login')
            },
        },

        created() {
            if (localStorage.getItem('auth') === 'true') {
                this.$store.commit('setTokenMutation', localStorage.getItem('token'))
                this.$store.commit('setUserNameMutation', localStorage.getItem('username'))

                this.$store.dispatch('getTimeRecordsAction')
                if(localStorage.getItem('isAdmin')==="true")
                    this.$store.commit('setAdminMutationTrue')
                else
                    this.$store.commit('setAdminMutationFalse')
            }

        }

    }
</script>

<style scoped>
    a:link{
        color: black;
        text-decoration: none;
    }
    a:visited{
        color: black;
        text-decoration: none;
    }
    .exit:hover{
        cursor: pointer;
    }
</style>