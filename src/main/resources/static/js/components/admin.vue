<template>
    <v-card
            class="mx-auto my-7"
            max-width="95%"
    >
        <v-toolbar-title class="text-center">
            <h1>Список пользователей</h1>
        </v-toolbar-title>
        <v-simple-table>
            <template v-slot:default>
                <thead>
                <tr>
                    <th class="text-center ">Id</th>
                    <th class="text-center">Имя пользователя</th>
                    <th class="text-center">Админ?</th>
                    <th class="text-center">Удалить</th>
                    <th class="text-center">Сделать админом</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="user in allUsers" :key="user.id">
                    <td class="text-center">{{user.id}}</td>
                    <td class="text-center">{{ user.userName}}</td>
                    <td class="text-center"><div v-if="user.admin" class="admin" >Да</div><div v-else>Нет</div></td>
                    <td class="text-center"><v-btn @click="deleteUser(user.userName)" v-if="!user.admin">Удалить</v-btn></td>
                    <td class="text-center"><v-btn @click="setAdmin(user.userName)" v-if="!user.admin">Сделать админом</v-btn></td>
                                            <!--<v-icon @click="deleteTR(timeRecord.id)" v-if="$route.params.username === getUserName" class="clickable" >
                            close</v-icon></td>/-->
                </tr>
                </tbody>
            </template>
        </v-simple-table>
    </v-card>
</template>

<script>
    import Vue from "vue";
    import router from "../router/router";

    export default {
        created() {

        },
        methods:{
            async deleteUser(user){
                const req = await Vue.http.get("http://192.168.0.12:8080/api/deleteUser/"+user, {headers: {'Authorization': localStorage.getItem('token')}})
                if(req.ok){
                    let index = this.allUsers.findIndex(user=> user.userName===user)
                    this.allUsers.splice(index, 1)
                }
            },
            async setAdmin(user){

                const newAdmin = await Vue.http.get('http://192.168.0.12:8080/api/setAdmin/'+user, {headers: {'Authorization': localStorage.getItem('token')}})
                this.allUsers.findIndex()
            },
            findUser(element, index, array){

            }
        },
        data(){
            return{
                allUsers: [],
            }
        },
        async beforeCreate() {
            const users = await Vue.http.get('http://192.168.0.12:8080/api/allusers/', {headers: {'Authorization': localStorage.getItem('token')}})
            if(users.ok){
                this.allUsers = users.body;

                console.log(this.allUsers)
            }
        }
    }
</script>

<style scoped>
    .admin{
        color: red;
    }
</style>