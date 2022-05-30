<template>
  <b-container class="mt-4" v-if="userInfo">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>내 정보 수정</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-jumbotron>
          <template #header>My Page </template>

          <template #lead> 내 정보 수정페이지입니다. </template>

          <hr class="my-4" />

          <b-container class="mt-4">
            <b-row class="mb-2">
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">아이디</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.userid }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row class="mb-2">
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="center">이름</b-col
              ><b-col cols="4" align-self="center"
                ><b-form-input
                  id="username"
                  v-model="userInfo.username"
                  type="text"
                  required
                ></b-form-input
              ></b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row class="mb-2">
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="center">이메일</b-col
              ><b-col cols="4" align-self="start"
                ><b-form-input
                  id="email"
                  v-model="userInfo.email"
                  type="text"
                  required
                ></b-form-input
              ></b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row class="mb-2">
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="center">가입일</b-col
              ><b-col cols="4" align-self="start">{{
                userInfo.joindate
              }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="center">비밀번호</b-col
              ><b-col cols="4" align-self="start"
                ><b-form-input
                  id="password"
                  v-model="userInfo.userpwd"
                  type="text"
                  required
                ></b-form-input
              ></b-col>
              <b-col cols="2"></b-col>
            </b-row>
          </b-container>
          <hr class="my-4" />

          <b-button
            type="submit"
            variant="primary"
            @click="updateuser"
            class="mr-1"
            >정보수정</b-button
          >
          <b-button
            type="submit"
            variant="primary"
            @click="movemypage"
            class="mr-1"
            >취소</b-button
          >
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { modifyUser } from "@/api/member";
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberModify",
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo", "isLogin"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.userInfo.username &&
        ((msg = "이름을 입력해주세요"),
        (err = false),
        this.$refs.username.focus());
      err &&
        !this.userInfo.email &&
        ((msg = "이메일을 입력해주세요"),
        (err = false),
        this.$refs.email.focus());
      err &&
        !this.userInfo.userpwd &&
        ((msg = "비밀번호를 입력해주세요"),
        (err = false),
        this.$refs.userpwd.focus());

      if (!err) alert(msg);
      else this.updateUser();
    },

    updateuser() {
      modifyUser(
        {
          userid: this.userInfo.userid,
          username: this.userInfo.username,
          email: this.userInfo.email,
          userpwd: this.userInfo.userpwd,
        },
        ({ data }) => {
          let msg =
            "수정 처리시 문제가 발생했습니다. 입력 내용을 확인 해 주세요";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
            this.confirm();
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "mypage" });
        },
        (error) => {
          console.log(error);
        } /* eslint-disable */
      );
    },

    async confirm() {
      await this.userConfirm(this.userInfo);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
      }
    },
    movemypage() {
      this.$router.replace({
        name: "mypage",
      });
    },
  },
};
</script>

<style></style>
