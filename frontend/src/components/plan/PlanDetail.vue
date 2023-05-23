<template>
    <div class="container-fluid p-0">
        <div class="title-area" >
            <h3>제목...</h3>
        </div>
        <div class="content-area d-flex">
            <b-row>
                <b-col>
                    <!-- 여기에 지도가 들어가면 되는데... -->
                    <div class="map"></div>
                </b-col>
                <b-col md="auto">
                    <b-calendar block  class="calendar"
                    v-model="value" :date-info-fn="dateClass"
                    label-no-date-selected="출발: ^^월 **일"
                    ></b-calendar>
                </b-col>
            </b-row>
        </div>
    </div>
</template>
<script>

export default {
    name:'PlanDetail',
    components:{
    },
    data() {
        return {
        };
    },
    setup() {},
    created() {},
    mounted() {},
    unmounted() {},
    methods: {
      // 특정 날짜에 대해서만 표시되도록...
      dateClass(ymd, date) {
        const day = date.getDate()
        return (day >= 10 && day <= 15 ? 'table-primary' : '') ||
        (day >= 20 && day <= 25 ? 'table-secondary' : '') ||
        (day >= 1 && day <= 4 ? 'table-info' : '')
      }
    }
  }
</script>

<style scoped>
.title-area {
    padding: 40px;
}
.content-area {
    width: 80%;
    margin: auto;
    display: flex;
    justify-content: center;
}
.calendar {
    width: 400px;
    height: 400px;
}
.map {
    width: 400px;
    height: 400px;
    background-color: lightblue;
}
</style>