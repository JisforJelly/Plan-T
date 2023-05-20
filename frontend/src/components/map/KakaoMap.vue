<template>
    <div id="map" :style="mapStyle"></div>
</template>
<script>
export default {
    name:'KakaoMap',
    components:{},
    props: {
        mapCss: Object,
    },
    data() {
        return {
            map: null,
        };
    },
    mounted() {
        this.drawMap();
    },
    computed: {
        mapStyle() {
            return this.mapCss;
        }
    },
    methods: {
        drawMap() { 
            console.log(window.kakao)
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

</style>