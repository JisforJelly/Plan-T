<template>
    <div id="map"></div>
</template>
<script>
export default {
    name:'KakaoMap',
    components:{},
    data() {
        return {
            map: null,
        };
    },
    setup() {},
    created() {},
    mounted() {
        this.drawMap();
    },
    unmounted() {},
    methods: {
        drawMap() { 
            const container = document.getElementById('map');
            const options = {
                center: new window.kakao.maps.LatLng(33.450701, 126.570667),
                level: 3,
            };

            this.map = new window.kakao.maps.Map(container, options);
        },
    }
}
</script>

<style scoped>
#map {
    display: table;
    width: 49%;
    height: 560px;
}
</style>