<template>
  <b-container class="bv-example-row mt-3 text-center">
    <div class="mb-3">
      <b-carousel
        id="carousel-1"
        :interval="4000"
        controls
        background="#ababab"
        img-width="1024"
        img-height="480"
        style="text-shadow: 1px 1px 2px #333"
      >
        <!-- Text slides with image -->
        <!-- Slides with custom text -->
        <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
        <b-carousel-slide>
          <template #img>
            <img
              class="d-block img-fluid w-100"
              src="http://s3-ap-northeast-1.amazonaws.com/zigbang/upload/event/20210916_155510.jpeg?w=978&h=100&q=70&a=1"
              alt="image slot"
            />
          </template>
        </b-carousel-slide>
        <b-carousel-slide>
          <template #img>
            <img
              class="d-block img-fluid w-100"
              src="http://s3-ap-northeast-1.amazonaws.com/zigbang/upload/event/20220314_162923.png?w=978&h=100&q=70&a=1"
              alt="image slot"
            />
          </template>
        </b-carousel-slide>
        <b-carousel-slide>
          <template #img>
            <img
              class="d-block img-fluid w-100"
              src="http://s3-ap-northeast-1.amazonaws.com/zigbang/upload/event/20220502_121323.jpeg?w=978&h=100&q=70&a=1"
              alt="image slot"
            />
          </template>
        </b-carousel-slide>
      </b-carousel>
    </div>
    <h3 class="underline-hotpink">
      <b-icon icon="patch-question"></b-icon> QnA 게시판 - QnA Board

      <b-spinner variant="success" label="Spinning"></b-spinner>
      <b-spinner variant="success" type="grow" label="Spinning"></b-spinner>
    </h3>
    <router-view></router-view>
  </b-container>
</template>
<script>
export default {
  name: "QnaBoardView",
};
</script>
<style scoped>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgb(217, 255, 0) 30%
  );
}

.img-fluid {
  max-width: 100%;
  height: 100px;
}
</style>
