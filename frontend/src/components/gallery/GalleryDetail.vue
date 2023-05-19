<template>
    <div class="container-fluid p-0">
        <!-- title section -->
        <div class="title-section d-flex flex-column">

            <div class="title d-flex mt-3 font-weight-bold"> {{ hotplaceInfo.title }}</div>

            <div class="d-flex align-items-center mt-1">
                <div class="mr-3">
                    <b-badge class="p-2 mr-3" variant="secondary">#해쉬태그</b-badge>
                    <b-icon class="mr-1 font-weight-bold font-red" icon="heart-fill" aria-hidden="true"></b-icon>
                    <span class="like font-weight-bold pt-1">{{ hotplaceInfo.like }} </span>
                </div>
                
                <p class="writer m-0">작성자 :
                    <span class="name font-weight-bold pt-1">
                        {{ hotplaceInfo.userName }}
                    </span>
                </p>
                <p class="time my-0 ml-3 font-italic">
                    {{ hotplaceInfo.createdAt }}
                </p>
            </div>

            <div class="d-flex justify-content-between mt-4">
                <KaKaoMap />
                <b-carousel :interval="0" controls indicators background="#ababab" class="rounded w-50">
                    <b-carousel-slide>
                        <template #img>
                            <img
                                class="d-block img-fluid w-100"
                                src="http://localhost:8080/assets/img/gallery/gallery-1.jpg"
                                alt="image slot">
                        </template>
                    </b-carousel-slide>
                    <b-carousel-slide>
                        <template #img>
                            <img
                                class="d-block img-fluid w-100"
                                src="http://localhost:8080/assets/img/gallery/gallery-2.jpg"
                                alt="image slot">
                        </template>
                    </b-carousel-slide>
                </b-carousel>
            </div>
            <!--  Body -->
            <div class="d-flex flex-column mt-4 pr-3">
                <div class="info d-flex font-weight-bold">
                    Hot Place information
                </div>
                <div class="location d-flex font-weight-bold mt-4">
                    Location
                </div>
                <div class="d-flex content mt-1">
                    {{ hotplaceInfo.location }}
                </div>
                <div class="desc d-flex font-weight-bold mt-4">
                    상세 설명
                </div>
                <div class="d-flex justify-content-start content mt-1">
                    <p class="text-left">
                        {{ hotplaceInfo.content }}
                    </p>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import KaKaoMap from "@/components/map/KakaoMap.vue"

export default {
    name: 'GalleryDetail',
    components: {
        KaKaoMap
    },
    data() {
        return {
            hotplaceInfo: {
                title: '핫플레이스 제목 구간',
                like: 0,
                userName: "김한성",
                createdAt: "2023-05-12",
                location: "서울시 노원구 상계동 주공아파트",
                content: "핫플레이스 컨텐츠내용 핫플레이스 컨텐츠내용 핫플레이스 컨텐츠내용핫플레이스 컨텐츠내용 핫플레이스 컨텐츠내용 핫플레이스 컨텐츠내용 핫플레이스 컨텐츠내용 핫플레이스 컨텐츠내용핫플레이스 컨텐츠내용 핫플레이스 컨텐츠내용핫플레이스 컨텐츠내용 핫플레이스 컨텐츠내용 핫플레이스 컨텐츠내용핫플레이스 컨텐츠내용 핫플레이스 컨텐츠내용핫플레이스 컨텐츠내용 핫플레이스 컨텐츠내용 핫플레이스 컨텐츠내용핫플레이스 컨텐츠내용 핫플레이스 컨텐츠내용핫플레이스 컨텐츠내용 핫플레이스 컨텐츠내용 핫플레이스 컨텐츠내용핫플레이스 컨텐츠내용 핫플레이스 컨텐츠내용핫플레이스 컨텐츠내용 핫플레이스 컨텐츠내용 핫플레이스 컨텐츠내용핫플레이스 컨텐츠내용 핫플레이스 컨텐츠내용핫플레이스 컨텐츠내용 핫플레이스 컨텐츠내용 핫플레이스 컨텐츠내용핫플레이스 컨텐츠내용 핫플레이스 컨텐츠내용",
            }
        };
    },
    setup() { },
    created() { },
    mounted() { },
    unmounted() { },
    methods: {},
}
</script>

<style scoped>
.title-section {
    padding: 20px 300px 200px 300px;
    min-width: 1100px !important;
}
.title {
    font-size: 1.8rem;
}
.like {
    font-size: 1.2rem;
    text-decoration: underline;
}
.writer {
    font-size: 1.2rem;
}
.name {
    text-decoration: underline;
}
.time {
    font-size: 1.1rem;
}
img {
    overflow-x: hidden;
    height: 560px !important;
    object-fit: cover !important;
}
.info {
    font-size: 1.6rem;
}
.location .desc {
    font-size: 1.3rem;
}
</style>
