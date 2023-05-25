<template>
    <!-- Plan Card Start -->
    <b-col lg="3" md="4" sm="12" class="mb-2 p-3 col-sm card-item">
        <b-card
            style="min-width: 250px;"
            :img-src="tripPlan.imgPath"
            img-alt="Image"
            img-top
            img-height="200px"
            img-width="auto"
            tag="article"
            class="mb-2">
            
            <div class="d-flex flex-column">
                <p class="font-weight-bold" style="font-size: 1.2rem">{{ tripPlan.title }}</p>
                <b-card-text style="font-size: 0.9rem"> {{ tripPlan.startDate }} </b-card-text>
                <b-button @click="movePlanDetail" class="button">플랜 보기</b-button>
            </div>
        </b-card>
    </b-col>
    <!-- Plan Card End -->
</template>
<script>
export default {
    name:'PlanListItem',
    components:{},
    props: {
        tripPlan: Object,
    },
    data() {
        return {
        };
    },
    setup() {},
    created() {},
    mounted() {},
    unmounted() {},
    methods:{
       movePlanDetail() {
            this.$router.push({ name: "PlanDetail", params: {'no': this.tripPlan.tripPlanId }}).catch(() => { });
        },
    }
}
</script>

<style scoped>
.card-item {
    max-width: 20rem;
    margin-bottom: 20px;
}
.button {
    border: none;
    background-color: #62aff6;
    color:white;
    font-weight: bold;
}
</style>