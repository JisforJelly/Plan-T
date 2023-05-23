<template>
    <div class="container-fluid p-0">
        <!-- Header -->
        <div class="header d-flex flex-column h-100">
            <div>
                <h2 class="font-weight-bold mb-0 w-auto"> QNA 등록 </h2>
            </div>
            <div class="board-content d-flex align-items-center justify-content-between">
                <div class="d-flex flex-grow-2 w-auto">
                    <b-button class="button mr-2" @click="movePostList">목록</b-button>
                    <b-button class="button" @click="insertPost">게시글 등록</b-button>
                </div>
            </div>
        </div>

        <!-- Board Section -->
        <div class="board-section">
            <b-form-input size="lg" placeholder="제목" v-model="postForm.title" class="mb-2"></b-form-input>
            <VueEditor v-model="postForm.content" :editorToolbar="customToolbar"></VueEditor>
        </div>
    </div>
</template>
<script>
import { savePost } from "@/api/post"
import { mapState } from "vuex";
import { VueEditor } from "vue2-editor";

export default {
    name: 'FreeBoardRegist',
    components: {
        VueEditor,
    },
    data() {
        return {
            postForm: {
                title: "",
                content: "",
            },
            customToolbar: [
                ["bold", "italic", "underline"],
                [{ list: "ordered" }, { list: "bullet" }],
                ["code-block"]
            ]
        };
    },
    computed: {
        ...mapState("userStore", ["userInfo", "isLogin"]),
    },
    setup() { },
    created() { },
    mounted() { },
    unmounted() { },
    methods: {
        movePostList() { 
            this.$router.push({ name: "FreeBoard" }).catch(() => { });
        },
        insertPost() { 
            savePost(this.postForm, this.movePostList);
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
.board-section {
    padding: 20px 300px;
    height: 2000px;
    min-width: 1100px !important;
}
.board-content {
    margin-top: 60px;
}
.button {
    white-space: nowrap;
}
</style>