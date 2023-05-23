<template>
    <!-- Plan Card Start -->
    <b-col lg="3" md="4" sm="12" class="mb-2 p-3 col-sm card-item">
        <b-card
            :title="tripplan.title"
            :img-src="tripplan.img"
            img-alt="Image"
            img-top
            tag="article"
            class="mb-2 b-card-item"
        >
            <b-card-text>
                2023년 6월 4일 출발
            </b-card-text>
            <b-button @click="movePlanDetail" class="button">플랜 보기</b-button>
        </b-card>
    </b-col>
    <!-- Plan Card End -->
</template>
<script>
export default {
    name:'PlanListItem',
    components:{},
    props: {
        tripplan: {}
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
            this.$router.push({ name: "PlanDetail", params: {'no': this.tripPlanId }}).catch(() => { });
        },
    }
}
</script>

<style scoped>
.card-item {
    max-width: 20rem;
    margin-bottom: 20px;
}
.b-card-item {
    height: 350px;
}
.button {
    border: none;
    background-color: lightblue;
}
</style>