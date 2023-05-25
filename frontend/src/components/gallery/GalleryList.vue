<template>
    <div class="container-fluid p-0">
        <!-- main content-->
        <div class="d-flex" :class="[isNotMyPage ? 'main-content' : '']">
            <b-row class="w-100">
                <!-- width height 1: ? 비율 유지되야함 -->
                <HotPlaceItem :hotplace="hotplace"  v-for="hotplace in hotplaces" :key="hotplace.hotplaceId"  @toggleLike="toggleLikeButton"/>
            </b-row>
        </div>
        <!-- main end-->
    </div>
</template>

<script>
import HotPlaceItem from "@/components/gallery/GalleryListItem.vue"
import { getHotPlaces, getUserLikeHotPlace } from "@/api/hotplace"

export default {
    name: 'GalleryList',
    props: {
        isNotMyPage: {
            default: true,
            type: Boolean,
        }
    },
    components: {
        HotPlaceItem,
    },
    data() {
        return {
            hotplaces: [],
            hotPlaceIds: {},
        }
    },
    created() { 
        if(this.isNotMyPage) {
            getHotPlaces((response)=>{
                this.hotplaces = response.data.hotPlaces.map((hotplace)=>{
                    const isFill = (this.hotPlaceIds[hotplace.hotPlaceId]===true);
                    return {
                        ...hotplace,
                        isFill
                    }
                });
            }, (response)=>{
                response.data.hotplaceIds.forEach(id=>{
                    this.hotPlaceIds[id] = true;
                });
            });
        } else {
            getUserLikeHotPlace((response)=>{
                this.hotplaces = response.data.hotPlaces.map((hotplace)=>{
                    return {
                        ...hotplace,
                        isFill: true,
                    }
                });
            });
        }
    },
    mounted() {
    },
    methods: {
        toggleLikeButton(id) {
            this.hotplaces[id].isFill = !this.hotplaces[id].isFill;
        }
    }
}
</script>

<style scoped>
.main-content {
    padding: 0px 300px 1100px 300px;
    margin-top:80px;
}
</style>