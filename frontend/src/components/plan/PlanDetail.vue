<template>
<div class="content-area d-flex">
    <!-- Sidebar Start -->
    <div class="sidebar bg-light border-right">
        <div class="sidebar-heading shadow">
            <div>
                <img class="image" src="@/assets/plane.png" />
                <span class="font-weight-bold ml-2"> {{ title }}</span>
            </div>
            <div class="d-flex  w-100 justify-content-center">
                <div class="d-flex  w-100 mt-2">
                    <b-button class="btn mr-2" @click="movePlanList">목록</b-button>
                    <b-button v-if="auth" class="btn flex-grow-1" @click="movePlanEdit(false)">수정</b-button>
                    <b-button v-else class="btn btn flex-grow-1" @click="movePlanEdit(true)">이 플랜 시작하기</b-button>
                    <b-button v-if="auth" class="btn btn-danger flex-grow-1 ml-2" @click="deleteTripPlan">삭제</b-button>
                </div>
            </div>
        </div>

        <div class="d-flex w-100 align-items-center mt-3 mb-3 sidebar-heading">
            <b-button v-if="auth" class="btn flex-grow-1" @click="showInviteUserModal">친구 초대하기</b-button>
            <b-button v-else class="btn flex-grow-1">스크랩</b-button>
        </div>
        <PlanEditItem :isEdit='false' :item="item" v-for="(item, index) in lists" :key="index + 0" @modifyEditItem="showModal"/>
    </div>  
    <!-- Sidebar End -->

    <!-- Page Content Start -->
    <div class="map-container">
        <Tmap :lists="lists" :detailPath="lists" @showAttrModal="showModal"></Tmap>
    </div>
    <!-- Page Content End -->
    <b-modal style="width: 120px" 
        ref="add-modal"
        centered 
        hide-footer
        class="h-50 d-inline-block min-vw-100">
        <template #modal-title>
            <code> {{ currentModalContent.placeName }}</code>
        </template>

        <div class="d-flex flex-column w-100">
            <div>
                <b-img v-if="currentModalContent.imgPath" :src="currentModalContent.imgPath" fluid alt="Responsive image"></b-img>
                <img v-else class="image" src="@/assets/noimg.jpg" style="width:100%; height: auto;"/>
            </div>

            <div class="mt-2">
                <p class="font-weight-bold">{{ currentModalContent.location }} </p>
                <b-textarea class="mt-2"
                    v-model="newTimeLineContent"
                    readonly>
                </b-textarea>
            </div>
        </div>
    </b-modal>

    <b-modal style="width: 120px" 
        ref="user-modal"
        centered 
        hide-footer
        class="h-50 d-inline-block min-vw-100">
        <template #modal-title>
            <p class="p-0 font-weight-bold" style="margin-bottom: 0px;">사용자 초대</p>
        </template>
        <div class="d-flex flex-column w-100">
            <b-input-group class="border rounded">
                <b-form-input 
                    v-model="userSearchParams"
                    class="p-2"
                    type="search"
                    placeholder="사용자를 검색하세요."
                    style="border: 0px;"
                ></b-form-input>
                <b-input-group-append>
                    <b-button @click="searchUser">
                        <b-icon icon="search"></b-icon>
                    </b-button>
                </b-input-group-append>
            </b-input-group>
            <div v-if="searchUsers.length == 0" class="d-flex flex-column w-100 mt-5 mb-3 justify-content-center align-items-center">
                <b-icon icon="exclamation-circle" class="mb-4" style="width: 100px; height: 100px; color:gray"></b-icon>
                <p style="color:gray; font-size: 1.4rem;">검색된 사용자가 없습니다. </p>
            </div>
            <div v-else class="mt-2">
                <UserListItem :userInfo="user" v-for="(user) in searchUsers" :key="user.userId"/>
            </div>
        </div>
    </b-modal>
</div>
</template>

<script>
import Tmap from '@/components/map/TMap.vue'
import PlanEditItem from "@/components/plan/PlanEditItem.vue"
import UserListItem from "@/components/user/UserListItem.vue"
import { getTripPlan, deleteTripPlan, hasTripPlanAuth } from "@/api/tripPlan"
import { searchUsers } from "@/api/user"
import { mapState } from "vuex";

export default {
    name:'PlanDetail',
    components: {
        PlanEditItem,
        Tmap,
        UserListItem,
    },
    data() {
        return {
            userSearchParams:"",
            selectedDay: 1,
            title: "",
            tripPlandId: -1,
            userId: -1,
            userName: "",
            lists: [],
            attractions: [],
            currentModalContent: {},
            newTimeLineContent: "",
            searchUsers: [],
            auth: false,
        };
    },
    created() {
        getTripPlan(this.$route.params.no, (response)=>{
            this.lists = response.data.destinations;
            this.title = response.data.title;
            this.userId = response.data.userId;
            this.userName = response.data.userName;
            this.tripPlandId = this.$route.params.no;
        });

        hasTripPlanAuth(this.$route.params.no, (response) => { 
            this.auth = response.data;
        })
    },
    computed: {
        ...mapState("userStore", ["userInfo"]),
    },
    mounted() {
    },
    unmounted() {},
    methods: {
        showModal(attr, isEdit) { 
            this.isEditMode = isEdit;
            if (isEdit) { 
                this.newTimeLineContent = attr.content;
            }
            this.currentModalContent = attr;
            this.$refs['add-modal'].show();
        },
        cancelModal() { 
            this.$refs['add-modal'].hide();
        },
        movePlanList() {
            this.$router.push({name: 'PlanView'});
        },
        deleteTripPlan() {
            deleteTripPlan(this.tripPlandId, ()=>{
                alert("삭제 되었습니다.")
                this.$router.push({name: 'PlanView'}).catch(()=>{})
            })
        },
        movePlanEdit(isNew) {
            this.$router.push({name: 'PlanRegist', params:{no: this.tripPlandId, isNew: isNew} });
        },
        showInviteUserModal() { 
            this.$refs['user-modal'].show();
        },
        searchUser() { 
            searchUsers(this.userSearchParams, (response) => { 
                this.searchUsers = response.data.users;

            });
        }
    },
};
</script>

<style scoped>
.image {
    width: 30px;
}
.content-area {
    height: 100vh;
}
.sidebar {
    padding-top: 10px;
    width: 320px;
    overflow-y: scroll;
    overflow-x: hidden;
}
.sidebar-heading {
    padding: 0.875rem 1.25rem;
    font-size: 1.2rem;
}
.list-item {
    height: 40px;
    line-height: 40px; /* 글자 vertical 중앙 정렬 */
    border: 1px solid #62aff6;
    margin: 10px 30px;
    border-radius: 5px;
    background-color: #62aff6;
    color: black;
    font-weight: bold;
}
.datepicker {
    margin-top: 10px;
    font-size: 1.0rem;
}
.map-container {
    float: center;
    width: calc(100% - 280px);
    overflow: hidden;
    position: relative;
    height: 100vh;
}
.image-map {
    position: relative;
}
.search-save {
    position: absolute; 
    display: flex;
    top: 0;
    left: 0;
    right: 0;
}
.search {
    float: left;
    padding: 10px;
    margin: 30px 0px 30px 10px;
}
.button {
    color:white;
    background-color: #62aff6;
    border: 1px solid #62aff6;
}
.save-button {
    margin-right: 50px;
}

.sidebar::-webkit-scrollbar {
    width: 5px;
  }
  .sidebar::-webkit-scrollbar-thumb {
    background-color: #62aff6;
    border-radius: 10px;
    background-clip: padding-box;
    border: 2px solid transparent;
  }
  .sidebar::-webkit-scrollbar-track {
    background-color: grey;
    border-radius: 10px;
    box-shadow: inset 0px 0px 5px white;
  }
</style>