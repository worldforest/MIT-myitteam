<template>

  <div>
    {{ nav }}
    <div id="map"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      nav: window.navigator
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
              center: new kakao.maps.LatLng(33.450701, 126.570667),
              level: 3
            };

            var map = new kakao.maps.Map(container, options);
            map.setMapTypeId(kakao.maps.MapTypeId.NORMAL);

            var infowindow = new kakao.maps.InfoWindow({zIndex:1});


            // 지도를 생성합니다    

            // 장소 검색 객체를 생성합니다
            var ps = new kakao.maps.services.Places(map); 

            // 카테고리로 은행을 검색합니다
            ps.categorySearch('CE7', placesSearchCB, {useMapBounds:true}); 

            // 키워드 검색 완료 시 호출되는 콜백함수 입니다
            function placesSearchCB (data, status) {
                if (status === kakao.maps.services.Status.OK) {
                    for (var i=0; i<data.length; i++) {
                        displayMarker(data[i]);    
                    }       
                }
            }

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
            

            // var geocoder = new kakao.maps.services.Geocoder();

            // 주소로 좌표를 검색합니다
            // geocoder.addressSearch('호미곶 ', function(result, status) {

            //     // 정상적으로 검색이 완료됐으면 
            //     if (status === kakao.maps.services.Status.OK) {

            //         var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

            //         // 결과값으로 받은 위치를 마커로 표시합니다
            //         var marker = new kakao.maps.Marker({
            //             map: map,
            //             position: coords
            //         });

            //         // 인포윈도우로 장소에 대한 설명을 표시합니다
            //         var infowindow = new kakao.maps.InfoWindow({
            //             content: '<div style="width:150px;text-align:center;padding:6px 0;">우리집</div>'
            //         });
            //         infowindow.open(map, marker);

            //         // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            //         map.setCenter(coords);
            //     } 
            // });    
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
