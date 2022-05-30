import {
  /* eslint-disable */
  listComment,
} from "@/api/qnaBoard";

const qnaBoardStore = {
  namespaced: true,
  state: {
    comments: []
  },
  getters: {},
  mutations: {
    SET_COMMENT: (state, comments) => {
      state.comments = comments;
    },
  },
  actions: {
    getComment: ({ commit }, articleno) => {
      const params = {
        articleno: articleno,
      };
      listComment(
        params,
        ({ data }) => {
          commit("SET_COMMENT", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default qnaBoardStore;
