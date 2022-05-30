<template>
  <div>
    <div id="map"></div>
    <div class="button-group">
      <b-button
        variant="outline-primary"
        size="sm"
        pill
        @click="displayMarker(housemarkers)"
        >전체보기</b-button
      >

      <b-button
        variant="outline-primary"
        size="sm"
        pill
        @click="setOverlayMapTypeId('roadview')"
      >
        로드뷰 도로정보 보기
      </b-button>
      <b-button
        variant="outline-primary"
        size="sm"
        pill
        @click="setOverlayMapTypeId('terrain')"
        >지형정보 보기</b-button
      >
      <b-button
        variant="outline-primary"
        size="sm"
        pill
        @click="setOverlayMapTypeId('use_district')"
      >
        지적편집도 보기
      </b-button>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import { EventBus } from "@/utils/event-bus";
const housevStore = "housevStore";
export default {
  name: "KakaoMap",
  data() {
    return {
      markers: [],
      infowindow: null,
      changeMaptype: null,
      currentTypeId: null,
    };
  },
  created() {
    EventBus.$on("moveToPlace", (house) => {
      this.panTo(house.lat, house.lng);
    });
  },

  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
    }
    this.displayMarker(this.housemarkers);
  },
  computed: {
    ...mapState(housevStore, ["houses", "housemarker", "housemarkers"]),
  },

  methods: {
    ...mapActions(housevStore, ["detailHouse"]),

    selectHouse() {
      // console.log("listRow : ", this.house);
      // this.$store.dispatch("getHouse", this.house);
      this.detailHouse(this.house);
    },
    initMap() {
      const container = document.getElementById("map");
      container.style.width = "100%";
      container.style.height = "500px";
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
      // this.map.addOverlayMapTypeId(kakao.maps.MapTypeId.TRAFFIC);

      // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
      var mapTypeControl = new kakao.maps.MapTypeControl();

      // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
      // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
      this.map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

      // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
      var zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

      // this.map.addOverlayMapTypeId(kakao.maps.MapTypeId.ROADVIEW);

      // this.map.addOverlayMapTypeId(kakao.maps.MapTypeId.TERRAIN);
    },

    panTo(lat, lng) {
      // 이동할 위도 경도 위치를 생성합니다
      var moveLatLon = new kakao.maps.LatLng(lat, lng);

      // 지도 중심을 부드럽게 이동시킵니다
      // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
      console.log(lat, lng);
      this.map.panTo(moveLatLon);
      this.map.setLevel(3);
    },

    displayMarker(markerPositions) {
      console.log(markerPositions + "입니다.");
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(...position) /* eslint-disable */
      );

      if (positions.length > 0) {
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
            })
        );

        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );

        this.map.setBounds(bounds);
        this.map.addOverlayMapTypeId(kakao.maps.MapTypeId.TRAFFIC);
      }
    },

    setOverlayMapTypeId(maptype) {
      // maptype에 따라 지도에 추가할 지도타입을 결정합니다
      console.log(this);
      if (maptype === "traffic") {
        // 교통정보 지도타입
        this.changeMaptype = kakao.maps.MapTypeId.TRAFFIC;
      } else if (maptype === "roadview") {
        // 로드뷰 도로정보 지도타입
        this.changeMaptype = kakao.maps.MapTypeId.ROADVIEW;
      } else if (maptype === "terrain") {
        // 지형정보 지도타입
        this.changeMaptype = kakao.maps.MapTypeId.TERRAIN;
      } else if (maptype === "use_district") {
        // 지적편집도 지도타입
        this.changeMaptype = kakao.maps.MapTypeId.USE_DISTRICT;
      }

      // 이미 등록된 지도 타입이 있으면 제거합니다
      if (this.currentTypeId) {
        this.map.removeOverlayMapTypeId(this.currentTypeId);
      }

      // maptype에 해당하는 지도타입을 지도에 추가합니다
      this.map.addOverlayMapTypeId(this.changeMaptype);

      // 지도에 추가된 타입정보를 갱신합니다
      this.currentTypeId = this.changeMaptype;
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped postcss-loader>
#map {
  width: 400px;
  height: 400px;
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}
</style>
