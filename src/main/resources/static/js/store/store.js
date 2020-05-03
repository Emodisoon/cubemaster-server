import Vue from 'vue'
import Vuex from 'vuex'
import VueResource from "vue-resource";
import moment from "moment";

Vue.use(Vuex);
Vue.use(VueResource)
export default new Vuex.Store({
    state:{
        timeRecords: [],
        jwtToken: "",
        auth: false,
        username: "",
        isAdmin: false,
    },

    getters:{
        getSortedTimeRecords(state){
            return state.timeRecords.sort((a,b) => (a.time - b.time))
        },
        getUserName(state){
            return state.username
        },
        getToken(state){
            return state.jwtToken
        },
        getAuth(state){
            return state.auth
        },
        getAdmin(state){
            return state.isAdmin
        }
    },
    actions:{
        async deleteTimeRecord({commit}, id){
            const result = await Vue.http.delete('/api/timeRecord/'+id, {headers: {'Authorization': localStorage.getItem('token')}})
            if(result.ok){
                commit('deleteTrMutation', id)
            }
        },

        async getTimeRecordsAction({commit}){

            const result = await Vue.http.get('/api/timeRecord', {headers: {'Authorization': localStorage.getItem('token')}})
            const data = result.body;
            data.forEach(timeRecord => commit('addTimeRecordsMutation', timeRecord))

        },

    },
    mutations:{
        deleteTrMutation(state, id){
            const deletionIndex = state.timeRecords.findIndex(item => item.id === id)
            if (deletionIndex > -1) {
                state.timeRecords = [
                    ...state.timeRecords.slice(0, deletionIndex),
                    ...state.timeRecords.slice(deletionIndex + 1)
                ]
            }
        },
        setAdminMutationTrue(state){
            state.isAdmin = true;
            localStorage.setItem('isAdmin', "true")
        },
        setAdminMutationFalse(state){
            state.isAdmin = false;
            localStorage.setItem('isAdmin', "false")
        },
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
        addTimeRecordsMutation(state, timeRecord){
            timeRecord.user.password = "Ha ha u wont get it"
            timeRecord.creationDate = moment(timeRecord.creationDate).format('MM/DD/YYYY hh:mm')

            state.timeRecords = [
                ...state.timeRecords, timeRecord
            ]
        },

    }
})