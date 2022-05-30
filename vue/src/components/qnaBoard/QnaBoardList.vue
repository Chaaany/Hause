<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>QnA 게시판</h3></b-alert>
      </b-col>
    </b-row>
    <div
      class="text-center mb-3"
      style="display: flex; align-items: center; justify-content: center"
    >
      <input
        class="col-7 view mr-3 form-control"
        type="text"
        v-model="searchValue"
        placeholder="검색어를 입력하세요."
      />
      <div class="col-3">
        <b-form-select
          v-model="selected"
          :options="options"
          size="m"
        ></b-form-select>
      </div>
      <button class="btn btn-primary" @click="search">검색</button>
    </div>
    <b-row class="mb-1">
      <b-col class="text-right" v-if="checkUserInfo">
        <b-button variant="outline-primary" @click="moveWrite()"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="articles.length">
        <b-table-simple hover responsive>
          <b-thead head-variant="dark">
            <b-tr>
              <b-th>글번호</b-th>
              <b-th>제목</b-th>
              <b-th>작성자</b-th>
              <b-th>작성일</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <qna-board-list-item
              v-for="article in articles"
              :key="article.articleno"
              v-bind="article"
            />
          </tbody>
        </b-table-simple>
      </b-col>
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-row>
    <b-pagination
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      @page-click="pageClick"
      align="center"
    ></b-pagination>
  </b-container>
</template>

<script>
import { listQnaArticle, countQnaArticle, searchQna } from "@/api/qnaBoard.js";
import QnaBoardListItem from "@/components/qnaBoard/item/QnaBoardListItem";
import { mapGetters } from "vuex";

export default {
  name: "QnaBoardList",
  components: {
    QnaBoardListItem,
  },
  data() {
    return {
      articles: [],
      rows: 1,
      perPage: 10,
      currentPage: 1,
      searchValue: "",
      selected: null,
      options: [
        { value: null, text: "전체 조회" },
        { value: "subject", text: "제목" },
        { value: "userid", text: "아이디" },
        { value: "articleno", text: "글번호" },
      ],
    };
  },
  created() {
    listQnaArticle(
      this.currentPage,
      (response) => {
        this.articles = response.data;
      },
      (error) => {
        console.log(error);
      } /* eslint-disable */
    );
    countQnaArticle(
      (response) => {
        this.rows = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "qnaBoardRegister" });
    },

    pageClick: function (button, page) {
      this.getNoticeListByPage(page);
    },

    getNoticeListByPage: function (page) {
      listQnaArticle(
        page,
        (response) => {
          this.articles = response.data;
        },
        (error) => {
          console.log(error);
        } /* eslint-disable */
      );
    },

    search() {
      searchQna(
        this.selected,
        this.searchValue,
        (response) => {
          this.articles = response.data;
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },

  computed: {
    ...mapGetters("memberStore", ["checkUserInfo"]),
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
