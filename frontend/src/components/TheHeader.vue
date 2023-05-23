<template>
    <div class="d-flex align-items-center mb-2 headerStyle" ref="ObeservingPoint">
        <span p class="ssafy">SSAFY</span>
    </div>
</template>
<script>
export default {
    name:'TheHeader',
}
</script>

<style scoped>
.headerStyle {
    background-color: black; 
    height: 80px; 
    padding: 0px 300px; 
    font-size: 18px;
}
.ssafy {
    color: #737373;
    padding-top: 22px;
    padding-bottom: 20px;
    font-weight: bold;
}
</style>