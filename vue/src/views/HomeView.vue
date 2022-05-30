<template>
  <b-container class="bv-example-row mt-5 text-center">
    <!-- <h3 class="underline-steelblue"><b-icon icon="house"></b-icon> SSAFY</h3>
    <b-row>
      <b-col></b-col>
      <b-col cols="10">
        <b-jumbotron
          bg-variant="muted"
          text-variant="dark"
          border-variant="dark"
        >
          <template #header>SSAFY Home</template>

          <template #lead>
            슬기로운 싸피 생활 (:7기편) <br />
            행운을 부르는 러~~~~키 Seven!!!!!
          </template>

          <hr class="my-4" />

          <p>Vue + Bootstrap활용.</p>
          <p>Bootstrap-vue는 버전 <b>4.6.1</b>을 권장합니다.</p>
          <p><b>BoardList.vue</b>를 바꿔가면서 테스트하세요.</p>
          <p>Bootstrap의 <b>table</b> 사용법을 익히게됩니다.</p>
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row> -->
    <div class="text-center">
      <b-spinner label="Spinning"></b-spinner>
      <b-spinner type="grow" label="Spinning"></b-spinner>
      <b-spinner variant="danger" label="Spinning"></b-spinner>
      <b-spinner variant="danger" type="grow" label="Spinning"></b-spinner>
      <b-spinner variant="success" label="Spinning"></b-spinner>
      <b-spinner variant="success" type="grow" label="Spinning"></b-spinner>
      <b-spinner variant="info" label="Spinning"></b-spinner>
      <b-spinner variant="info" type="grow" label="Spinning"></b-spinner>
    </div>
    <h3>행복한 집, happy house, "hause"</h3>
    <h3>hause와 함께 당신의 house를 찾아보세요</h3>

    <img src="@/assets/house.jpg" alt="" style="object-fit: cover" />
  </b-container>
</template>

<script>
export default {
  name: "HomeView",
  props: {
    msg: String,
  },
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
</style>
