import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import VueCookies from 'vue-cookies'
import vuetify from './plugins/vuetify';
import BootstrapVue from 'bootstrap-vue'
import VueDaumPostcode from "vue-daum-postcode"

// Install BootstrapVue
Vue.use(BootstrapVue)
Vue.use(VueDaumPostcode)
Vue.use(VueCookies)
Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
