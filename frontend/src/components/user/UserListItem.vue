<template>
    <div class="d-flex w-100 my-3 border rounded p-2">
        <div class="profile-img">
            <b-avatar class="profile-img" :src="`${rootPath}/image/`+userInfo.imgPath"></b-avatar>
        </div>
        <div class="d-flex align-items-center">
            <p align="left" class="ml-2 mt-0 mb-0" style="font-size: 1.2rem; font-weight: bold;"> {{ userInfo.email }}</p>
        </div>
        <div class="d-flex flex-grow-1 justify-content-end d-flex align-items-center">
            <b-button class="btn-sm" style="height: 40px" @click="invite">초대하기</b-button>
        </div>
    </div>
</template>
<script>

import { inviteTripPlan } from "@/api/tripPlan"

export default {
    name:'UserListItem',
    components: {},
    props: {
        userInfo: Object,
    },
    data() {
        return {
            
        };
    },
    created() {
    },
    computed: {
        rootPath: function () { 
            return process.env.VUE_APP_API;
        }
    },
    mounted() {},
    unmounted() {},
    methods: {
        invite() { 
            console.log(this.$route.params.no);
            inviteTripPlan(this.$route.params.no, this.userInfo.userId, () => { 
                alert("초대되었습니다.");
            });
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
</style>