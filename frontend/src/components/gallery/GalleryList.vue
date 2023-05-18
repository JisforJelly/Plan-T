<template>
    <div class="container-fluid p-0">
        <!-- main content-->
        <div class="d-flex" style="padding: 0px 300px; margin-top:80px">
            <b-row class="w-100" style="padding-bottom: 200px;">
                <!-- width height 1: ? 비율 유지되야함 -->
                <HotPlaceItem  v-for="i in 50" :key=i />
            </b-row>
        </div>
        <!-- main end-->
    </div>
</template>

<script>
import HotPlaceItem from "@/components/gallery/GalleryListItem.vue"

export default {
    name: 'GalleryList',
    components: {
        HotPlaceItem,
    },
    data() {
        return {
            // mock up용 데이터 
            hotplaces: [
                {},
                {},
                {},
                {},
                {},
            ],
        }
    },
    created() { },
    mounted() {
    },
    methods: {
    }
}
</script>

<style>
</style>