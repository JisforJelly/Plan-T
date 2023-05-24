<template>
    <div class="d-flex w-100 ml-2">
        <div v-if="isEdit">
            <div class="vertical-line"></div>
            <div class="day-selector" style="font-size: 0.8rem; font-weight: bold;" @click="editItems">
                <div>{{ item.day }}박 <b-icon icon="chevron-down" scale="0.5"></b-icon> </div>
            </div>
            <div class="vertical-line"></div>
        </div>
        <div v-else class="profile-img" @click="editItems">
            <b-avatar class="mt-2 profile-img" :src="item.imgPath"></b-avatar>
        </div>
        <div v-if="isEdit" class="d-flex ml-2 mt-3 mr-2" style="font-size: 0.8rem; font-weight: bold;">
            <p class="mr-2 one-line-text">{{ item.placeName }}</p> 
            <b-icon icon="x-circle-fill" scale="1" style="color:gray; margin-top: 3px; cursor:pointer;" @click="deleteEditItem"></b-icon>
        </div>
        <div v-else class="d-flex flex-column ml-2 mr-2" style="font-size: 0.8rem; font-weight: bold;">
            <p align="left" class="mt-2 mb-0 one-line-text">{{ item.placeName }}</p> 
            <p>{{item.startDate}} ~ {{item.endDate}}</p> 
        </div>
    </div>
</template>
<script>
export default {
    name:'PlanEditItem',
    components: {},
    props: {
        item: Object,
        isEdit: {
            type:Boolean,
            default: ()=>{
                return true;
            }
        }
    },
    data() {
        return {
            EditDataForm: {
                tripPlanTimeLineId: -1,
                tripPlanId: -1,
                order: -1,
                placeName: '서울시 노원구 상계동 동일로1',
                latitude: -1,
                longitude: -1,
                location: "",
                content: "",
                imgPath: "",
                startDate: "",
                endDate: "",
            },
            day: 1,
        };
    },
    created() {
    },
    mounted() {},
    unmounted() {},
    methods: {
        deleteEditItem() { 
            this.$emit("deleteEditItem", this.item);
        },
        editItems() { 
            this.$emit("modifyEditItem", this.item, true);
        }
    }
}
</script>

<style scoped>
.day-selector {
    float:left;
    width:53px;
    height:53px;
    padding-left:7px;
    background:#fff;
    padding-top:13px;
    border-radius:100px;
    border:3px solid #62aff6;
    cursor:pointer;
}
.vertical-line {
    width:29px;
    border-right:3px solid #62aff6;
    height:7px;
}

.one-line-text {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}

.profile-img {
    cursor: pointer;
}
</style>