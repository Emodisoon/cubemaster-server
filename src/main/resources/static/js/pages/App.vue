var app = new Vue({
  el: '#app',
  template: '<messages-list :messages="messages"/>',
  data: {
    messages: [

    ]
  }

 <template>
    <messages-list :messages="messages"/>
 </template>

 <script>
    import MessagesList from 'components/messages/MessageList.vue'
    export default {
        components: {
            MessagesList
        },
        data() {
        return {messages: []}
        }
    }
 </script>