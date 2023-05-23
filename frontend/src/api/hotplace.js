import { apiInstance } from "./index.js";

const api = apiInstance();

async function getHotPlaces(success, likeSuccess) {
  await api.get("/hotplaces/likes")
  .then(likeSuccess)
  .catch(()=>{});

  await api.get("/hotplaces")
  .then(success)
  .catch(e=>{console.log(e)});
}

async function getHotPlace(hotplaceId, success) {
  await api.get(`/hotplaces/${hotplaceId}`)
  .then(success)
  .catch(e=>console.log(e));
}

async function insertHotPlace(form, success) {   
  await api.postForm("/hotplaces", form)
  .then(success)
  .catch(e=>{console.log(e)});
}

async function toggleHotPlaceLike(hotplaceId, success) {
  await api.post(`hotplaces/${hotplaceId}/likes`)
  .then(success)
  .catch(
    e=>{
      console.log(e)
      alert("로그인이 필요한 서비스입니다.")
    });
}

async function deleteHotPlace() {
}

async function updateHotPlace() {
}

export { getHotPlaces, getHotPlace, insertHotPlace, deleteHotPlace, updateHotPlace, toggleHotPlaceLike };
