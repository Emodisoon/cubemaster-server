<template>
    <div>
    <h1>Вы разавторизованы</h1>
    <v-btn @click="logout"> логаут</v-btn>
    </div>
</template>

<script>
    import {mapMutations} from 'vuex'
    export default {
        name: "logout",
        computed: {...mapMutations[('logOutMutation')]},
        methods:{
            logout(){
                console.log('Logout')
                this.$store.commit('logOutMutation')
            }
        }
    }
</script>



<style scoped>

</style>