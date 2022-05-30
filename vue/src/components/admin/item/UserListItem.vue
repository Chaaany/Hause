<template>
  <b-tr>
    <b-td>{{ userid }}</b-td>
    <b-td
      ><b-form-input v-model="User.username" type="text" required></b-form-input
    ></b-td>
    <b-td
      ><b-form-input v-model="User.userpwd" type="text" required></b-form-input>
    </b-td>
    <b-td
      ><b-form-input v-model="User.email" type="text" required></b-form-input
    ></b-td>
    <b-td>{{ joindate | dateFormat }}</b-td>
    <b-td><b-button variant="primary" @click="updateuser">수정</b-button></b-td>
    <b-td><b-button variant="danger" @click="deluser">삭제</b-button></b-td>
  </b-tr>
</template>

<script>
import moment from "moment";
import { modifyUser, deleteUser } from "@/api/member";
import { mapState, mapMutations } from "vuex";

export default {
  name: "UserListItem",
  props: {
    userid: String,
    username: String,
    userpwd: String,
    email: String,
    joindate: String,
  },
  data() {
    return {
      User: {
        userid: this.userid,
        username: this.username,
        userpwd: this.userpwd,
        email: this.email,
        joindate: this.joindate,
      },

      modiUser: {
        userid: this.userid,
        username: "",
        userpwd: "",
        email: "",
        joindate: this.joindate,
      },
    };
  },

  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },

  computed: {
    ...mapState("memberStore", ["isLogin", "userInfo"]),
  },

  methods: {
    ...mapMutations("memberStore", ["SET_IS_LOGIN", "SET_USER_INFO"]),
    updateuser() {
      if (!this.User.username) {
        alert("이름을 입력해주세요.");
      } else if (!this.User.userpwd) {
        alert("비밀번호를 입력해주세요.");
      } else if (!this.User.email) {
        alert("이메일을 입력해주세요.");
      } else {
        this.modiUser.username = this.User.username;
        this.modiUser.userpwd = this.User.userpwd;
        this.modiUser.email = this.User.email;
        modifyUser(
          {
            userid: this.User.userid,
            username: this.User.username,
            email: this.User.email,
            userpwd: this.User.userpwd,
          },
          (response) => {
            console.log(response.data);
            alert("회원 수정이 완료되었습니다.");
            this.$router.go();
            if (this.User.userid == "admin") {
              this.SET_USER_INFO(this.modiUser);
            }
          },
          (error) => {
            console.log(error);
          } /* eslint-disable */
        );
      }
    },
    deluser() {
      deleteUser(
        this.userid,
        (response) => {
          console.log(response.data);
          alert("회원 삭제가 완료되었습니다.");
          if (this.User.userid == "admin") {
            this.SET_IS_LOGIN(false);
            this.SET_USER_INFO(null);
            sessionStorage.removeItem("access-token");
            this.$router.push({ name: "home" });
          } else {
            this.$router.go();
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style></style>
