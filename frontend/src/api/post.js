import { apiInstance } from "./index.js";

const api = apiInstance();

async function savePost(post, success) {
  await api
    .post(`/posts`, JSON.stringify(post))
    .then(success)
    .catch((e) => {
      alert("게시글 등록에 실패했습니다. 관리자에게 문의하세요.");
      console.log(e);
    });
}

async function getPosts(page, size, success) {
  await api
    .get(`/posts?page=${page}&size=${size}`)
    .then(success)
    .catch((e) => {
      alert("게시글 조회에 실패했습니다. 관리자에게 문의하세요.");
      console.log(e);
    });
}

async function searchPosts(type, params, success) {
  await api
    .get(`/posts/${type}/${params}?page=0&size=10`)
    .then(success)
    .catch((e) => {
      alert("게시글 조회에 실패했습니다. 관리자에게 문의하세요.");
      console.log(e);
    });
}

async function getPostByPostId(postId, success) {
  await api
    .get(`/posts/${postId}`)
    .then(success)
    .catch((e) => {
      alert("게시글 조회에 실패했습니다. 관리자에게 문의하세요.");
      console.log(e);
    });
}

async function deletePost(postId, success) {
  await api
    .delete(`/posts/${postId}`)
    .then(success)
    .catch((e) => {
      alert("게시글 삭제에 실패했습니다. 관리자에게 문의하세요.");
      console.log(e);
    });
}

async function modifyPost(post, success) {
  await api
    .put(`/posts`, JSON.stringify(post))
    .then(success)
    .catch((e) => {
      alert("게시글 수정에 실패했습니다. 관리자에게 문의하세요.");
      console.log(e);
    });
}

export { savePost, getPosts, getPostByPostId, deletePost, modifyPost, searchPosts };
