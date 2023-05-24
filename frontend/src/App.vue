<template>
  <div id="app" class="m-0">
    <TheHeader v-if="!isPlagRegistPage" v-observe-visibility="(isVisible) => visibilityChanged(isVisible)"/>
    <TheNavBar v-if="!isPlagRegistPage" :is-fixed="!headerVisible" :menu="this.currentMenu" @menu-change="changeMenu"/>
    <router-view></router-view>
    <TheFooter v-if="!isPlagRegistPage"/>
  </div>
</template>

<script>

import TheHeader from "@/components/TheHeader.vue"
import TheNavBar from "@/components/TheNavBar.vue"
import TheFooter from "@/components/TheFooter.vue"

export default {
  name: 'App',
  components: {
    TheHeader,
    TheNavBar,
    TheFooter,
  },
  created() { 
    const currnetMenuText = this.$route.path.split("/")[1];
    if (currnetMenuText === "hotplace") {
      this.currentMenu = {
        value: 0,
        text: "Hot-Place",
      }
    } else if (currnetMenuText === "board") {
      this.currentMenu = {
        value: 2,
        text: "자유 게시판",
      }
    } else if (currnetMenuText === "trip") {
      this.currentMenu = {
        value: 1,
        text: "Trip Plan",
      }
    } else { 
      this.currentMenu = {
        value: 3,
        text: "My Page",
      }
    }
    
    this.isPlagRegistPage = (this.$route.path == '/trip/regist' || this.$route.path.includes('plan'));
  },
  data() {
    return {
      currentMenu: {
        value: 0,
        text: "Hot-Place",
      },
      headerVisible: true,
      isPlagRegistPage: false,
    }
  },
  updated() {
    this.isPlagRegistPage = (this.$route.path == '/trip/regist' || this.$route.path.includes('plan'));
  },
  methods: {
    visibilityChanged(isVisible) { 
      this.headerVisible = isVisible;
    },

    changeMenu(menu) {
      this.currentMenu.value = menu.value;
      this.currentMenu.mkey = menu.key;
      this.currentMenu.text = menu.text;
    },
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.custom-font-style {
    font-weight: bolder !important;
    font-size: 1.6rem !important;
}

.font-red {
  color: red;
}

.round-drop-down {
  border-radius: 1.8rem !important;
}
</style>
