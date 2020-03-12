<template>
    <div style="position: relative; width:300px;">
        <message-form  :messageAttr="message"/>
        <message-row v-for="msg in getMessages"
                     :key="msg.id"
                     :message="msg"
                     :editMessage="editMessage"
        />
    </div>
</template>

<script>
    import MessageRow from 'components/messages/MessageRow.vue'
    import MessageForm from 'components/messages/MessageForm.vue'
    import {mapGetters} from 'vuex'
    import router from "../../router/router";
    export default{
        components: {
            MessageRow,
            MessageForm
        },
        computed: mapGetters(['getMessages']),
        data(){
            return{
                message: null
            }
        },


        methods: {
            editMessage(message){
                this.message = message;
            },

        }
    }
</script>