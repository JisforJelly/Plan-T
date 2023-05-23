<template>
    <div class="container-fluid p-0">
        <!-- main content-->
        <div class="regist-button">
            <b-button class="button" @click="movePlanRegist">나만의 플랜 만들기</b-button>
        </div>
        <div class="main-content d-flex">
            <b-row class="w-100">
                <PlanListItem :tripplan="tripplan" v-for="tripplan in tripplans" :key="tripplan.tripPlanId"></PlanListItem>
            </b-row>
        </div>
    </div>
</template>

<script>
import PlanListItem from "@/components/plan/PlanListItem.vue";

export default {
    name:'PlanView',
    components: {
        PlanListItem,
    },
    data() {
        return {
              tripplans: [ 
                {
                    title: "김싸피의 여행 계획✨",
                    tripPlanId: 1,
                    img: "https://ofs-cdn.italki.com/u/8903161/galaxy/post/c31n3okpfd435t8a6nj0.jpg",
                },
                {
                    title: "6월에 만난 제주😍",
                    tripPlanId: 2,
                    img: "https://assets.blog.engoo.com/wp-content/uploads/sites/2/2022/03/06222737/travel-back-image-%EB%B3%B5%EC%82%AC.jpg.webp",
                },
                {
                    title: "정싸피의 6월 기차여행🚝",
                    tripPlanId: 3,
                },
                {
                    title: "111",
                    tripPlanId: 4,
                },
                {
                    title: "222",
                    tripPlanId: 5,
                },
                {
                    title: "333",
                    tripPlanId: 6,
                },
                {
                    title: "444",
                    tripPlanId: 7,
                },
                {
                    title: "555",
                    tripPlanId: 8,
                },
                {
                    title: "666",
                    tripPlanId: 9,
                },
                {
                    title: "777",
                    tripPlanId: 10,
                },
                {
                    title: "888",
                    tripPlanId: 11,
                },
                {
                    title: "999",
                    tripPlanId: 12,
                },
                {
                    title: "계획을 세워보자📝",
                    tripPlanId: 13,
                },
                {
                    title: "여행가고싶다!!!🛫",
                    tripPlanId: 14,
                },
                ],
              tripPlanIds: {},
        };
    },
    created() {
    },
    methods: {
        movePlanRegist() {
            this.$router.push({ name: "PlanRegist" }).catch(() => { });
        },
    }
}
</script>

<style scoped>
.main-content {
    padding: 0px 300px;
    margin-top:80px;
    padding-bottom: 200px;
}
.regist-button {
    margin-top: 80px;
}
</style>