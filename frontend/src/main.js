import Vue from "vue";
import App from "./App.vue";
import router from "@/router"
import VeeValidate from "vee-validate";

import VueObserveVisibility from "vue-observe-visibility";

import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import store from './store'

Vue.config.productionTip = false;
Vue.use(BootstrapVue);
Vue.use(IconsPlugin);
Vue.use(VueObserveVisibility);
Vue.use(VeeValidate, {
  inject: true,
  fieldsBagName: "veeFields",
  errorBagName: "veeErrors"
});

new Vue({
  router,
  store,
  render: (h) => h(App)
}).$mount("#app");
