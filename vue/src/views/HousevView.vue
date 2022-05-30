<template>
  <b-container class="mt-3 text-center">
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
              src="http://s3-ap-northeast-1.amazonaws.com/zigbang/upload/event/20220314_162923.png?w=978&h=100&q=70&a=1"
              alt="image slot"
            />
          </template>
        </b-carousel-slide>
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
              src="http://s3-ap-northeast-1.amazonaws.com/zigbang/upload/event/20220502_121323.jpeg?w=978&h=100&q=70&a=1"
              alt="image slot"
            />
          </template>
        </b-carousel-slide>
      </b-carousel>
    </div>

    <h3 class="underline-orange">
      <b-icon icon="house-fill"></b-icon> 아파트 찾기 - Apt Search
    </h3>
    <b-spinner label="Spinning"></b-spinner>
    <b-spinner type="grow" label="Spinning"></b-spinner>

    <b-row>
      <b-col>
        <house-search-bar></house-search-bar>
        <house-search-bar-i></house-search-bar-i>
      </b-col>
    </b-row>
    <b-row>
      <!-- 리스트 -->
      <b-col cols="3" style="padding: 0" align="left">
        <house-list />
      </b-col>
      <!-- 지도 -->
      <b-col cols="6" style="padding: 0" align="left">
        <house-map-list />
      </b-col>
      <!-- 상세정보 -->
      <b-col cols="3" style="padding: 0" align="left">
        <house-detail />
      </b-col>
    </b-row>
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
  </b-container>
</template>
<script>
import HouseSearchBar from "@/components/housev/HouseSearchBar.vue";
import HouseSearchBarI from "@/components/housev/HouseSearchBarI.vue";
import HouseList from "@/components/housev/HouseList.vue";
import HouseDetail from "@/components/housev/HouseDetail.vue";
import HouseMapList from "@/components/housev/HouseMapList.vue";

export default {
  name: "HousevView",
  components: {
    HouseSearchBar,
    HouseSearchBarI,
    HouseList,
    HouseDetail,
    HouseMapList,
  },
};
</script>
<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 149, 27, 0.3) 30%
  );
}
.img-fluid {
  max-width: 100%;
  height: 100px;
}
</style>
