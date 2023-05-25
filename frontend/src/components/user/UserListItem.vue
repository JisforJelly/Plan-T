<template>
    <div class="d-flex w-100 my-3 border rounded p-2">
        <div class="profile-img">
            <b-avatar class="profile-img" :src="'http://localhost:8080/image/'+userInfo.imgPath"></b-avatar>
        </div>
        <div class="d-flex align-items-center">
            <p align="left" class="ml-2 mt-0 mb-0" style="font-size: 1.2rem; font-weight: bold;"> {{ userInfo.email }}</p>
        </div>
        <div class="d-flex flex-grow-1 justify-content-end d-flex align-items-center">
            <b-button class="btn-sm" style="height: 40px">초대하기</b-button>
        </div>
    </div>
</template>
<script>
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
        console.log(this.userInfo);
    },
    mounted() {},
    unmounted() {},
    methods: {
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