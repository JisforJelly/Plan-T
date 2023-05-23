<template>
    <div id="map" :style="mapStyle"></div>
</template>
<script>
import { setDaumMap } from "@/util/daumPostUtil";
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
            setDaumMap(document.getElementById('map'));
        },
    }
}
</script>

<style scoped>

</style>