<template>
  <b-container v-if="houses && houses.length != 0" class="bv-example-row mt-3">
    <house-map :house="house" />
  </b-container>
</template>

<script>
import HouseMap from "@/components/housev/HouseMap.vue";
import { mapState } from "vuex";

const housevStore = "housevStore";

export default {
  name: "HouseMapList",
  components: {
    HouseMap,
  },
  data() {
    return {};
  },
  props: {
    house: Object,
  },
  computed: {
    ...mapState(housevStore, ["houses"]),
  },
};
</script>

<style></style>
