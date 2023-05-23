import { apiInstance } from "./index.js";

const api = apiInstance();

async function updateUserProfile(form, success, fail) {
  await api.postForm("/user", form).then(success).catch(fail);
}

export { updateUserProfile };
