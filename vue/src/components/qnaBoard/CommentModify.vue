<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>댓글수정</h3></b-alert>
      </b-col>
    </b-row>
    <comment-input-item type="modify" />
  </b-container>
</template>

<script>
import CommentInputItem from "@/components/qnaBoard/item/CommentInputItem.vue";

export default {
  name: "CommentModify",
  components: {
    CommentInputItem,
  },
};
</script>

<style></style>
