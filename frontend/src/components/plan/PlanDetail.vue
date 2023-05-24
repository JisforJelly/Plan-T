<template>
<div class="content-area d-flex">
    <!-- Sidebar Start -->
    <div class="sidebar bg-light border-right">
        <div class="sidebar-heading shadow">
            <div>
                <img class="image" src="@/assets/plane.png" />
                <span class="font-weight-bold ml-2"> {{ title }}</span>
            </div>
            <div class="d-flex w-100 justify-content-center">
                <div class="d-flex mt-2">
                    <b-button class="btn mr-2" @click="movePlanList">목록</b-button>
                    <b-button v-if="userInfo && userInfo.userId == userId" class="btn mr-2" @click="movePlanEdit(false)">수정</b-button>
                    <b-button v-else class="btn btn-success mr-2" @click="movePlanEdit(true)">이 플랜으로 시작하기</b-button>
                    <b-button v-if="userInfo && userInfo.userId == userId" class="btn btn-danger" @click="deleteTripPlan">삭제</b-button>
                </div>
            </div>
        </div>
        <PlanEditItem :isEdit='false' :item="item" v-for="(item, index) in lists" :key="index + 0" @deleteEditItem="deleteEditDto" @modifyEditItem="showModal"/>
    </div>  
    <!-- Sidebar End -->

    <!-- Page Content Start -->
    <div class="map-container">
        <Tmap :lists="lists" :detailPath="lists" @showAttrModal="showModal"></Tmap>
        <div class="search-save">

        </div>
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
</div>
</template>

<script>
import Tmap from '@/components/map/TMap.vue'
import PlanEditItem from "@/components/plan/PlanEditItem.vue"
import { getTripPlan, deleteTripPlan } from "@/api/tripPlan"
import { mapState } from "vuex";

export default {
    name:'PlanDetail',
    components: {
        PlanEditItem,
        Tmap,
    },
    data() {
        return {
            selectedDay: 1,
            title: "",
            tripPlandId: -1,
            userId: -1,
            userName: "",
            lists: [],
            attractions: [],
            currentModalContent: {},
            newTimeLineContent: "",
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
</style>