<template>
    <div>
    <v-card
            class="mx-auto my-7"
            max-width="95%"
    >
            <v-toolbar-title class="text-center pa-3"><h1>Пользователь: {{$route.params.username}}</h1></v-toolbar-title>
            <v-spacer></v-spacer>

        <template>
            <v-container class="grey lighten-5">
                <v-row no-gutters>
                    <v-col
                            cols="12"
                            sm="4"
                            class="px-4"
                    >
                        <v-card
                                class="pa-2 text-center"
                                tile
                        >
                            PB: {{getMinits(pb)}}:{{getSeconds(pb)}}:{{getMiliSeconds(pb)}}
                        </v-card>
                    </v-col>
                    <v-col
                            cols="12"
                            sm="4"
                            class="px-4"
                    >
                        <v-card
                                class="pa-2 text-center"
                                tile
                        >
                            AVG5: {{getMinits(avg5)}}:{{getSeconds(avg5)}}:{{getMiliSeconds(avg5)}}
                        </v-card>
                    </v-col>
                    <v-col
                            cols="12"
                            sm="4"
                            class="px-4"
                    >
                        <v-card
                                class="pa-2 text-center"
                                tile
                        >
                            AVG12: {{getMinits(avg12)}}:{{getSeconds(avg12)}}:{{getMiliSeconds(avg12)}}
                        </v-card>
                    </v-col>
                </v-row>
            </v-container>
        </template>
        <v-simple-table>
            <template v-slot:default>
                <thead>
                <tr>
                    <th class="text-center clickable" @click="sortByTime">Время</th>
                    <th class="text-center">Скрамбл</th>
                    <th class="text-center clickable" @click="sortByDate">Дата</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="timeRecord in personalTimeRecords" :key="timeRecord.id">
                    <td>{{getMinits(timeRecord.time)}}:{{getSeconds(timeRecord.time)}}:{{getMiliSeconds(timeRecord.time)}}</td>
                    <td>{{ timeRecord.scramble }}</td>
                    <td>{{$moment(timeRecord.creationDate).format('DD/MM/YYYY hh:mm')}}
                        <v-icon @click="deleteTR(timeRecord.id)" v-if="$route.params.username === getUserName" class="clickable" >
                        close</v-icon></td>
                </tr>
                </tbody>
            </template>
        </v-simple-table>
    </v-card>
    </div>
</template>

<script>
    import Vue from "vue";
    import moment from "vue-moment"
    import {mapGetters, mapActions} from "vuex";

    export default {
        computed: mapGetters(['getUserName']), ...mapActions(['deleteTimeRecord']),
        data(){
            return{
                personalTimeRecords: [],
                pb: 0,
                avg12: 0,
                avg5: 0,
                sortedFrombigtolow: false

            }
        },
        methods: {
            deleteTR(id){
                this.$store.dispatch('deleteTimeRecord', id)
                const deletionIndex = this.personalTimeRecords.findIndex(item => item.id === id)
                this.personalTimeRecords.splice(deletionIndex,1)
            },
            sortByDate(){
                if(!this.sortedFrombigtolow) {
                    this.personalTimeRecords = this.personalTimeRecords.sort((a, b) => (b.creationDate - a.creationDate))
                    this.sortedFrombigtolow = true
                }
                else{
                    this.personalTimeRecords = this.personalTimeRecords.sort((a, b) => (a.creationDate - b.creationDate))
                    this.sortedFrombigtolow = false
                }
            },
            sortByTime(){
                this.personalTimeRecords = this.personalTimeRecords.sort((a,b) => (a.time - b.time))
                this.sortedFrombigtolow = false
            },
            getMiliSeconds(time){
                return time % 1000;
            },
            getSeconds(time){
                return Math.floor(time/1000);
            },
            getMinits(time){
                let secs = Math.floor(time/1000);
                return Math.floor(secs/60);
            }
        },
        async created() {
            const result = await Vue.http.get('/api/GetPersonalRecords/'+this.$route.params.username, {headers: {'Authorization': localStorage.getItem('token')}})
            if(result.ok) {
                this.personalTimeRecords = result.body;
                for(let i=0; i<this.personalTimeRecords.length; i++){
                    //this.personalTimeRecords[i].creationDate = moment(this.personalTimeRecords[i].creationDate).format('MM/DD/YYYY hh:mm')
                    this.personalTimeRecords[i].creationDate = new Date(this.personalTimeRecords[i].creationDate)
                }

                this.personalTimeRecords = this.personalTimeRecords.sort((a, b) => (b.creationDate - a.creationDate))

                //counting avg5
                let min=9999999
                let max=0
                let sum = 0;
                if(this.personalTimeRecords.length>=5) {
                    for (let i = 0; i < 5; i++) {
                        if (this.personalTimeRecords[i].time > max)
                            max = this.personalTimeRecords[i].time
                        if (this.personalTimeRecords[i].time < min)
                            min = this.personalTimeRecords[i].time
                    }
                    for (let i = 0; i < 5; i++) {
                        if (this.personalTimeRecords[i].time === max || this.personalTimeRecords[i].time === min)
                            continue;
                        else sum += this.personalTimeRecords[i].time;
                    }
                    this.avg5 = Math.floor(sum / 3)
                }
                else{
                    avg5=-1
                }
                //same avg 12
                min=9999999
                max=0
                sum = 0;
                if(this.personalTimeRecords.length>=12) {
                    for (let i = 0; i < 12; i++) {
                        if (this.personalTimeRecords[i].time > max)
                            max = this.personalTimeRecords[i].time
                        if (this.personalTimeRecords[i].time < min)
                            min = this.personalTimeRecords[i].time
                    }
                    for (let i = 0; i < 12; i++) {
                        if (this.personalTimeRecords[i].time === max || this.personalTimeRecords[i].time === min)
                            continue;
                        else sum += this.personalTimeRecords[i].time;
                    }
                    this.avg12 = Math.floor(sum / 10)
                }
                else{
                    avg12=-1
                }
                this.personalTimeRecords = this.personalTimeRecords.sort((a,b) => (a.time - b.time))
                this.pb=this.personalTimeRecords[0].time;
            }
        }
    }
</script>

<style scoped>
    h1{
        text-align: center;
    }
    th{
        text-align: center;
    }
    td{
        text-align: center;
    }
    .clickable{
        cursor: pointer;
    }
</style>