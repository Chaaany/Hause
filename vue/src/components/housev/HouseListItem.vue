<template>
  <b-row
    class="mb-3"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-col cols="1" class="text-center align-self-center">
      <b-img
        thumbnail
        src="https://picsum.photos/250/250/?image=58"
        alt="Image 1"
      ></b-img>
    </b-col>
    <b-col cols="10" class="align-self-center" style="padding: 0">
      [{{ house.aptCode }}] {{ house.aptName }}
      <b-badge variant="success" pill>{{ house.hit }}</b-badge>
    </b-col>
  </b-row>
</template>

<script>
import { EventBus } from "@/utils/event-bus";
import { mapActions, mapState } from "vuex";
import { aptHit } from "@/api/housev";

const housevStore = "housevStore";

export default {
  name: "HouseListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    house: Object,
  },
  computed: {
    ...mapState(housevStore, ["houses", "dongName"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
  methods: {
    ...mapActions(housevStore, [
      "detailHouse",
      "houseMaxPrice",
      "houseMinPrice",
      "getHouseList",
    ]),

    async selectHouse() {
      // console.log("listRow : ", this.house);
      // this.$store.dispatch("getHouse", this.house);
      aptHit(
        this.house.aptCode,
        (response) => {
          console.log(response.data);
          this.getHouseList(this.dongName);
        },
        (error) => {
          console.log(error);
        } /* eslint-disable*/
      );

      this.detailHouse(this.house);
      this.houseMaxPrice(this.house.aptName);
      this.houseMinPrice(this.house.aptName);

      // 맵과 통신하기 위한 이벤트 버스
      EventBus.$emit("moveToPlace", this.house);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
