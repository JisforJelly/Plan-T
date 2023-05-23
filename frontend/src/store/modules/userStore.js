import jwtDecode from "jwt-decode";
import { signIn } from "@/api/auth";

const userStore = {
  namespaced: true,
  state: {
    isLogin: false,
    userInfo: null,
  },
  getters: {
    getUserInfo: function (state) {
      return state.userInfo;
    },
    getToken: function (state) {
      return state.accessToken;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    LOGOUT: (state) => {
      state.isLogin = false;
      state.userInfo = null;
      sessionStorage.removeItem("access-token");
    },
    SET_ACCESS_TOKEN: (state, token) => {
      state.accessToken = token;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    CHNAGE_USER_IMAGE: (state, changeInfo) => {
      console.log(changeInfo);
      const newUserInfo = {
        ...state.userInfo,
        name: changeInfo.name,
      };
      if (changeInfo.profileImgPath) {
        newUserInfo.profileImgPath = changeInfo.profileImgPath;
      }
      state.userInfo = newUserInfo;
    },
  },

  actions: {
    async userConfirm({ commit }, user) {
      await signIn(user, (data) => {
        const accessToken = data.headers.authorization;
        sessionStorage.setItem("access-token", accessToken);
        commit("SET_IS_LOGIN", true);
        commit("SET_USER_INFO", jwtDecode(accessToken.split(" ")[1]));
        alert("로그인 되었습니다.");
      });
    },
  },
};

export default userStore;
