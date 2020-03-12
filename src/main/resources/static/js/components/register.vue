<template>
    <v-container class="login">
        <v-card class="elevation-12 pa-10" border="3px" >

            <v-form align="center" justify="center">
                <h1>Регистрация</h1>
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
                <v-btn @click="register" color="primary">Регистрция</v-btn>
            </v-form>

        </v-card>
    </v-container>
</template>

<script>
    export default {
        data(){
            return{
                username: "",
                password: "",
                errorText: ""
            }
        },
        methods:{
            register(){
                //TODO: реализовать регистрацию
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
</style>