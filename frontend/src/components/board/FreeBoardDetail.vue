<template>
    <div class="container-fluid p-0">
        <!-- Header -->
        <div class="d-flex flex-column h-100"
            style="padding: 20px 300px; min-width: 1100px !important;">
            <div>
                <h2 class="font-weight-bold mb-0 w-auto"> QNA 조회 </h2>
            </div>
            <div class="d-flex align-items-center justify-content-between" style="margin-top: 60px;">
                <div class="d-flex flex-grow-2 w-auto">
                    <b-button class="mr-2" style="white-space:nowrap;" @click="movePostList">목록</b-button>
                    <b-button class="mr-2" style="white-space:nowrap;" @click="modifyPost">게시글 수정</b-button>
                    <b-button class="btn-danger" style="white-space:nowrap;" @click="deletePost">게시글 삭제</b-button>
                </div>
            </div>
        </div>

        <!-- Board Section -->
        <div style="padding: 20px 300px; height: 2000px; min-width: 1100px !important;">
            <b-form-input size="lg" v-model="post.title" class="mb-2" readonly></b-form-input>
            <div class="d-flex align-items-center mt-1">                
                <p class="m-0"  style="font-size: 1.2rem;">작성자 :
                    <span class="font-weight-bold pt-1" style="text-decoration: underline;">
                            {{ post.name }}
                    </span>
                </p>
                <p class="my-0 ml-3 font-italic" style="font-size: 1.1rem;">
                    {{ post.createdAt }}
                </p>
            </div>
            <VueEditor class="mt-2" v-model="post.content" :editorToolbar="customToolbar" :disabled="readOnly"></VueEditor>

            <!-- 덧글 표시 영역 -->
            <TheComment :comment="comment" v-for="comment in comments" :key="comment.commentId" v-on:deleteComment="deleteComment"/>
            
            <!-- 덧글 등록 form-->
            <div class="mt-1 d-flex flex-column">
                <b-form-textarea v-model="commentInsertForm" placeholder="Enter something..."></b-form-textarea>
                <div class="d-flex mt-1  flex-row-reverse">
                    <b-button style="white-space:nowrap;"  @click="pushComment">덧글 등록</b-button>
                </div>
            </div>
        </div>
    </div>

    
</template>
<script>
import { getPostByPostId, deletePost } from "@/api/post"
import { getComments, insertComment, deleteComment } from "@/api/comment"
import TheComment from "@/components/comment/TheComment.vue"
import { VueEditor } from "vue2-editor";

export default {
    name:'FreeBoardDetail',
    components: {
        VueEditor,
        TheComment,
    },
    data() {
        return {
            postId : -1,
            readOnly: true,
            post: {
                title: "",
                content: "<p>dsfsdfsdfs</p>",
            },
            comments: [{
                createdAt: "1124.12.31",
                content :"덧글 내용1111",
            }],
            customToolbar: [
                ["bold", "italic", "underline"],
                [{ list: "ordered" }, { list: "bullet" }],
                ["code-block"]
            ],
            commentInsertForm : "",
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
        async renderPost() {
            await getPostByPostId(this.postId, (response) => {
                this.post = response.data;
            });

            await getComments(this.postId, (response)=>{
                this.comments = response.data;
            });
        },
        movePostList() { 
            this.$router.push({ name: "FreeBoard" }).catch(() => { });
        },
        modifyPost() {
            this.$router.push({ name: "FreeBoardModify", params: { 'no': this.postId } }).catch(() => { });
        },
        deletePost() { 
            deletePost(this.postId, () => {
                this.$router.push({ name: "FreeBoard" }).catch(() => { });
            });
        },
        pushComment() {
            if(!this.commentInsertForm) {
                alert("입력된 값이 없습니다.")
                return;
            }
            insertComment({
                postId: this.postId,
                content: this.commentInsertForm,
            }, ()=>{
                this.renderPost();
            });
        },
        deleteComment(commentId) {
            deleteComment(commentId, ()=>{
                this.renderPost();
            })
        }
    }
}
</script>

<style>

</style>