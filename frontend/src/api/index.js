import axios from "axios";

// local vue api axios instance
function apiInstance() {
  const instance = axios.create({
    baseURL: process.env.VUE_APP_API_BASE_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });

  instance.interceptors.request.use(function (config) {
    const token = sessionStorage.getItem("access-token");
    config.headers.Authorization = token ? token : "";
    return config;
  });

  return instance;
}

export { apiInstance };
