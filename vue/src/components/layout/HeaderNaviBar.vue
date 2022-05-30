<template>
  <div>
    <b-navbar toggleable="lg" style="background-color: royalblue" opacity="0.2">
      <b-navbar-brand>
        <router-link to="/">
          <img
            src="@/assets/ssafy_logo.png"
            class="d-inline-block align-middle"
            width="110px"
            alt="ssafy"
          />
        </router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <!-- <b-nav-item
            ><router-link :to="{ name: 'home' }" class="link"
              ><b-icon icon="house" font-scale="1.5"></b-icon> 홈</router-link
            ></b-nav-item
          > -->
          <b-nav-item href="#"
            ><router-link :to="{ name: 'housev' }" class="link"
              ><b-icon icon="house" font-scale="1.5"></b-icon>
              아파트정보</router-link
            ></b-nav-item
          >

          <b-nav-item
            ><router-link :to="{ name: 'board' }" class="link"
              ><b-icon icon="journal" font-scale="1.5"></b-icon>
              공지사항</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'qnaBoard' }" class="link"
              ><b-icon icon="patch-question" font-scale="1.5"></b-icon>
              QnA게시판</router-link
            ></b-nav-item
          >
          <!-- <b-nav-item href="#"
            ><router-link :to="{ name: 'instagram' }" class="link"
              ><b-icon icon="instagram" font-scale="1.5"></b-icon>
              인별그램</router-link
            ></b-nav-item
          > -->

          <!-- <b-nav-item href="#"
            ><router-link :to="{ name: 'house' }" class="link"
              ><b-icon icon="house-fill" font-scale="1.5"></b-icon>
              아파트정보</router-link
            ></b-nav-item
          > -->

          <b-nav-item href="#"
            ><router-link :to="{ name: 'todo' }" class="link"
              ><b-icon icon="calendar-check" font-scale="1.5"></b-icon>
              TodoList</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'instagramv' }" class="link"
              ><b-icon icon="instagram" font-scale="1.5"></b-icon>
              인별그램</router-link
            ></b-nav-item
          >

          <b-nav-item href="#" v-if="userInfo && userInfo.userid == 'admin'"
            ><router-link :to="{ name: 'admin' }" class="link"
              ><b-icon icon="lock-fill" font-scale="1.5"></b-icon> 관리자
              페이지</router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto" v-if="userInfo">
          <b-nav-item href="#"
            ><router-link :to="{ name: 'zigbang' }" class="link"
              ><img
                src="@/assets/zigbang_logo.png"
                class="d-inline-block align-middle"
                width="38px"
                alt="zigbang"
              />
              직방</router-link
            ></b-nav-item
          >
          <div style="margin: auto">
            <b-nav-form
              method="get"
              action="http://www.google.co.kr/search"
              target="_blank"
            >
              <b-form-input
                class="mr-sm-2"
                type="text"
                name="q"
                size="25"
                maxlength="255"
                value=""
                style="boarder-radius: 5px"
                placeholder="Search"
              ></b-form-input>
              <b-button variant="warning" class="my-2 my-sm-0" type="submit"
                >Google 검색</b-button
              >
            </b-nav-form>
          </div>
          <!-- <div style="margin: auto">
            <form
              method="get"
              action="https://new.land.naver.com/search"
              target="_blank"
            >
              <table bgcolor="white">
                <tr>
                  <td>
                    <input
                      type="text"
                      name="ks"
                      size="25"
                      maxlength="255"
                      value=""
                      style="boarder-radius: 5px"
                    /> -->
          <!-- 구글 검색 입력 창 -->
          <!-- <input
                      type="submit"
                      name="btnG"
                      value="네이버부동산 검색"
                    /> -->
          <!-- 검색 버튼 -->
          <!-- </td>
                </tr>
              </table>
            </form> -->
          <!-- </div> -->
          <!-- <div style="margin: auto">
            <form
              method="get"
              action="https://hogangnono.com/search"
              target="_blank"
            >
              <table bgcolor="white">
                <tr>
                  <td>
                    <input
                      type="text"
                      name="q"
                      size="25"
                      maxlength="255"
                      value=""
                      style="boarder-radius: 5px"
                    /> -->
          <!-- 구글 검색 입력 창 -->
          <!-- <input type="submit" name="btnG" value="호갱노노 검색" /> -->
          <!-- 검색 버튼 -->
          <!-- </td>
                </tr>
              </table>
            </form>
          </div> -->
          <b-nav-item class="align-self-center"
            ><b-avatar
              variant="primary"
              v-text="userInfo ? userInfo.userid.charAt(0).toUpperCase() : ''"
            ></b-avatar
            ><router-link
              :to="{ name: 'mypage' }"
              class="link align-self-center"
              >{{ userInfo.username }}({{ userInfo.userid }})님 </router-link
            >환영합니다.</b-nav-item
          >
          <!-- <b-nav-item class="align-self-center"
            ><router-link
              :to="{ name: 'mypage' }"
              class="link align-self-center"
              >내정보보기</router-link
            ></b-nav-item
          > -->
          <b-nav-item class="align-self-center" @click.prevent="onClickLogout"
            >로그아웃</b-nav-item
          >
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto" v-else>
          <!-- <b-nav-item-dropdown right> -->

          <b-nav-item
            ><router-link :to="{ name: 'signup' }" class="link"
              ><b-icon icon="people" font-scale="1.5"></b-icon>
              회원가입</router-link
            ></b-nav-item
          >

          <b-nav-item
            ><router-link :to="{ name: 'signIn' }" class="link"
              ><b-icon icon="key" font-scale="1.5"></b-icon> 로그인</router-link
            ></b-nav-item
          >

          <!-- </b-nav-item-dropdown> -->
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
// import ms from "@/store/modules/memberStore";

const memberStore = "memberStore";

export default {
  name: "HeaderNaviBar",
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // console.log("memberStore : ", ms);
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "home" });
    },
  },
};
</script>

<style>
.navbar-light .navbar-nav .nav-link {
  color: white;
  text-decoration: none;
}
</style>
