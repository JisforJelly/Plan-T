<template>
    <b-col lg="3" md="4" sm="12" class="mb-2 p-3">
        <b-carousel
        :interval="0"
        controls
        indicators
        background="#ababab"
        img-height="560"
        class="rounded"
        >
            <router-link :to="{name: 'GalleryDetail', params: { 'no': hotplace.hotPlaceId }}">
                <b-carousel-slide v-for="image in hotplace.imgPaths" :key="image" :img-src="'http://localhost:8080/image/'+image"> </b-carousel-slide>
            </router-link>
        </b-carousel>

        <div class="d-flex flex-column justify-content-start">
            <router-link :to="{name: 'GalleryDetail', params: { 'no': hotplace.hotPlaceId }}">
                <p class="hotplace-name text-truncate fw-bolder mt-2 mb-1" align="left"> {{hotplace.title}}</p>
                <p class="fw-bolder hotplace-content m-0" align="left"> {{ hotplace.shortContents }} </p>
            </router-link>
            <div class="d-flex align-items-center justify-content-between mt-2">
                <b-badge class="p-2" variant="secondary"> {{hotplace.hashTag}}</b-badge>
                <b-icon class="font-red" :icon="isFill ? 'heart-fill' : 'heart'"></b-icon>
            </div>
        </div>
    </b-col>
</template>
<script>

export default {
    name: 'HotPlaceListItem',
    components: {},
    props: {
      hotplace: Object,
    },
    data() {
        return {
            isFill: true,
        };
    },
    setup() { },
    created() { },
    mounted() { 
    },
    unmounted() { },
    methods: {}
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
</style>

<style>
.carousel-inner {
    border-radius: 5px;
    background-color: rgba(0,0,0,0);
    overflow: hidden;
}
</style>