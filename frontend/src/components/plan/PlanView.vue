<template>
    <div class="container-fluid p-0">
        <!-- main content-->
        <div class="main-content d-flex">
            <b-row class="w-100">
                <PlanListItem :tripPlan="tripPlan" v-for="tripPlan in tripPlans" :key="tripPlan.tripPlanId"></PlanListItem>
            </b-row>
        </div>
    </div>
</template>

<script>
import PlanListItem from "@/components/plan/PlanListItem.vue";
import { getTripPlans } from "@/api/tripPlan"

export default {
    name:'PlanView',
    components: {
        PlanListItem,
    },
    data() {
        return {
              tripPlans: [ ],
        };
    },
    created() {
        getTripPlans((response) => { 
            this.tripPlans = response.data.tripPlans;
        })
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