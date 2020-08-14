<template>

  <div>
    {{data_for}}
    <div id="map"></div>
    <span>모임장소 추천</span>
    <!-- {{data_for}} -->
    <div v-for="data in data_for" :key="data.i">
        {{ data }}
      <!-- <a :href="data.cafe_url">{{ data.cafe }}</a> -->
    </div>
  </div>
</template>

<script>
// import { mapState } from 'vuex'

export default {
  data() {
    return {
      data_for: {
        cafe: [],
        cafe_url: [],
      }
    }
  },
    mounted() {
        if (window.kakao && window.kakao.maps) {
            this.initMap();
        } else {
            const script = document.createElement('script');
            /* global kakao */
            script.onload = () => kakao.maps.load(this.initMap);
            script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=dcbbf2565c8cbfbd6437c6bd4e215c3d';
            document.head.appendChild(script);
        }
    },
    methods: {
        initMap() {
            var container = document.getElementById('map');

            // var geocoder = new kakao.maps.services.Geocoder();

            var options = {
              center: new kakao.maps.LatLng(36.3494403, 127.2982799),
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
                for (var i=0; i<3; i++) {
                  displayMarker(data[i]);
                  this.data_for.cafe.push(data[i].place_name)
                  this.data_for.cafe_url.push(data[i].place_url)
                }
                this.data_for.cafe = { ...this.data_for.cafe }
                this.data_for.cafe_url = { ...this.data_for.cafe_url }
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
        }
    }
}
</script>

<style>
#map {
    width: 800px;
    height: 500px;
}
</style>
