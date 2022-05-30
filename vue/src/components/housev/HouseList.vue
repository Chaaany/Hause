<template>
  <b-container v-if="houses && houses.length != 0" class="bv-example-row mt-3">
    <house-list-item
      v-for="(house, index) in houses"
      :key="index"
      :house="house"
    />
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import HouseListItem from "@/components/housev/HouseListItem.vue";
import { mapState, mapMutations } from "vuex";

const housevStore = "housevStore";

export default {
  name: "HouseList",
  components: {
    HouseListItem,
  },
  data() {
    return {};
  },
  created() {
    this.CLEAR_MARKER_LIST();
    this.CLEAR_HOUSES();
  },
  computed: {
    ...mapState(housevStore, ["houses"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
  methods: {
    ...mapMutations(housevStore, [
      "CLEAR_HOUSE",
      "CLEAR_MARKER_LIST",
      "CLEAR_HOUSE_MARKER",
      "CLEAR_HOUSES",
    ]),
  },
};
</script>

<style></style>
