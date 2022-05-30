<template>
  <b-container v-if="house" class="bv-example-row">
    <b-row>
      <b-col
        ><h4>
          {{ house.aptName }}
          <a href="#"
            ><img
              src="@/assets/naverlog.png"
              alt="네이버 로고"
              style="
                width: 40px;
                height: 40px;
                background-color: #fff;
                color: #96a8c5;
                font-size: 0.9em;
                padding: 0 !important;
                border-radius: 50%;
              "
              @click="searchNaver"
            />
          </a>
          <a href="#"
            ><img
              src="https://logo-resources.thevc.kr/organizations/80x80/9e31cadbe652b5ef35b13a8d8ab6be1a284163f657aa531b974e881f84ce0eb8_1484801679556058.jpg"
              alt="호갱노노 로고"
              style="
                width: 40px;
                height: 40px;
                background-color: #fff;
                color: #96a8c5;
                font-size: 0.9em;
                padding: 0 !important;
                border-radius: 50%;
              "
              @click="searchHogangnono"
            />
          </a>
        </h4>
      </b-col>
    </b-row>
    <b-row class="mb-2 mt-1">
      <b-col
        ><b-img :src="require('@/assets/apt.png')" fluid-grow></b-img
      ></b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="secondary"
          >아파트 코드 : {{ house.aptCode }}</b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="primary"
          >아파트 이름 : {{ house.aptName }}
        </b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="info"
          >주소 : {{ house.dongName }} {{ house.jibun }}</b-alert
        >
      </b-col>
    </b-row>
    <!-- <b-row>
      <b-col>
        <b-alert show variant="warning">지번 : {{ house.jibun }}층</b-alert>
      </b-col>
    </b-row> -->
    <b-row>
      <b-col>
        <b-alert show variant="danger"
          >최근거래가 :
          {{
            (parseInt(house.recentPrice.replace(",", "")) * 10000) | price
          }}원</b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="warning"
          >최고거래가 :

          {{ (parseInt(maxprice.replace(",", "")) * 10000) | price }}원</b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="dark"
          >최저거래가 :
          {{ (parseInt(minprice.replace(",", "")) * 10000) | price }}원</b-alert
        >
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";

const housevStore = "housevStore";

export default {
  name: "HouseDetail",
  computed: {
    ...mapState(housevStore, ["house", "minprice", "maxprice"]),
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  methods: {
    searchHogangnono() {
      window.open("https://hogangnono.com/search?q=" + this.house.aptName);
    },

    searchNaver() {
      window.open("https://new.land.naver.com/search?ks=" + this.house.aptName);
    },
  },
};
</script>

<style scoped lang="scss"></style>
