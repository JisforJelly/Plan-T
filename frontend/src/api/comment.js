import { apiInstance } from "./index.js";

const api = apiInstance();

async function getComments(postId, success) {
  await api
    .get(`/comments?postId=${postId}`)
    .then(success)
    .catch((e) => {
      console.log(e);
    });
}

async function insertComment(dto, success) {
  await api
    .post(`/comments`, JSON.stringify(dto))
    .then(success)
    .catch((e) => {
      console.log(e);
    });
}

async function deleteComment(commentId, success) {
  await api
    .delete(`/comments/${commentId}`)
    .then(success)
    .catch((e) => {
      console.log(e);
    });
}

async function updateComment(commentId, dto, success) {
  await api
    .put(`/comments/${commentId}`, JSON.stringify(dto))
    .then(success)
    .catch((e) => {
      console.log(e);
    });
}

export { getComments, insertComment, deleteComment, updateComment };
