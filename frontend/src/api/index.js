import axios from "axios";
import store from "@/store";
import router from "@/router";
// local vue api axios instance
function apiInstance() {
  const instance = axios.create({
    baseURL: process.env.VUE_APP_API_BASE_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
      "appKey": "Ui71N3fk9Y6Yx6OPdc8IX2ObPAvU4lzK63Z7Jn6o",
    },
  });

  instance.interceptors.request.use(function (config) {
    const token = sessionStorage.getItem("access-token");
    config.headers.Authorization = token ? token : "";
    return config;
  });

  instance.interceptors.response.use(
    function (response) {
      return response;
    },
    function (error) {
      if (error.response && error.response.status) {
        switch (error.response.status) {
          case 401:
            store.commit("userStore/LOGOUT");
            alert("토큰이 만료되었습니다. 다시 로그인 해주세요.");
            router.push("/").catch(() => {});
        }
      }
    }
  );

  return instance;
}

export { apiInstance };
