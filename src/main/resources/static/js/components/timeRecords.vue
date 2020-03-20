<template>
    <v-container class="ma-12">
        <h1 >Список рекордов</h1>
        <v-simple-table>
            <template v-slot:default>
                <thead>
                <tr>
                    <th class="text-center">Пользователь</th>
                    <th class="text-center">Время</th>
                    <th class="text-center">Скрамбл</th>
                    <th class="text-center">Дата</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="timeRecord in getSortedTimeRecords" :key="timeRecord.id">
                    <td><router-link :to="{ name: 'user', params: { username: timeRecord.user.userName }}">{{ timeRecord.user.userName }}</router-link></td>
                    <td>{{getMinits(timeRecord.time)}}:{{getSeconds(timeRecord.time)}}:{{getMiliSeconds(timeRecord.time)}}</td>
                    <td>{{ timeRecord.scramble }}</td>
                    <td>{{ timeRecord.creationDate  }}</td>
                </tr>
                </tbody>
            </template>
        </v-simple-table>
    </v-container>
</template>

<script>
    import {mapGetters} from 'vuex'
    export default {
        computed:mapGetters(['getSortedTimeRecords']),
        methods: {
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
    a:link{
        color: black;
    }
    a:visited{
        color: black;
    }
</style>