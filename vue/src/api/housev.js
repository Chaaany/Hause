import { apiInstance } from "./index.js";

const api = apiInstance();

function sidoList(success, fail) {
  api.get(`/map/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}

function dongList(params, success, fail) {
  api.get(`/map/dong`, { params: params }).then(success).catch(fail);
}

function houseList(params, success, fail) {
  api.get(`/map/apt`, { params: params }).then(success).catch(fail);
}

function searchByAptName(params, success, fail) {
  api.get(`/map/searchbyaptname`, { params: params }).then(success).catch(fail);
}

function searchByAptCode(params, success, fail) {
  api.get(`/map/searchbyaptcode`, { params: params }).then(success).catch(fail);
}

function aptHighest(params, success, fail) {
  api.get(`/map/highest`, { params: params }).then(success).catch(fail);
}

function aptLowest(params, success, fail) {
  api.get(`/map/lowest`, { params: params }).then(success).catch(fail);
}

function aptHit(aptCode, success, fail) {
  api.put(`/map/hit/${aptCode}`).then(success).catch(fail);
}

export {
  sidoList,
  gugunList,
  dongList,
  houseList,
  searchByAptName,
  searchByAptCode,
  aptHighest,
  aptLowest,
  aptHit,
};
