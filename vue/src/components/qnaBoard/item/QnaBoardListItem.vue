<template>
  <b-tr>
    <b-td>{{ articleno }}</b-td>
    <b-th class="text-left">
      <router-link
        @click="getCommentList(articleno)"
        :to="{ name: 'qnaBoardDetail', params: { articleno: articleno } }"
        >{{ subject }}</router-link
      >
    </b-th>
    <b-td>{{ userid }}</b-td>
    <b-td>{{ regtime | dateFormat }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";
import { mapActions } from "vuex";

export default {
  name: "QnaBoardListItem",
  props: {
    articleno: Number,
    userid: String,
    subject: String,
    regtime: String,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
  created: {
    ...mapActions("getComment"),
  },
  methods: {
    getCommentList(articleno) {
      console.log(articleno);
      this.getCommentList(articleno);
    },
  },
};
</script>

<style></style>
