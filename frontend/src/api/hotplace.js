import { apiInstance } from "./index.js";

const api = apiInstance();

async function getHotPlaces(success, likeSuccess) {
  await api
    .get("/hotplaces/likes")
    .then(likeSuccess)
    .catch(() => {});

  await api
    .get("/hotplaces")
    .then(success)
    .catch((e) => {
      console.log(e);
    });
}

async function getHotPlace(hotplaceId, success) {
  await api
    .get(`/hotplaces/${hotplaceId}`)
    .then(success)
    .catch((e) => console.log(e));
}

async function insertHotPlace(form, success, fail) {
  await api.postForm("/hotplaces", form).then(success).catch(fail);
}

async function updateHotPlace(form, success, fail) {
  await api.put("/hotplaces", form).then(success).catch(fail);
}

async function toglgeHotPlaceLike(hotplaceId, success) {
  await api
    .post(`hotplaces/${hotplaceId}/likes`)
    .then(success)
    .catch((e) => {
      console.log(e);
      alert("로그인이 필요한 서비스입니다.");
    });
}

async function deleteHotPlace(hotplaceId, success) {
  await api
    .delete(`hotplaces/${hotplaceId}`)
    .then(success)
    .catch((e) => {
      console.log(e);
      alert("삭제에 실패했습니다. 관리자에게 문의하세요. ");
    });
}

async function isUserLikeHotplace(hotplaceId, success) {
  await api
    .get(`hotplaces/${hotplaceId}/likes`)
    .then(success)
    .catch((e) => {
      console.log(e);
    });
}

export {
  getHotPlaces,
  getHotPlace,
  insertHotPlace,
  deleteHotPlace,
  updateHotPlace,
  toglgeHotPlaceLike,
  isUserLikeHotplace,
};
