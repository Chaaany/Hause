<template>
  <div id="app">
    <navi-bar />

    <div style="display: flex">
      <b-jumbotron>
        <b-row>
          <b-col md="12">
            <b-calendar
              v-model="dateValue"
              @context="onContext"
              locale="ko-en"
            ></b-calendar>
          </b-col>
        </b-row>
        <img
          src="https://t1.daumcdn.net/thumb/C209x134/?fname=https://www.zipdeco.co.kr/upload/2022/05/16/IMAGE_202205160422555910_97U4E"
          width="700"
          height="400"
          alt="[광고]N쇼핑 X 리빙윈도 창작공방 기분전환에 좋은 휴대폰 케이스 사랑스러운 컬러 조합에 시선 강탈"
          border="0"
          style="width: 350px; height: 200px"
        />

        <!-- <iframe
          id="a"
          width="250"
          height="160"
          src="https://www.youtube.com/embed/fFatyuH630E"
          title="YouTube video player"
          frameborder=""
          allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
          allowfullscreen
        ></iframe>
        <iframe
          width="250"
          height="160"
          src="https://www.youtube.com/embed/W8F67ypm2Gk"
          title="YouTube video player"
          frameborder="0"
          allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
          allowfullscreen
        ></iframe> -->

        <b-embed
          width="250"
          height="160"
          src="https://www.youtube.com/embed/z98G3BrvRT8"
          title="YouTube video player"
          frameborder="0"
          allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
          allowfullscreen
        ></b-embed>
      </b-jumbotron>
      <router-view />
      <b-jumbotron>
        <b-time v-model="timeValue" show-seconds locale="en">
          <div class="d-flex" dir="ltr"></div>
        </b-time>
        <b-embed
          width="250"
          height="160"
          src="https://www.youtube.com/embed/koWQnay4luw"
          title="YouTube video player"
          frameborder="0"
          allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
          allowfullscreen
        ></b-embed>
        <img
          src="https://ssl.pstatic.net/tveta/libs/1223/1223670/261c3de00e5f9c4c7ecd_20220512171331701.jpg"
          width="700"
          height="400"
          alt="[광고]N쇼핑 X 리빙윈도 창작공방 기분전환에 좋은 휴대폰 케이스 사랑스러운 컬러 조합에 시선 강탈"
          border="0"
          style="width: 350px; height: 200px"
        />
        <img
          src="https://t1.daumcdn.net/thumb/C209x134/?fname=https://www.zipdeco.co.kr/upload/2022/05/10/IMAGE_202205100737524900_vBDhG"
          width="700"
          height="400"
          alt="[광고]N쇼핑 X 리빙윈도 창작공방 기분전환에 좋은 휴대폰 케이스 사랑스러운 컬러 조합에 시선 강탈"
          border="0"
          style="width: 350px; height: 200px"
        />
        <!-- <iframe
          width="250"
          height="160"
          src="https://www.youtube.com/embed/BZXAyI1PqVA"
          title="YouTube video player"
          frameborder="0"
          allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
          allowfullscreen
        ></iframe>
        <iframe
          width="250"
          height="160"
          src="https://www.youtube.com/embed/d6fyrCYHtzU"
          title="YouTube video player"
          frameborder="0"
          allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
          allowfullscreen
        ></iframe> -->
      </b-jumbotron>
    </div>
  </div>
</template>

<script>
import NaviBar from "@/components/layout/HeaderNaviBar.vue";

export default {
  name: "App",
  components: {
    NaviBar,
  },
  data() {
    return {
      timeValue: null,
      dateValue: null,
      context: null,
    };
  },
  created() {
    const now = new Date();
    // Grab the HH:mm:ss part of the time string
    this.timeValue = now.toTimeString().slice(0, 8);

    setInterval(this.setNow.bind(this), 1000);
  },

  methods: {
    setNow() {
      const now = new Date();
      // Grab the HH:mm:ss part of the time string
      this.timeValue = now.toTimeString().slice(0, 8);
    },
    onContext(ctx) {
      this.context = ctx;
    },
  },
};
</script>

<style>
a {
  text-decoration: none;
}

a.router-link-active:hover {
  text-decoration: none;
  /* font-weight: bold; */
}

a.link {
  color: white;
  font-size: larger;
}
a.router-link-active {
  color: initial;
}
a.router-link-exact-active {
  color: black;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
