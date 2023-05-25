import { apiInstance } from "./index.js";

const api = apiInstance();

async function updateUserProfile(form, success, fail) {
  await api.postForm("/user", form).then(success).catch(fail);
}

async function searchUsers(searchParams, success) {
  await api
    .get(`/user?searchParams=${searchParams}`)
    .then(success)
    .catch(() => {});
}

export { updateUserProfile, searchUsers };
