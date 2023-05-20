<template>
    <div class="container-fluid p-0">
        <!-- Header -->
        <div class="header d-flex flex-column h-100">
            <div>
                <h2 class="font-weight-bold mb-0 w-auto"> 핫플레이스 등록 </h2>
            </div>
            <div class="board-content d-flex align-items-center justify-content-between">
                <div class="d-flex flex-grow-2 w-auto">
                    <b-button class="button mr-2" @click="moveGalleryList">목록</b-button>
                    <b-button class="button" @click="insertGallery">핫플레이스 등록</b-button>
                </div>
            </div>
        </div>

        <!-- Board Section -->
        <div class="board-section">
            <!-- 제목 -->
            <b-form-input size="lg" placeholder="제목" class="mb-2"></b-form-input>
            <!-- 해시태그 -->
            <b-form-input size="lg" placeholder="#해시태그" class="mb-2"></b-form-input>
            <!-- 주소 -->
            <b-input-group>
                <b-form-input size="lg" disabledtype="text" placeholder="주소"></b-form-input>
                <b-input-group-append>
                    <b-button class="address-button" size="lg">주소 검색</b-button>
                </b-input-group-append>
            </b-input-group>
            <div id="map"></div>
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
            <VueEditor :editorToolbar="customToolbar"></VueEditor>
            </div>
            <!-- Board Section End -->
        </div>
</template>
<script>
// import { VueDaumPostcode } from "vue-daum-postcode"
import { VueEditor } from "vue2-editor";

export default {
    name:'GalleryRegist',
    components:{
        VueEditor,
        // VueDaumPostcode,
    },
    data() {
        return {
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
    padding: 20px 300px;
    overflow: hidden;
    height: 800px;
    min-width: 1100px !important;
}
.board-content {
    margin-top: 60px;
}
#map {
    width:300px;
    height:300px;
    margin-top:10px;
    display:none;
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
</style>