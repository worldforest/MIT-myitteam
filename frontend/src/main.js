import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './vuex/store'
import VueCookies from 'vue-cookies'
import vuetify from './plugins/vuetify'
<<<<<<< HEAD
import BootstrapVue from 'bootstrap-vue'
=======
import { BootstrapVue } from 'bootstrap-vue'

>>>>>>> 618dc3cf313f5dc7b08ad42782110c3d4f901c7f
import VueDaumPostcode from "vue-daum-postcode"
import VueCarousel from 'vue-carousel'

// Install BootstrapVue
Vue.use(BootstrapVue)
Vue.use(VueDaumPostcode)
Vue.use(VueCookies)
Vue.use(VueCarousel)
Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
