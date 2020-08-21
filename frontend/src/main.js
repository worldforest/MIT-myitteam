import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './vuex/store'
import VueCookies from 'vue-cookies'
import vuetify from './plugins/vuetify'
import BootstrapVue from 'bootstrap-vue'
import VueDaumPostcode from "vue-daum-postcode"
import VueCarousel from 'vue-carousel'

// firebase 설정------------------------------------------
import * as firebase from 'firebase/app'

// Required for side-effects
require("firebase/firestore");

// // firebase intialize
var firebaseConfig = {
  apiKey: "AIzaSyBzruTO6rkxZAMLxHGWajdzxXEudjuP838",
  authDomain: "mit-my-it-team.firebaseapp.com",
  databaseURL: "https://mit-my-it-team.firebaseio.com",
  projectId: "mit-my-it-team",
  storageBucket: "mit-my-it-team.appspot.com",
  messagingSenderId: "1002570956122",
  appId: "1:1002570956122:web:0d0bd71bd06e58c8eb0763",
  measurementId: "G-8F3CQPXRMH"
};
firebase.initializeApp(firebaseConfig);

// Initialize Cloud firestore through firebase
var db = firebase.firestore();

window.db=db;
//--------------------------------------------------------

var infiniteScroll =  require('vue-infinite-scroll');

// Install BootstrapVue
Vue.use(BootstrapVue)
Vue.use(VueDaumPostcode)
Vue.use(VueCookies)
Vue.use(VueCarousel)
Vue.config.productionTip = false
Vue.use(infiniteScroll)
Vue.config.devtools = false

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
  
}).$mount('#app')
