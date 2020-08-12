<template>
  <div id="app">
    <div id="map"></div>
  </div>
</template>

<script>
export default {
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
            var options = {
              center: new kakao.maps.LatLng(33.450701, 126.570667),
              level: 3
            };

            var map = new kakao.maps.Map(container, options);
            map.setMapTypeId(kakao.maps.MapTypeId.NORMAL);
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