<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글목록</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col
        class="text-right"
        v-if="checkUserInfo && checkUserInfo.userid == 'admin'"
      >
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
              <b-th>조회수</b-th>
              <b-th>작성자</b-th>
              <b-th>작성일</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <board-list-item
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
import { listArticle, countArticle } from "@/api/board.js";
import BoardListItem from "@/components/board/item/BoardListItem";
import { mapGetters } from "vuex";

export default {
  name: "BoardList",
  components: {
    BoardListItem,
  },
  data() {
    return {
      articles: [],
      rows: 1,
      perPage: 10,
      currentPage: 1,
    };
  },
  created() {
    listArticle(
      this.currentPage,
      (response) => {
        this.articles = response.data;
      },
      (error) => {
        console.log(error);
      } /* eslint-disable */
    );
    countArticle(
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
      this.$router.push({ name: "boardRegister" });
    },

    pageClick: function (button, page) {
      this.getNoticeListByPage(page);
    },
    getNoticeListByPage: function (page) {
      listArticle(
        page,
        (response) => {
          this.articles = response.data;
        },
        (error) => {
          console.log(error);
        } /* eslint-disable */
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
