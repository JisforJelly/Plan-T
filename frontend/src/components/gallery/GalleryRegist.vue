<template>
    <div class="container-fluid p-0">
        <!-- Header -->
        <div class="header d-flex flex-column h-100 sticky-top">
            <div>
                <h2 class="font-weight-bold mb-0 w-auto"> 핫플레이스 등록 </h2>
            </div>
            <div class="board-content d-flex align-items-center justify-content-between">
                <div class="d-flex flex-grow-2 w-auto">
                    <b-button class="button mr-2" @click="moveGalleryList">목록</b-button>
                    <b-button class="button">핫플레이스 등록</b-button>
                </div>
            </div>
        </div>

        <!-- Board Section -->
        <div class="board-section">
            <!-- 제목 -->
            <b-form-input size="lg" placeholder="제목" class="mb-2" v-model="hotplaceForm.title"></b-form-input>
            <!-- 해시태그 -->
            <b-form-input size="lg" placeholder="#해시태그" class="mb-2" v-model="hotplaceForm.hashTag"></b-form-input>

           <div class="d-flex justify-content-between mb-2">
                <KaKaoMap :mapCss="mapStyle"/>
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

            <!-- 주소 -->
            <b-input-group>
                <b-form-input size="lg" v-model="hotplaceForm.location" disabledtype="text" placeholder="주소" readonly></b-form-input>
                <b-input-group-append>
                    <b-button class="address-button" size="lg" @click="openAddressSearch">주소 검색</b-button>
                </b-input-group-append>
            </b-input-group>

            <b-form-select class="mt-2" size="lg" v-model="hotplaceForm.contentType" :options="options"></b-form-select>

            <!-- 사진(여러개 업로드 가능하게...) -->
            <b-form-group>
                <b-form-file multiple
                    class="upload"
                    size="lg"
                    accept="image/*"
                    placeholder="파일을 선택하세요"
                    browse-text="파일 선택"
                ></b-form-file>
            </b-form-group>
            <!-- 상세 설명 -->
            <b-form-textarea
                placeholder="HotPlace 설명을 작성해주세요."
                rows="3"
                max-rows="8"
                v-model="hotplaceForm.content"
            ></b-form-textarea>
        </div>
    </div>
</template>
<script>
import KaKaoMap from "@/components/map/KakaoMap.vue"
import { openDaumPost, getLatLng } from "@/util/daumPostUtil";

export default {
    name:'GalleryRegist',
    components:{
        KaKaoMap
    },
    data() {
        return {
            mapStyle : {
                'display': 'table',
                'width': '49%',
                'height': '300px',
            },
            hotplaceForm: {
                hotPlaceId: -1,
                title: "",
                content: "",
                location: "",
                latitude: "",
                longitude: "",
                hashTag: "",
                contentType: null,
                imgPath: [],
            },
            options: [
                { value: null, text: '관광지 타입을 설정하세요.', disabled: true },
                { value: 'd', text: 'Please select an option' },
                { value: 'a', text: 'This is First option' },
                { value: 'b', text: 'Selected Option' },
                { value: { C: '3PO' }, text: 'This is an option with object value' },
            ]
        }
    },
    setup() {},
    created() {},
    mounted() {},
    unmounted() {},
    methods:{
        moveGalleryList() { 
            this.$router.push({ name: "GalleryList" }).catch(() => { });
        },

        openAddressSearch() {
            openDaumPost((address)=>{
                this.hotplaceForm.location = address;
                const loc = getLatLng();
                this.hotplaceForm.latitude = loc.latitude;
                this.hotplaceForm.longitude = loc.longitude;
            });
        }
    }
}
</script>

<style scoped>
.header {
    padding: 20px 300px;
    margin-top: 60px;
    min-width: 1100px !important;
}
.board-section {
    padding: 20px 300px 1000px 300px;
    overflow: hidden;
    height: 800px;
    min-width: 1100px !important;
}
.board-content {
    margin-top: 60px;
}
.upload {
    margin-top: 10px;
    text-align: left;
}
.address-button {
    color: #495057;
    background-color: #e9ecef;
    border: 1px solid #ced4da;
}
.address-button:hover {
    color: #495057;
    background-color: #ced4da;
    border: 1px solid #ced4da;
}
img {
    overflow-x: hidden;
    height: 300px !important;
    object-fit: cover !important;
}
</style>