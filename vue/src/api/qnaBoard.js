import { apiInstance } from "./index.js";

const api = apiInstance();

function listQnaArticle(page, success, fail) {
  api.get(`/qnaboard/?pg=${page}`).then(success).catch(fail);
}

function writeQnaArticle(article, success, fail) {
  api.post(`/qnaboard`, JSON.stringify(article)).then(success).catch(fail);
}

function getQnaArticle(articleno, success, fail) {
  api.get(`/qnaboard/${articleno}`).then(success).catch(fail);
}

function modifyQnaArticle(article, success, fail) {
  api
    .put(`/qnaboard/${article.articleno}`, JSON.stringify(article))
    .then(success)
    .catch(fail);
}

function deleteQnaArticle(articleno, success, fail) {
  api.delete(`/qnaboard/${articleno}`).then(success).catch(fail);
}

function countQnaArticle(success, fail) {
  api.get(`/qnaboard/count`).then(success).catch(fail);
}

function searchQna(key, word, success, fail) {
  api
    .get(`/qnaboard/search?key=${key}&value=${word}`)
    .then(success)
    .catch(fail);
}

function listComment(articleno, success, fail) {
  api.get(`/qnaboard/comment/${articleno}`).then(success).catch(fail);
}

function writeComment(comment, success, fail) {
  api
    .post(`/qnaboard/comment`, JSON.stringify(comment))
    .then(success)
    .catch(fail);
}

function updateComment(comment, success, fail) {
  api
    .put(`/qnaboard/comment/${comment.commentno}`, JSON.stringify(comment))
    .then(success)
    .catch(fail);
}

function deleteComment(commentno, success, fail) {
  api.delete(`/qnaboard/comment/${commentno}`).then(success).catch(fail);
}

function getComment(commentno, success, fail) {
  api.get(`/qnaboard/comment/detail/${commentno}`).then(success).catch(fail);
}

export {
  listQnaArticle,
  writeQnaArticle,
  getQnaArticle,
  modifyQnaArticle,
  deleteQnaArticle,
  countQnaArticle,
  searchQna,
  listComment,
  writeComment,
  updateComment,
  deleteComment,
  getComment,
};
