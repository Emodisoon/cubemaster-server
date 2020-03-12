<template>
    <v-container class="login">
        <v-card class="elevation-12 pa-10" border="3px" >

            <v-form align="center" justify="center">
                <h1>Авторизация</h1>
                <v-toolbar-title align="center" class="err">{{errorText}}</v-toolbar-title>
                <v-text-field
                        label="Логин"
                        v-model="username"
                        prepend-icon="person"
                        type="text"
                        required
                ></v-text-field>
                <v-text-field
                        label="Пароль"
                        v-model="password"
                        name="password"
                        prepend-icon="lock"
                        type="password"
                />
                <v-btn @click="makeAuth" color="primary">Авторизация</v-btn>
                <v-btn color="primary">
                    <router-link to="/reg">
                        Регистрация
                    </router-link>
                </v-btn>
            </v-form>

        </v-card>
    </v-container>
</template>

<script>
    import {mapActions, mapMutations} from 'vuex'
    import Vue from "vue";
    import jwt from "jsonwebtoken";
    import router from "../router/router";

    export default {
        name: "login",
        data(){
            return{
                username: "",
                password: "",
                errorText: "",
            }
        },

        methods: {
            ...mapActions(['getTokenAction']), ...mapMutations(['setUserNameMutation',"setTokenMutation"]),

            async makeAuth(){
                let data = {
                    userName: this.username,
                    password: this.password
                }
                try {
                    const result = await Vue.http.post('http://192.168.0.12:8080/login', data)
                    if (result.ok) {
                        let token = result.headers.get('authorization')
                        token = token.substr(7, token.length)
                        let decoded = jwt.verify(token, 'SecretKeyToGenJWTs')
                        this.setUserNameMutation(decoded.sub)
                        this.setTokenMutation(result.headers.get('authorization'))
                        router.push('/')
                    }
                } catch(err){
                    this.errorText = "Ошибка авторизации."
                }



            }
        }

    }
</script>
<style scoped>
    .err{
        color: red;
    }
    .login{
        max-width: 25%;
    }
    a:link{
        color: white;
        text-decoration: none;
    }
    a:visited{
        color: white;
        text-decoration: none;
    }
    </style>