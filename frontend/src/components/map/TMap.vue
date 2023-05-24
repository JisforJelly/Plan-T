<template>
    <div id="map"></div>
</template>
<script>
export default {
    name:'TMap',
    components: {},
    props: {
        attractions: Array,
        cssStyle: {
            type: Object,
            default: () => {
                return {
                    width: "100vw-320px",
                    height: "100vh"
                }
            } 
        },
    },
    data() {
        return { 
            map: null,
            markers: [],
        };
    },
    watch: {
        attractions(newValue) { 
            this.renderMarkers(newValue);
        }
    },
    setup() {},
    created() {},
    mounted() {
        this.map = new window.Tmapv2.Map("map", {
            center : new window.Tmapv2.LatLng(37.56520450, 126.98702028),
			width : this.cssStyle.width,
			height : this.cssStyle.height,
			zoom : 16
        });
    },
    unmounted() {},
    methods: {
        renderMarkers(attractions) { 
            this.removeMarkers();

            const self = this;
            if (attractions) {
                this.map.setCenter(new window.Tmapv2.LatLng(attractions[0].latitude, attractions[0].longitude));
                attractions.forEach((attraction) => {
                    const marker = new window.Tmapv2.Marker({
                        position: new window.Tmapv2.LatLng(attraction.latitude, attraction.longitude),
                        label: attraction.placeName,
                    })

                    marker.addListener("click", function() {
                        self.$emit("showAttrModal", attraction, false);
                    });
                    marker.setMap(this.map);
                    this.markers.push(marker);
                })
            }
        },
        removeMarkers() { 
            this.markers.forEach(marker => {
                marker.setMap(null);
            });
            this.markers = [];
        },
    }
}

</script>

<style>

</style>