<template>
    <b-col lg="3" md="4" sm="12" class="mb-2 p-3"   style="min-width: 300px;">
        <b-carousel
        :interval="0"
        controls
        indicators
        background="#ababab"
        class="rounded"
        img-height="250px"
        style="min-height: 250px;">
            <router-link :to="{name: 'GalleryDetail', params: { 'no': hotplace.hotPlaceId }}">
                <b-carousel-slide 
                    v-for="image in hotplace.imgPaths" 
                    :key="image"> 
                    <template #img>
                            <img class="d-block img-fluid w-100"
                                :src="`${rootPath}/image/`+image"
                                alt="image slot">
                    </template>
                </b-carousel-slide>
            </router-link>
        </b-carousel>

        <div class="d-flex flex-column justify-content-start">
            <router-link :to="{name: 'GalleryDetail', params: { 'no': hotplace.hotPlaceId }}">
                <p class="hotplace-name text-truncate fw-bolder mt-2 mb-1" align="left"> {{hotplace.title}}</p>
                <p class="fw-bolder hotplace-content m-0" align="left" style="height: 50px;"> {{ hotplace.shortContents }} </p>
            </router-link>
            <div class="d-flex align-items-center justify-content-between mt-2">
                <b-badge class="p-2" variant="secondary"> #{{hotplace.hashTag}}</b-badge>

                <b-button variant="none" @click="toggleHotplaceLike"> 

                <b-icon v-if="isFill" class="font-red" icon="heart-fill"></b-icon>
                <b-icon v-else class="font-red" icon="heart"></b-icon>
                </b-button>
            </div>
        </div>
    </b-col>
</template>
<script>
import { toggleHotPlaceLike } from "@/api/hotplace"

export default {
    name: 'HotPlaceListItem',
    components: {},
    props: {
      hotplace: Object,
    },
    data() {
        return {
            isFill: this.hotplace.isFill,
        };
    },
    setup() { },
    created() { },
    mounted() { 
    },
    unmounted() { },
    methods: {
        toggleHotplaceLike: function() {
            toggleHotPlaceLike(this.hotplace.hotPlaceId, (response)=>{
                if(response.status === 200) {
                    this.isFill = !this.isFill;
                }
            });
        }
    },
    computed: {
        rootPath: function () { 
            return process.env.VUE_APP_API;
        }
    },
}
</script>

<style scoped>
.hotplace-content {
    display :-webkit-box;
    color: gray;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    overflow: hidden;
}
.hotplace-name {
    font-weight: bolder;
    font-size: 1.1rem;
}

img {
    overflow-x: hidden;
    height: 300px !important;
    object-fit: cover !important;
}
</style>

<style>
.carousel-inner {
    border-radius: 5px;
    background-color: rgba(0,0,0,0);
    overflow: hidden;
}
</style>