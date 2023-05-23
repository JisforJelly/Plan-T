import { apiInstance } from "./index.js";

const api = apiInstance();

async function getSido(success) {
  await api
    .get(`/meta/sido`)
    .then(success)
    .catch((e) => {
      console.log(e);
    });
}

async function getContent(success) {
    await api
      .get(`/meta/content`)
      .then(success)
      .catch((e) => {
        console.log(e);
      });
  }

  async function getGugun(success) {
    await api
      .get(`/meta/gugun`)
      .then(success)
      .catch((e) => {
        console.log(e);
      });
  }

export { getSido, getContent, getGugun };
