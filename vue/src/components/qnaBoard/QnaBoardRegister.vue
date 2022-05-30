<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글작성</h3></b-alert>
      </b-col>
    </b-row>
    <qna-board-input-item type="register" />
  </b-container>
</template>

<script>
import QnaBoardInputItem from "@/components/qnaBoard/item/QnaBoardInputItem.vue";

export default {
  name: "QnaBoardWrite",
  components: {
    QnaBoardInputItem,
  },
};
</script>

<style></style>
