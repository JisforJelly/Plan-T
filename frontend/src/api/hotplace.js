import { apiInstance } from "./index.js";

const api = apiInstance();

async function getHotPlaces(success) {
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

async function deleteHotPlace() {
}

async function updateHotPlace() {
}

export { getHotPlaces, getHotPlace, insertHotPlace, deleteHotPlace, updateHotPlace };
