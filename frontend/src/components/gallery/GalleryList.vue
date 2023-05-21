<template>
    <div class="container-fluid p-0">
        <!-- main content-->
        <div class="main-content d-flex" >
            <b-row class="w-100">
                <!-- width height 1: ? 비율 유지되야함 -->
                <HotPlaceItem :hotplace="hotplace"  v-for="hotplace in hotplaces" :key="hotplace.hotplaceId"  />
            </b-row>
        </div>
        <!-- main end-->
    </div>
</template>

<script>
import HotPlaceItem from "@/components/gallery/GalleryListItem.vue"
import { getHotPlaces } from "@/api/hotplace"

export default {
    name: 'GalleryList',
    components: {
        HotPlaceItem,
    },
    data() {
        return {
            hotplaces: [],
        }
    },
    created() { 
        getHotPlaces((response)=>{
            this.hotplaces = response.data.hotPlaces;
        })
    },
    mounted() {
    },
    methods: {
    }
}
</script>

<style scoped>
.main-content {
    padding: 0px 300px;
    margin-top:80px;
    padding-bottom: 200px;
}
</style>