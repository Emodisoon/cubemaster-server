<template>
    <v-app>
        <v-app-bar color="blue" dense app>
            <v-toolbar-title>CubeMaster</v-toolbar-title>
            <v-spacer></v-spacer>
            {{username}}
            <v-btn icon href="/login">
                <v-icon>mdi-arrow-left</v-icon>
            </v-btn>
        </v-app-bar>

        <v-content>
            <messages-list :messages="messages"/>
        </v-content>


    </v-app>
</template>

<script>
    import MessagesList from 'components/messages/MessageList.vue'
    export default {
        components: {
            MessagesList
        },
        data() {
            return {messages: [],
                username: "Emodisoon"
            }
        }
    }
</script>