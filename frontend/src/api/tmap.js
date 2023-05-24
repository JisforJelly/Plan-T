import { apiInstance } from "./index.js";

const api = apiInstance();

async function getOptimalPath(data, success) {
    await api
      .post(`https://apis.openapi.sk.com/tmap/routes/routeSequential30?version=1&format=json`, JSON.stringify(data))
      .then(success)
      .catch((e) => {
        console.log(e);
      });
  }

  export { getOptimalPath };
