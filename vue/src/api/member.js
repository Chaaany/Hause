import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}

async function deleteUser(userid, success, fail) {
  await api.delete(`/user/${userid}`).then(success).catch(fail);
}

async function modifyUser(userInfo, success, fail) {
  await api
    .put(`/user/${userInfo.userid}`, JSON.stringify(userInfo))
    .then(success)
    .catch(fail);
}

function getUserlist(success, fail) {
  api.get(`/user/userlist`).then(success).catch(fail);
}

function checkId(userid, success, fail) {
  api.get(`/user/checkid/${userid}`).then(success).catch(fail);
}

function registerUser(user, success, fail) {
  api.post(`/user/register`, JSON.stringify(user)).then(success).catch(fail);
}

// function logout(success, fail)

export {
  login,
  findById,
  deleteUser,
  modifyUser,
  getUserlist,
  checkId,
  registerUser,
};
