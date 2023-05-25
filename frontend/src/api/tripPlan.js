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

async function getUserPlans(success) {
  await api
    .get(`trip/user`)
    .then(success)
    .catch(() => {});
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
  await api
    .delete(`/trip/${id}`)
    .then(success)
    .catch(() => {});
}

async function inviteTripPlan(tripPlanId, userId, success) {
  await api
    .post(`/trip/invite/${tripPlanId}?userId=${userId}`)
    .then(success)
    .catch(() => {});
}

async function hasTripPlanAuth(tripPlanId, success) {
  await api
    .get(`/trip/authority?tripPlanId=${tripPlanId}`)
    .then(success)
    .catch(() => {});
}

export {
  insertTripPlan,
  getTripPlans,
  getTripPlan,
  deleteTripPlan,
  inviteTripPlan,
  hasTripPlanAuth,
  getUserPlans,
};
