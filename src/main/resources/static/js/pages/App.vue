<template>
    <v-app>

        <v-app-bar color="blue" dense app>
            <v-toolbar-title><router-link to='/'>CubeMaster</router-link></v-toolbar-title>
            <v-spacer></v-spacer>
            <div v-if="getAuth">
                Добро пожаловать, {{getUserName}}
            </div>
            <v-spacer></v-spacer>
            <router-link to='/logout'>логаут</router-link>
            <router-link to="/login">
                <v-btn v-if="!getAuth" icon href="/login">
                    <v-icon>input</v-icon>
                </v-btn>
            </router-link>
        </v-app-bar>

        <v-content>
            <v-toolbar-title >{{getToken}}</v-toolbar-title>

            <router-view></router-view>
        </v-content>

    </v-app>
</template>

<script>

    import {mapActions, mapGetters, mapMutations} from 'vuex'

    export default {

        computed: mapGetters(['getToken', 'getAuth', 'getUserName']),
            ...mapActions(['getMessageAction', 'getTokenAction']),
            ...mapMutations(['setTokenMutation']),


        created() {
            if(localStorage.getItem('auth')==='true'){
                this.$store.commit('setTokenMutation', localStorage.getItem('token'))
                this.$store.commit('setUserNameMutation', localStorage.getItem('username'))
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
</style>