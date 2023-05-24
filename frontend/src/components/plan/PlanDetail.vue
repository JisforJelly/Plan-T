<template>
    <div class="container-fluid p-0">
        <div class="header d-flex flex-column h-100 mt-5">
            <div class="board-content d-flex align-items-center">
                <div class="d-flex flex-grow-2 w-auto" style="padding: 0px 300px">
                    <b-button class="button mr-2" @click="moveListView">목록</b-button>
                    <b-button v-if="userInfo && (userInfo.userId == planDetail.userId)" class="button mr-2">계획 수정</b-button>
                    <b-button v-if="userInfo && (userInfo.userId == planDetail.userId)" class="button btn-danger mr-2" @click="deleteAndMoveListView"> 삭제</b-button>
                </div>
            </div>
        </div>

        <div class="title-section d-flex flex-column">
            <div class="title d-flex mt-3 font-weight-bold"> {{ planDetail.title }}</div>
            <div class="d-flex align-items-center mt-1">
                <div class="mr-3">
                    <b-button class="p-0" variant="none">
                        <b-icon v-if="isFill" class="mr-1 font-weight-bold font-red" icon="heart-fill" aria-hidden="true"></b-icon>
                        <b-icon v-else class="mr-1 font-weight-bold font-red" icon="heart" aria-hidden="true"></b-icon>
                    </b-button>
                    <span class="like font-weight-bold pt-1">0 </span>
                </div>
                <p class="writer m-0">작성자 :
                    <span class="name font-weight-bold pt-1">
                    {{ planDetail.userName }}
                    </span>
                </p>
            </div>
        </div>
        <!-- Content Area -->
        <div class="d-flex content-area w-100">
            <TMap :cssStyle="mapStyle" :detailPath="this.planDetail.destinations"/>
        </div>
    </div>
</template>
<script>
import { getTripPlan, deleteTripPlan } from "@/api/tripPlan"
import { mapState } from "vuex"; 
import TMap from "@/components/map/TMap.vue"

export default {
    name:'PlanDetail',
    components: {
        TMap,
    },
    data() {
        return {
            planDetail: {},
            isFill: false,
            mapStyle: {
                width: '100%',
                height: '600px'
            }
        };
    },
    computed: {
        ...mapState("userStore", ["userInfo"]),
    },
    created() {
        getTripPlan(this.$route.params.no, (resposne) => { 
            this.planDetail = resposne.data;
            console.log(this.planDetail);
        })
    },
    mounted() {},
    unmounted() {},
    methods: {
      dateClass(ymd, date) {
        const day = date.getDate()
        return (day >= 10 && day <= 15 ? 'table-primary' : '') ||
        (day >= 20 && day <= 25 ? 'table-secondary' : '') ||
        (day >= 1 && day <= 4 ? 'table-info' : '')
      },
      moveListView(){
          this.$router.push({name:"PlanView"});
      },
      deleteAndMoveListView() {
          deleteTripPlan(this.$route.params.no, ()=>{
              alert("삭제 되었습니다.");
              this.$router.push({name:"PlanView"});
          })
      }
    }
  }
</script>

<style scoped>
.title-area {
    padding: 40px;
}
.title {
    font-size: 1.8rem;
}
.content-area {
    padding: 0px 300px 2000px 300px;
}
.calendar {
    width: 30%;
}

.title-section {
    padding: 20px 300px 20px 300px;
    min-width: 1100px !important;
}

</style>