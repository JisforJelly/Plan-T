import { apiInstance } from "./index.js";

const api = apiInstance();

async function signUp(user, success) {
  await api
    .post(`/auth/sign-up`, JSON.stringify(user))
    .then(success)
    .catch((e) => {
      console.log(e);
    });
}

async function signIn(user, success) {
  await api
    .post(`/auth/sign-in`, JSON.stringify(user))
    .then(success)
    .catch((e) => {
      alert("아이디 또는 비밀번호가 틀렸습니다.");
      console.log(e);
    });
}

export { signUp, signIn };
