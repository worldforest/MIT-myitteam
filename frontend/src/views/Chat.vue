<template>
    <div class="py-2">
      <h4 class=" text-center mb-3">{{privateChatTitle}}의 Message</h4>
      <div class="messaging"  :class="[ {'login-box': windowWidth >= 767 } ]">
        <div class="inbox_msg">
            <div class="msg_history">
              <div v-for="(message, index) in messages" :key="index" class="incoming_msg">
                <div v-if="message.senduser !== myNick">
                  <div class="incoming_msg_img"> <img class="ml-3" src="https://ptetutorials.com/images/user-profile.png" alt="sunil"> </div>
                    <div class="received_msg mb-4">
                      <div class="received_withd_msg ml-3">
                        {{ message.senduser }}
                        <p>{{ message.message }}</p>
                        <span class="time_date"> {{ message.createdAt }}</span>
                      </div>
                    </div>
                </div>
                <div v-if="message.senduser === myNick">
                  <div class="outgoing_msg mr-3">
                    <div class="sent_msg">
                      <p>{{ message.message }}</p>
                      <span class="time_date"> {{ message.createdAt }}</span>
                    </div> 
                  </div>
                </div>
              </div>
            </div>
            <div class="type_msg">
              <div class="input_msg_write">
                <input @keypress.enter="saveMessage(myNick)" v-model="message" type="text" class="write_msg px-3" placeholder="Type a message" />
                <button @click="saveMessage(myNick)" class="msg_send_btn mr-4" type="button"><i class="fa fa-paper-plane-o" aria-hidden="true"></i></button>
              </div>
            </div>
        </div>
      </div>
    </div>
</template>
<!-- The core Firebase JS SDK is always required and must be listed first -->
<script src="https://www.gstatic.com/firebasejs/7.17.2/firebase-app.js"></script>
<!-- TODO: Add SDKs for Firebase products that you want to use
     https://firebase.google.com/docs/web/setup#available-libraries -->
<script src="https://www.gstatic.com/firebasejs/7.17.2/firebase-analytics.js"></script>
<script>
import { mapState, mapActions } from 'vuex'
export default {
  name: 'Chat',
  data(){
    return {
      message: "",
      messages: [],
      privateChatTitle: '',
    }
  },
  props: {
    nickname: String,
    // privateChatTitle: String, 
  },
  methods:{
    ...mapActions(['getNickname', 'privateChat']),
    newChat(chat){
      this.privateChatTitle = chat
      this.$router.push({name:'Chat', params: { privateChatTitle : this.privateChatTitle  }});
    },
    saveMessage(sendnick){
      //save to message
      db.collection(this.privateChatTitle).add({
        senduser: sendnick,
        message:this.message,
        createdAt: new Date().toLocaleString()
      })
      this.message = null
      setTimeout(()=> {
        document.querySelector('.msg_history').scrollTop = document.querySelector('.msg_history').scrollHeight
      }, 100)  
    },
    fetchMessages(){
      db.collection(this.privateChatTitle)
      .orderBy("createdAt")
      .onSnapshot( querySnapshot => {
        let allMessages=[];
        querySnapshot.forEach( doc =>{
          allMessages.push(doc.data())
        });
          this.messages=allMessages
      })
    },
  },

  mounted() {
    window.scrollTo(0,document.body.scrollHeight);
  },
  computed:{
    ...mapState(['email', 'myNick', 'privateChatTitle']),
  },
  created(){
    if (typeof(sessionStorage.getItem('chatinfo')) === 'string') {
      if (sessionStorage.getItem('chatinfo')[0] === '{' ) {
        const info = JSON.parse(sessionStorage.getItem('chatinfo'))
        this.privateChat(info)
        this.privateChatTitle = this.$store.state.privateChatTitle
      } else {
        this.privateChatTitle = sessionStorage.getItem('chatinfo')
      }
    } 
    
    this.fetchMessages();
  },
}
</script>
<style scoped>
   *{ font-family: 'Jua', sans-serif;}
   
  .container{max-width:1170px; margin:auto;}
  img{ max-width:100%;}
  .inbox_people {
    background: #F8F8F8 none repeat scroll 0 0;
    float: left;
    overflow: hidden;
    width: 40%; border-right:1px solid #C4C4C4;
  }
  .inbox_msg {
    border: 1px solid #C4C4C4;
    clear: both;
    overflow: hidden;
  }
  .top_spac{ margin: 20px 0 0;}
  .recent_heading {float: left; width:40%;}
  .srch_bar {
    display: inline-block;
    text-align: right;
    width: 60%;
  }
  .headind_srch{ padding:10px 29px 10px 20px; overflow:hidden; border-bottom:1px solid #C4C4C4;}
  .recent_heading h4 {
    color: #05728F;
    font-size: 21px;
    margin: auto;
  }
  .srch_bar input{ border:1px solid #CDCDCD; border-width:0 0 1px 0; width:80%; padding:2px 0 4px 6px; background:none;}
  .srch_bar .input-group-addon button {
    background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
    border: medium none;
    padding: 0;
    color: #707070;
    font-size: 18px;
  }
  .srch_bar .input-group-addon { margin: 0 0 0 -27px;}
  .chat_ib h5{ font-size:15px; color:#464646; margin:0 0 8px 0;}
  .chat_ib h5 span{ font-size:13px; float:right;}
  .chat_ib p{ font-size:14px; color:#989898; margin:auto}
  .chat_img {
    float: left;
    width: 11%;
  }
  .chat_ib {
    float: left;
    padding: 0 0 0 15px;
    width: 88%;
  }
  .chat_people{ overflow:hidden; clear:both;}
  .chat_list {
    border-bottom: 1px solid #C4C4C4;
    margin: 0;
    padding: 18px 16px 10px;
  }
  .inbox_chat { height: 550px; overflow-y: scroll;}
  .active_chat{ background:#ebebeb;}
  .incoming_msg_img {
    display: inline-block;
    width: 6%;
  }
  .received_msg {
    display: inline-block;
    padding: 0 0 0 10px;
    vertical-align: top;
    width: 92%;
  }
  .received_withd_msg p {
    background: #EBEBEB none repeat scroll 0 0;
    border-radius: 3px;
    color: #646464;
    font-size: 14px;
    margin: 0;
    padding: 5px 10px 5px 12px;
    width: 100%;
  }
  .time_date {
    color: #747474;
    display: block;
    font-size: 12px;
    margin: 8px 0 0;
  }
  .received_withd_msg { width: 50%;}
  .mesgs {
    float: left;
    padding: 30px 15px 0 25px;
    width: 60%;
  }
  .sent_msg p {
    background: #05728F none repeat scroll 0 0;
    border-radius: 3px;
    font-size: 14px;
    margin: 0; color:#fff;
    padding: 5px 10px 5px 12px;
    width:100%;
  }
  .outgoing_msg{ overflow:hidden; margin:26px 0 26px;}
  .sent_msg {
    float: right;
    width: 45%;
  }
  .input_msg_write input {
    background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
    border: medium none;
    color: #4C4C4C;
    font-size: 15px;
    min-height: 48px;
    width: 100%;
  }
  .type_msg {border-top: 1px solid #C4C4C4;position: relative;}
  .msg_send_btn {
    background: #05728F none repeat scroll 0 0;
    border: medium none;
    border-radius: 50%;
    color: #fff;
    cursor: pointer;
    font-size: 17px;
    height: 30px;
    position: absolute;
    right: 0;
    top: 11px;
    width: 30px;
  }
  .msg_history {
    margin-top: 1rem;
    height: 450px;
    overflow-y: auto;
  }
</style>