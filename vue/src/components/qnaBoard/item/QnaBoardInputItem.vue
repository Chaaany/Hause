<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group
          id="userid-group"
          label="작성자:"
          label-for="userid"
          description="작성자를 입력하세요."
        >
          <b-form-input
            id="userid"
            disabled
            v-model="article.userid"
            type="text"
            required
            placeholder="작성자 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="subject-group"
          label="제목:"
          label-for="subject"
          description="제목을 입력하세요."
        >
          <b-form-input
            id="subject"
            v-model="article.subject"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="article.content"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <b-button
          type="submit"
          variant="primary"
          class="m-1"
          v-if="this.type === 'register'"
          >글작성</b-button
        >
        <b-button type="submit" variant="primary" class="m-1" v-else
          >글수정</b-button
        >
        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import {
  writeQnaArticle,
  getQnaArticle,
  modifyQnaArticle,
} from "@/api/qnaBoard";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "QnaBoardInputItem",
  data() {
    return {
      article: {
        articleno: 0,
        userid: "",
        subject: "",
        content: "",
      },
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    if (this.type === "modify") {
      getQnaArticle(this.$route.params.articleno, ({ data }) => {
        // this.qnaArticle.qnaArticleno = data.qnaArticle.qnaArticleno;
        // this.qnaArticle.userid = data.qnaArticle.userid;
        // this.qnaArticle.subject = data.qnaArticle.subject;
        // this.qnaArticle.content = data.qnaArticle.content;
        this.article = data;
      });
      this.isUserid = true;
    } else {
      this.article.userid = this.userInfo.userid;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.article.userid &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.userid.focus());
      err &&
        !this.article.subject &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.article.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else
        this.type === "register"
          ? this.registqnaArticle()
          : this.modifyQnaArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.article.articleno = 0;
      this.article.subject = "";
      this.article.content = "";
      this.$router.push({ name: "qnaBoardList" });
    },
    registqnaArticle() {
      writeQnaArticle(
        {
          userid: this.article.userid,
          subject: this.article.subject,
          content: this.article.content,
        },
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        } /* eslint-disable */
      );
    },
    modifyQnaArticle() {
      modifyQnaArticle(
        {
          articleno: this.article.articleno,
          userid: this.article.userid,
          subject: this.article.subject,
          content: this.article.content,
        },
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "qnaBoardList" });
        },
        (error) => {
          console.log(error);
        } /* eslint-disable */
      );
    },
    moveList() {
      this.$router.push({ name: "qnaBoardList" });
    },
  },
};
</script>

<style></style>
