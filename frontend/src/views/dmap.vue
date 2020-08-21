<template>

  <div>
    <div id="map"></div>
    <div class="center-text">
      <h4> 이러한 장소는 어떠세요? </h4>
    </div>
    
    <div v-if="windowWidth <= 600">
      <table class="table">
        <caption>카페</caption>
        <tr><th>카페명</th></tr>
        <tr v-for="(data, index) in cafe_data" :key="index">
          <td><a :href="data.place_url" target="_blank" class="no-deco">{{ data.place_name }}</a></td>
          <!-- <td>{{ data.road_address_name }}</td> -->
        </tr>
      </table> 
    </div>

    <div v-else-if="windowWidth > 600 && windowWidth <= 800">
      <table class="table">
        <caption>카페</caption>
        <tr><th>카페명</th><th>주소</th></tr>
        <tr v-for="(data, index) in cafe_data" :key="index">
          <td><a :href="data.place_url" target="_blank" class="no-deco">{{ data.place_name }}</a></td>
          <td class="center-text">{{ data.road_address_name }}</td>
        </tr>
      </table> 
    </div>

    <div v-else class="cont10">
      <table class="table">
        <caption>카페</caption>
        <tr><th>카페명</th><th>주소</th></tr>
        <tr v-for="(data, index) in cafe_data" :key="index">
          <td><a :href="data.place_url" target="_blank" class="no-deco">{{ data.place_name }}</a></td>
          <td class="center-text">{{ data.road_address_name }}</td>
        </tr>
      </table> 
    </div>


  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  data() {
    return {
      cafe_data: [],
      windowWidth: window.innerWidth,
    }
  },
  beforeDestroy() { 
    window.removeEventListener('resize', this.onResize); 
  },
  mounted() {
      if (window.kakao && window.kakao.maps) {
          this.initMap();
      } else {
          const script = document.createElement('script');
          /* global kakao */
          script.onload = () => kakao.maps.load(this.initMap);
          script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=dcbbf2565c8cbfbd6437c6bd4e215c3d&libraries=services';
          document.head.appendChild(script);
      }
      this.$nextTick(() => {
        window.addEventListener('resize', this.onResize);
      })
      window.scrollTo(0, 0)
    },
    methods: {
        initMap() {
          var container = document.getElementById('map');

          // var geocoder = new kakao.maps.services.Geocoder();

          var options = {
            center: new kakao.maps.LatLng(this.mapY, this.mapX),
            level: 4
          };

          var map = new kakao.maps.Map(container, options);
          map.setMapTypeId(kakao.maps.MapTypeId.NORMAL);

          var infowindow = new kakao.maps.InfoWindow({zIndex:1});

          // 장소 검색 객체를 생성합니다
          var ps = new kakao.maps.services.Places(map); 

          // 카테고리로 은행을 검색합니다
          ps.categorySearch('CE7', // 키워드 검색 완료 시 호출되는 콜백함수 입니다
          (data, status) => {
            if (status === kakao.maps.services.Status.OK) {
              for (var i=0; i< data.length; i++) {
                displayMarker(data[i]);
                this.cafe_data.push(data[i])
                // this.data_for.cafe_url.push(data[i].place_url)
              }
              // this.data_for.cafe = { ...this.data_for.cafe }
              // this.data_for.cafe_url = { ...this.data_for.cafe_url }
            }
          }, {useMapBounds:true}); 

            

          // 지도에 마커를 표시하는 함수입니다
          function displayMarker(place) {
              // 마커를 생성하고 지도에 표시합니다
              var marker = new kakao.maps.Marker({
                  map: map,
                  position: new kakao.maps.LatLng(place.y, place.x) 
              });

              // 마커에 클릭이벤트를 등록합니다
              kakao.maps.event.addListener(marker, 'click', function() {
                  // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
                  infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
                  infowindow.open(map, marker);
              });
          }
      },
      onResize() {
        this.windowWidth = window.innerWidth
      },
    },
    computed: {
      ...mapState(['mapY', 'mapX'])
    }
}
</script>

<style scoped>
  #map {
      width: 100%;
      height: 300px;
  }

  .center-text {
    text-align: center;
    margin-top: 1rem;
  }

  .no-deco {
    text-decoration: none;
    color: black;
    cursor: pointer;
  }

  .table {
    border-collapse: collapse;
    border-top: 3px solid #168;
  }  

  .table th {
    color: #168;
    background: #f0f6f9;
    text-align: center;
  }

  .table th, .table td {
    padding: 10px;
    border: 1px solid #ddd;
  }

  .table th:first-child, .table td:first-child {
    border-left: 0;
  }

  .table th:last-child, .table td:last-child {
    border-right: 0;
  }

  .table tr td:first-child {
    text-align: center;
  }

  .table caption {
    caption-side: bottom; 
    display: none;
  }

  .cont10 {
    margin: 0 15%;
  }
</style>
