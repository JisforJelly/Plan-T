<template>
<div class="content-area d-flex">
    <!-- Sidebar Start -->
    <div class="sidebar bg-light border-right">
        <div class="sidebar-heading">
            <img class="image" src="@/assets/plane.png" />
            나만의 플랜 만들기
            <b-form-datepicker 
                v-model="value"
                class="datepicker mb-2"
                placeholder="출발일 선택"
                :date-format-options="{ year: 'numeric', month: 'short', day: '2-digit'}"
            ></b-form-datepicker>
        </div>
        <!-- Drag and Drop Start -->
        <draggable>
            <transition-group>
                <div class="list-item" v-for="(item, index) in lists" :key="index + 0">
                    {{ item.content }}
                </div>
            </transition-group>
        </draggable>
        <!-- Drag and Drop End -->
    </div>  
    <!-- Sidebar End -->

    <!-- Page Content Start -->
    <div class="map-container">
        <!-- 지도... -->
        <div class="image-map">
            <img src="https://wallpaperaccess.com/full/185289.jpg" style="height: 925px;">
        </div>
        <div class="search-save">
            <b-input-group class="search">
                <b-form-input 
                type="search"
                placeholder="지역을 검색하세요"
                ></b-form-input>
                <b-input-group-append>
                    <b-button class="button">
                        <b-icon icon="search"></b-icon>
                    </b-button>
                </b-input-group-append>
            </b-input-group>
            <b-button class="button save-button">저장하기</b-button>
        </div>
    </div>
    <!-- Page Content End -->
</div>
</template>

<script>
import draggable from 'vuedraggable'

export default {
    name:'PlanRegist',
    components: {
        draggable,
    },
    data() {
        return {
            lists: [
                { content: 1 },
                { content: 2 },
                { content: 3 },
                { content: 4 },
                { content: 5 },
                { content: 6 },
                { content: 7 }
            ]
        };
    },
    setup() {},
    created() {
    },
    mounted() {
    },
    unmounted() {},
    methods: {
    },
};
</script>

<style scoped>
.image {
    width: 30px;
}
.content-area {
    height: 45rem;
}
.sidebar {
    padding-top: 10px;
    width: 280px;
}
.sidebar-heading {
    padding: 0.875rem 1.25rem;
    font-size: 1.2rem;
}
.list-item {
    height: 40px;
    line-height: 40px; /* 글자 vertical 중앙 정렬 */
    border: 1px solid lightblue;
    margin: 10px 30px;
    border-radius: 5px;
    background-color: lightblue;
    color: white;
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
    height: 45rem;
}
.image-map {
    position: relative;
}
.search-save {
    position: absolute; 
    display: flex;
    justify-content: space-between;
    top: 0;
    left: 0;
    right: 0;
}
.search {
    float: left;
    width: 300px;
    padding: 40px;
}
.button {
    background-color: #adb0b4;
    border: 1px solid #ced4da;
}
.save-button {
    float: right;
    margin: 40px;
}
</style>