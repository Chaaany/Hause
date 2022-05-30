<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listQnaArticle"
          >목록</b-button
        >
      </b-col>
      <b-col
        class="text-right"
        v-if="
          checkUserInfo.userid == article.userid ||
          checkUserInfo.userid == 'admin'
        "
      >
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyQnaArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="deleteQnaArticle"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${article.articleno}.
          ${article.subject}</h3><div><h6>${article.userid}</div><div>${article.regtime}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col v-if="comments.length">
        <!-- <div>
              username : kimssafy
              <br />
              <br />
              저는 1주택자도 아니고 다주택자도 아닌 무주택잡니다. 팔 집이
              없습니다. ㅠㅠ<br />
              2022-05-25
            </div> -->
        <comment-list-item
          v-for="comment in comments"
          :key="comment.commentno"
          v-bind="comment"
        />
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <textarea
          name="commentInput"
          v-model="writecontent"
          id="comment"
          cols="135"
          rows="3"
        ></textarea>
        <b-button
          class="ml-2 mb-4"
          variant="outline-dark"
          size="md"
          @click="insertComment"
          >댓글작성</b-button
        >
      </b-col>
    </b-row>
    <!-- <comment-list v-bind="article"></comment-list> -->
  </b-container>
</template>

<script>
// import moment from "moment";
import {
  getQnaArticle,
  deleteQnaArticle,
  listComment,
  writeComment,
} from "@/api/qnaBoard";
import CommentListItem from "@/components/qnaBoard/item/CommentListItem";
import { mapGetters, mapState } from "vuex";
export default {
  name: "QnaBoardDetail",
  components: {
    CommentListItem,
  },
  data() {
    return {
      article: {},
      comments: {},
      writecontent: "",
    };
  },
  computed: {
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
    ...mapGetters("memberStore", ["checkUserInfo"]),
    ...mapState("memberStore", ["isLogin", "userInfo"]),
  },
  created() {
    getQnaArticle(this.$route.params.articleno, (response) => {
      this.article = response.data;
    });
    listComment(
      this.$route.params.articleno,
      (response) => {
        this.comments = response.data;
      },
      (error) => {
        console.log(error);
      } /* eslint-disable*/
    );
  },
  methods: {
    listQnaArticle() {
      this.$router.push({ name: "qnaBoardList" });
    },
    moveModifyQnaArticle() {
      this.$router.replace({
        name: "qnaBoardModify",
        params: { articleno: this.article.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteQnaArticle() {
      if (confirm("정말로 삭제?")) {
        deleteQnaArticle(this.article.articleno, () => {
          this.$router.push({ name: "qnaBoardList" });
        });
      }
    },
    insertComment() {
      writeComment(
        {
          articleno: this.$route.params.articleno,
          userid: this.userInfo.userid,
          content: this.writecontent,
        },
        (response) => {
          console.log(response.data);
          alert("댓글 작성이 완료되었습니다.");
          this.$router.go();
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style></style>
