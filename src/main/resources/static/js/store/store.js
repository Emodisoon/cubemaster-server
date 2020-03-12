import Vue from 'vue'
import Vuex from 'vuex'
import VueResource from "vue-resource";
import jwt from 'jsonwebtoken'
import router from "../router/router";

Vue.use(Vuex);
Vue.use(VueResource)
export default new Vuex.Store({
    state:{
        messages: [],
        jwtToken: "",
        auth: false,
        username: "",
    },

    getters:{
        getUserName(state){
            return state.username
        },
        getMessages(state){
            return state.messages
        },
        getToken(state){
            return state.jwtToken
        },
        getAuth(state){
            return state.auth
        }
    },
    actions:{
        /*
        async getTokenAction({commit}, data){
            console.log()
            commit('setUserNameMutation', data.username)
            commit('setTokenMutation', data.token)
        },*/
        async getMessageAction({commit}, message){
            const result = await Vue.http.get('/message{/id}')
            const data = await result.json()
            data.forEach(message => commit('addMessageMutation', message))

        },
        async deleteMessageAction({commit}, message) {
            const result = await Vue.http.delete('/message/' + message.id)
            if (result.ok) {
                commit('deleteMessageMutation', message)
            }
        }
    },
    mutations:{
        setUserNameMutation(state, name){
            state.username = name;
            localStorage.setItem('username', name)
        },
        logOutMutation(state){
            state.jwtToken = ""
            state.auth = false
            localStorage.auth = false
            localStorage.token = ""
            state.username = ""
        },
        setTokenMutation(state, token){
            state.jwtToken = token;
            state.auth = true;
            localStorage.setItem('auth', true);
            localStorage.setItem('token', token);

        },
        addMessageMutation(state, message){
            state.messages = [
                ...state.messages, message
            ]
        },
        getMessageMutation(state, message){

        },
        updateMessageMutation(state, message){
            const updateIndex = state.messages.findIndex(item => item.id === message.id)
            state.messages = [
                ...state.messages.slice(0, updateIndex),
                message,
                ...state.messages.slice(updateIndex+1)
            ]
        },
        deleteMessageMutation(state, message){
            const deletionIndex = state.messages.findIndex(item => item.id === message.id)
            console.log("deletion index:" + deletionIndex)
            if(deletionIndex > -1){
                state.messages = [
                    ...state.messages.slice(0,deletionIndex),
                    ...state.messages.slice(deletionIndex)
                ]
            }
        }
    }
})