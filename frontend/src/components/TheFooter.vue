<template>
    <footer class="d-flex-column fixed-bottom w-100">
        <div class="d-flex w-100 align-items-center justify-content-between footer-layer-1">
            <div class="font-weight-bold">© 2023 SSAFY, 김한성 이정민</div>
            <div class="font-weight-bold">9기 서울 12반</div>
        </div>
        <div class="d-flex w-100 align-items-center justify-content-between footer-layer-2">
            <div>
                본 사이트의 콘텐츠는 저작권법의 보호를 받는 바 무단 전재, 복사, 배포 등을 금합니다.
            </div>
        </div>
    </footer>
</template>

<script>
export default {
    name: 'TheFooter',
    components: {},
    data() {
        return {
        };
    },
    setup() { },
    created() { },
    mounted() { },
    unmounted() { },
    methods: {}
}
</script>

<style scoped>
.footer-layer-1 {
    background-color: white;
    height: 60px;
    padding: 0px 300px;
    font-size: 1rem;
}
.footer-layer-2 {
    background-color: black;
    height: 67px;
    padding: 0px 300px;
    font-size: 0.5rem;
    color: white;
}
</style>