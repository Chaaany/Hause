import {
  sidoList,
  gugunList,
  dongList,
  houseList,
  searchByAptName,
  aptHighest,
  aptLowest,
} from "@/api/housev";

const housevStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    aptname: [{ value: null }],
    aptcode: [{ value: null }],
    houses: [],
    house: [],
    maxprice: null,
    minprice: null,
    housemarker: [],
    housemarkers: [],
    dongName: null,
  },

  getters: {},

  mutations: {
    SET_SIDO_LIST: (state, sidos) => {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST: (state, guguns) => {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST: (state, dongs) => {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },
    SET_HOUSE_LIST: (state, houses) => {
      state.houses = houses;
    },
    SET_MARKER_LIST: (state, houses) => {
      houses.forEach((house) => {
        state.housemarkers.push([house.lat, house.lng]);
      });
    },
    SET_DETAIL_HOUSE: (state, house) => {
      state.house = house;
    },

    SET_HOUSE_MAX_PRICE: (state, price) => {
      state.maxprice = price;
    },

    SET_HOUSE_MIN_PRICE: (state, price) => {
      state.minprice = price;
    },

    SET_HOUSE_MARKER: (state, housemarker) => {
      console.log(housemarker);
      state.housemarker = housemarker;
    },
    SET_DONG_NAME: (state, dongCode) => {
      state.dongName = dongCode;
    },

    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST: (state) => {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_MARKER_LIST: (state) => {
      state.housemarkers = [];
    },
    CLEAR_HOUSE: (state) => {
      state.house = [];
    },
    CLEAR_HOUSES: (state) => {
      state.houses = [];
    },
    CLEAR_HOUSE_MARKER: (state) => {
      state.housemarkers = [];
    },
  },

  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        } /* eslint-disable */
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      const params = {
        sido: sidoCode,
      };
      gugunList(
        params,
        ({ data }) => {
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getDong: ({ commit }, gugunCode) => {
      const params = {
        gugun: gugunCode,
      };
      dongList(
        params,
        ({ data }) => {
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },

    getHouseList: ({ commit }, dongCode) => {
      console.log("dongCode : " + dongCode);
      const params = {
        dong: dongCode,
      };
      houseList(
        params,
        (response) => {
          commit("SET_HOUSE_LIST", response.data);
          commit("SET_MARKER_LIST", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },

    detailHouse: ({ commit }, house) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", house);
      let housemarker = [house.lat, house.lng];
      commit("SET_HOUSE_MARKER", housemarker);
    },

    houseMaxPrice: ({ commit }, aptname) => {
      console.log("aptName : " + aptname);
      const params = {
        aptname: aptname,
      };
      aptHighest(
        params,
        (response) => {
          console.log(response.data);
          commit("SET_HOUSE_MAX_PRICE", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },

    houseMinPrice: ({ commit }, aptname) => {
      console.log("aptName : " + aptname);
      const params = {
        aptname: aptname,
      };
      aptLowest(
        params,
        (response) => {
          console.log(response.data);
          commit("SET_HOUSE_MIN_PRICE", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },

    searchByName: ({ commit }, aptname) => {
      console.log("aptName : " + aptname);
      const params = {
        aptname: aptname,
      };
      searchByAptName(
        params,
        (response) => {
          console.log(response.data);
          commit("SET_HOUSE_LIST", response.data);
          commit("SET_MARKER_LIST", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default housevStore;
