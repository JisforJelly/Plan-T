import { apiInstance } from "./index.js";

const api = apiInstance();

async function insertTripPlan(dto, success) {
  await api
    .post(`/trip`, JSON.stringify(dto))
    .then(success)
    .catch((e) => {
      console.log(e);
    });
}

async function getTripPlans(success) {
  await api
    .get(`/trip`)
    .then(success)
    .catch((e) => {
      console.log(e);
    });
}

async function getTripPlan(id, success) {
  await api
    .get(`/trip/${id}`)
    .then(success)
    .catch((e) => {
      console.log(e);
    });
}

async function deleteTripPlan(id, success) {
  await api.delete(`/trip/${id}`)
    .then(success).catch(()=>{});
}

export { insertTripPlan, getTripPlans, getTripPlan, deleteTripPlan };
