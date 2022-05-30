<template>
  <b-card class="mb-2" border-variant="dark" no-body>
    <b-card-body class="text-left">
      <div style="display: flex">
        <div>
          username : {{ username }}&nbsp; &#40; {{ regtime | dateFormat }} &#41;
          <br />
          <br />
          {{ content }}
        </div>
        <div
          style="margin-left: auto"
          v-if="userInfo.userid == this.params.userid"
        >
          <b-button variant="info" class="mr-2" size="sm" @click="updateco"
            >수정</b-button
          >
          <b-button variant="danger" size="sm" @click="deleteco">삭제</b-button>
        </div>
      </div>
    </b-card-body>
  </b-card>
</template>

<script>
import { deleteComment } from "@/api/qnaBoard";
import { mapState } from "vuex";
import moment from "moment";

export default {
  name: "CommentListItem",
  props: {
    commentno: Number,
    userid: String,
    username: String,
    content: String,
    regtime: String,
  },

  data() {
    return {
      params: {
        commentno: this.commentno,
        userid: this.userid,
      },
    };
  },

  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY-MM-DD");
    },
  },

  methods: {
    deleteco() {
      if (confirm("댓글을 삭제하시겠습니까?")) {
        deleteComment(
          this.params.commentno,
          (response) => {
            console.log(response.data);
            alert("댓글 삭제가 완료되었습니다.");
            this.$router.go();
          },
          (error) => {
            console.log(error);
          },
        );
      }
    },

    updateco() {
      this.$router.replace({
        name: "commentModify",
        params: { commentno: this.params.commentno },
      });
    },
  },

  computed: {
    ...mapState("memberStore", ["isLogin", "userInfo"]),
  },
};
</script>

<style></style>
