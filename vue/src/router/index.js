import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";

import store from "@/store/index.js";

Vue.use(VueRouter);

// https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  // console.log(store);
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const getUserInfo = store._actions["memberStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    next({ name: "signIn" });
    // router.push({ name: "signIn" });
  } else {
    // console.log("로그인 했다.");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/MemberView.vue"),
    children: [
      {
        path: "singin",
        name: "signIn",
        component: () => import("@/components/user/MemberLogin.vue"),
      },
      {
        path: "singup",
        name: "signup",
        component: () => import("@/components/user/MemberRegister.vue"),
      },
      {
        path: "mypage",
        name: "mypage",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/user/MemberMyPage.vue"),
      },
      {
        path: "mymodifypage",
        name: "mymodifypage",
        component: () => import("@/components/user/MemberModify.vue"),
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: () => import("@/views/BoardView.vue"),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardList",
        component: () => import("@/components/board/BoardList.vue"),
      },
      {
        path: "write",
        name: "boardRegister",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardRegister.vue"),
      },
      {
        path: "detail/:articleno",
        name: "boardDetail",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardDetail.vue"),
      },
      {
        path: "modify/:articleno",
        name: "boardModify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardModify.vue"),
      },
    ],
  },
  {
    path: "/qnaboard",
    name: "qnaBoard",
    component: () => import("@/views/QnaBoardView.vue"),
    redirect: "/qnaboard/qnalist",
    children: [
      {
        path: "qnalist",
        name: "qnaBoardList",
        component: () => import("@/components/qnaBoard/QnaBoardList.vue"),
      },
      {
        path: "write",
        name: "qnaBoardRegister",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/qnaBoard/QnaBoardRegister.vue"),
      },
      {
        path: "detail/:articleno",
        name: "qnaBoardDetail",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/qnaBoard/QnaBoardDetail.vue"),
      },
      {
        path: "modify/:articleno",
        name: "qnaBoardModify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/qnaBoard/QnaBoardModify.vue"),
      },
      {
        path: "modify/comment/:commentno",
        name: "commentModify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/qnaBoard/CommentModify.vue"),
      },
    ],
  },
  {
    path: "/instagram",
    name: "instagram",
    component: () => import("@/views/InstagramView.vue"),
  },

  {
    path: "/housev",
    name: "housev",
    beforeEnter: onlyAuthUser,
    component: () => import("@/views/HousevView.vue"),
  },
  {
    path: "/todo",
    name: "todo",
    beforeEnter: onlyAuthUser,
    component: () => import("@/views/TodoView.vue"),
  },
  {
    path: "/admin",
    name: "admin",
    beforeEnter: onlyAuthUser,
    component: () => import("@/views/AdminView.vue"),
    redirect: "/admin/userlist",
    children: [
      {
        path: "userlist",
        name: "userlist",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/admin/UserList.vue"),
      },
    ],
  },
  {
    path: "/zigbang",
    name: "zigbang",
    component: () => import("@/views/ZigbangView.vue"),
    beforeEnter() {
      window.open("https://www.zigbang.com/home/apt/map");
    },
  },
  {
    path: "/instagramv",
    name: "instagramv",
    component: () => import("@/views/InstagramvView.vue"),
    beforeEnter() {
      window.open("https://www.instagram.com/");
    },
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
