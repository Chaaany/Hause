<template>
  <b-row class="mt-4 mb-4 text-center">
    <b-col class="sm-3">
      <b-form-select
        v-model="sidoCode"
        :options="sidos"
        @change="gugunList"
      ></b-form-select>
    </b-col>
    <b-col class="sm-3">
      <b-form-select
        v-model="gugunCode"
        :options="guguns"
        @change="dongList"
      ></b-form-select>
    </b-col>
    <b-col class="sm-3">
      <b-form-select
        v-model="dongCode"
        :options="dongs"
        @change="searchApt"
      ></b-form-select>
    </b-col>
  </b-row>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

/*
  namespaced: true를 사용했기 때문에 선언해줍니다.
  index.js 에서 modules 객체의 '키' 이름입니다.

  modules: {
    키: 값
    memberStore: memberStore,
    houseStore: houseStore
  }  
*/
const housevStore = "housevStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
    };
  },
  computed: {
    ...mapState(housevStore, [
      "sidos",
      "guguns",
      "dongs",
      "houses",
      "dongName",
    ]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_MARKER_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(housevStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
      "getDongName",
    ]),
    ...mapMutations(housevStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_MARKER_LIST",
      "SET_DONG_NAME",
    ]),
    // sidoList() {
    //   this.getSido();
    // },
    gugunList() {
      console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      console.log(this.gugunCode);
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    searchApt() {
      this.CLEAR_MARKER_LIST();
      if (this.dongCode) {
        this.SET_DONG_NAME(this.dongCode);
        this.getHouseList(this.dongCode);
      }
    },
  },
};
</script>

<style></style>
