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
        <!-- 테스트 하실 때 img-src 경로, 이미지 있는 경로로 잡아주셔야 정상적으로 렌더링 됩니다. -->
            <router-link :to="{name: 'GalleryDetail'}">
                <b-carousel-slide
                    img-src="http://localhost:8080/assets/img/gallery/gallery-1.jpg">
                </b-carousel-slide>
            </router-link>
            <router-link :to="{name: 'GalleryDetail'}">
                <b-carousel-slide
                    img-src="http://localhost:8080/assets/img/gallery/gallery-2.jpg">
                </b-carousel-slide>
            </router-link>
        </b-carousel>

        <div class="d-flex flex-column justify-content-start">
            <router-link :to="{name: 'GalleryDetail'}">
                <p class="text-truncate fw-bolder mt-2 mb-1" align="left" style="font-weight:bolder; font-size: 1.1rem; ">핫플레이스명</p>
                <p class="fw-bolder hotplace-content m-0" align="left">핫플레이스 설명입니다. 핫플레이스 설명입니다. 핫플레이스 설명입니다. 핫플레이스 설명입니다. 핫플레이스 설명입니다. 핫플레이스 설명입니다. </p>
            </router-link>
            <div class="d-flex align-items-center justify-content-between mt-2">
                <b-badge class="p-2" variant="secondary">#해쉬태그</b-badge>
                <b-icon class="font-red" :icon="isFill ? 'heart-fill' : 'heart'"></b-icon>
            </div>
        </div>
    </b-col>
</template>
<script>

export default {
    name: 'HotPlaceListItem',
    components: {},
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
</style>

<style>
.carousel-inner {
    border-radius: 5px;
    background-color: rgba(0,0,0,0);
    overflow: hidden;
}

</style>