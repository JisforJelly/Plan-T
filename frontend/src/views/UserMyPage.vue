<template>
    <div class="container-fluid p-0">
        <div class="d-flex flex-column w-100">
            <b-tabs pills class="d-flex flex-column px-300 mt-5" content-class="d-flex align-items-center mt-5">
                <!-- ui test -->
                <b-tab title="내프로필" active :title-link-class="'tab-title-class'">
                    <div>
                        <h4 class="font-weight-bold text-left">프로필 수정</h4>
                        <p class="text-left text-secondary mt-2" style="font-size: 0.8rem;">대표 프로필과 별명을 수정 하실 수 있습니다.</p>
                    </div>

                    <div class="d-flex mt-4">
                        <table class="w-100 text-left">
                            <tr>
                                <th>프로필 사진</th>
                                <td class="w-100">
                                    <div class="d-flex flex-column"> 
                                        <b-avatar size="6rem"></b-avatar>
                                        <div class="d-flex flex-row mt-3">
                                            <button class="mr-2 profile-btn">사진 변경</button>
                                            <button class="mr-2 profile-btn">삭제</button>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th>별명</th>
                                <td>
                                    <div class="p-2 w-50">
                                        <b-input></b-input>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>
                </b-tab>
                <b-tab title="나의 여행 계획" :title-link-class="'tab-title-class'">
                    <div class="d-flex" style="background-color: brown; height:2000px;">
                            adfsdf
                    </div>
                </b-tab>
                <b-tab title="좋아요 (Hot Place)" :title-link-class="'tab-title-class'">
                    <HotPlaceList :isNotMyPage='false'/>
                </b-tab>
                <b-tab title="좋아요 (여행 계획)" :title-link-class="'tab-title-class'">
                  <div class="d-flex" style="background-color: brown; height:2000px;">
                            adfsdf
                    </div>
                </b-tab>
            </b-tabs>
        </div>
    </div>
</template>
<script>

import HotPlaceList from "@/components/gallery/GalleryList.vue"

export default {
    name:'UserMyPage',
    components:{
        HotPlaceList,
    },
    data() {
        return {
        };
    },
    setup() {},
    created() {},
    mounted() {},
    unmounted() {},
    methods:{}
}
</script>

<style scoped>
    th {
        width: 35%;
        background: #dcdcdc;
        padding: 2vw;
        font-size: 0.8rem;
        vertical-align: top;
        border: 1px solid gray;
        border-left: none !important;
    }

    td {
        padding: 2vw;
    }

    td:nth-child(2) {
        border-top: 1px solid gray !important;
        border-bottom: 1px solid gray !important;
    }

    .px-300 {
        padding: 0px 300px;
        min-width: 1100px !important;
    }

    .profile-btn {
        border: 1px solid #bfbfbf;
        background: #fff;
        padding: 0.6vw 1vw 0.6vw 1vw;
        font-size: 0.8rem;
        font-weight: bold;
    }
</style>

<style>
    .tab-pane {
        width: 100% !important
    }

    .tab-title-class {
        color: gray !important;  
    }

    .tab-title-class.active {
        color: black !important;  
        font-weight: bolder;
        border-bottom: 2px solid !important;
        border-radius: 0 !important;
    }

    .nav-pills .nav-link {
        background-color: white !important;
    }
</style>