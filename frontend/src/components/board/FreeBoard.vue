<template>
    <div class="container-fluid p-0">
        <!-- Header -->
        <div class="header d-flex flex-column h-100 sticky-top">
            <div>
                <h2 class="font-weight-bold mb-0 w-auto"> 게시글 목록 </h2>
            </div>
            <div class="board-content d-flex align-items-center justify-content-between">
                <div class="d-flex flex-grow-2 w-auto">
                    <b-button class="button" @click="movePostRegist">게시글 등록</b-button>
                </div>
                <div class="d-flex flex-grow-1 align-items-center justify-content-end">
                    <b-form-select class="w-auto mr-1" v-model="selected" :options="options"></b-form-select>
                    <b-form-input class="w-auto mr-1" placeholder="검색어를 입력해주세요." v-model="searchParams"></b-form-input>
                    <b-button class="button w-auto" @click="searchRender">검색</b-button>
                </div>
            </div>
        </div>

        <!-- Board Section -->
        <div class="board-section">
            <b-table hover :items="items" @row-clicked="onRowClicked" />
        </div>

        <!-- Page nation -->
        <div class="page-nation d-flex justify-content-center">
            <b-pagination-nav class="mt-2 w-auto" size="lg" @page-click="pageClick" :current-page="currentPage"
                :number-of-pages="totalPage" base-url="#"></b-pagination-nav>
        </div>
    </div>
</template>
<script>
import { mapState } from "vuex";
import { getPosts, searchPosts } from "@/api/post"

export default {
    name: 'FreeBoard',
    components: {},
    data() {
        return {
            currentPage: 1,
            pagingSize: 10,
            selected: 'title',
            options: [
                { value: 'title', text: '제목' },
                { value: 'content', text: '내용' },
            ],
            searchParams: "",
            totalPage: 0,
            items: [],
        };
    },
    setup() { },
    created() {
        this.renderPosts();
    },
    mounted() { },
    unmounted() { },
    computed: {
        ...mapState("userStore", ["userInfo", "isLogin"]),
    },
    methods: {
        movePostRegist() {
            if (!this.isLogin) {
                alert("로그인이 필요한 서비스입니다.");
                return;
            }
            this.$router.push({ name: "FreeBoardRegist" }).catch(() => { });
        },
        onRowClicked(item) {
            this.$router.push({ name: "FreeBoardDetail", params: { 'no': item.No } }).catch(() => { });
        },
        pageClick(event, page) {
            event.preventDefault();
            this.currentPage = page;
            this.renderPosts();
        },
        renderPosts() {
            getPosts(this.currentPage - 1, this.pagingSize, (response) => {
                this.items = response.data.posts.map(post => {
                    return {
                        'No': post.postId,
                        '제목': post.title,
                        '작성자': post.name,
                        '작성일': post.createdAt,
                        '조회수': post.viewCnt
                    };
                });
                this.currentPage = response.data.currentPage;
                this.totalPage = response.data.totalPages;
            });
        },
        searchRender() { 
            if (!this.searchParams) { 
                alert("검색어를 입력해주세요.");
                return;
            }

            searchPosts(this.selected, this.searchParams, (response) => {
                this.items = response.data.posts.map(post => {
                    return {
                        'No': post.postId,
                        '제목': post.title,
                        '작성자': post.name,
                        '작성일': post.createdAt,
                        '조회수': post.viewCnt
                    };
                });
                this.currentPage = response.data.currentPage;
                this.totalPage = response.data.totalPages;
            });
        }
    }
}
</script>

<style scoped>
.header {
    padding: 20px 300px;
    margin-top: 60px;
    min-width: 1100px !important;
}
.board-content {
    margin-top: 60px; 
}
.button {
    white-space: nowrap;
}
.board-section {
    padding: 20px 300px;
    margin-top:20px;
    min-width: 1100px !important;
}
.page-nation {
    height: 100px;
    margin-bottom: 220px;
}
</style>
