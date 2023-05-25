<template>
    <div class="container-fluid p-0">
        <div class="d-flex flex-column w-100">
            <b-tabs pills class="d-flex flex-column px-300 mt-5" content-class="d-flex align-items-center mt-5" lazy>
                <!-- ui test -->
                <b-tab title="내프로필" active :title-link-class="'tab-title-class'" style="padding: 0px 0px 100px 0px">
                    <div>
                        <h4 class="font-weight-bold text-left">프로필 수정</h4>
                        <p class="text-left text-secondary mt-2" style="font-size: 0.8rem;">대표 프로필과 별명을 수정 하실 수 있습니다.</p>
                    </div>

                    <div class="d-flex mt-4">
                        <table class="w-100 text-left">
                            <tr>
                                <th>프로필 사진</th>
                                <td>
                                    <div class="d-flex flex-column"> 
                                        <b-avatar :src="prevImgPath" size="6rem"></b-avatar>
                                        <div class="d-flex flex-row mt-3">
                                            <button class="profile-btn ">
                                                <label for="imgUpload" class="m-0 padding-btn">사진 변경</label>
                                            </button>
                                            <input type="file" id="imgUpload" accept="image/*"  style="display:none" @change="uploadNewProfile">
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th>별명</th>
                                <td>
                                    <div class="p-2 w-50">
                                        <b-input v-model="updateName"></b-input>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>

                    <div class="d-flex justify-content-center bottom-div mt-3">
                        <button class="mr-2 profile-btn padding-btn tab-title-class" @click="updateUserInfo">적용</button>
                        <button class="mr-2 profile-btn padding-btn" @click="cancelProfileEdit">취소</button>
                    </div>
                </b-tab>
                <b-tab title="좋아요 (Hot Place)" :title-link-class="'tab-title-class'" style="padding: 0px 0px 500px 0px">
                    <HotPlaceList :isNotMyPage='false'/>
                </b-tab>

                <b-tab title="나의 여행 계획" :title-link-class="'tab-title-class'" style="padding: 0px 0px 500px 0px">
                    <PlanView :isNotMyPage='false'/>
                </b-tab>
            </b-tabs>
        </div>
    </div>
</template>
<script>
import { mapState, mapMutations } from 'vuex'
import HotPlaceList from "@/components/gallery/GalleryList.vue"
import  PlanView from "@/components/plan/PlanView.vue"
import { updateUserProfile } from "@/api/user"

export default {
    name:'UserMyPage',
    components:{
        HotPlaceList,
        PlanView,
    },
    data() {
        return {
            prevImgPath : "",
            uploadImg: null,
            updateName: "",
        };
    },
    setup() {},
    created() {
        this.prevImgPath = (this.userInfo.profileImgPath == undefined) ? "" : this.rootPath+`/image/` + this.userInfo.profileImgPath;
        this.updateName = this.userInfo.name;
    },
    mounted() {},
    unmounted() { },
    computed: {
        ...mapState("userStore", ["userInfo"]),
        rootPath: function () { 
            return process.env.VUE_APP_API;
        },
    },
    methods: {
        ...mapMutations("userStore", {'changeInfo' : "CHNAGE_USER_IMAGE"}),
        cancelProfileEdit() { 
            this.$router.go(0);
        },
        uploadNewProfile(e) { 
            this.uploadImg = e.target.files[0];
            this.prevImgPath = URL.createObjectURL(e.target.files[0]);
        },
        updateUserInfo() { 
            const formData = new FormData();
            formData.append("name", this.updateName)
            formData.append("image", this.uploadImg);            
            updateUserProfile(formData, (response) => { 
                alert("변경되었습니다.")
                this.changeInfo({
                    profileImgPath: response.data,
                    name : this.updateName
                });
            }, () => { 
                alert("서버와 통신이 원할하지 않습니다. 관리자에게 문의하세요.");
            });
        }
    }
}
</script>

<style scoped>
    th {
        width: 200px;
        background: #dcdcdc;
        padding: 2vw;
        font-size: 0.8rem;
        vertical-align: top;
        border: 1px solid gray;
        border-left: none !important;
    }

    td {
        widows: '100vw-200px';
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
        font-size: 0.8rem;
        font-weight: bold;
    }
    .padding-btn {
        padding: 0.6vw 1vw 0.6vw 1vw;
    }

    .bottom-div {
        margin-bottom: 350px;
    }

    label {
        cursor: pointer;
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