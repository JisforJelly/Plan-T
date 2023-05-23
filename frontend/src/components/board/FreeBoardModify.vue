<template>
    <div class="container-fluid p-0">
        <!-- Header -->
        <div class="header d-flex flex-column h-100">
            <div>
                <h2 class="font-weight-bold mb-0 w-auto"> QNA 조회 </h2>
            </div>
            <div class="board-content d-flex align-items-center justify-content-between" >
                <div class="d-flex flex-grow-2 w-auto">
                    <b-button class="button mr-2" @click="movePostList">목록</b-button>
                    <b-button class="button mr-2" @click="modifyPost">게시글 수정</b-button>
                </div>
            </div>
        </div>

        <!-- Board Section -->
        <div class="board-section">
            <b-form-input size="lg" v-model="post.title" class="mb-2"></b-form-input>
            <div class="writer d-flex align-items-center mt-1">                
                <p class="m-0" >작성자 :
                    <span class="name font-weight-bold pt-1">
                            {{ post.name }}
                    </span>
                </p>
                <p class="time my-0 ml-3 font-italic">
                    {{ post.createdAt }}
                </p>
            </div>
            <VueEditor v-model="post.content" :editorToolbar="customToolbar"></VueEditor>
        </div>
    </div>
</template>
<script>
import { getPostByPostId, modifyPost } from "@/api/post"
import { VueEditor } from "vue2-editor";

export default {
    name:'FreeBoardModify',
    components: {
        VueEditor,
    },
    data() {
        return {
            postId : -1,
            readOnly: true,
            post: {
                title: "",
                content: "<p>dsfsdfsdfs</p>",
            },
            customToolbar: [
                ["bold", "italic", "underline"],
                [{ list: "ordered" }, { list: "bullet" }],
                ["code-block"]
            ]
        };
    },
    setup() {},
    created() {
        this.postId = this.$route.params.no;
        this.renderPost();
    },
    mounted() {},
    unmounted() {},
    methods: {
        renderPost() {
            getPostByPostId(this.postId, (response) => {
                this.post = response.data;
            });
        },
        movePostList() { 
            this.$router.push({ name: "FreeBoard" }).catch(() => { });
        },
        modifyPost() {
            const dto = {
                postId: this.postId,
                title: this.post.title,
                content: this.post.content,
            };

            console.log(dto);

            modifyPost(dto, () => { 
                this.$router.push({ name: "FreeBoardDetail", params: { 'no': this.postId } }).catch(() => { });
            });
            this.$router.push({ name: "FreeBoard" }).catch(() => { });
        },
    }
}
</script>

<style scoped>
.header {
    padding: 20px 300px;
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
    height: 2000px;
    min-width: 1100px !important;
}
.writer {
    font-size: 1.2rem;
}
.name {
    text-decoration: underline;
}
.time {
    font-size: 1.1rem;
}
</style>