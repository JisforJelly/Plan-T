<template>
    <div id="map"></div>
</template>
<script>

export default {
    name:'TMap',
    components: {},
    props: {
        attractions: Array,
        lists: Array,
        cssStyle: {
            type: Object,
            default: () => {
                return {
                    width: "100vw-320px",
                    height: "100vh"
                }
            } 
        },
        detailPath: {
            type: Array,
            default: ()=>{
                return [];
            }
        },
    },
    data() {
        return { 
            map: null,
            markers: [],
            drawInfoArr: [],
            polyLine: null,
            mapBound: null,
        };
    },
    watch: {
        attractions(newValue) { 
            this.renderMarkers(newValue, true);
        },
        lists(newValue) {
            this.renderPath(newValue);
        },
        detailPath(newValue) {
            if(newValue.length) {
                this.renderMarkers(newValue, false);
                this.renderPath(newValue);
            }
        }
    },
    setup() {},
    created() {
    },
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
        renderMarkers(attractions, isMutable) { 
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
                        self.$emit("showAttrModal", attraction, isMutable);
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
        renderPath(paths) {
            if(this.polyLine != null) {
                this.polyLine.setMap(null);
                this.drawInfoArr = [];
            }

            if(paths.length > 1) {
                this.mapBound = new window.Tmapv2.LatLngBounds();

                for(let i in paths) {
                    let latlng = new window.Tmapv2.LatLng(paths[i].latitude, paths[i].longitude)
                    this.mapBound.extend(latlng); 
                    this.drawInfoArr.push(latlng);
                }
                this.polyLine = new window.Tmapv2.Polyline({
                    path: this.drawInfoArr,
                    strokeColor : "red",
                    strokeWeight:6,
                    strokeStyle:"solid",
                    map: this.map
                });
                this.map.fitBounds(this.mapBound);
            }
        }
    }
}

</script>

<style>

</style>