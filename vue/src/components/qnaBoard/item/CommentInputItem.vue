<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form>
        <b-form-group id="userid-group" label="작성자:" label-for="userid">
          <b-form-input
            id="userid"
            disabled
            v-model="comment.userid"
            type="text"
            required
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="comment.content"
            placeholder="내용 입력..."
            rows="3"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <b-button variant="info" class="m-1" @click="updateco"
          >댓글수정</b-button
        >
        <b-button variant="danger" class="m-1" @click="moveback">취소</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import { getComment, updateComment } from "@/api/qnaBoard";
import { mapState } from "vuex";

export default {
  name: "CommentInputItem",
  props: {
    type: { type: String },
  },

  data() {
    return {
      comment: {
        commentno: 0,
        articleno: 0,
        userid: "",
        content: "",
      },
    };
  },

  computed: {
    ...mapState("memberStore", ["isLogin", "userInfo"]),
  },

  created() {
    getComment(
      this.$route.params.commentno,
      (response) => {
        this.comment = response.data;
      },
      (error) => {
        console.log(error);
      },
    );
  },

  methods: {
    updateco() {
      updateComment(
        {
          commentno: this.comment.commentno,
          content: this.comment.content,
        },
        (response) => {
          console.log(response.data);
          alert("댓글이 수정되었습니다.");
          this.moveback();
        },
        (error) => {
          console.log(error);
          alert("오류가 발생했습니다.");
        },
      );
    },

    moveback() {
      this.$router.push({
        name: "qnaBoardDetail",
        params: { articleno: this.comment.articleno },
      });
    },
  },
};
</script>

<style></style>
