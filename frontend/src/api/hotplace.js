import { apiInstance } from "./index.js";

const api = apiInstance();

async function getHotPlaces() {
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

export { getHotPlaces, insertHotPlace, deleteHotPlace, updateHotPlace };
