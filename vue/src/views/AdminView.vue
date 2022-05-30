<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-hotpink">
      <b-icon icon="lock-fill"></b-icon> 관리자 페이지
    </h3>
    <router-view></router-view>
  </b-container>
</template>
<script>
export default {
  name: "AdminView",
};
</script>
<style scoped>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgb(221, 139, 241) 30%
  );
}
</style>
