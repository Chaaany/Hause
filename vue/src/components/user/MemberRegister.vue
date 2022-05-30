<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
      </b-col>
    </b-row>
    <div>
      <b-form>
        <b-row>
          <b-col sm="4" offset-md="4">
            <label for="feedback-userid">아이디</label>
            <b-form-input
              id="feedback-userid"
              v-model="userid"
              :state="validation"
              ref="focusid"
              aria-describedby="feedback-userid"
            ></b-form-input>
            <b-form-invalid-feedback>
              중복된 아이디입니다.
            </b-form-invalid-feedback>
            <b-form-valid-feedback>
              사용 가능한 아이디입니다.
            </b-form-valid-feedback>
          </b-col>
        </b-row>
        <b-row>
          <b-col sm="4" offset-md="4" style="margin-top: 10px">
            <label for="feedback-username">이름</label>
            <b-form-input
              v-model="username"
              :state="validation2"
              id="feedback-username"
              ref="focusname"
            ></b-form-input>
            <b-form-invalid-feedback>
              이름을 입력해주세요.
            </b-form-invalid-feedback>
          </b-col>
        </b-row>
        <b-row>
          <b-col sm="4" offset-md="4" style="margin-top: 10px">
            <label for="feedback-userpwd">비밀번호</label>
            <b-form-input
              v-model="userpwd"
              :state="validation3"
              id="feedback-userpwd"
              ref="focuspwd"
            ></b-form-input>
            <b-form-invalid-feedback>
              비밀번호를 입력해주세요.
            </b-form-invalid-feedback>
          </b-col>
        </b-row>
        <b-row>
          <b-col sm="4" offset-md="4" style="margin-top: 10px">
            <label for="feedback-email">이메일</label>
            <b-form-input
              v-model="email"
              :state="validation4"
              id="feedback-email"
              ref="focusemail"
            ></b-form-input>
            <b-form-invalid-feedback>
              이메일을 입력해주세요.
            </b-form-invalid-feedback>
          </b-col>
        </b-row>
        <b-row>
          <b-col sm="4" offset-md="4" style="margin-top: 10px">
            <b-button variant="success" class="mr-2" @click="register"
              >가입하기</b-button
            >
            <b-button variant="primary" @click="movehome">홈 화면</b-button>
          </b-col>
        </b-row>
      </b-form>
    </div>
  </b-container>
</template>

<script>
import { checkId, registerUser } from "@/api/member";

export default {
  name: "MemberRegister",
  data() {
    return {
      userid: "",
      username: "",
      userpwd: "",
      email: "",
      result: 0,
    };
  },
  computed: {
    validation() {
      if (this.userid.length == 0) {
        return null;
      }
      if (this.userid.length > 0) {
        checkId(
          this.userid,
          (response) => {
            this.result = response.data;
          },
          (error) => {
            console.log(error);
          } /* eslint-disable */
        );
      }
      return this.result < 1;
    },
    validation2() {
      return this.username.length != 0;
    },
    validation3() {
      return this.userpwd.length != 0;
    },
    validation4() {
      return this.email.length != 0 && this.email.includes("@");
    },
  },

  methods: {
    movehome() {
      this.$router.push({ name: "home" });
    },

    register() {
      if (!this.userid) {
        alert("아이디를 입력해주세요.");
        this.$refs.focusid.focus();
      } else if (!this.username) {
        alert("이름을 입력해주세요.");
        this.$refs.focusname.focus();
      } else if (!this.userpwd) {
        alert("비밀번호를 입력해주세요.");
        this.$refs.focuspwd.focus();
      } else if (!this.email) {
        alert("이메일을 입력해주세요.");
        this.$refs.focusemail.focus();
      } else {
        registerUser(
          {
            userid: this.userid,
            username: this.username,
            userpwd: this.userpwd,
            email: this.email,
          },
          (response) => {
            console.log(response.data);
            alert("회원가입이 완료되었습니다.");
            this.$router.push({ name: "signIn" });
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
  },
};
</script>

<style></style>
