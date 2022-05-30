<template>
  <b-row class="mt-4 mb-4 text-center">
    <b-col class="sm-3">
      <b-form-input
        v-model.trim="aptName"
        placeholder="아파트 이름 입력...(예 : 경희궁)"
        @keypress.enter="searchbyaptname"
      ></b-form-input>
    </b-col>
    <b-col class="sm-3" align="left">
      <b-button variant="outline-primary" @click="searchbyaptname"
        >검색</b-button
      >
    </b-col>
  </b-row>
</template>

<script>
import { mapState, mapActions } from "vuex";

const housevStore = "housevStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      aptName: null,
    };
  },
  computed: {
    ...mapState(housevStore, ["houses"]),
  },
  methods: {
    ...mapActions(housevStore, ["getHouseList", "searchByName"]),

    searchbyaptname() {
      // this.CLEAR_MARKER_LIST();
      if (this.aptName) this.searchByName(this.aptName);
    },
  },
};
</script>

<style></style>
