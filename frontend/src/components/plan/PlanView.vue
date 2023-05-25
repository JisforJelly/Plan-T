<template>
    <div class="container-fluid p-0">
        <!-- main content-->
        <div class="d-flex" :class="[isNotMyPage ? 'main-content' : '']">
            <b-row class="w-100">
                <PlanListItem :tripPlan="tripPlan" v-for="tripPlan in tripPlans" :key="tripPlan.tripPlanId"></PlanListItem>
            </b-row>
        </div>
    </div>
</template>

<script>
import PlanListItem from "@/components/plan/PlanListItem.vue";
import { getTripPlans, getUserPlans } from "@/api/tripPlan"

export default {
    name: 'PlanView',
    props: {
        isNotMyPage: {
            default: true,
            type: Boolean,
        }
    },
    components: {
        PlanListItem,
    },
    data() {
        return {
              tripPlans: [ ],
        };
    },
    created() {
        if (this.isNotMyPage) {
            getTripPlans((response) => {
                this.tripPlans = response.data.tripPlans;
            })
        } else { 
            getUserPlans((response) => { 
                this.tripPlans = response.data.tripPlans;
            })
        }
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
</style>